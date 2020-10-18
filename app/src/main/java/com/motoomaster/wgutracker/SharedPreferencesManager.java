package com.motoomaster.wgutracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Set;

public class SharedPreferencesManager {
    private static Context context;
    private static SharedPreferencesManager sharedPreferencesManager = null;

    private SharedPreferencesManager(Context context) {
        this.context = context;
    }

    public static SharedPreferencesManager getInstance(Context context){
        if(sharedPreferencesManager==null){
            sharedPreferencesManager = new SharedPreferencesManager(context);
        }
        return sharedPreferencesManager;
    }

    private SharedPreferences getSharedPreference(){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    public void setStringVar(String key, String value){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String getStringVar(String key){
        return getSharedPreference().getString(key,"");
    }

    public void setIntVar(String key, int value){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putInt(key,value);
        editor.apply();
    }

    public int getIntVar(String key){
        return getSharedPreference().getInt(key,0);
    }


    public void setFloatVar(String key, float value){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putFloat(key,value);
        editor.apply();
    }

    public float getFloatVar(String key){
        return getSharedPreference().getFloat(key,0);
    }

    public void setStringSetVar(String key, Set<String> value){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putStringSet(key,value);
        editor.apply();
    }


    public void logout(){
        getSharedPreference().edit().clear().commit();
    }

}