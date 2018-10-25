package com.test.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.test.R;


public class PrimaryFragment extends Fragment {

    int color;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.primary_fragment, container, false);
        //  return inflater.inflate(R.layout.primary_fragment,null);


        return view;

    }

}
