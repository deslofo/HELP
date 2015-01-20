package com.example.klaudia.help;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Bliscy extends ActionBarActivity {

    String telefony[];
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bliscy);

        /*Info.dodajTelefon("Sylwia: 881204283");
        Info.dodajTelefon("Magda: 796558784");
        Info.dodajTelefon("Wojtek: 608090497");
*/
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.klaudia.help", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        ArrayList<String> kontakty;
        //String s = sharedPreferences.getString("konakty", null);
        //kontakty = Arrays.asList(TextUtils.split(s, "?newKontakt?"));
        int size = 0;
        size = sharedPreferences.getInt("konakty", 0);
        Log.d("size", String.valueOf(size));
        telefony = new String[size];

        for(int i = 0; i < size; i++) {

            telefony[i] = sharedPreferences.getString(String.valueOf(i), null);
            //i++;
        }
        //String[] elementy = {"Element 1", "Element 2", "Element 3", "Element 4", "Element 5", "Element 6", "Element 7",
          //      "Element 8", "Element 9", "Element 10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.bliscy_kontakty, R.id.kontakt, telefony);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String telefon = telefony[position];
                telefon.replace(" ", "");
                String s[] = telefon.split(":");
                Toast.makeText(getApplicationContext(), s[1] + (position +1), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SMS.class);
                intent.putExtra("telefon", s[1]);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bliscy, menu);
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
            Intent intent = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
