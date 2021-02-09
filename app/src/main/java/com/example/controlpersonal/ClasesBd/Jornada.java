package com.example.controlpersonal.ClasesBd;

public class Jornada {
    private int idJornada;
    private String nombre;

    public Jornada() {
    }

    public Jornada(int idJornada, String nombre) {
        this.idJornada = idJornada;
        this.nombre = nombre;
    }

    public int getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(int idJornada) {
        this.idJornada = idJornada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
