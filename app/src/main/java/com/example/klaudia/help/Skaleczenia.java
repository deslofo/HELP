package com.example.klaudia.help;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class Skaleczenia extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skaleczenia);

        WebView webView = (WebView) findViewById(R.id.info_skaleczenia);
        String text = "<html>" +
                "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />" +
                "<body style=\"text-align:justify;color:black;;\"> <ol>\n" +
                "<li>Najpierw oczyść ranę jałowym gazikiem. W razie potrzeby usuń ciała obce (drobiny ziemi, szkła etc.). Jeśli nie mamy pod ręką nic do dezynfekcji, wystarczy umyć ranę wodą z mydłem.</li>" +
                "<li>Miejsce zranienia możesz zabezpieczyć gazą i bandażem.</li>" +
                "<li>W przypadku rozległych i głębokich ran, które mogą zagrażać życiu, natychmiast wezwij pogotowie.</li>" +
                "<li>Do czasu przyjazdu lekarza, spróbuj powstrzymać krwawienie. W przypadku rany na ręce lub nodze nakładamy opatrunek uciskowy, gazę, na nią wkład uciskowy, np. rolkę bandaża, i mocno zawijamy, żeby zacisnąć uszkodzone naczynie. Ran na głowie i tułowiu nie należy uciskać – kładziemy na nie tylko gazę, a następnie bandażujemy.</li>" +
                "<li>Jeśli krwawienie nie ustaje, nie zmieniaj bandaża, lecz załóż kolejny. Unieś wyżej skaleczoną rękę (nogę). Przy intensywnych krwawieniach możesz ucisnąć ręką większe naczynie krwionośne powyżej miejsca zranienia.</li>" +
                "</ol></body></Html>";

        webView.loadData(text, "text/html; charset=utf-8", "utf-8");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.skaleczenia, menu);
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
