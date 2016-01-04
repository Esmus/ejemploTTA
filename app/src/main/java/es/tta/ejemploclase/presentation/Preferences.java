package es.tta.ejemploclase.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


import java.util.AbstractCollection;

/**
 * Created by Miren on 30/12/2015.
 */
public abstract class Preferences {

    private SharedPreferences prefs;
    public final static String PREFERENCIAS="es.tta.ejemplo_tta.presentation.preflogin";

    public Preferences(Context context){


        prefs=context.getSharedPreferences(PREFERENCIAS, context.MODE_PRIVATE);


    }

    public void saveLogin(String login){

        SharedPreferences.Editor editor;
        editor = prefs.edit();
        editor.putString(PREFERENCIAS,login);
        editor.commit();
    }

    public  String loadLogin() {


        return prefs.getString(PREFERENCIAS, null);


    }



}
