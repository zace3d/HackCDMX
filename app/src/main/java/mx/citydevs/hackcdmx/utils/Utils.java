package mx.citydevs.hackcdmx.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import mx.citydevs.hackcdmx.R;

/**
 * Created by mikesaurio on 08/01/16.
 */
public class Utils {


    public String getPreferences(Activity activity, String key,String textdefault){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(key, textdefault);
    }

    public  void setPreference(Activity activity, String key, String value){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
