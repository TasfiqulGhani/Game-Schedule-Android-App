package tashfik.bughunters.iccworldtwenty202016;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

public class PushNotificationReceiver  extends ParsePushBroadcastReceiver{

private static final String TAG = "PushNotificationReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        Log.d("TEST", action.toString());
        if (action.equals(ParsePushBroadcastReceiver.ACTION_PUSH_RECEIVE)) {
            JSONObject extras;
            try {
                extras = new JSONObject(intent.getStringExtra(ParsePushBroadcastReceiver.KEY_PUSH_DATA));

                // I get this on my log like this:
                // Received push notification. Alert: A test push from Parse!


                String notice=extras.getString("alert");
                intent.putExtra("notice", notice);
                intent.setClass(context, Notifications.class);
                context.startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}