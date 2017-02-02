package alex.personalvocabulary.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import alex.personalvocabulary.common.ConnectivityChangeReceiver;

/**
 * Created by yegor on 14/02/16.
 */
public class RubykoApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static RubykoApplication instance;

    {
        instance = this;
    }

    public static RubykoApplication getInstance() {
        return instance;
    }

    private boolean isDestroyed = false;
    private boolean isStopped = false;
    private boolean isPaused = false;

    public RubykoApplication() {
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        isDestroyed = false;
    }

    @Override
    public void onActivityStarted(Activity activity) {
        isStopped = false;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        isPaused = false;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        isPaused = true;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        isStopped = true;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public boolean isPaused() {
        return isPaused;
    }
}
