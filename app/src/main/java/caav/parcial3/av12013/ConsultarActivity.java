package caav.parcial3.av12013;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ConsultarActivity extends AppCompatActivity {
    EditText idAutorTxt, codLibroTxt;
    TextView cantidadTxt;
    //URL.
    private final String urlLocal ="http://192.168.1.8/av12013/ws_dato_query.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        idAutorTxt = (EditText) findViewById(R.id.editText_autor);
        codLibroTxt = (EditText) findViewById(R.id.editText_libro);
        cantidadTxt = (TextView) findViewById(R.id.editText_cantidad);
    }

    public void consultar(View v){
        String idautor = idAutorTxt.getText().toString();
        String codLibro = codLibroTxt.getText().toString();
        String url = "";
        url = urlLocal + "?idautor=" + idautor + "&codlibro=" + codLibro;
        String cantidad = ControlWS.obtenerPeticion(url, this);
        Log.v("Respuesta", cantidad);
        cantidadTxt.setText("Cantidad: " + ControlWS.consultarDato(cantidad, this));
    }
}