package com.mdstudios.diabeticons;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
  private static final String LOGTAG = "MD/MainActivity";

  // The Actionbar-replacement Toolbar that runs along the top of the screen
  Toolbar mToolbar;

  ListView mListView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Add the toolbar
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }

    // TODO: Check if there is saved data- if so, then don't redo everything unnecessarily
    // Set up the basic content
    initContent();
  }

  private void initContent() {
    // Grab the ListView, so can set it up
    mListView = (ListView) findViewById(R.id.listview);

    // Give it the appropriate adapter
    IconListAdapter adapter = new IconListAdapter(this);
    mListView.setAdapter(adapter);

    //testAllImages();
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

  private class IconListAdapter extends BaseAdapter {
    Context mContext;

    String[] mTitles;             // The titles for each item
    ArrayList<Drawable> mImages;  // All the images for each item

    public IconListAdapter(Context context) {
      this.mContext = context;

      // Get all the necessary images and titles
      AssetManager assetManager = context.getAssets(); // Necessary to access assets
      try {
        // Get all the file names
        mTitles = assetManager.list("diabeticons"); // "diabeticons" = path

        // Save all the names and images
        mImages = new ArrayList<>();
        for(int i = 0; i < mTitles.length; ++i) {
          // TODO: Get and cache the appropriate image
          // Cache the image for this item from its file path
          String path = "diabeticons/" + mTitles[i];
          Drawable d = Drawable.createFromStream(getAssets().open(path), null);
          mImages.add(d);

          // Get the simplified, displayable name (no .filetype, no nyNameIsFrankenstein)
          String simplerName = mTitles[i].substring(0, mTitles[i].indexOf('.'));
            // Looks for a lower case followed by an upper case, adds space between
            // Source: http://stackoverflow.com/questions/4886091/insert-space-after-capital-letter
          String simplerNameFinal = simplerName.replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2");

          // Replace the title with the easier-to-read title
          mTitles[i] = simplerNameFinal;
        }

      } catch (IOException e) {
        Log.e(LOGTAG, "There was an error! Error: " + e.toString());
        e.printStackTrace();
      }
    }

    @Override
    public int getCount() {
      return mTitles.length;
    }

    @Override
    public Object getItem(int position) {
      return null;
    }

    @Override
    public long getItemId(int position) {
      return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View row = null;
      ViewHolder holder;

      if(convertView == null) {
        // Then gotta set up this row for the first time
        LayoutInflater inflater =
            (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.list_itemselect, parent, false);

        // Create a ViewHolder to save all the different parts of the row
        holder = new ViewHolder();
        holder.icon = (ImageView) row.findViewById(R.id.icon);
        holder.title = (TextView) row.findViewById(R.id.title);

        // Make the row reuse the ViewHolder
        row.setTag(holder);
      }
      else { // Otherwise, use the recycled view
        row = convertView;
        holder = (ViewHolder) row.getTag();
      }

      // Set the title and icon of this item according to the position
      holder.title.setText(mTitles[position]);
      holder.icon.setImageDrawable(mImages.get(position));

      return row;
    }

    class ViewHolder{
      public ImageView icon;
      public TextView title;
    }
  }
}
