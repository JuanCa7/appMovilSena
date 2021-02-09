package com.example.controlpersonal.ClasesBd;

public class Persona {
    private int id_Persona;
    private String nombre;
    private String apellido;
    private int celular;
    private String numIdentificacion;
    private String usuario;

    public Persona(int id_Persona, String nombre, String apellido) {
        this.id_Persona = id_Persona;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Persona(int id_Persona, String nombre, String apellido, int celular, String numIdentificacion, String usuario) {
        this.id_Persona = id_Persona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.numIdentificacion = numIdentificacion;
        this.usuario = usuario;
    }

    public int getId_Persona() {
        return id_Persona;
    }

    public void setId_Persona(int id_Persona) {
        this.id_Persona = id_Persona;
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

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return nombre+" "+apellido;
    }
}
