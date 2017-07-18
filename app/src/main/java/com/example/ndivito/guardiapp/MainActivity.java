package com.example.ndivito.guardiapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler db;
    List<Persona> personas;
    List<Excepciones> excepciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this).inicializar();

        personas=db.obtenerPersonas();
        excepciones=db.obtenerExcepcion();
        System.out.println("las personas son");
        for(int i=0;i<personas.size();i++)
        {
            System.out.println("Las personas son" + personas.get(i).getNombre() +personas.get(i).getApellido());
            System.out.println("Las excepciones son" + excepciones.get(i).getIdPersona() +excepciones.get(i).getAnio());
        }
    }
}
