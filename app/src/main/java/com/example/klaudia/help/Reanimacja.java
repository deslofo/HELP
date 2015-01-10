package com.example.klaudia.help;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class Reanimacja extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reanimacja);

        WebView webView = (WebView) findViewById(R.id.info_reanimacja);
        String text = "<html>" +
                "<meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />" +
                "<body style=\"text-align:justify;color:black;;\"> <ol>\n" +
                "<li>Oceń stan świadomości</li>" +
                "<ol type=\"a\"><li>Delikatnie potrząśnij i głośno zapytaj czy wszystko jest w porządku</li></ol>" +
                "<li>Jeżeli nie reaguje głośno zawołaj o pomoc</li>" +
                "<li>Udrożnij drogi oddechowe</li>" +
                "<ol type=\"a\"><li>Odwróć poszkodowanego na plecy.</li><li>Umieść jedną rękę na czole poszkodowanego, opuszki palców drugiej ręki umieść na żuchwie poszkodowanego, a następnie unieś ją w celu udrożnienia dróg oddechowych.</li></ol>" +
                "<li>Oceń czy oddycha (wykorzystaj zmysł słuchu, dotyku i wzroku)</li>" +
                "<ol type=\"a\"><li>Oceń wzrokiem ruchy klatki piersiowej, nasłuchuj przy ustach poszkodowanego szmerów oddechowych, staraj się wyczuć ruch powietrza na swoim policzku.</li><li>W przypadku jakichkolwiek wątpliwości dotyczących prawidłowego oddechu działaj tak, jakby był nieprawidłowy.</li></ol>" +
                "<li>Jeżeli oddech jest prawidłowy:</li>" +
                "<ol type=\"a\"><li>Ułóż poszkodowanego w pozycji bezpiecznej.</li><li>Wyślij kogoś lub sam udaj się po pomoc (wezwij pogotowie).</li><li>Regularnie oceniaj oddech.</li></ol>" +
                "<li>Jeżeli oddech nie jest prawidłowy:</li>" +
                "<ol type=\"a\"><li>wyślij kogoś po pomoc, a jeżeli jesteś sam, zostaw poszkodowanego i wezwij pogotowie, wróć i rozpocznij uciskanie klatki piersiowej zgodnie z poniższym opisem.</li><li>Połóż ręce na środku klatki piersiowej</li><li>Wykonaj 30 uciśnięć klatki piersiowej: Uciskaj mocno na głębokość 5-6 cm i z częstością 100-120/min</li><li>Przyłóż szczelnie usta wokół ust poszkodowanego i wykonaj 2 oddechy</li><li>Kontynuuj resuscytację</li></ol>" +
                "<li>Jeżeli w pobliżu znajduje się automatyczny defibrylator zewnętrzny AED włącz go i rozpocznij defibrylację zgodnie z poleceniami głosowymi urządzenia</li>" +
                "<li>Jeżeli poszkodowany zaczyna reagować: porusza się, otwiera oczy, oddycha prawidłowo, przerwij resuscytację\"</li>" +
                "</ol></body></Html>";

        webView.loadData(text, "text/html; charset=utf-8", "utf-8");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.reanimacja, menu);
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
