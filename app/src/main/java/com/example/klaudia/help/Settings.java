package com.example.klaudia.help;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Settings extends ActionBarActivity {

    SharedPreferences sharedPreferences;
    EditText  grupa_krwi, chorby, leki;
    ListView listView;
    ArrayList<String> telefony = new ArrayList<String>();
    ArrayList<String> kontakty = new ArrayList<String>();
    String string1;
    String string2;
    TextView textView1;
    TextView textView2;
    boolean kont = true;

    public void dalej(View view){
        Log.d("ilosc", String.valueOf(listView.getCount()));
        if (kontakty.size() > 0) {
            SharedPreferences preferences = getSharedPreferences("com.example.klaudia.help", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("grupa_krwi", grupa_krwi.getText().toString());
            editor.putString("choroby_zakazne", chorby.getText().toString());
            editor.putString("aktualne_leki", leki.getText().toString());
            editor.putBoolean("firststart", false);
            int i = 0;
            for (String s : kontakty) {
                editor.putString(String.valueOf(i), s);
                //kontakty.add(i, s);
                i++;
            }
            editor.putInt("konakty", kontakty.size());
            Log.d("lista size", String.valueOf(kontakty.size()));
            editor.apply();
            editor.commit();
            Log.d("lista size", String.valueOf(preferences.getInt("konakty", 0)));
            Intent intent = new Intent(getApplicationContext(), Main.class);
            startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(), "musisz podac conajmniej jeden kontakt do bliskiej osoby", Toast.LENGTH_SHORT).show();
    }

    public void anuluj(View view){
        finish();
        return;
    }

    public void add_person(View view){
        showDialog();
        string1 = textView1.getText().toString();
        string2 = textView2.getText().toString();
        Log.d("nazwa", string1);
        Log.d("telefon", string2);
        listView.invalidateViews();
        Helper.getListViewSize(listView);
    }

    protected void showDialog(){

        LayoutInflater li = LayoutInflater.from(Settings.this);
        View propset = li.inflate(R.layout.newkontakt_dialog, null);
        final EditText nazwa = (EditText) propset.findViewById(R.id.edit_nazwa);
        final EditText telefon = (EditText) propset.findViewById(R.id.dialog_telefon);
        final AlertDialog.Builder alterdialog = new AlertDialog.Builder(Settings.this);
        alterdialog.setTitle("Dodaj kontakt");
        alterdialog.setView(propset);
        alterdialog.setNeutralButton("Dodaj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView1.setText(nazwa.getText());
                        textView2.setText(telefon.getText());
                        string1 = textView1.getText().toString();
                        string2 = textView2.getText().toString();
                        Log.d("nazwa", string1);
                        Log.d("telefon", string2);
                        if(string1.length() > 0 & string2.length() == 9) {
                            String s = string1 + ": " + string2;
                            string1 = string1 + ":\n" + string2;

                            if (kont == false) {
                                telefony.add(0, string1);
                                telefony.remove(1);
                                kontakty.add(0, s);
                                kont = true;
                                listView.invalidateViews();
                                Helper.getListViewSize(listView);
                            }
                            else {
                                kontakty.add(s);
                                telefony.add(string1);
                                listView.invalidateViews();
                                Helper.getListViewSize(listView);
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(), "telefon lub nazwa jest nie wlasciwa", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        alterdialog.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alterdialog.create();
        alertDialog.show();



    }

    protected void poprawKontakDialog(final int position){

        LayoutInflater li = LayoutInflater.from(Settings.this);
        View propset = li.inflate(R.layout.newkontakt_dialog, null);
        final EditText nazwa = (EditText) propset.findViewById(R.id.edit_nazwa);
        nazwa.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        final EditText telefon = (EditText) propset.findViewById(R.id.dialog_telefon);
        String s = telefony.get(position);
        String t[] = s.split(":");
        nazwa.setText(t[0]);
        t[1].replace(" ", "");
        s = t[1];
        s.replace(" ", "");
        s = s.substring(1, s.length());
        telefon.setText(s);
        final TextView textView1 = (TextView) findViewById(R.id.firtstart_pom1);
        final TextView textView2 = (TextView) findViewById(R.id.firtstart_pom2);
        final AlertDialog.Builder alterdialog = new AlertDialog.Builder(Settings.this);
        alterdialog.setTitle("Dodaj kontakt");
        alterdialog.setView(propset);
        alterdialog.setNeutralButton("Usun", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (kontakty.size() > 1){
                    kontakty.remove(position);
                    telefony.remove(position);
                    listView.invalidateViews();
                    Helper.getListViewSize(listView);
                }
                else
                    Toast.makeText(getApplicationContext(), "Nie można usunać kontaktu. Musi być conajmniej jeden kontakt", Toast.LENGTH_SHORT).show();
            }
        });
        alterdialog.setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView1.setText(nazwa.getText());
                        textView2.setText(telefon.getText());
                        string1 = textView1.getText().toString();
                        string2 = textView2.getText().toString();
                        Log.d("nazwa", string1);
                        Log.d("telefon", string2);
                        if(string1.length() > 0 & string2.length() == 9) {
                            String s = string1 + ": " + string2;
                            string1 = string1 + ":\n" + string2;
                            telefony.add(position, string1);
                            telefony.remove(position + 1);
                            kontakty.add(position, s);
                            kontakty.remove(position+1);
                            listView.invalidateViews();
                            Helper.getListViewSize(listView);

                        }
                        else
                            Toast.makeText(getApplicationContext(), "telefon lub nazwa jest nie wlasciwa", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        alterdialog.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alterdialog.create();
        alertDialog.show();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        textView1 = (TextView) findViewById(R.id.firtstart_pom1);
        textView2 = (TextView) findViewById(R.id.firtstart_pom2);
        sharedPreferences = getSharedPreferences("com.example.klaudia.help", Context.MODE_PRIVATE);
        grupa_krwi = (EditText) findViewById(R.id.setings_grupa_krwi);
        chorby = (EditText) findViewById(R.id.setings_choroby_zakazne);
        leki = (EditText) findViewById(R.id.setings_leki);
        grupa_krwi.setText(sharedPreferences.getString("grupa_krwi", null));
        String krew = sharedPreferences.getString("grupa_krwi", null);
        grupa_krwi.setText(krew);
        Log.d("krew", sharedPreferences.getString("grupa_krwi", null));
        chorby.setText(sharedPreferences.getString("choroby_zakazne", null));
        leki.setText(sharedPreferences.getString("aktualne_leki", null));
        listView = (ListView) findViewById(R.id.setings_kontakty);

        int size = 0;
        size = sharedPreferences.getInt("konakty", 0);
        Log.d("size", String.valueOf(size));
        //telefony = new String[size];

        for(int i = 0; i < size; i++) {

            telefony.add(sharedPreferences.getString(String.valueOf(i), null));
            String s = telefony.get(i);
            kontakty.add(s);
            char ts[] = s.toCharArray();

            char tsn[] = new char[s.length()+1];
            for (int j = 0; j < s.length(); j++){
                tsn[j] = ts[j];
                if (ts[j] == ':'){
                    //char tsn[] = new char[s.length()+1];
                    tsn[j+1] = '\n';
                    j++;
                }
            }
            tsn[s.length()] = ts[s.length()-1];
            s = String.valueOf(tsn);
            telefony.remove(i);
            telefony.add(s);
            //i++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.firststart_telefony, R.id.nazwa, telefony);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                poprawKontakDialog(position);
            }
        });
        listView.setAdapter(adapter);
        Helper.getListViewSize(listView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
