package com.michiganhackers.diabeticons.SubPages;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.michiganhackers.diabeticons.R;
import com.michiganhackers.diabeticons.Util.IntentStarter;

/**
 * Created by jawad on 21/10/15.
 */
public class ShareAppActivity extends ActionBarActivity {
    Toolbar mToolbar;

    // Well, these don't really need to be cached at the moment...
    LinearLayout mRowFacebook;
    LinearLayout mRowTwitter;
    LinearLayout mRowEmail;
    LinearLayout mRowOthers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareapp);

        // Add the toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Get all the necessary views
        mRowFacebook = (LinearLayout) findViewById(R.id.layout_facebook);
        mRowTwitter = (LinearLayout) findViewById(R.id.layout_twitter);
        mRowEmail = (LinearLayout) findViewById(R.id.layout_mail);
        mRowOthers = (LinearLayout) findViewById(R.id.layout_others);

        // Set all the appropriate listeners to share the content correctly
        mRowFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentStarter.shareToFacebook(ShareAppActivity.this,
                        getResources().getString(R.string.share_app_link));
            }
        });
        mRowTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentStarter.shareToTwitter(ShareAppActivity.this, getShareText());
            }
        });
        mRowEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentStarter.sendEmail(ShareAppActivity.this,
                        getResources().getString(R.string.share_app_email_subject),
                        getShareText());
            }
        });
        mRowOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentStarter.shareToGeneral(ShareAppActivity.this, getShareText());
            }
        });
    }

    // Returns the message that should be shared
    private String getShareText() {
        return getResources().getString(R.string.share_app_basemessage) + " " +
                getResources().getString(R.string.share_app_link);
    }
}
