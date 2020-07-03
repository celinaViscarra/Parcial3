package caav.parcial3.av12013;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void lanzarActividad(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.button_grupo:
                i = new Intent(this, ActualizarGrupoActivity.class);
                break;
            case R.id.button_avg:
                i = new Intent(this, PromedioActivity.class);
                break;
            case R.id.button_max:
                i = new Intent(this, MaximoActivity.class);
                break;
            case R.id.button_min:
                i = new Intent(this, MinimoActivity.class);
                break;
            case R.id.button_sum:
                i = new Intent(this, SumatoriaActivity.class);
                break;
            case R.id.button_count:
                i = new Intent(this, CountActivity.class);
                break;
            case R.id.button_ingresar:
                i = new Intent(this, IngresarActivity.class);
                break;
            case R.id.button_actualizar:
                i = new Intent(this, ActualizarActivity.class);
                break;
            case R.id.button_consultar:
                i = new Intent(this, ConsultarActivity.class);
                break;
            case R.id.button_eliminar:
                i = new Intent(this, EliminarActivity.class);
                break;
        }
        if (i != null)
            startActivity(i);
    }
}
