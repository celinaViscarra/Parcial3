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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ActualizarGrupoActivity extends AppCompatActivity {
    ControlBD db;
    //Agregar dato.
    static List<Cuenta> lista; //Lista de producto a actualizar.
    static List<String> nombres; //Lista con el primer atributo del producto.
    EditText fechaTxt,editText_numcuenta,editText_corr;
    ListView listaView; //Lista que se desplegara con los datos, a partir de la fecha indicada.

    //URL.
    private final String urlLocal = "http://192.168.1.8/av12013/ws_cuentas_fecha_av12013.php?day=%s&month=%s&year=%s";
    private final String urlLocalUpdate = "http://192.168.1.8/av12013/ws_partida_update_av12013.php?concepto=%s&numpartida=%s&correlativo=%s";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_grupo);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        db = new ControlBD(this);
        lista = new ArrayList<Cuenta>(); //Modificar dato.
        nombres = new ArrayList<String>();
        fechaTxt = (EditText) findViewById(R.id.editText_Fecha);
        editText_numcuenta = (EditText) findViewById(R.id.editText_numcuenta);
        editText_corr = (EditText) findViewById(R.id.editText_corr);
        listaView = (ListView) findViewById(R.id.listView1);
    }

    //TODO: Hacer la actualizacion
    public void updatePartida(View v){
        String mensaje = "";
        String numeroPartida = editText_numcuenta.getText().toString();
        String correlativo = editText_corr.getText().toString();
        try{
            if(!(numeroPartida.isEmpty() || correlativo.isEmpty())){
                db.abrir();
                Partida consulta = db.consultar(numeroPartida, correlativo);
                db.cerrar();
                if(consulta != null){
                    String url = String.format(urlLocalUpdate,"Nuevo",numeroPartida,correlativo);
                    String respuesta = ControlWS.obtenerPeticion(url, this);
                    Log.v("Respuesta", respuesta);
                    if(!respuesta.isEmpty()){
                        JSONObject resp = new JSONObject(respuesta);
                        mensaje = (resp.getInt("resultado") == 1) ? "Actualizado con exito" : "No se actualizo";
                    }
                }else{
                    mensaje = "El numero de la partida y el correlativo no corresponden a una partida valida para actualizar.";
                }
            }else{
                mensaje = "Los datos no pueden ir vacios!";
            }
        }catch (JSONException e){
            mensaje = "Error en el parseo JSON";
        }finally{
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        }
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
            lista = (ArrayList<Cuenta>) ControlWS.obtenerLista(respuesta, this);
            ArrayList<String> nombre = new ArrayList<>();
            //Modificar dato.
            for (Cuenta pivote : lista)
                nombre.add(String.format("%s - %s", pivote.getCodcuenta(), pivote.getNomcuenta()));
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
        for (Cuenta pivote : lista)
            nombres.add(String.format("%s - %s", pivote.getCodcuenta(), pivote.getNomcuenta()));
        eliminarDuplicados();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nombres);
        listaView.setAdapter(adapter);
    }

    private void eliminarDuplicados() {
        //c a m b i a r  d a t o.
        HashSet<Cuenta> conjunto = new HashSet<>();
        conjunto.addAll(lista);
        lista.clear();
        lista.addAll(conjunto);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombres);
        nombres.clear();
        nombres.addAll(conjuntoNombre);
    }
}