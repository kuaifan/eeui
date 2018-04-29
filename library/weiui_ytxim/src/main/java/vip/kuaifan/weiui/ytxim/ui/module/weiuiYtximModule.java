package vip.kuaifan.weiui.ytxim.ui.module;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import vip.kuaifan.weiui.ytxim.weiui_ytxim;

/**
 * Created by WDM on 2018/4/15.
 */

@WeexModule(name = "weiui_ytxim")
public class weiuiYtximModule extends WXModule {

    private static final String TAG = "weiuiYtximModule";

    /**
     * 初始化
     * @param appId
     * @param appToken
     * @param callback
     */
    @JSMethod
    public void init(String appId, String appToken, JSCallback callback) {
        weiui_ytxim.init(appId, appToken, callback);
    }

    /**
     * 登录
     * @param userphone
     * @param callback
     */
    @JSMethod
    public void login(String userphone, JSCallback callback) {
        weiui_ytxim.login(userphone, callback);
    }

    /**
     * 登出
     */
    @JSMethod
    public void logout() {
        weiui_ytxim.logout();
    }

    /**
     * 进入聊天室
     * @param roomId
     * @param callback
     */
    @JSMethod
    public void joinLiveChatRoom(String roomId, JSCallback callback) {
        weiui_ytxim.joinLiveChatRoom(roomId, callback);
    }

    /**
     * 监听聊天室
     * @param callback
     */
    @JSMethod
    public void setOnLiveChatRoomListener(JSCallback callback) {
        weiui_ytxim.setOnLiveChatRoomListener(callback);
    }

    /**
     * 发送聊天室消息
     * @param roomId
     * @param text
     * @param callback
     */
    @JSMethod
    public void sendLiveChatRoomMessage(String roomId, String text, JSCallback callback) {
        weiui_ytxim.sendLiveChatRoomMessage(roomId, text, callback);
    }

    /**
     * 退出聊天室
     * @param roomId
     * @param callback
     */
    @JSMethod
    public void exitLiveChatRoom(String roomId, JSCallback callback) {
        weiui_ytxim.exitLiveChatRoom(roomId, callback);
    }
}
