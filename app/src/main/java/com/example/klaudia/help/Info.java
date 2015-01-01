package com.example.klaudia.help;

import android.preference.PreferenceActivity;

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

    public static void daneUzupelnione(){
        dane = true;
    }

    public static boolean sprawdzCzyUzupelnicDane(){
        return dane;
    }
}
