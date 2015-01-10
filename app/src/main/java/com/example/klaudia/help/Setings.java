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


public class Setings extends ActionBarActivity {

    SharedPreferences sharedPreferences;
    EditText  grupa_krwi, chorby, leki;
    ListView listView;
    ArrayList<String> telefony = new ArrayList<String>();
    String string1;
    String string2;

    public void dalej(View view){

    }

    public void anuluj(View view){
        finish();
        return;
    }

    public void add_person(View view){

    }

    protected void poprawKontakDialog(final int position){

        LayoutInflater li = LayoutInflater.from(Setings.this);
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
        final AlertDialog.Builder alterdialog = new AlertDialog.Builder(Setings.this);
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
                            telefony.add(position, s);
                            telefony.remove(position + 1);
                            //lista_kontaktow.add(position, string1);
                            //lista_kontaktow.remove(position+1);
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
        setContentView(R.layout.activity_setings);

        sharedPreferences = getSharedPreferences("com.example.klaudia.help", Context.MODE_PRIVATE);
        grupa_krwi = (EditText) findViewById(R.id.setings_choroby_zakazne);
        chorby = (EditText) findViewById(R.id.setings_choroby_zakazne);
        leki = (EditText) findViewById(R.id.setings_leki);
        grupa_krwi.setText(sharedPreferences.getString("grupa_krwi", null));
        chorby.setText(sharedPreferences.getString("choroby_zakazne", null));
        leki.setText(sharedPreferences.getString("aktualne_leki", null));
        listView = (ListView) findViewById(R.id.setings_kontakty);

        int size = 0;
        size = sharedPreferences.getInt("konakty", 0);
        Log.d("size", String.valueOf(size));
        //telefony = new String[size];

        for(int i = 0; i < size; i++) {

            telefony.add(sharedPreferences.getString(String.valueOf(i), null));
            //i++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.firststart_telefony, R.id.nazwa, telefony);
        listView.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setings, menu);
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
