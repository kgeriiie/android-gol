package com.ottone.gameoflife.bl;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by koleszargergo on 7/27/16.
 */
public class EventManager {

    private static EventManager sInstance = null;
    private EventBus mEventBus;

    public static EventManager getInstance() {
        if (sInstance == null) {
            sInstance = new EventManager();
        }

        return sInstance;
    }

    private EventManager() {
        mEventBus = new EventBus();
    }

    public boolean isSubscribed(Object subscriber) {
        return mEventBus.isRegistered(subscriber);
    }

    public void subscribeToEventBus(Object subscriber) {
        if (!mEventBus.isRegistered(subscriber))
            mEventBus.register(subscriber);
    }

    public void unsubscribeFromEventBus(Object subscriber) {
        if (mEventBus.isRegistered(subscriber))
            mEventBus.unregister(subscriber);
    }

    public void sendEvent(Object event) {
        if (mEventBus.hasSubscriberForEvent(event.getClass()))
            mEventBus.post(event);
    }
}
