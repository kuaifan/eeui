package io.rong.imlib.library.fakeserver;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v4.util.ArrayMap;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;

public class HttpUtil {

    private static final String TAG = "HttpUtil";

    public interface OnResponse {
        void onResponse(int code, String body);
    }

    public void post(String url, Header header, String body, OnResponse callback) {
        HttpAsyncTask httpTask = new HttpAsyncTask(url, header, body, callback);
        httpTask.execute();
    }

    public static Header getRcHeader(String appKey, String secret) {
        String nonce = String.valueOf(Math.random() * 0xffffff);
        String time_stamp = String.valueOf(System.currentTimeMillis() / 1000);
        String signature = toHex(toSHA1(secret + nonce + time_stamp));

        Header header = new Header();
        header.addHeader("App-Key", appKey);
        header.addHeader("Nonce", nonce);
        header.addHeader("Timestamp", time_stamp);
        header.addHeader("Signature", signature);
        header.addHeader("Content-Type", "application/x-www-form-urlencoded");
        return header;
    }

    private static byte[] toSHA1(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(value.getBytes());
            return md.digest();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String toHex(byte[] data) {
        final char[] DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int l = data.length;
        char[] out = new char[l << 1];
        int i = 0;

        for (int j = 0; i < l; ++i) {
            out[j++] = DIGITS_LOWER[(0xf0 & data[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0f & data[i]];
        }
        return String.valueOf(out);
    }

    public static class Header {

        private ArrayMap<String, String> headerMap = new ArrayMap<>();

        public void addHeader(String key, String value) {
            headerMap.put(key, value);
        }

        public int size() {
            return headerMap.size();
        }

        public String getKey(int pos) {
            return headerMap.keyAt(pos);
        }

        public String getValue(int pos) {
            return headerMap.valueAt(pos);
        }
    }

    private static class Response {
        int code;
        String body;

        public Response(int code, String body) {
            this.code = code;
            this.body = body;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class HttpAsyncTask extends AsyncTask<Void, Void, Response> {

        private String url;
        private Header header;
        private String body;
        private OnResponse callback;
        private HttpURLConnection conn;

        HttpAsyncTask(String url, Header header, String body, OnResponse callback) {
            this.url = url;
            this.header = header;
            this.body = body;
            this.callback = callback;
        }

        @Override
        protected void onPreExecute() {
            try {
                // Create HttpURLConnection.
                URL reqUrl = new URL(url);
                conn = (HttpURLConnection) reqUrl.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setInstanceFollowRedirects(true);
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);

                // Write header.
                for (int i = 0; i < header.size(); i++) {
                    conn.setRequestProperty(header.getKey(i), header.getValue(i));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected Response doInBackground(Void... params) {
            try {
                // Write body.
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes(body);
                out.flush();

                int code = conn.getResponseCode();
                InputStream in;
                if (code == 200) {
                    in = conn.getInputStream();
                } else {
                    in = conn.getErrorStream();
                }

                // Send http request, convert response to String
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                byte[] data = outStream.toByteArray();
                out.close();
                outStream.close();

                String json = new String(data);
                return new Response(code, json);
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return new Response(-1, "网络连接出错!");
            } catch (IOException e) {
                throw new RuntimeException("Parser http response error!");
            }
        }

        @Override
        protected void onPostExecute(Response res) {
            callback.onResponse(res.code, res.body);
        }
    }
}
