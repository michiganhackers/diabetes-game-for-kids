package com.michiganhackers.diabeticons.SubPages;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.michiganhackers.diabeticons.R;

/**
 * Created by jawad on 21/10/15.
 */
public class ShareAppActivity extends ActionBarActivity {
    Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        // Add the toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
