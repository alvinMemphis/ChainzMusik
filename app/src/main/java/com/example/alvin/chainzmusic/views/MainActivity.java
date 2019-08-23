package com.example.alvin.chainzmusic.views;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.alvin.chainzmusic.Fragments.AboutFragment;
import com.example.alvin.chainzmusic.Fragments.BrowseFragment;
import com.example.alvin.chainzmusic.Fragments.HomeFragment;
import com.example.alvin.chainzmusic.Fragments.LibraryFragment;
import com.example.alvin.chainzmusic.Fragments.ProfileFragment;
import com.example.alvin.chainzmusic.Fragments.SearchFragment;
import com.example.alvin.chainzmusic.Fragments.SettingsFragment;
import com.example.alvin.chainzmusic.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity implements BottomNavigationViewEx.OnNavigationItemSelectedListener , NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationViewEx bottomNavigationViewEx;
    private ImageView headerImage;

private static String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView navigationView=findViewById(R.id.navigation_view);
        View headerView=navigationView.getHeaderView(0);
        headerImage=headerView.findViewById(R.id.imageView);
        setHeaderImage();
        bottomNavigationViewEx=findViewById(R.id.bottom_nav_view);
        initBottomNavigationView();
        init();
    }

    private void setHeaderImage() {

    }

    private void initBottomNavigationView(){
        Log.d(TAG, "initBottomNavigationView: ");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(this);
    }
    private void init() {
        HomeFragment homeFragment=new HomeFragment();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_cotent_frame,homeFragment,getString(R.string.tag_fragment_home));
        transaction.addToBackStack(getString(R.string.tag_fragment_home));
        transaction.commit();

    }





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_share:{
                break;

            }
            case R.id.nav_settings:{
                Log.d(TAG, "onNavigationItemSelected: profile selected");
                SettingsFragment settingsFragment=new SettingsFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_cotent_frame,settingsFragment,getString(R.string.tag_fragment_settings));
                transaction.addToBackStack(getString(R.string.tag_fragment_settings));
                transaction.commit();
                menuItem.setChecked(true);
                break;

            }
            case R.id.nav_profile:{
                Log.d(TAG, "onNavigationItemSelected: profile selected");
                ProfileFragment profileFragment=new ProfileFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_cotent_frame,profileFragment,getString(R.string.tag_fragment_proifle));
                transaction.addToBackStack(getString(R.string.tag_fragment_proifle));
                transaction.commit();
                menuItem.setChecked(true);
                break;

            }
            case R.id.nav_about:{
                Log.d(TAG, "onNavigationItemSelected: About selected");
               AboutFragment aboutFragment=new AboutFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_cotent_frame,aboutFragment,getString(R.string.tag_fragment_about));
                transaction.addToBackStack(getString(R.string.tag_fragment_about));
                transaction.commit();
                menuItem.setChecked(true);
                break;

            }
            case R.id.bottom_nav_home:{
                Log.d(TAG, "onNavigationItemSelected: home selected");
                HomeFragment homeFragment=new HomeFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_cotent_frame,homeFragment,getString(R.string.tag_fragment_home));
                transaction.addToBackStack(getString(R.string.tag_fragment_home));
                transaction.commit();
                menuItem.setChecked(true);
                break;
            }
            case R.id.bottom_nav_browse:{
                Log.d(TAG, "onNavigationItemSelected: browse");
                BrowseFragment browseFragment=new BrowseFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_cotent_frame,browseFragment,getString(R.string.tag_fragment_browse));
                transaction.addToBackStack(getString(R.string.tag_fragment_browse));
                transaction.commit();
                menuItem.setChecked(true);
                break;
            }
            case R.id.bottom_nav_search:{
                SearchFragment homeFragment=new SearchFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_cotent_frame,homeFragment,getString(R.string.tag_fragment_search));
                transaction.addToBackStack(getString(R.string.tag_fragment_search));
                transaction.commit();
                menuItem.setChecked(true);
                break;
            }
            case R.id.bottom_library:{
                LibraryFragment libraryFragment=new LibraryFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_cotent_frame,libraryFragment,getString(R.string.tag_fragment_library));
                transaction.addToBackStack(getString(R.string.tag_fragment_library));
                transaction.commit();
                menuItem.setChecked(true);
                break;
            }



        }
        //navigationV

        return false;
    }
}
