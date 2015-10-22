package com.michiganhackers.diabeticons;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jawad on 22/10/15.
 */
public class HomeListAdapter extends BaseAdapter {
    private static final String LOGTAG = "MD/HomeListAdapter";

    Context mContext;
    ArrayList<Icon> mAllIcons;

    public HomeListAdapter(Context context, ArrayList<Icon> allIcons) {
        this.mContext = context;
        this.mAllIcons = allIcons;
    }

    public void updatedFavorites() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mAllIcons.size();
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
        Icon curIcon = mAllIcons.get(position);

        holder.title.setText(curIcon.getTitle());
        holder.icon.setImageDrawable(curIcon.getImage());

        return row;
    }

    class ViewHolder{
        public ImageView icon;
        public TextView title;
    }
}
