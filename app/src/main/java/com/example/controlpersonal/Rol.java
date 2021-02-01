package com.example.controlpersonal;

public class Rol {
    private int rol;
    private String nombre;

    public Rol(int rol, String nombre) {
        this.rol = rol;
        this.nombre = nombre;
    }

    public Rol() {
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
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
