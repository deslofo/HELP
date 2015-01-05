package com.example.klaudia.help;

import android.content.Context;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Info extends PreferenceActivity {
    private static ArrayList<String> telefony = new ArrayList<String>();
    private static boolean dane = false;

    public static void dodajTelefon(String s){
        telefony.add(s);
    }

    public static ArrayList listaKontaktow(){
        return telefony;
    }

    public static void daneUzupelnione(Context context){
        //PreferenceManager.getDefaultSharedPreferences(context)
        dane = true;
    }

    public static boolean sprawdzCzyUzupelnicDane(){
        return dane;
    }
}
