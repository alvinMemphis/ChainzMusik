package com.example.alvin.chainzmusic;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareprefManager {
    private static ShareprefManager myManager=null;

    private static Context cntx;
    private static final String SHARED_PREF_NAME="mysharedpref12";
    private static final String KEY_USERNAME="username";
    private static final String KEY_USER_EMAIL="email";
    private static final String KEY_USER_ID="id";
    ShareprefManager(Context context){
        cntx=context;
    }
    public static synchronized ShareprefManager getInstance(Context context){
        if (myManager==null){
            myManager=new ShareprefManager(context);

        }
        return myManager;
    }
    public boolean userLogin(int id,String username,String email){
        SharedPreferences sharedpref=cntx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedpref.edit();
        editor.putInt(KEY_USER_ID,id);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_USER_EMAIL,email);
        editor.apply();
        return true;
    }
    public boolean isLoggedIn(){
        SharedPreferences sharedpref=cntx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if (sharedpref.getString(KEY_USERNAME,null)!=null)
            return true;
        return false;
    }


    public boolean logOut() {
        SharedPreferences sharedpref=cntx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedpref.edit();
        editor.clear();
        editor.apply();
        return true;

    }
    public String getUsername(){
        SharedPreferences sharedpref=cntx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedpref.getString(KEY_USERNAME,null);
    }
    public String getUserEmail(){
        SharedPreferences sharedpref=cntx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedpref.getString(KEY_USER_EMAIL,null);
    }



}
