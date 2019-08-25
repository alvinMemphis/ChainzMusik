package com.example.alvin.chainzmusic;

import android.support.v4.app.Fragment;

public class FragmentTags {
    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private Fragment fragment;
    private String tag;

    public FragmentTags(){

    }
    public FragmentTags(Fragment frag,String tags){
        this.fragment=frag;
        this.tag=tags;

    }
}
