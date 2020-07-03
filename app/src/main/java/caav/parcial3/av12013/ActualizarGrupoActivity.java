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

public class ActualizarGrupoActivity extends AppCompatActivity {
    ControlBD db;
    //Agregar dato.
    static List<Libro> lista; //Lista de producto a actualizar.
    static List<String> nombres; //Lista con el primer atributo del producto.
    EditText fechaTxt;
    ListView listaView; //Lista que se desplegara con los datos, a partir de la fecha indicada.

    //URL.
    private final String urlLocal = "http://192.168.1.8/av12013/ws_tabla2_fecha.php?day=%s&month=%s&year=%s";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_grupo);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        db = new ControlBD(this);
        lista = new ArrayList<Libro>(); //Modificar dato.
        nombres = new ArrayList<String>();
        fechaTxt = (EditText) findViewById(R.id.editText_Fecha);
        listaView = (ListView) findViewById(R.id.listView1);
    }

    public void servicioPHP(View v) {
        try {
            //Formato dd-mm-yyyy
            String[] fecha = fechaTxt.getText().toString().split("/");
            String url = String.format(urlLocal, fecha[0], fecha[1], fecha[2]);
            Log.v("Url enviada", url);
            String respuesta = ControlWS.obtenerPeticion(url, this);
            Log.v("Respuesta", respuesta);
            //Modificar dato.
            lista = (ArrayList<Libro>) ControlWS.obtenerLista(respuesta, this);
            ArrayList<String> nombre = new ArrayList<>();
            //Modificar dato.
            for (Libro pivote : lista)
                nombre.add(String.format("%s - %s", pivote.getCODLIBRO(), pivote.getNOMLIBRO()));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nombre);
            listaView.setAdapter(adapter);
        } catch (ArrayIndexOutOfBoundsException e) {
            Toast.makeText(this, "No ha ingresado una fecha valida", Toast.LENGTH_LONG).show();
        }
    }

    public void guardar(View v) {
        db.abrir();
        for (int i = 0; i < lista.size(); i++) {
            Log.v("Guardar", db.insertar(lista.get(i)));
        }
        db.cerrar();
        Toast.makeText(this, "Guardado con exito", Toast.LENGTH_SHORT).show();
        lista.removeAll(lista);
        actualizarListView();
    }

    private void actualizarListView() {
        String dato = "";
        nombres.clear();
        for (int i = 0; i < lista.size(); i++) {
            dato = lista.get(i).getCODLIBRO() + "" + lista.get(i).getNOMLIBRO();
            nombres.add(dato);
        }
        eliminarDuplicados();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nombres);
        listaView.setAdapter(adapter);
    }

    private void eliminarDuplicados() {
        //c a m b i a r  d a t o.
        HashSet<Libro> conjunto = new HashSet<Libro>();
        conjunto.addAll(lista);
        lista.clear();
        lista.addAll(conjunto);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombres);
        nombres.clear();
        nombres.addAll(conjuntoNombre);
    }
}