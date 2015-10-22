package com.michiganhackers.diabeticons;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.michiganhackers.diabeticons.Core.MyApplication;

import java.util.ArrayList;

/**
 * Created by jawad on 22/10/15.
 */
public class HomeListAdapter extends BaseAdapter {
    private static final String LOGTAG = "MD/HomeListAdapter";

    Context mContext;
    MyApplication mAppReference;

    public HomeListAdapter(Context context, MyApplication myApplication) {
        this.mContext = context;
        this.mAppReference = myApplication;
    }

    public void updatedFavorites() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mAppReference.getAllIcons().size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
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
            holder.favBtn = (ImageButton) row.findViewById(R.id.btn_fav);

            // Make the row reuse the ViewHolder
            row.setTag(holder);
        }
        else { // Otherwise, use the recycled view
            row = convertView;
            holder = (ViewHolder) row.getTag();
        }

        // Set the title and icon of this item according to the position
        Icon curIcon = mAppReference.getAllIcons().get(position);

        holder.title.setText(curIcon.getTitle());
        holder.icon.setImageDrawable(curIcon.getImage());

        // Set the favorite button to toggle correctly and to notify
        holder.favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isSelected()) {
                    // Now unselected
                    v.setSelected(false);

                    // Let the entire app know of the change
                    mAppReference.setFavoriteState(position, false);
                }
                else {
                    // Now selected
                    v.setSelected(true);

                    // Let the entire app know of the change
                    mAppReference.setFavoriteState(position, true);
                }
            }
        });

        return row;
    }

    class ViewHolder{
        public ImageView icon;
        public TextView title;
        public ImageButton favBtn;
    }
}
