package vip.kuaifan.weiui.extend.integration.xutils.http.body;


import vip.kuaifan.weiui.extend.integration.xutils.http.ProgressHandler;

/**
 * Created by wyouflf on 15/8/13.
 */
public interface ProgressBody extends RequestBody {
    void setProgressHandler(ProgressHandler progressHandler);
}
