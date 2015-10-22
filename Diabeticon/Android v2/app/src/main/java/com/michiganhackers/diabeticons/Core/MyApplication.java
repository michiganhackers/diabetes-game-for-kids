package com.michiganhackers.diabeticons.Core;

import android.app.Application;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.michiganhackers.diabeticons.FavoriteChangeSubscriber;
import com.michiganhackers.diabeticons.Icon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by jawad on 22/10/15.
 */
public class MyApplication extends Application {
    private final static String LOGTAG = "MD/MyApplicaiton";

    private ArrayList<Icon> mIconList;
    private FavoriteChangeSubscriber mFavSubscriber = null;

    @Override
    public void onCreate() {
        super.onCreate();

        // Create all the icons for the rest of the entire application to use
        generateAllIcons();
    }

    private void generateAllIcons() {
        mIconList = new ArrayList<>();

        // Get all the necessary images and titles
        AssetManager assetManager = getAssets(); // Necessary to access assets
        try {
            // Get all the file names
            String[] files = assetManager.list("diabeticons"); // "diabeticons" = path

            // Save all the names and images
            for(int i = 0; i < files.length; ++i) {
                // Create the new icon object for this round
                Icon curIcon = new Icon();

                // Cache the image for this item from its file path
                String path = "diabeticons/" + files[i];
                Drawable d = Drawable.createFromStream(assetManager.open(path), null);
                curIcon.setImage(d);

                // Cache the path, for later use
                curIcon.setPath(path);

                // Get the simplified, displayable name (no .filetype, no nyNameIsFrankenstein)
                String simplerName = files[i].substring(0, files[i].indexOf('.'));
                // Looks for a lower case followed by an upper case, adds space between
                // Source: http://stackoverflow.com/questions/4886091/insert-space-after-capital-letter
                String simplerNameFinal = simplerName.replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2");

                // Save the easier-to-read title
                curIcon.setTitle(simplerNameFinal);

                // Finally put the icon into the list for later use
                mIconList.add(curIcon);
            }

        } catch (IOException e) {
            Log.e(LOGTAG, "There was an error! Error: " + e.toString());
            e.printStackTrace();
        }
    }

    public ArrayList<Icon> getAllIcons() {
        return mIconList;
    }

    public Icon getIcon(int index) {
        return mIconList.get(index);
    }

    public void setFavoriteState(int index, boolean value) {
        // Change the actual stored value
        this.mIconList.get(index).setIsFavorite(value);

        // Notify the subscriber, if valid
        if(mFavSubscriber != null) {
            mFavSubscriber.favoriteStatusChanged();
        }
    }

    public void setFavoriteChangeNotifier(FavoriteChangeSubscriber subscriber) {
        this.mFavSubscriber = subscriber;
    }

    public void removeFavoriteChangeNotifier() {
        this.mFavSubscriber = null;
    }
}
