package com.mdstudios.diabeticons;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

/**
 * Created by jawad on 24/03/15.
 */
public class ItemSelectFragment extends Fragment {
  private static final String LOGTAG = "MD/ItemSelectFragment";

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_itemselect, container, false);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    testAllImages();
  }

  // Prints out all the target images from assets to the log
  private void testAllImages() {
    AssetManager assetManager = getActivity().getAssets(); // Necessary to access assets
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
}
