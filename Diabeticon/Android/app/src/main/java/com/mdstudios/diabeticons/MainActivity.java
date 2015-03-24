package com.mdstudios.diabeticons;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.AssetManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {
  private static final String LOGTAG = "MD/MainActivity";

  // The Actionbar-replacement Toolbar that runs along the top of the screen
  Toolbar mToolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Add the toolbar
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }

    // Set up the basic content
    initContent();
  }

  private void initContent() {
    testAllImages();
  }

  // Prints out all the target images from assets to the log
  private void testAllImages() {
    AssetManager assetManager = getAssets(); // Necessary to access assets
    try {
      // Get all the file names
      String[] files = assetManager.list("diabeticons"); // "diabeticons" = path

      // For now, print out all the names
      int i = 0;
      for(String name : files) {
        // Print out the name + the index
        Log.d(LOGTAG, "Index " + i + ": " + name);

        // Testing out chopping off the last bunch of the name
        String simplerName = name.substring(0, name.indexOf('.'));
        // Looks for a lower case followed by an upper case, adds space between
        // Source: http://stackoverflow.com/questions/4886091/insert-space-after-capital-letter
        String simplerOutput = simplerName.replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2");
        Log.d(LOGTAG, "Simplified: \"" + simplerOutput + "\"");

        // Increment the index
        ++i;
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_activity_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
