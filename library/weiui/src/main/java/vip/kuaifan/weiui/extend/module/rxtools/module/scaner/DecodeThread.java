package vip.kuaifan.weiui.extend.module.rxtools.module.scaner;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.CountDownLatch;

import vip.kuaifan.weiui.activity.PageActivity;

/**
 * @author Vondear
 * 描述: 解码线程
 */
final class DecodeThread extends Thread {

    private final CountDownLatch handlerInitLatch;
    PageActivity activity;
    private Handler handler;

	DecodeThread(PageActivity activity) {
		this.activity = activity;
		handlerInitLatch = new CountDownLatch(1);
	}

	Handler getHandler() {
		try {
			handlerInitLatch.await();
		} catch (InterruptedException ie) {
			// continue?
		}
		return handler;
	}

	@Override
	public void run() {
		Looper.prepare();
		handler = new DecodeHandler(activity);
		handlerInitLatch.countDown();
		Looper.loop();
	}

}
