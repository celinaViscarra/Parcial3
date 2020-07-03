package caav.parcial3.av12013;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PromedioActivity extends AppCompatActivity {
    EditText idAutorTxt;
    TextView promedioTxt;
    //URL.
    private final String urlLocal = "http://192.168.1.8/av12013/ws_promedio.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promedio);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        idAutorTxt = (EditText) findViewById(R.id.editText_autor);
        promedioTxt = (TextView) findViewById(R.id.textView_promedio);
    }

    public void consultarPromedio(View v) {
        String id = idAutorTxt.getText().toString();
        String url = urlLocal + "?idautor=" + id;
        String conteo = ControlWS.obtenerPeticion(url, this);
        Log.v("Respuesta", conteo);
        promedioTxt.setText("Promedio: " + ControlWS.obtenerPromedioJSON(conteo, this));
    }
}
