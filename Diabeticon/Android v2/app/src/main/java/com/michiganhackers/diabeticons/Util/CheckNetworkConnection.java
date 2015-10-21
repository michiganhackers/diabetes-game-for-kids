package com.michiganhackers.diabeticons.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import com.michiganhackers.diabeticons.R;

/**
 * Created by jawad on 18/10/14.
 *
 * Checks if network connection is available
 * TODO: Comment the code
 */
public class CheckNetworkConnection {

    public static boolean isConnectionAvailable(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    // Shows a dialog telling the user failed to connect to Internet and to check settings
    public static void showNoConnectionDialog(Context context) {
        // Need to declare final context in order to start the Activity on positive button click
        final Context ctx = context;

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setCancelable(true);
        builder.setMessage(R.string.noconnection_dialog_message);
        builder.setTitle(R.string.noconnection_dialog_title);
        builder.setPositiveButton(R.string.noconnection_dialog_yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which)
            {
                ctx.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            }
        });

        builder.setNegativeButton(R.string.noconnection_dialog_no, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                return;
            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
            public void onCancel(DialogInterface dialog) {
                return;
            }
        });

        builder.show();
    }
}