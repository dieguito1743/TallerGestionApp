package com.buildingdreamcars.tallergestionapp.DatosTemp;


import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {
    private static String PREFS_KEY = "mypreferences";

    public static void setLoged (Context context, boolean login){
        SharedPreferences settings = context.getSharedPreferences(PREFS_KEY,context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor=settings.edit();
        editor.putBoolean("Login",login);
        editor.apply();
    }
    public static boolean getLoged (Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY,context.MODE_PRIVATE);
        return preferences.getBoolean("Login",false);
    }
    public static void setNickname(Context context, String nombre){
        SharedPreferences settings = context.getSharedPreferences(PREFS_KEY, context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString("Nickname","Usuario: "+nombre);
        editor.apply();
    }
    public static String getNickname (Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, context.MODE_PRIVATE);
        return preferences.getString("Nickname","No estas logueado");
    }
    public static void setMail(Context context, String mail){
        SharedPreferences settings = context.getSharedPreferences(PREFS_KEY, context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString("Mail",mail);
        editor.apply();

    }
    public static String getMail (Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, context.MODE_PRIVATE);
        return preferences.getString("Mail",null);
    }
    public static void setPass(Context context, String pass){
        SharedPreferences settings = context.getSharedPreferences(PREFS_KEY, context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString("Pass",pass);
        editor.apply();
    }
    public static String getPass (Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREFS_KEY, context.MODE_PRIVATE);
        return preferences.getString("Pass",null);
    }

}
