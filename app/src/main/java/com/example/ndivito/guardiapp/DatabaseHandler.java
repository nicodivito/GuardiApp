package com.example.ndivito.guardiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ndivito on 16/07/2017.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHandler";

    // Database Version
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "guardiapp";


    private static final String INSERT_PERSONA = "INSERT INTO `PERSONAS` (`id`, `nombre`, `apellido`, `telefono`, `cantGuardias`, `cantViernes`, `cantSabados`, `cantDomingos`, `cantFeriados`, `rango`) VALUES\n" +
            "(1,\"Nicolas\",\"Di Vito\",114479700,4,2,2,1,3,1),\n" +
            "(2,\"Lucia\",\"Masino\",112279700,4,2,2,1,2,1),\n" +
            "(3,\"Rufina\",\"Rufi\",1144444,1,32,22,11,4,21);";


    private static final String INSERT_EXCEPCIONES = "INSERT INTO `EXCEPCIONES` (`id`, `idPersona`, `dia`, `mes`, `anio`) VALUES\n" +
            "(1,1,24,12,2017),\n" +
            "(2,1,12,09,2017),\n" +
            "(3,2,01,01,2017);";

    private static final String CREATE_TABLE_PERSONAS = "\n" +
            "Create table personas (\n" +
            "id INTEGER PRIMARY KEY,\n" +
            "nombre text,\n" +
            "apellido text,\n" +
            "telefono int,\n" +
            "cantGuardias int,\n" +
            "cantViernes int,\n" +
            "cantSabados int,\n" +
            "cantDomingos int,\n" +
            "cantFeriados int,\n" +
            "rango int  );";

    private static final String CREATE_TABLE_GUARDIAS = "create table GUARDIAS (\n" +
            "id INTEGER PRIMARY KEY,\n" +
            "dia int, \n" +
            "mes int,\n" +
            "anio int,\n" +
            "medible int,\n" +
            "cantPersonas int,\n" +
            "feriado text)";


    private static final String CREATE_TABLE_EXCEPCIONES = "create table excepciones (\n" +
            "id INTEGER PRIMARY KEY,\n" +
            "idPersona INTEGER,\n" +
            "dia int,\n" +
            "mes int,\n" +
            "anio int)";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_PERSONAS);
        db.execSQL(CREATE_TABLE_GUARDIAS);
        db.execSQL(CREATE_TABLE_EXCEPCIONES);
        db.execSQL(INSERT_PERSONA);
        db.execSQL(INSERT_EXCEPCIONES);
        System.out.println("Ejecute el OnCreate");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS personas");
        db.execSQL("DROP TABLE IF EXISTS guardias");
        db.execSQL("DROP TABLE IF EXISTS excepciones");
        // create new tables
        onCreate(db);
    }

    public DatabaseHandler inicializar() { //Este metodo hace la carga inicial

        List<Persona> personas;
        personas = obtenerPersonas(); //Hago esto para ver si ya habian datos...

        if(personas.size()!=0){ //Si entro aca es porque la carga inicial ya estaba hecha
            System.out.println("La BD ya estaba inicializada...");
            return this;
        }
        else
        {
            System.out.println("la bd ta vacia...");
        }

        return this;
    }

    public List<Persona> obtenerPersonas() {
        List<Persona> Persona = new ArrayList<Persona>();
        String selectQuery = "SELECT  * FROM personas";

        Log.d(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        Persona cn[] = new Persona[150];
        // looping through all rows and adding to list
        int i = 0;
        if (c.moveToFirst()) {
            do {
                cn[i] = new Persona();
                //Cargo este elemento en cn
                cn[i].setId(Integer.parseInt(c.getString(0)));
                System.out.println("EN DBHANDLER - Saque de la db la persona:" + c.getString(1));
                cn[i].setNombre(c.getString(1));
                cn[i].setApellido(c.getString(2));
                cn[i].setTelefono(c.getInt(3));
                cn[i].setCantGuardias(c.getInt(4));
                cn[i].setCantViernes(c.getInt(5));
                cn[i].setCantSabados(c.getInt(6));
                cn[i].setCantDomingos(c.getInt(7));
                cn[i].setCantFeriados(c.getInt(8));
                // La agrego a la lista que voy a devolver
                Persona.add(cn[i]);
                i++;
            } while (c.moveToNext());
        }

        db.close();

        return Persona;
    }



    public int agregarPersona(Persona persona){
        int id;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //El id no lo ingresamos porque se calcula solo
        values.put("nombre", persona.getNombre()); // Contact Name
        values.put("apellido", persona.getApellido()); // Contact Name
        values.put("telefono", persona.getTelefono()); // Contact Name
        values.put("rango", persona.getRango()); // Contact Name
        values.put("cantGuardias", persona.getCantGuardias()); // Contact Name
        values.put("cantViernes", persona.getCantViernes()); // Contact Name
        values.put("cantSabados", persona.getCantSabados()); // Contact Name
        values.put("cantDomingos", persona.getCantDomingos()); // Contact Name
        values.put("cantFeriados", persona.getCantFeriados()); // Contact Name
        // Inserting Row
        id=(int)db.insert("personas", null, values); //Agrega la persona y me devuelve el id
        db.close(); // Closing database connection
        return id;
    }

    public int agregarExcepcion(Excepciones excepcion){
        int id;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //El id no lo ingresamos porque se calcula solo
        values.put("idPersona", excepcion.getIdPersona()); // Contact Name
        values.put("dia", excepcion.getDia()); // Contact Name
        values.put("mes", excepcion.getMes()); // Contact Name
        values.put("anio", excepcion.getAnio()); // Contact Name
        // Inserting Row
        id=(int)db.insert("excepciones", null, values); //Agrega la excepcion y me devuelve el id
        db.close(); // Closing database connection
        return id;
    }

    public List<Excepciones> obtenerExcepcion() {
        List<Excepciones> Excepcion = new ArrayList<Excepciones>();
        String selectQuery = "SELECT  * FROM excepciones";

        Log.d(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        Excepciones cn[] = new Excepciones[150];
        // looping through all rows and adding to list
        int i = 0;
        if (c.moveToFirst()) {
            do {
                cn[i] = new Excepciones();
                //Cargo este elemento en cn
                cn[i].setId(Integer.parseInt(c.getString(0)));
                System.out.println("EN DBHANDLER - Saque de la db la persona:" + c.getString(1));
                cn[i].setIdPersona(c.getInt(1));
                cn[i].setDia(c.getInt(2));
                cn[i].setMes(c.getInt(3));
                cn[i].setAnio(c.getInt(4));

                Excepcion.add(cn[i]);
                i++;
            } while (c.moveToNext());
        }

        db.close();

        return Excepcion;
    }

}
