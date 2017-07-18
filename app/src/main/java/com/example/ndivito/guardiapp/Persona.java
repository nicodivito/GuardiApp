package com.example.ndivito.guardiapp;

/**
 * Created by ndivito on 16/07/2017.
 */
public class Persona {



    private int id;
    private String nombre;
    private String apellido;
    private int rango;
    private int telefono;
    private int cantGuardias;
    private int cantViernes;
    private int cantSabados;
    private int cantDomingos;
    private int cantFeriados;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getCantGuardias() {
        return cantGuardias;
    }

    public void setCantGuardias(int cantGuardias) {
        this.cantGuardias = cantGuardias;
    }

    public int getCantViernes() {
        return cantViernes;
    }

    public void setCantViernes(int cantViernes) {
        this.cantViernes = cantViernes;
    }

    public int getCantSabados() {
        return cantSabados;
    }

    public void setCantSabados(int cantSabados) {
        this.cantSabados = cantSabados;
    }

    public int getCantDomingos() {
        return cantDomingos;
    }

    public void setCantDomingos(int cantDomingos) {
        this.cantDomingos = cantDomingos;
    }

    public int getCantFeriados() {
        return cantFeriados;
    }

    public void setCantFeriados(int cantFeriados) {
        this.cantFeriados = cantFeriados;
    }


}
