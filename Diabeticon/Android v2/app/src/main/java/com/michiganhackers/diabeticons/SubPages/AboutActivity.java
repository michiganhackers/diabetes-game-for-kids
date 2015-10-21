package com.michiganhackers.diabeticons.SubPages;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.michiganhackers.diabeticons.R;

/**
 * Created by jawad on 21/10/15.
 */
public class AboutActivity extends ActionBarActivity {
    private static final String LOGTAG = "MD/AboutActivity";
    Toolbar mToolbar;

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
    }
}
