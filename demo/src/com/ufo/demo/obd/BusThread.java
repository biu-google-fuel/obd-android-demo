package com.ufo.demo.obd;
import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

public class BusThread {
	public final Bus mBus;
	private final Handler mHandler = new Handler(Looper.getMainLooper());

	public BusThread(){
		mBus = new Bus();
	}

	public void post(final Object event) {
		if (Looper.myLooper() == Looper.getMainLooper()) {
			mBus.post(event);
		} else {
			mHandler.post(new Runnable() {
				@Override public void run() {
					mBus.post(event);
				}
			});
		}
	}


	public void register(Object arg0) {
		mBus.register(arg0);
	}


	public void unregister(Object arg0) {
		mBus.unregister(arg0);
	}

}
