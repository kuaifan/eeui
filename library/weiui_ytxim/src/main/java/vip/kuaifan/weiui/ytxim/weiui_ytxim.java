package vip.kuaifan.weiui.ytxim;

import android.util.Log;

import com.taobao.weex.bridge.JSCallback;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.ECInitParams;
import com.yuntongxun.ecsdk.ECLiveCallBack;
import com.yuntongxun.ecsdk.ECLiveChatRoom;
import com.yuntongxun.ecsdk.ECLiveChatRoomManager;
import com.yuntongxun.ecsdk.ECLiveChatRoomMember;
import com.yuntongxun.ecsdk.ECLiveChatRoomMemberInfoBuilder;
import com.yuntongxun.ecsdk.ECLiveEnums;
import com.yuntongxun.ecsdk.ECLiveNotifyWrapper;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.OnLiveChatRoomListener;
import com.yuntongxun.ecsdk.SdkErrorCode;
import com.yuntongxun.ecsdk.im.ECTextMessageBody;
import com.yuntongxun.ecsdk.livechatroom.ECLiveChatRoomNotification;

import java.util.HashMap;
import java.util.Map;

import vip.kuaifan.weiui.ui.weiui;

public class weiui_ytxim {

    private static final String TAG = "weiui_ytxim";

    private static String appId = "";

    private static String appToken = "";

    private static String lastLogin = "";

    /**
     * 初始化
     * @param appId
     * @param appToken
     * @param callback
     */
    public static void init(String appId, String appToken, JSCallback callback) {
        if (appId != null && !"".equals(appId)) {
            weiui_ytxim.appId = appId;
        }
        if (appToken != null && !"".equals(appToken)) {
            weiui_ytxim.appToken = appToken;
        }

        //判断SDK是否已经初始化
        if (!ECDevice.isInitialized()) {
            //初始化接口
            ECDevice.initial(weiui.getApplication(), new ECDevice.InitListener() {
                @Override
                public void onInitialized() {
                    // SDK已经初始化成功
                    if (callback != null) {
                        Map<String, Object> data = new HashMap<>();
                        data.put("status", "success");
                        callback.invoke(data);
                    }
                    Log.i(TAG, "初始化SDK成功");
                }

                @Override
                public void onError(Exception exception) {
                    //在初始化错误的方法中打印错误原因
                    if (callback != null) {
                        Map<String, Object> data = new HashMap<>();
                        data.put("status", "error");
                        data.put("error", exception.getMessage());
                        callback.invoke(data);
                    }
                    Log.i(TAG, "初始化SDK失败" + exception.getMessage());
                }
            });
        } else {
            //已经初始化
            if (callback != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("status", "success");
                callback.invoke(data);
            }
            Log.i(TAG, "已初始化SDK成功");
        }
    }

    /**
     * 登录
     * @param userphone
     * @param callback
     */
    public static void login(String userphone, JSCallback callback) {
        if (userphone == null) {
            if (callback != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("status", "error");
                data.put("code", -801);
                data.put("msg", "no User");
                callback.invoke(data);
            }
            return;
        }
        //创建登录参数对象
        ECInitParams params = ECInitParams.createParams();
        //设置用户登录账号
        params.setUserid(userphone);
        //设置appId
        params.setAppKey(weiui_ytxim.appId);
        //设置appToken
        params.setToken(weiui_ytxim.appToken);
        //设置登陆验证模式：自定义登录方式
        params.setAuthType(ECInitParams.LoginAuthType.NORMAL_AUTH);
        //LoginMode（强制上线：FORCE_LOGIN  默认登录：AUTO。使用方式详见注意事项）
        params.setMode(ECInitParams.LoginMode.FORCE_LOGIN);
        //验证参数是否正确
        if (params.validate()) {
            //设置登录回调监听
            ECDevice.setOnDeviceConnectListener(new ECDevice.OnECDeviceConnectListener() {
                @Override
                public void onConnect() {
                    //兼容旧版本的方法，不必处理
                }

                @Override
                public void onDisconnect(ECError error) {
                    //兼容旧版本的方法，不必处理
                }

                @Override
                public void onConnectState(ECDevice.ECConnectState state, ECError error) {
                    if (callback != null) {
                        Map<String, Object> data = new HashMap<>();
                        data.put("code", error.errorCode);
                        data.put("msg", error.errorMsg);
                        if (state == ECDevice.ECConnectState.CONNECTING) {
                            data.put("status", "connecting");
                            callback.invokeAndKeepAlive(data);
                        }else if (state == ECDevice.ECConnectState.CONNECT_FAILED) {
                            data.put("status", "error");
                            data.put("msg", error.errorMsg + " " + (error.errorCode == SdkErrorCode.SDK_KICKED_OFF ? "被提出" : ""));
                            callback.invokeAndKeepAlive(data);
                        }else if (state == ECDevice.ECConnectState.CONNECT_SUCCESS) {
                            lastLogin = userphone;
                            data.put("status", "success");
                            callback.invoke(data);
                        }
                    }
                }
            });
            //开始登录
            ECDevice.login(params);
        }else{
            //参数不正确
            if (callback != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("status", "error");
                data.put("code", -802);
                data.put("msg", "err params");
                callback.invoke(data);
            }
        }
    }

    /**
     * 登出
     * @param userphone
     */
    public static void logout(String userphone) {
        ECDevice.logout(() -> {
            // SDK 回调通知当前登出成功
            // 这里可以做一些（与云通讯IM相关的）应用资源的释放工作
            // 如（关闭数据库，释放界面资源和跳转等）
        });
    }

    /**
     * 进入聊天室
     * @param roomId
     * @param callback
     */
    public static void joinLiveChatRoom(String roomId, JSCallback callback) {
        //获得SDK聊天室管理类
        ECLiveChatRoomManager roomManager = ECDevice.getECLiveChatRoomManager();
        if (roomManager == null) {
            return;
        }
        ECLiveChatRoomMemberInfoBuilder infoBuilder = new ECLiveChatRoomMemberInfoBuilder(lastLogin, "");
        ECLiveNotifyWrapper notifyWrapper = new ECLiveNotifyWrapper(lastLogin, ECLiveEnums.ECNotifyOption.ECNotifyOption_ON);
        //调用加入房间，传入相应的参数
        try {
            roomManager.joinLiveChatRoom(roomId, infoBuilder, notifyWrapper, new ECLiveCallBack.OnEnterLiveChatRoomListener() {
                @Override
                public void onEnterResult(ECError error, ECLiveChatRoom liveChatRoom, ECLiveChatRoomMember member) {
                    if (callback != null) {
                        if (error.errorCode == SdkErrorCode.REQUEST_SUCCESS) {
                            Map<String, Object> data = new HashMap<>();
                            data.put("status", "success");
                            data.put("code", error.errorCode);
                            data.put("msg", error.errorMsg);
                            callback.invoke(data);
                        } else {
                            Map<String, Object> data = new HashMap<>();
                            data.put("status", "error");
                            data.put("code", error.errorCode);
                            data.put("msg", error.errorMsg);
                            callback.invoke(data);
                        }
                    }
                }
            });
        } catch (Exception e) {
            Map<String, Object> data = new HashMap<>();
            data.put("status", "error");
            data.put("code", -804);
            data.put("msg", e.getMessage());
            callback.invoke(data);
        }
    }

    /**
     * 监听聊天室
     * @param callback
     */
    public static void setOnLiveChatRoomListener(JSCallback callback) {
        if (callback == null) {
            return;
        }
        ECLiveChatRoomManager mECLiveChatRoomManager = ECDevice.getECLiveChatRoomManager();
        if (mECLiveChatRoomManager == null) {
            Map<String, Object> data = new HashMap<>();
            data.put("status", "error");
            data.put("msg", "没有进入聊天室");
            callback.invoke(data);
            return;
        }
        mECLiveChatRoomManager.setOnLiveChatRoomListener(new OnLiveChatRoomListener() {
            @Override
            public void OnReceiveLiveChatRoomMessage(ECMessage message) {
                if (message == null) {
                    return;
                }
                Map<String, Object> data = new HashMap<>();
                data.put("status", "message");
                data.put("id", message.getId());
                data.put("to", message.getTo());
                data.put("form", message.getForm());
                data.put("msgid", message.getMsgId());
                data.put("msgStatus", message.getMsgStatus().toString());
                data.put("msgTime", message.getMsgTime());
                data.put("nickName", message.getNickName());
                data.put("sessionId", message.getSessionId());
                data.put("version", message.getVersion());
                data.put("type", message.getType().toString());
                data.put("userdata", message.getUserData());
                if (message.getBody() instanceof  ECTextMessageBody) {
                    data.put("body", ((ECTextMessageBody) message.getBody()).getMessage());
                }else{
                    data.put("body", message.getBody().toString());
                }
                data.put("apsAlert", message.getApsAlert());
                data.put("direction", message.getDirection());
                callback.invokeAndKeepAlive(data);
            }

            @Override
            public void OnReceiveLiveChatRoomNotification(ECLiveChatRoomNotification notice) {
                if (notice == null) {
                    return;
                }
                Map<String, Object> data = new HashMap<>();
                data.put("status", "notification");
                data.put("member", notice.getMember());
                data.put("nickName", notice.getNickName());
                data.put("noticeType", notice.getNoticeType().toString());
                data.put("notifyExt", notice.getNotifyExt());
                data.put("role", notice.getRole().toString());
                data.put("roomId", notice.getRoomId());
                data.put("roomName", notice.getRoomName());
                data.put("sender", notice.getSender());
                data.put("timestamp", notice.getTimestamp());
                callback.invokeAndKeepAlive(data);
            }
        });
    }

    /**
     * 发送聊天室消息
     * @param roomId
     * @param text
     * @param callback
     */
    public static void sendLiveChatRoomMessage(String roomId, String text, JSCallback callback) {
        if (text == null || text.trim().length() <= 0) {
            if (callback != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("status", "error");
                data.put("code", -803);
                data.put("msg", "请输入要发送的内容");
                callback.invoke(data);
            }
            return;
        }
        if (text.length() >= 2048) {
            if (callback != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("status", "error");
                data.put("code", -803);
                data.put("msg", "发送内容不得超过2048个字符");
                callback.invoke(data);
            }
            return;
        }
        //创建一个文本消息体，并添加到消息对象中
        ECMessage msg = ECMessage.createECMessage(ECMessage.Type.TXT);
        //发送者
        msg.setFrom(lastLogin);
        //接受者
        msg.setTo(roomId);
        //创建消息对象
        ECTextMessageBody msgBody = new ECTextMessageBody(text);
        msg.setBody(msgBody);
        //获得SDK聊天室管理类
        ECLiveChatRoomManager roomManager = ECDevice.getECLiveChatRoomManager();
        if (roomManager == null) {
            return;
        }
        //调用发送消息的方法，传入相应的参数
        roomManager.sendLiveChatRoomMessage(msg, new ECLiveCallBack.OnSendLiveChatRoomMessageListener() {
            @Override
            public void onSendResult(ECError error, ECMessage message) {
                if (callback != null) {
                    if (error.errorCode == SdkErrorCode.REQUEST_SUCCESS) {
                        Map<String, Object> data = new HashMap<>();
                        data.put("status", "success");
                        data.put("code", error.errorCode);
                        data.put("msg", error.errorMsg);
                        callback.invoke(data);
                    } else {
                        Map<String, Object> data = new HashMap<>();
                        data.put("status", "error");
                        data.put("code", error.errorCode);
                        data.put("msg", error.errorMsg);
                        callback.invoke(data);
                    }
                }
            }
        });
    }

    /**
     * 退出聊天室
     * @param roomId
     * @param callback
     */
    public static void exitLiveChatRoom(String roomId, JSCallback callback) {
        //获得SDK聊天室管理类
        ECLiveChatRoomManager roomManager = ECDevice.getECLiveChatRoomManager();
        if (roomManager == null) {
            return;
        }
        //构建通知对象
        ECLiveNotifyWrapper notifyWrapper = new ECLiveNotifyWrapper(lastLogin, ECLiveEnums.ECNotifyOption.ECNotifyOption_ON);
        //调用退出聊天室
        roomManager.exitLiveChatRoom(roomId, notifyWrapper, error -> {
            if (callback != null) {
                if (error.errorCode == SdkErrorCode.REQUEST_SUCCESS) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("status", "success");
                    data.put("code", error.errorCode);
                    data.put("msg", error.errorMsg);
                    callback.invoke(data);
                } else {
                    Map<String, Object> data = new HashMap<>();
                    data.put("status", "error");
                    data.put("code", error.errorCode);
                    data.put("msg", error.errorMsg);
                    callback.invoke(data);
                }
            }
        });
    }

}
