package com.mdstudios.diabeticons.Core;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
  ImageView mImageView;  // The actual image for this item

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
        Drawable drawable = Drawable.createFromStream(getAssets().open(path), null);

        mImageView = (ImageView) findViewById(R.id.image);
        mImageView.setImageDrawable(drawable);

      } catch (IOException e) {
        Log.e(LOGTAG, "There was an error! Error: " + e.toString());
        e.printStackTrace();
      }

      // Set the send button to actually send the image
      Button sendButton = (Button) findViewById(R.id.button_send);
      sendButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          sendImage();
        }
      });
    }
    else {
      // Something must've went wrong- throw an error to the log
      Log.e(LOGTAG, "No extras bundle was passed in!");
    }
  }

  // Sends an intent to share the image that was passed to this Activity
  private void sendImage() {
    // Create the special share content path (can't share images directly from assets)
    String sharePath = MediaStore.Images.Media.insertImage(
        getContentResolver(),
        ((BitmapDrawable)mImageView.getDrawable()).getBitmap(),
        mTitle,
        null
    );
    Uri imageUri = Uri.parse(sharePath);

    // Create the intent
    Intent shareIntent = new Intent(Intent.ACTION_SEND);
    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
    shareIntent.setType("image/png");

    // Send the intent finally (and always use the chooser dialogue)
    startActivity(
        Intent.createChooser(shareIntent, getResources().getText(R.string.share_chooser_title))
    );
  }
}
