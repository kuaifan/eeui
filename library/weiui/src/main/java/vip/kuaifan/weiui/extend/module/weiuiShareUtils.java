package vip.kuaifan.weiui.extend.module;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.taobao.weex.bridge.JSCallback;

import java.io.FileNotFoundException;
import java.util.Map;

import vip.kuaifan.weiui.extend.view.loading.LoadingDialog;

public class weiuiShareUtils {

    /**
     * 分享文字
     *
     * @param context
     */
    public static void shareText(Context context, String text) {
        if (text == null || "".equals(text)) {
            return;
        }
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(textIntent, "分享"));
    }

    /**
     * 分享图片
     *
     * @param context
     */
    public static void shareImage(Context context, String imgUrl) {
        if (imgUrl == null || "".equals(imgUrl)) {
            return;
        }
        LoadingDialog.init(context, null, null);
        weiuiCommon.saveImage(context, imgUrl, new JSCallback() {
            @Override
            public void invoke(Object data) {
                LoadingDialog.close(null);
                //
                Map<String, Object> res = weiuiMap.objectToMap(data);
                if (weiuiParse.parseStr(res.get("status")).equals("success")) {
                    String imageUri = insertImageToSystem(context, weiuiParse.parseStr(res.get("path")));
                    Intent imageIntent = new Intent(Intent.ACTION_SEND);
                    imageIntent.setType("image/*");
                    imageIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(imageUri));
                    context.startActivity(Intent.createChooser(imageIntent, "分享"));
                }else{
                    Toast.makeText(context, "图片读取失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void invokeAndKeepAlive(Object data) {

            }
        });
    }

    /**
     * 通知系统更新图片
     * @param context
     * @param imagePath
     * @return
     */
    private static String insertImageToSystem(Context context, String imagePath) {
        String url = null;
        try {
            url = MediaStore.Images.Media.insertImage(context.getContentResolver(), imagePath, "分享", "分享图片");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return url;
    }
}
