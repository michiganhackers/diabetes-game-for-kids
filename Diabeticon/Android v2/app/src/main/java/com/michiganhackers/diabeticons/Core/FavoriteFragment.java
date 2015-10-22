package com.michiganhackers.diabeticons.Core;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.michiganhackers.diabeticons.FavListAdapter;
import com.michiganhackers.diabeticons.HomeListAdapter;
import com.michiganhackers.diabeticons.R;
import com.michiganhackers.diabeticons.SubPages.SendActivity;
import com.michiganhackers.diabeticons.Util.Util;

/**
 * Created by jawad on 12/10/15.
 */
public class FavoriteFragment extends Fragment {
    ListView mIconList;
    FavListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.frag_favorties,container,false);
        mIconList = (ListView) v.findViewById(R.id.listview);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Give it the appropriate adapter
        mAdapter = new FavListAdapter(getActivity(), (MyApplication) getActivity().getApplication());
        mIconList.setAdapter(mAdapter);

        // Set the click listener for the ListView
        mIconList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MyApplication) getActivity().getApplication())
                        .addRecentItem(mAdapter.getIconIndex(position));

                Intent intent = new Intent(getActivity(), SendActivity.class);
                intent.putExtra(Util.KEY_INDEX, mAdapter.getIconIndex(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        updateAdapter();
    }

    public void updateAdapter() {
        mAdapter.updateData();
    }
}
