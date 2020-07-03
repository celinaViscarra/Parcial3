package caav.parcial3.av12013;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        private static final String BASE_DATOS = "parcial3_av12013.s3bd";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                //Creacion de las tablas
                db.execSQL("create table CUENTA(CODCUENTA char(11) not null,NOMCUENTA  varchar(30) not null, SALDOINIC float not null, SALDODEBE float not null, \n" +
                        "SALDOHABER float not null, primary key (CODCUENTA));");
                db.execSQL("create table PARTIDA(NUMPARTIDA char(5),CORR char(3),CODCUENTA char(11),CONCEPTO char(20),CAR_ABO char(1),VALOR float not null, primary key (NUMPARTIDA,CORR));");
                //Insercion de datos iniciales.
                //Cuenta
                db.execSQL("INSERT INTO CUENTA VALUES ('1101001', 'Banco Agricola  cta 111',0,0,0);");
                db.execSQL("INSERT INTO CUENTA VALUES ('1101002', 'Banco Cuscatlan cta 222',0,0,0);");
                db.execSQL("INSERT INTO CUENTA VALUES ('1101003', 'Banco Azul      cta 333',0,0,0);");
                db.execSQL("INSERT INTO CUENTA VALUES ('4101002', 'Gastos Representacion  ',0,0,0);");
                //Partida
                db.execSQL("INSERT INTO PARTIDA VALUES ('07001', '001','1101001','Defensa de caso 1','C',2000);");
                db.execSQL("INSERT INTO PARTIDA VALUES ('07001', '002','4101002','Abogado 1','A',2000);");
                db.execSQL("INSERT INTO PARTIDA VALUES ('07002', '001','1101002','Defensa de caso 2','C',1500);");
                db.execSQL("INSERT INTO PARTIDA VALUES ('07002', '002','4101002','Abogado 1','A',1500);");
                db.execSQL("INSERT INTO PARTIDA VALUES ('07003', '001','1101003','Defensa de caso 3','C',500);");
                db.execSQL("INSERT INTO PARTIDA VALUES ('07003', '002','4101003','Abogado 2','A',500);");

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

    //INSERTAR
    public String insertar(Cuenta cuenta) {
        String regInsertados = "Registro Insertado Nº= ";
        long contador = 0;
        ContentValues dato = new ContentValues();

        //Aca se obtienen los datos de acuerdo a la actualizacion que se hara.
        dato.put("CODCUENTA", cuenta.getCodcuenta());
        dato.put("NOMCUENTA", cuenta.getNomcuenta());
        dato.put("SALDOINIC", cuenta.getSaldoinicial());
        dato.put("SALDODEBE", cuenta.getSaldodebe());
        dato.put("SALDOHABER", cuenta.getSaldohaber());

        //El siguiente string es la tabla de donde se obtendran los datos.
        //SE MODIFICARA EL DATO ENTRTE COMILLAS
        contador = db.insert("CUENTA", null, dato);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar insercion";
        } else {
            regInsertados = regInsertados + contador;
        } return regInsertados;
    }

    public Partida consultar(String numpartida, String corr){
        String[] id = {numpartida, corr, "C"};
        Cursor cursor = db.query("PARTIDA",null,"NUMPARTIDA = ? AND CORR = ? AND CAR_ABO = ?",id,null,null,null);
        if(cursor.moveToFirst()){
            Partida partida = new Partida(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getFloat(5)
            );
            return partida;
        }
        else return null;
    }
}
