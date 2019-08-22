package com.example.alvin.chainzmusic;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationViewEx bottomNavigationViewEx;
private static String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationViewEx=findViewById(R.id.bottom_nav_view);
        init();
    }
    private void initBottomNavigationView(){
        Log.d(TAG, "initBottomNavigationView: ");
        bottomNavigationViewEx.enableAnimation(false);
    }
    private void init() {
        HomeFragment homeFragment=new HomeFragment();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_cotent_frame,homeFragment,getString(R.string.tag_fragment_home));
        transaction.addToBackStack(getString(R.string.tag_fragment_home));
        transaction.commit();
    }
}
