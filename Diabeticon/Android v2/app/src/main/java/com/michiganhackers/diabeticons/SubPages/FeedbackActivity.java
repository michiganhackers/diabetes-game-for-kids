package com.michiganhackers.diabeticons.SubPages;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.michiganhackers.diabeticons.R;

/**
 * Created by jawad on 21/10/15.
 */
public class FeedbackActivity extends ActionBarActivity {
    Toolbar mToolbar;
    EditText mInputEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // Add the toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Cache the EditText for later use
        mInputEditText = (EditText) findViewById(R.id.input);

        // Set up the send button
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the input
                String input = mInputEditText.getText().toString();

                // For now, just toast it
                Toast.makeText(FeedbackActivity.this, "Input: " + input, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
