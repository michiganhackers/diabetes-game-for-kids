package com.michiganhackers.diabeticons.Util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

/**
 * Created by jawad on 04/12/14.
 *
 * Creates and starts any basic intents necessary
 */
public class IntentStarter {
    // Simply open a url
    public static void openUrl(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    // Open an url to send an email
    public static void sendEmail(Context context, String toEmail, String subject) {
        // Originally from: http://stackoverflow.com/questions/8284706/send-email-via-gmail

        Intent sendTo = new Intent(Intent.ACTION_SENDTO);
        String uriText = "mailto:" + Uri.encode(toEmail) +
                "?subject=" + Uri.encode(subject);
        Uri uri = Uri.parse(uriText);
        sendTo.setData(uri);

        List<ResolveInfo> resolveInfos =
                context.getPackageManager().queryIntentActivities(sendTo, 0);

        // Emulators may not like this check...
        if (!resolveInfos.isEmpty())
        {
            context.startActivity(sendTo);
            return;
        }

        // Nothing resolves send to, so fallback to send...
        Intent send = new Intent(Intent.ACTION_SEND);

        send.setType("text/plain");
        send.putExtra(Intent.EXTRA_EMAIL,
                new String[] { toEmail });
        send.putExtra(Intent.EXTRA_SUBJECT, subject);

        context.startActivity(Intent.createChooser(send, "Send Email"));
    }

    // Share an url to Facebook. Note: FB doesn't allow setting default text anymore
    public static void shareToFacebook(Context context, String urlToShare) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, urlToShare);

        // See if official Facebook app is found
        boolean facebookAppFound = false;
        List<ResolveInfo> matches = context.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                intent.setPackage(info.activityInfo.packageName);
                facebookAppFound = true;
                break;
            }
        }

        // As fallback, launch sharer.php in a browser
        if (!facebookAppFound) {
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }

        context.startActivity(intent);
    }

    // Share text and an url directly to TWitter
    public static void shareToTwitter(Context context, String tweetText, String tweetUrl) {
        // Helps direct to Twitter
        final String twitterUrl = "https://twitter.com/intent/tweet?text=%s&url=%s";

        // Format the url and create an intent with it
        String finalUrl =
                String.format(twitterUrl,
                        Util.urlEncode(tweetText), Util.urlEncode(tweetUrl));
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl));

    // Narrow down to official Twitter app, if available:
        List<ResolveInfo> matches = context.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                intent.setPackage(info.activityInfo.packageName);
            }
        }

        context.startActivity(intent);
    }

    // Share text and an url in the general Android fashion
    public static void shareToGeneral(Context context, String subject, String url) {
        // Create the intent
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        // Add the url and subject to the intent
        intent.putExtra(Intent.EXTRA_TEXT, url);
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);

        // Finally, actually start the intent
        context.startActivity(Intent.createChooser(intent, "Share"));
    }
}
