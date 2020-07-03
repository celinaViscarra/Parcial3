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
                    }
        if (i != null)
            startActivity(i);
    }
}
