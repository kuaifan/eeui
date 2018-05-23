package io.rong.imlib.library.fakeserver;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.rong.imlib.model.UserInfo;
import io.rong.imlib.weiui.weiui_rongim;
import vip.kuaifan.weiui.extend.module.weiuiCommon;
import vip.kuaifan.weiui.extend.module.weiuiJson;
import vip.kuaifan.weiui.ui.weiui;

public class FakeServer {

    private static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    public static void getToken(UserInfo user, final HttpUtil.OnResponse callback) {
        final String post = "userId=" + user.getUserId() + "&name=" + user.getName() + "&portraitUri=" + user.getPortraitUri();
        final String md5Id = md5(weiui_rongim.appKey + "@" + weiui_rongim.appSecret + "@" + post);
        String token = weiuiCommon.getCachesString(weiui.getApplication(), "rongimToken", md5Id, "");
        if (!"".equals(token)) {
            if (callback != null) {
                callback.onResponse(200, token);
            }
            return;
        }
        String url = "http://api.cn.ronghub.com/user/getToken.json";
        HttpUtil.Header header = HttpUtil.getRcHeader(weiui_rongim.appKey, weiui_rongim.appSecret);
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.post(url, header, post, new HttpUtil.OnResponse() {
            @Override
            public void onResponse(int code, String body) {
                String token = body;
                if (code == 200) {
                    token = weiuiJson.getString(token, "token");
                    if ("".equals(token)) {
                        code = -1;
                    } else {
                        weiuiCommon.setCachesString(weiui.getApplication(), "rongimToken", md5Id, token, 3600);
                    }
                }
                callback.onResponse(code, token);
            }
        });
    }
}
