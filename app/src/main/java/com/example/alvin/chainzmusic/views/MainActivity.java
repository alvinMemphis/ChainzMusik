package com.example.alvin.chainzmusic.views;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alvin.chainzmusic.FragmentTags;
import com.example.alvin.chainzmusic.Fragments.AboutFragment;
import com.example.alvin.chainzmusic.Fragments.BrowseFragment;
import com.example.alvin.chainzmusic.Fragments.HomeFragment;
import com.example.alvin.chainzmusic.Fragments.LibraryFragment;
import com.example.alvin.chainzmusic.Fragments.ProfileFragment;
import com.example.alvin.chainzmusic.Fragments.SearchFragment;
import com.example.alvin.chainzmusic.Fragments.SettingsFragment;
import com.example.alvin.chainzmusic.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationViewEx.OnNavigationItemSelectedListener , NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationViewEx bottomNavigationViewEx;
    private ImageView headerImage;

private static String TAG="MainActivity";

//vars
    private ArrayList<String> mFragmentTags=new ArrayList<>();
    private ArrayList<FragmentTags> mFragment=new ArrayList<>();
    private int mExitCount=0;
    private ProfileFragment profileFragment;
    private AboutFragment aboutFragment;
    private HomeFragment homeFragment;
    private BrowseFragment browseFragment;
    private SearchFragment searchFragment;
    private LibraryFragment libraryFragment;
    private SettingsFragment settingsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView navigationView=findViewById(R.id.navigation_view);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorAccent));

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View headerView=navigationView.getHeaderView(0);
        headerImage=headerView.findViewById(R.id.imageView);
        setHeaderImage();
        bottomNavigationViewEx=findViewById(R.id.bottom_nav_view);
        init();
        initBottomNavigationView();
    }

    private void setHeaderImage() {

    }

    private void initBottomNavigationView(){
        Log.d(TAG, "initBottomNavigationView: ");
        bottomNavigationViewEx.enableAnimation(false);
       // bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(this);
    }
    private void init() {

        if (homeFragment==null) {
            Log.d(TAG, "onNavigationItemSelected: home selected");
            homeFragment = new HomeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_cotent_frame, homeFragment, getString(R.string.tag_fragment_home));
            transaction.commit();
            mFragmentTags.add(getString(R.string.tag_fragment_home));
            mFragment.add(new FragmentTags(homeFragment,getString(R.string.tag_fragment_home)));
        }
        else {
            mFragmentTags.remove(getString(R.string.tag_fragment_home));
            mFragmentTags.add(getString(R.string.tag_fragment_home));
        }
        setFragmentVisibal(getString(R.string.tag_fragment_home));

    }
    private void setFragmentVisibal (String tagname){
        for (int i=0;i<mFragment.size();i++){
            if (tagname.equals(mFragment.get(i).getTag())){
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.show(mFragment.get(i).getFragment());
                transaction.commit();
            }else {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.hide(mFragment.get(i).getFragment());
                transaction.commit();
            }
        }
        setnavigationIcon(tagname);
    }


    @Override
    public void onBackPressed() {
        int backstackCount=mFragmentTags.size();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
       if(backstackCount>1) {
            String topFragment=mFragmentTags.get(backstackCount-1);
            String newTopFragment=mFragmentTags.get(backstackCount-2);
            setFragmentVisibal(newTopFragment);
            mFragmentTags.remove(topFragment);
          mExitCount=0;

        }
     else  if (backstackCount==1){
            mExitCount++;
            Toast.makeText(this,"1 more click to exit",Toast.LENGTH_SHORT).show();
        }
    if(mExitCount>=2){
            super.onBackPressed();
        }
    }
    private void setnavigationIcon(String tagname){
        Menu menu=bottomNavigationViewEx.getMenu();
        MenuItem menuItem=null;
        if (tagname.equals(getString(R.string.tag_fragment_home))){
            Log.d(TAG, "onNavigationItemSelected: home fragment setting");
            menuItem=menu.getItem(0);
            menuItem.setChecked(true);
        }
      else  if (tagname.equals(getString(R.string.tag_fragment_browse))){
            Log.d(TAG, "onNavigationItemSelected: browse fragment setting");
            menuItem=menu.getItem(1);
            menuItem.setChecked(true);
        }
      else  if (tagname.equals(getString(R.string.tag_fragment_search))){
            Log.d(TAG, "onNavigationItemSelected: search fragment setting");
            menuItem=menu.getItem(2);
            menuItem.setChecked(true);
        }
       else if (tagname.equals(getString(R.string.tag_fragment_library))){
            Log.d(TAG, "onNavigationItemSelected: home fragment setting");
            menuItem=menu.getItem(3);
            menuItem.setChecked(true);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_share:{
                break;

            }
            case R.id.nav_settings:{
                Log.d(TAG, "onNavigationItemSelected: profile selected");
                if (settingsFragment==null){
                    settingsFragment = new SettingsFragment();
                    FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_cotent_frame, settingsFragment,getString(R.string.tag_fragment_settings));
                    transaction.commit();
                    mFragmentTags.add(getString(R.string.tag_fragment_settings));
                    mFragment.add(new FragmentTags(settingsFragment,getString(R.string.tag_fragment_settings)));

                }
                else {

                    mFragmentTags.remove(getString(R.string.tag_fragment_settings));
                    mFragmentTags.add(getString(R.string.tag_fragment_settings));

                }
                setFragmentVisibal(getString(R.string.tag_fragment_settings));
                break;

            }
            case R.id.nav_profile:{

                if (profileFragment==null) {
                    Log.d(TAG, "onNavigationItemSelected: profile selected");
                    profileFragment = new ProfileFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_cotent_frame, profileFragment, getString(R.string.tag_fragment_proifle));
                    transaction.commit();
                    mFragmentTags.add(getString(R.string.tag_fragment_proifle));
                    mFragment.add(new FragmentTags(profileFragment,getString(R.string.tag_fragment_proifle)));

                } else {
                    mFragmentTags.remove(getString(R.string.tag_fragment_proifle));
                    mFragmentTags.add(getString(R.string.tag_fragment_proifle));
                }
                setFragmentVisibal(getString(R.string.tag_fragment_proifle));
                break;

            }
            case R.id.nav_about:{
                if (aboutFragment==null) {
                    Log.d(TAG, "onNavigationItemSelected: About selected");
                    aboutFragment = new AboutFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_cotent_frame, aboutFragment, getString(R.string.tag_fragment_about));
                    transaction.commit();
                    mFragmentTags.add(getString(R.string.tag_fragment_about));
                    mFragment.add(new FragmentTags(aboutFragment,getString(R.string.tag_fragment_about)));

                }
                else {
                    mFragmentTags.remove(getString(R.string.tag_fragment_about));
                    mFragmentTags.add(getString(R.string.tag_fragment_about));
                }
                setFragmentVisibal(getString(R.string.tag_fragment_about));
                break;

            }
            case R.id.bottom_nav_home:{
                Log.d(TAG, "onNavigationItemSelected: home");
                mFragmentTags.clear();
                mFragmentTags=new ArrayList<>();
                init();
                break;
            }
            case R.id.bottom_nav_browse:{
                Log.d(TAG, "onNavigationItemSelected: browse");
                if (browseFragment==null) {

                    browseFragment = new BrowseFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_cotent_frame, browseFragment, getString(R.string.tag_fragment_browse));
                    transaction.commit();
                    mFragmentTags.add(getString(R.string.tag_fragment_browse));
                    mFragment.add(new FragmentTags(browseFragment,getString(R.string.tag_fragment_browse)));


                }
                else {
                    mFragmentTags.remove(getString(R.string.tag_fragment_browse));
                    mFragmentTags.add(getString(R.string.tag_fragment_browse));
                }

                setFragmentVisibal(getString(R.string.tag_fragment_browse));
                break;
            }
            case R.id.bottom_nav_search:{
                Log.d(TAG, "onNavigationItemSelected:search");
                if (searchFragment==null) {
                    searchFragment = new SearchFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_cotent_frame, searchFragment, getString(R.string.tag_fragment_search));
                    transaction.commit();
                    mFragmentTags.add(getString(R.string.tag_fragment_search));
                    mFragment.add(new FragmentTags(searchFragment,getString(R.string.tag_fragment_search)));



                }
                else {
                    mFragmentTags.remove(getString(R.string.tag_fragment_search));
                    mFragmentTags.add(getString(R.string.tag_fragment_search));
                }
                setFragmentVisibal(getString(R.string.tag_fragment_search));
                break;
            }
            case R.id.bottom_library:{
                Log.d(TAG, "onNavigationItemSelected: livrary");
                if (libraryFragment==null) {

                    libraryFragment = new LibraryFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.main_cotent_frame, libraryFragment, getString(R.string.tag_fragment_library));
                    transaction.addToBackStack(getString(R.string.tag_fragment_library));
                    transaction.commit();
                    mFragmentTags.add(getString(R.string.tag_fragment_library));
                    mFragment.add(new FragmentTags(libraryFragment,getString(R.string.tag_fragment_library)));


                }
                else {
                    mFragmentTags.remove(getString(R.string.tag_fragment_library));
                    mFragmentTags.add(getString(R.string.tag_fragment_library));

                }
                setFragmentVisibal(getString(R.string.tag_fragment_library));
                break;
            }



        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
