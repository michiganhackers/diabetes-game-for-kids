package com.michiganhackers.diabeticons;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.michiganhackers.diabeticons.R;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by jawad on 12/10/15.
 */
public class IconListAdapter extends BaseAdapter {
    private static final String LOGTAG = "MD/IconListAdapter";

    Context mContext;

    LinkedList<String> mPaths;      // The assets path for each item
    LinkedList<String> mTitles;     // The display-ready title for each item
    LinkedList<Drawable> mImages;   // All the images for each item

    public IconListAdapter(Context context) {
        this.mContext = context;

        // Get all the necessary images and titles
        AssetManager assetManager = context.getAssets(); // Necessary to access assets
        try {
            // Get all the file names
            String[] files = assetManager.list("diabeticons"); // "diabeticons" = path

            // Save all the names and images
            mPaths = new LinkedList<>();
            mTitles = new LinkedList<>();
            mImages = new LinkedList<>();
            for(int i = 0; i < files.length; ++i) {
                // Cache the image for this item from its file path
                String path = "diabeticons/" + files[i];
                Drawable d = Drawable.createFromStream(context.getAssets().open(path), null);
                mImages.add(d);

                // Cache the path, for later use
                mPaths.add(path);

                // Get the simplified, displayable name (no .filetype, no nyNameIsFrankenstein)
                String simplerName = files[i].substring(0, files[i].indexOf('.'));
                // Looks for a lower case followed by an upper case, adds space between
                // Source: http://stackoverflow.com/questions/4886091/insert-space-after-capital-letter
                String simplerNameFinal = simplerName.replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2");

                // Save the easier-to-read title
                mTitles.add(simplerNameFinal);
            }

        } catch (IOException e) {
            Log.e(LOGTAG, "There was an error! Error: " + e.toString());
            e.printStackTrace();
        }
    }

    public String getPath(int position) {
        return mPaths.get(position);
    }

    public Drawable getImage(int position) {
        return mImages.get(position);
    }

    public String getTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
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
        holder.title.setText(getTitle(position));
        holder.icon.setImageDrawable(getImage(position));

        return row;
    }

    class ViewHolder{
        public ImageView icon;
        public TextView title;
    }
}