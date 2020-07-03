package caav.parcial3.av12013;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

public class IngresarActivity extends AppCompatActivity {
    EditText idAutorTxt, codLibroTxt, cantidadTxt;
    //URL.
    private final String urlLocal ="http://192.168.1.8/av12013/ws_dato_insert.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        idAutorTxt = (EditText) findViewById(R.id.editText_autor);
        codLibroTxt = (EditText) findViewById(R.id.editText_libro);
        cantidadTxt = (EditText) findViewById(R.id.editText_cantidad);
    }

    public void insertar(View v){
        String idautor = idAutorTxt.getText().toString();
        String codLibro = codLibroTxt.getText().toString();
        String cantidad = cantidadTxt.getText().toString();
        String url = "";
        url = urlLocal + "?idautor=" + idautor + "&codlibro=" + codLibro + "&cantidad=" + cantidad;
        ControlWS.insertarDato(url, this);
    }
}
