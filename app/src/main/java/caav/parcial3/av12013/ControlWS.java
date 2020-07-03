package caav.parcial3.av12013;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ControlWS {
    //Se utiliza para php.
    public static String obtenerPeticion(String url, Context context){
        String respuesta = "";

        //Estableciendo tiempo de espera del servicio.

        HttpParams parametros = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(parametros, 3000);
        HttpConnectionParams.setSoTimeout(parametros, 5000);

        //Creando objetos de conexion.
        HttpClient cliente = new DefaultHttpClient(parametros);
        HttpGet httpGet = new HttpGet(url);
        try{
            HttpResponse httpRespuesta = cliente.execute(httpGet);
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            if(codigoEstado==200){
                HttpEntity entidad = httpRespuesta.getEntity();
                respuesta = EntityUtils.toString(entidad);
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error en la  conexion", Toast.LENGTH_LONG).show();
            //Desplegando el error en el LogCat
            Log.v("Error de conexion", e.toString());
        }
        return respuesta;
    }

    //Para obtener lista de datos de php.
    public static List<Cuenta> obtenerLista(String json, Context ctx){
        //Modificar de acuerdo a la lista de datos que se quieren obtener.
        List<Cuenta> lista = new ArrayList<Cuenta>();
        try{
            JSONArray datoJSON = new JSONArray(json);
            for(int i=0; i<datoJSON.length(); i++){
                JSONObject object = datoJSON.getJSONObject(i);
                //Dato segun lo solicitado.
               Cuenta cuenta = new Cuenta();
                //tienen que ir los nombres de las columnas en mayusculas.
                cuenta.setCodcuenta(object.getString("CODCUENTA"));
                cuenta.setNomcuenta(object.getString("NOMCUENTA"));
                cuenta.setSaldoinicial(Float.parseFloat(object.getString("SALDOINIC")));
                cuenta.setSaldodebe(Float.parseFloat(object.getString("SALDODEBE")));
                cuenta.setSaldohaber(Float.parseFloat(object.getString("SALDOHABER")));
                lista.add(cuenta);
            }
            return lista;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en el parseo de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }


}
