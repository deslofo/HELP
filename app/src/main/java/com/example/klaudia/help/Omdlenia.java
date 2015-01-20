package com.example.klaudia.help;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class Omdlenia extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omdlenia);

        WebView webView = (WebView) findViewById(R.id.info_omdlenia);
        String text = "<html>" +
                "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />" +
                "<body style=\"text-align:justify;color:black;;\"> <ol>\n" +
                "<li>wyniesienie poszkodowanego z miejsca, w którym omdlał, a gdy jest to niemożliwe zapewnienie dostępu świeżego powietrza,</li>" +
                "<li>ułożenie na plecach,</li>" +
                "<li>ocena funkcji życiowych, czyli udrożnienie dróg oddechowych poprzez odgięcie głowy do tyłu a następnie ocena oddechu przez 10 sekund (w tym czasie powinniśmy usłyszeć co najmniej dwa oddechy),</li>" +
                "<li>zastosowanie pozycji czterokończynowej, tj. jednoczesne uniesienie kończyn górnych i dolnych,</li>" +
                "<li>jeśli po kilku minutach świadomość nie powraca – wezwanie pogotowia.</li>" +
                "</ol></body></Html>";

        webView.loadData(text, "text/html; charset=utf-8", "utf-8");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.omdlenia, menu);
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
