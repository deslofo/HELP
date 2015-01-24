package com.example.klaudia.help;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Main extends ActionBarActivity {

    public void alarm(View view){
        Intent intent = new Intent(getApplicationContext(), Zadzwon.class);
        startActivity(intent);

    }

    public void SMS(View view){
        Intent intent = new Intent(getApplicationContext(), Bliscy.class);
        startActivity(intent);
    }
    public void Instrukcje(View view){
        Intent intent = new Intent(getApplicationContext(), Instrukcje.class);
        startActivity(intent);

    }
    public void Medyczne_info(View view){
        Intent intent = new Intent(getApplicationContext(), Medyczne_info.class);
        startActivity(intent);
    }

    public void Apteka(View view){
        Intent intent = new Intent(getApplicationContext(), Apteka.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.klaudia.help", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("firststart") == false){
            Intent intent = new Intent(getApplicationContext(), FirstStart.class);
            startActivity(intent);
            finish();
            return;
        }
        /*SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firststart", true);
        editor.commit();
        boolean def = false;
        def = sharedPreferences.getBoolean("firststart", def);
        if(def){
            Intent intent = new Intent(getApplicationContext(), FirstStart.class);
            startActivity(intent);
            finish();
            return;
        }*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
