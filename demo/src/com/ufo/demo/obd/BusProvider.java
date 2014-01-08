package com.ufo.demo.obd;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EBean.Scope;

import com.squareup.otto.Bus;

/**
 * Singleton that holds the app-wide eventbus
 * @author Stephen Asherson.
 */
@EBean(scope = Scope.Singleton)
public class BusProvider {
	private BusThread eventBus;

	/**
	 * Lazy load the event bus
	 */
	public synchronized BusThread getEventBus() {
		if (eventBus == null) {
			eventBus = new BusThread();
		}

		return eventBus;
	}
}