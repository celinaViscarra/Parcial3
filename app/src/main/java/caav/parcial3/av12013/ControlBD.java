package caav.parcial3.av12013;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlBD {
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlBD(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "parcial3.s3bd";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                //INGRESAR SENTENCIA SQL PARA CADA TABLA.
                db.execSQL("");
                db.execSQL("");
                db.execSQL("");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    }

    public void abrir() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar() {
        DBHelper.close();
    }

    //Insertar de acuerdo a Libro.
    //INSERTAR
    public String insertar(Libro libro) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        ContentValues dato = new ContentValues();

        //Aca se obtienen los datos de acuerdo a la actualizacion que se hara.
        dato.put("CODLIBRO", libro.getCODLIBRO());
        dato.put("NOMLIBRO", libro.getNOMLIBRO());
        dato.put("PRECIO", libro.getPRECIO());

        //El siguiente string es la tabla de donde se obtendran los datos.
        //SE MODIFICARA EL DATO ENTRTE COMILLAS
        contador = db.insert("LIBRO", null, dato);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar insercion";
        } else {
            regInsertados = regInsertados + contador;
        } return regInsertados;
    }
}
