package vip.kuaifan.weex;

import android.text.TextUtils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.ws.WebSocket;
import com.squareup.okhttp.ws.WebSocketCall;
import com.squareup.okhttp.ws.WebSocketListener;
import com.taobao.weex.utils.WXLogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okio.Buffer;


public class HotReloadManager {
    private static final String TAG = "HotReloadManager";
    private ActionListener listener;
    private WebSocket session;

    public HotReloadManager(String ws, final ActionListener actionListener) {
        if (TextUtils.isEmpty(ws) || actionListener == null) {
            WXLogUtils.w("HotReloadManager", "Illegal arguments");
            return;
        }
        this.listener = actionListener;

        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(ws);
        Request request = builder.build();
        WebSocketCall call = WebSocketCall.create(client, request);
        call.enqueue(new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                WXLogUtils.w(TAG, "ws session open");
                session = webSocket;
            }

            @Override
            public void onFailure(IOException e, Response response) {
                e.printStackTrace();
            }

            @Override
            public void onMessage(ResponseBody message) throws IOException {
                WXLogUtils.w(TAG, "on message");
                if (message.contentType() == WebSocket.TEXT) {
                    try {
                        JSONObject rpcMessage = new JSONObject(message.string());
                        String method = rpcMessage.optString("method", null);
                        if (!TextUtils.isEmpty(method)) {
                            if ("WXReload".equals(method)) {
                                listener.reload();
                            } else if ("WXReloadBundle".equals(method)) {
                                String bundleUrl = rpcMessage.optString("params", null);
                                if (!TextUtils.isEmpty(bundleUrl)) {
                                    listener.render(bundleUrl);
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onPong(Buffer payload) {
                WXLogUtils.w(TAG, "on pong");
            }

            @Override
            public void onClose(int code, String reason) {
                WXLogUtils.w("HotReloadManager", "Closed:" + code + ", " + reason);
            }
        });
    }

    public void destroy() {
        if (session != null) {
            try {
                session.close(1001, "GOING_AWAY");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public interface ActionListener {
        void reload();

        void render(String bundleUrl);
    }
}
