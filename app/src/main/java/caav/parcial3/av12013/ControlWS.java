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
    public static List<Libro> obtenerLista(String json, Context ctx){
        //Modificar de acuerdo a la lista de datos que se quieren obtener.
        List<Libro> lista = new ArrayList<Libro>();
        try{
            JSONArray materiaJSON = new JSONArray(json);
            for(int i=0; i<materiaJSON.length(); i++){
                JSONObject object = materiaJSON.getJSONObject(i);
                //Dato segun lo solicitado.
               Libro libro = new Libro();
                //tienen que ir los nombres de las columnas en mayusculas.
                libro.setCODLIBRO(object.getString("CODLIBRO"));
                libro.setNOMLIBRO(object.getString("NOMLIBRO"));
                libro.setPRECIO(object.getLong("PRECIO"));
                lista.add(libro);
            }
            return lista;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en el parseo de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    //Para insertar en php.
    public static void insertarDato(String peticion, Context ctx){
        String json = obtenerPeticion(peticion, ctx);
        try{
            JSONObject resultado = new JSONObject(json);
            Toast.makeText(ctx, "Registro ingresado"+
                            //Verificar resultado en ws. A veces sale mayus o minus.
                            resultado.getJSONArray("resultado").toString(),
                    Toast.LENGTH_LONG).show();
            int respuesta = resultado.getInt("resultado");
            if(respuesta==1)
                Toast.makeText(ctx, "Registro ingresado", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Para actualizar en php.
    public static void actualizarDato(String peticion, Context ctx){
        String json = obtenerPeticion(peticion, ctx);
        try{
            JSONObject resultado = new JSONObject(json);
            Toast.makeText(ctx, "Registro actualizado"+
                    //Verificar resultado en ws. A veces sale mayus o minus.
                            resultado.getJSONArray("resultado").toString(),
                    Toast.LENGTH_LONG).show();
            int respuesta = resultado.getInt("resultado");
            if(respuesta==1)
                Toast.makeText(ctx, "Registro actualizado", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(ctx, "Error", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Para consultar en php.
    public static String consultarDato(String json, Context ctx){
        try{
            JSONArray objs = new JSONArray(json);
            if (objs.length() != 0){
                //Verificar resultado en ws. A veces sale mayus o minus.
                return objs.getJSONObject(0).getString("CANTIDAD");
            }else {
                Toast.makeText(ctx, "Error carnet no existe", Toast.LENGTH_LONG).show();
                return "";
            }
        }catch (JSONException e){
            Toast.makeText(ctx, "Error con la respuesta JSON", Toast.LENGTH_LONG).show();
            return "";
        }
    }

    //Para eliminar en php.
    public static void eliminarDato(String peticion, Context ctx){
        String json = obtenerPeticion(peticion, ctx);
        try{
            JSONObject resultado = new JSONObject(json);
            Toast.makeText(ctx, "Registro eliminado"+
                            //Verificar resultado en ws. A veces sale mayus o minus.
                            resultado.getJSONArray("resultado").toString(),
                    Toast.LENGTH_LONG).show();
            int respuesta = resultado.getInt("resultado");
            if(respuesta==1)
                Toast.makeText(ctx, "Registro eliminado", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(ctx, "Error", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Funciones especiales.
    //Obtener promedio.
    public static String obtenerPromedioJSON(String json, Context ctx){
        try{
            JSONArray objs = new JSONArray(json);
            if (objs.length() != 0){
                //Promedio.
                //Verificar resultado en ws. A veces sale mayus o minus.
                return objs.getJSONObject(0).getString("PROMEDIO");
            }else {
                Toast.makeText(ctx, "Error, no existe", Toast.LENGTH_LONG).show();
                return "";
            }
        }catch (JSONException e){
            Toast.makeText(ctx, "Error con la respuesta JSON", Toast.LENGTH_LONG).show();
            return "";
        }
    }

    //Para obtener el valor maximo.
    public static String obtenerMaxJSON(String json, Context ctx) {
        try {
            JSONArray objs = new JSONArray(json);
            if (objs.length() != 0) {
                //Maximo
                return objs.getJSONObject(0).getString("MAXIMO");
            }else{
                Toast.makeText(ctx, "Error, no existe", Toast.LENGTH_LONG).show();
                return "";
            }
        } catch (JSONException e) {
            Toast.makeText(ctx, "Error con la respuesta JSON", Toast.LENGTH_LONG).show();
            return "";
        }
    }

    //Para obtener el valor minimo.
    public static String obtenerMinJSON(String json, Context ctx){
        try{
            JSONArray objs = new JSONArray(json);
            if (objs.length() != 0){
                //Minimo
                return objs.getJSONObject(0).getString("MINIMO");
            }else {
                Toast.makeText(ctx, "Error, no existe", Toast.LENGTH_LONG).show();
                return "";
            }
        }catch (JSONException e){
            Toast.makeText(ctx, "Error con la respuesta JSON", Toast.LENGTH_LONG).show();
            return "";
        }
    }

    //Para realizar consulta del conteo.
    public static String obtenerCountJSON(String json, Context ctx){
        try{
            JSONArray objs = new JSONArray(json);
            if (objs.length() != 0){
                //Count.
                return objs.getJSONObject(0).getString("CONTEO");
            }else {
                Toast.makeText(ctx, "Error, no existe", Toast.LENGTH_LONG).show();
                return "";
            }
        }catch (JSONException e){
            Toast.makeText(ctx, "Error con la respuesta JSON", Toast.LENGTH_LONG).show();
            return "";
        }
    }

    //Para realizar consulta de sumatoria.
    public static String obtenerSumatoriaJSON(String json, Context ctx){
        try{
            JSONArray objs = new JSONArray(json);
            if (objs.length() != 0){
                //Sumatoria.
                return objs.getJSONObject(0).getString("SUMATORIA");
            }else {
                Toast.makeText(ctx, "Error, no existe", Toast.LENGTH_LONG).show();
                return "";
            }
        }catch (JSONException e){
            Toast.makeText(ctx, "Error con la respuesta JSON", Toast.LENGTH_LONG).show();
            return "";
        }
    }
}
