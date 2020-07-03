package caav.parcial3.av12013;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ActualizarActivity extends AppCompatActivity {
    EditText idAutorTxt, codLibroTxt, cantidadTxt;
    //URL.
    private final String urlLocal ="http://192.168.1.8/av12013/ws_dato_update.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        idAutorTxt = (EditText) findViewById(R.id.editText_autor);
        codLibroTxt = (EditText) findViewById(R.id.editText_codigo);
        cantidadTxt = (EditText) findViewById(R.id.editText_cantidad);
    }

    public void actualizar(View v){
        String idautor = idAutorTxt.getText().toString();
        String codLibro = codLibroTxt.getText().toString();
        String cantidad = cantidadTxt.getText().toString();
        String url = "";
        url = urlLocal + "?idautor=" + idautor + "&codlibro=" + codLibro + "&cantidad=" + cantidad;
        ControlWS.actualizarDato(url, this);
    }
}
