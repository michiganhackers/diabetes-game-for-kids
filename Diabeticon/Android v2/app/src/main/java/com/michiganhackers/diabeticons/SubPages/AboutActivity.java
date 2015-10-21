package com.michiganhackers.diabeticons.SubPages;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.michiganhackers.diabeticons.R;
import com.michiganhackers.diabeticons.Util.CheckNetworkConnection;

/**
 * Created by jawad on 21/10/15.
 */
public class AboutActivity extends ActionBarActivity {
    private static final String LOGTAG = "MD/AboutActivity";
    private static final String URL = "http://www.healthdesignby.us/";
    Toolbar mToolbar;
    WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LOGTAG, "Started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Add the toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else {
            Log.e(LOGTAG, "Toolbar was null");
        }

        mWebView = (WebView) findViewById(R.id.webview);
        // If the internet can be connected to, load the WebView
        if(CheckNetworkConnection.isConnectionAvailable(this)) {
            // First, enable JavaScript
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            // TODO: Should javascript really be enabled? XSS vulnerabilities, potentially!

            // Force links and redirects to open in the WebView instead of in a browser
            CustomWebViewClient webViewClient = new CustomWebViewClient();
            mWebView.setWebViewClient(webViewClient);

            // Finally, load the URL
            mWebView.loadUrl(URL);
        }
        // Otherwise, show a dialog that failed to connect to the internet
        else {
            CheckNetworkConnection.showNoConnectionDialog(this);
        }
    }

    /**
     * WebViewClient that handles URL redirecting [currently]
     */
    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // Default behavior
            return super.shouldOverrideUrlLoading(view, url);

            // For now, simply return false- ie, ALL links will open in the webView
            // TODO: Fix this! Only open what links?
            // Help for later: https://developer.chrome.com/multidevice/webview/gettingstarted
//            return false;
        }
    }
}
