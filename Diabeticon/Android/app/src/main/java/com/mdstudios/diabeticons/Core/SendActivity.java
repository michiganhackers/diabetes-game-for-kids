package com.mdstudios.diabeticons.Core;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdstudios.diabeticons.R;
import com.mdstudios.diabeticons.Utils.Util;

import java.io.IOException;

/**
 * Created by jawad on 24/03/15.
 *
 * Shows off an image and title, while giving options to send it off
 */
public class SendActivity extends ActionBarActivity {
  private static final String LOGTAG = "MD/SendActivity";

  Toolbar mToolbar;
  String mTitle;    // Title of this item
  Drawable mImage;  // The actual image for this item

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_send);

    // Add the toolbar
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Grab the data passed to this Activity
    Bundle extras = getIntent().getExtras();
    if(extras != null) {
      // Get the passed in data
      mTitle = extras.getString(Util.KEY_TITLE);
      String path = extras.getString(Util.KEY_PATH);

      // Set the title ofthe title view
      TextView titleText = (TextView) findViewById(R.id.title);
      titleText.setText(mTitle);

      // Get the image for this item and set the image preview
      try {
        mImage = Drawable.createFromStream(getAssets().open(path), null);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageDrawable(mImage);
      } catch (IOException e) {
        Log.e(LOGTAG, "There was an error! Error: " + e.toString());
        e.printStackTrace();
      }
    }
    else {
      // Something must've went wrong- throw an error to the log
      Log.e(LOGTAG, "No extras bundle was passed in!");
    }
  }
}
