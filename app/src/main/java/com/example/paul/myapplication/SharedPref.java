package com.example.paul.myapplication;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by Paul on 30-05-2017.
 */
public class SharedPref
{   public static void setName(Context context,String name)

    {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("username",name).commit();
    }

    public static String getName(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("username","");

    }

    public static void setPass(Context context,String name)

    {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("password",name).commit();
    }

    public static String getPass(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("password","");

    }

    public static void setEmail(Context context,String name)

    {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("email",name).commit();
    }

    public static String getEmail(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("email","");

    }
    public static void setAge(Context context,String name)

    {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("age",name).commit();
    }

    public static String getAge(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("age","");

    }
    public static void setAddress(Context context,String name)

    {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("address",name).commit();
    }

    public static String getAddress(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("address","");

    }
    public static void setFlag(Context context,String name)

    {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("flag",name).commit();
    }

    public static String getFlag(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("flag","");

    }

    public static void setPicture(Context context,String name)

    {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("picture",name).commit();
    }

    public static String getPicture(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("picture","");

    }

    public static void setSection(Context context,String name)

    {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString("section",name).commit();
    }

    public static String getSection(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("section","");

    }
}
