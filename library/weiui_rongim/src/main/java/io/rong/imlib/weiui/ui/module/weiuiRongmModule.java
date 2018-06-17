package io.rong.imlib.weiui.ui.module;


import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.weex.plugin.annotation.WeexModule;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.HashMap;
import java.util.Map;

import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.library.fakeserver.FakeServer;
import io.rong.imlib.library.fakeserver.HttpUtil;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import io.rong.imlib.weiui.weiui_rongim;
import io.rong.message.TextMessage;
import vip.kuaifan.weiui.extend.module.weiuiJson;

/**
 * Created by WDM on 2018/4/29.
 */

@WeexModule(name = "weiui_rongim")
public class weiuiRongmModule extends WXModule {

    private static final String TAG = "weiuiRongmModule";

    //事件接收者（用于防止重复监听）
    private Handler mEventHandler;
    private JSCallback mEventHandlerJSCallback;

    private void invoke(JSCallback callback, Object data) {
        if (callback != null) {
            callback.invoke(data);
        }
    }

    private void invokeAndKeepAlive(JSCallback callback, Object data) {
        if (callback != null) {
            callback.invokeAndKeepAlive(data);
        }
    }

    /**
     * 登录
     * @param object
     * @param callback
     */
    @JSMethod
    public void login(String object, final JSCallback callback) {
        final Map<String, Object> data = new HashMap<>();
        data.put("errorCode", 0);
        data.put("errorMsg", "");
        //
        JSONObject json = weiuiJson.parseObject(object);
        if (json.size() == 0) {
            data.put("status", "error");
            data.put("errorCode", -801);
            data.put("errorMsg", "membership error");
            invoke(callback, data);
            return;
        }
        if (json.getString("userid") == null) {
            data.put("status", "error");
            data.put("errorCode", -801);
            data.put("errorMsg", "userId error");
            invoke(callback, data);
            return;
        }
        //
        final UserInfo user = new UserInfo(json.getString("userid"), weiuiJson.getString(json, "username", json.getString("userid")), Uri.parse(weiuiJson.getString(json, "userimg")));
        FakeServer.getToken(user, new HttpUtil.OnResponse() {
            @Override
            public void onResponse(int code, String body) {
                if (code != 200) {
                    JSONObject json = weiuiJson.parseObject(body);
                    data.put("status", "error");
                    data.put("errorCode", weiuiJson.getInt(json, "code", -801));
                    data.put("errorMsg", weiuiJson.getString(json, "errorMessage", body));
                    invoke(callback, data);
                    return;
                }
                //
                final String token = body;
                weiui_rongim.connect(body, new RongIMClient.ConnectCallback() {
                    @Override
                    public void onTokenIncorrect() {
                        data.put("status", "error");
                        data.put("errorCode", -801);
                        data.put("errorMsg", "connect onTokenIncorrect");
                        invoke(callback, data);
                    }

                    @Override
                    public void onSuccess(String userId) {
                        weiui_rongim.setCurrentUser(user);
                        //
                        data.put("status", "success");
                        data.put("userid", userId);
                        data.put("token", token);
                        invoke(callback, data);
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        data.put("status", "error");
                        data.put("errorCode", errorCode.getValue());
                        data.put("errorMsg", errorCode.getMessage());
                        invoke(callback, data);
                    }
                });
            }
        });
    }

    /**
     * 登出
     */
    @JSMethod
    public void logout() {
        weiui_rongim.logout();
    }

    /**
     * 加入聊天室。如果聊天室不存在则自动创建
     * @param roomId
     * @param defMessageCount
     * @param callback
     */
    @JSMethod
    public void joinChatRoom(String roomId, int defMessageCount, final JSCallback callback) {
        final Map<String, Object> data = new HashMap<>();
        data.put("errorCode", 0);
        data.put("errorMsg", "");
        //
        weiui_rongim.joinChatRoom(roomId, defMessageCount, new RongIMClient.OperationCallback(){
            @Override
            public void onSuccess() {
                data.put("status", "success");
                invoke(callback, data);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                data.put("status", "error");
                data.put("errorCode", errorCode.getValue());
                data.put("errorMsg", errorCode.getMessage());
                invoke(callback, data);
            }
        });
    }

    /**
     * 退出聊天室，不在接收其消息
     * @param callback
     */
    @JSMethod
    public void quitChatRoom(final JSCallback callback) {
        final Map<String, Object> data = new HashMap<>();
        data.put("errorCode", 0);
        data.put("errorMsg", "");
        //
        weiui_rongim.quitChatRoom(new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                data.put("status", "success");
                invoke(callback, data);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                data.put("status", "error");
                data.put("errorCode", errorCode.getValue());
                data.put("errorMsg", errorCode.getMessage());
                invoke(callback, data);
            }
        });
    }

    /**
     * 添加接收者
     * @param callback
     */
    @JSMethod
    public void addEventHandler(final JSCallback callback) {
        if (callback == null) {
            return;
        }
        mEventHandlerJSCallback = callback;
        //
        if (mEventHandler == null) {
            mEventHandler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    switch (msg.what) {
                        case weiui_rongim.MESSAGE_ARRIVED: {
                            if (msg.obj instanceof TextMessage) {
                                TextMessage message = (TextMessage) msg.obj;
                                MessageContent content = (MessageContent) msg.obj;
                                UserInfo mUserInfo = content.getUserInfo();
                                //
                                Map<String, Object> data = new HashMap<>();
                                data.put("status", "arrived");
                                data.put("body", message.getContent());
                                data.put("extra", message.getExtra());
                                if (mUserInfo == null) {
                                    data.put("userid", 0);
                                    data.put("username", "");
                                    data.put("userimg", "");
                                }else{
                                    data.put("userid", mUserInfo.getUserId());
                                    data.put("username", mUserInfo.getName());
                                    Uri mUri = mUserInfo.getPortraitUri();
                                    data.put("userimg", mUri == null ? "" : mUri.toString());
                                }
                                invokeAndKeepAlive(mEventHandlerJSCallback, data);
                            }
                            break;
                        }

                        case weiui_rongim.MESSAGE_SEND: {
                            if (msg.obj instanceof TextMessage) {
                                TextMessage message = (TextMessage) msg.obj;
                                MessageContent content = (MessageContent) msg.obj;
                                UserInfo mUserInfo = content.getUserInfo();
                                //
                                Map<String, Object> data = new HashMap<>();
                                data.put("status", "send");
                                data.put("body", message.getContent());
                                data.put("extra", message.getExtra());
                                if (mUserInfo == null) {
                                    data.put("userid", 0);
                                    data.put("username", "");
                                    data.put("userimg", "");
                                }else{
                                    data.put("userid", mUserInfo.getUserId());
                                    data.put("username", mUserInfo.getName());
                                    Uri mUri = mUserInfo.getPortraitUri();
                                    data.put("userimg", mUri == null ? "" : mUri.toString());
                                }
                                invokeAndKeepAlive(mEventHandlerJSCallback, data);
                            }
                            break;
                        }

                        case weiui_rongim.MESSAGE_SEND_ERROR: {
                            if (msg.obj instanceof TextMessage) {
                                TextMessage message = (TextMessage) msg.obj;
                                MessageContent content = (MessageContent) msg.obj;
                                UserInfo mUserInfo = content.getUserInfo();
                                //
                                Map<String, Object> data = new HashMap<>();
                                data.put("status", "send_error");
                                data.put("body", message.getContent());
                                data.put("extra", message.getExtra());
                                if (mUserInfo == null) {
                                    data.put("userid", 0);
                                    data.put("username", "");
                                    data.put("userimg", "");
                                }else{
                                    data.put("userid", mUserInfo.getUserId());
                                    data.put("username", mUserInfo.getName());
                                    Uri mUri = mUserInfo.getPortraitUri();
                                    data.put("userimg", mUri == null ? "" : mUri.toString());
                                }
                                invokeAndKeepAlive(mEventHandlerJSCallback, data);
                            }
                            break;
                        }
                    }
                    return false;
                }
            });
            weiui_rongim.addEventHandler(mEventHandler);
        }
    }

    /**
     * 移除接收者
     */
    @JSMethod
    public void removeEventHandler() {
        if (mEventHandler == null) {
            return;
        }
        weiui_rongim.removeEventHandler(mEventHandler);
        mEventHandler = null;
    }

    /**
     * 当前聊天室发送文本消息
     * @param text
     * @param callback
     */
    @JSMethod
    public void sendTextMessage(String text, final JSCallback callback) {
        if (text == null) {
            return;
        }
        weiui_rongim.sendMessage(TextMessage.obtain(text), new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(io.rong.imlib.model.Message message) {

            }

            @Override
            public void onSuccess(io.rong.imlib.model.Message message) {
                Map<String, Object> data = new HashMap<>();
                data.put("status", "success");
                invoke(callback, data);
            }

            @Override
            public void onError(io.rong.imlib.model.Message message, RongIMClient.ErrorCode errorCode) {
                Map<String, Object> data = new HashMap<>();
                data.put("status", "error");
                invoke(callback, data);
            }
        });
    }
}
