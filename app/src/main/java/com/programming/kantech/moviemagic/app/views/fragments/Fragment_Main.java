package com.programming.kantech.moviemagic.app.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.programming.kantech.moviemagic.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by patrick keogh on 2018-01-09.
 */

public class Fragment_Main extends Fragment {


    public Fragment_Main() {
        // no-op constructor
    }

    public static Fragment_Main newInstance() {
        return new Fragment_Main();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {




        // Get the fragment layout for the driving list
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        ButterKnife.bind(this, rootView);

        return rootView;
    }
}
