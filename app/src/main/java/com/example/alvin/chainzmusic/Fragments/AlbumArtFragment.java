package com.example.alvin.chainzmusic.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alvin.chainzmusic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumArtFragment extends Fragment {


    public AlbumArtFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album_art, container, false);
    }

}
