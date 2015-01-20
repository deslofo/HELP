package com.example.klaudia.help;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Instrukcje extends ActionBarActivity {



    public void reanimacja (View view){
        Log.d("Instrukcje", "Weszlo w reanimacje");
        Intent intent = new Intent(getApplicationContext(), Reanimacja.class);
        startActivity(intent);

    }
    public void oparzenie(View view){
        Intent intent = new Intent(getApplicationContext(), Oparzenia.class);
        startActivity(intent);
    }
    public void omdlenie(View view){
        Intent intent = new Intent(getApplicationContext(), Omdlenia.class);
        startActivity(intent);
    }
    public void złamanie(View view){
        Intent intent = new Intent(getApplicationContext(), Zlamania.class);
        startActivity(intent);
    }

    public void skaleczenie(View view){
        Intent intent = new Intent(getApplicationContext(), Skaleczenia.class);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Instrukcje","onCreate");
        setContentView(R.layout.wybor_instrukcji);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d("Instrukcje"," przed onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.instrukcje, menu);
        Log.d("Instrukcje"," po onCreateOptionsMenu");
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
