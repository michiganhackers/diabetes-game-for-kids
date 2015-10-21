package com.michiganhackers.diabeticons.Core;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.michiganhackers.diabeticons.R;
import com.michiganhackers.diabeticons.SubPages.AboutActivity;
import com.michiganhackers.diabeticons.SubPages.FeedbackActivity;
import com.michiganhackers.diabeticons.SubPages.SendActivity;
import com.michiganhackers.diabeticons.SubPages.ShareAppActivity;

/**
 * Created by jawad on 12/10/15.
 */
public class MoreFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.frag_more,container,false);

        v.findViewById(R.id.layout_about)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choseAbout();
                    }
                });
        v.findViewById(R.id.layout_feedback)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choseFeedback();
                    }
                });
        v.findViewById(R.id.layout_share)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choseShareApp();
                    }
                });

        return v;
    }

    private void choseAbout() {
//        Intent intent = new Intent(getActivity(), AboutActivity.cla)
        startActivity(new Intent(getActivity(), AboutActivity.class));
    }

    private void choseFeedback() {
        startActivity(new Intent(getActivity(), FeedbackActivity.class));
    }

    private void choseShareApp() {
        startActivity(new Intent(getActivity(), ShareAppActivity.class));
    }
}
