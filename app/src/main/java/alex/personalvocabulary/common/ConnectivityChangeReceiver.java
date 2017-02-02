package alex.personalvocabulary.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import alex.personalvocabulary.common.Utils;

/**
 * Created by yegor on 29/02/16.
 */
public class ConnectivityChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        debugIntent(intent, "grokkingandroid");

        Log.v(Utils.getIPAddress(true), Utils.getIPAddress(true));
    }

    private void debugIntent(Intent intent, String tag) {
        Log.v(tag, "action: " + intent.getAction());
        Log.v(tag, "component: " + intent.getComponent());
        Bundle extras = intent.getExtras();
        if (extras != null) {
            for (String key : extras.keySet()) {
                Log.v(tag, "key [" + key + "]: " +
                        extras.get(key));
            }
        } else {
            Log.v(tag, "no extras");
        }
    }

}
