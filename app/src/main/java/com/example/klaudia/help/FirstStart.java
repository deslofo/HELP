package com.example.klaudia.help;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class FirstStart extends ActionBarActivity {

    EditText krew;
    EditText choroby;
    EditText lek;
    ArrayList<String> lista_kontaktow = new ArrayList<String>();
    ArrayList<String> kontakty = new ArrayList<String>();
    ListView listView;
    ArrayAdapter<String> adapter;
    TextView textView1;
    TextView textView2;
    String string1;
    String string2;
    SharedPreferences sharedPreferences;
    boolean kont = false;

    public FirstStart() {
    }

    public void dalej(View view){
        //adapter.notifyDataSetChanged();
        Log.d("ilosc", String.valueOf(listView.getCount()));
        //adapter.notifyDataSetChanged();

        ListAdapter myListAdapter = listView.getAdapter();
        /*for (int size = 0; size < listView.getCount(); size++) {
            View listItem = adapter.getView(size, null, listView);

            EditText t = (EditText) listItem.findViewById(R.id.nazwa);
            //t.isEnabled();
            String s = t.getText().toString();
            Log.d("id", s);
            //listItem.measure(0, 0);
            //totalHeight += listItem.getMeasuredHeight();
            //View ww = l
            listView.g
            ClipData.Item ww = (ClipData.Item) listView.getItemAtPosition(size);
            EditText tt = (EditText) w;
            String ss = tt.getText().toString();
            Log.d("id", ss);
        }*/


        if (kontakty.size() > 0) {
            sharedPreferences = getSharedPreferences("com.example.klaudia.help", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("grupa_krwi", krew.getText().toString());
            editor.putString("choroby_zakazne", choroby.getText().toString());
            editor.putString("aktualne_leki", lek.getText().toString());
            editor.putBoolean("firststart", false);
            int i = 0;
            for (String s : kontakty) {
                editor.putString(String.valueOf(i), s);
                //kontakty.add(i, s);
                i++;
            }
            editor.putInt("konakty", lista_kontaktow.size());
            Log.d("lista size", String.valueOf(kontakty.size()));
            editor.apply();
            editor.commit();
            Log.d("lista size", String.valueOf(sharedPreferences.getInt("konakty", 0)));
            Intent intent = new Intent(getApplicationContext(), Bliscy.class);
            startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(), "musisz podac conajmniej jeden kontakt do bliskiej osoby", Toast.LENGTH_SHORT).show();
    }

    public void anuluj(View view){
        System.exit(0);
    }

    public void add_person(View view){

        showDialog();

        //kontakty.add("www");
        //adapter = null;
        string1 = textView1.getText().toString();
        string2 = textView2.getText().toString();
        Log.d("nazwa", string1);
        Log.d("telefon", string2);
        listView.invalidateViews();
        Helper.getListViewSize(listView);
        /*adapter.add("wf");
        adapter.notifyDataSetChanged();
        //adapter = new ArrayAdapter<String>(this, R.layout.firststart_telefony, R.id.nazwa, kontakty);
        listView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

        Helper.getListViewSize(listView);
*/
    }

    protected void showDialog(){

        LayoutInflater li = LayoutInflater.from(FirstStart.this);
        View propset = li.inflate(R.layout.newkontakt_dialog, null);
        final EditText nazwa = (EditText) propset.findViewById(R.id.edit_nazwa);
        final EditText telefon = (EditText) propset.findViewById(R.id.dialog_telefon);
        final AlertDialog.Builder alterdialog = new AlertDialog.Builder(FirstStart.this);
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
                                lista_kontaktow.add(0, string1);
                                lista_kontaktow.remove(1);
                                kontakty.add(s);
                                kont = true;
                                listView.invalidateViews();
                                Helper.getListViewSize(listView);
                            }
                            else {
                                kontakty.add(s);
                                lista_kontaktow.add(string1);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start);

        //sharedPreferences = getSharedPreferences("com.example.klaudia.help", Context.MODE_PRIVATE);
        textView1 = (TextView) findViewById(R.id.firtstart_pom1);
        textView2 = (TextView) findViewById(R.id.firtstart_pom2);
        krew = (EditText) findViewById(R.id.grupa_krwi);
        choroby =(EditText) findViewById(R.id.choroby_zakazne);
        lek = (EditText) findViewById(R.id.leki);
        listView = (ListView) findViewById(R.id.first_kontakty);
        lista_kontaktow.add("Brak kontaktów, dodaj kontakt");
        String s = "";
        adapter = new ArrayAdapter<String>(this, R.layout.firststart_telefony, R.id.nazwa, lista_kontaktow);
        listView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first_start, menu);
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
