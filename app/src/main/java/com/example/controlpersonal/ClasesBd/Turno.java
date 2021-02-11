package com.example.controlpersonal.ClasesBd;

public class Turno {
    private int idTurno;
    private int persona_fk;
    private String nombreP;
    private String apellidoP;
    private int jornada_fk;
    private String nombreJ;
    private int cargo_fk;
    private String nombreC;
    private String fecha;
    private String descripcion;
    private String estado;

    public Turno() {}

    public Turno(int idTurno, int persona_fk, String nombreP, String apellidoP, int jornada_fk, String nombreJ, int cargo_fk, String nombreC, String fecha, String descripcion, String estado) {
        this.idTurno = idTurno;
        this.persona_fk = persona_fk;
        this.nombreP = nombreP;
        this.apellidoP = apellidoP;
        this.jornada_fk = jornada_fk;
        this.nombreJ = nombreJ;
        this.cargo_fk = cargo_fk;
        this.nombreC = nombreC;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public int getPersona_fk() {
        return persona_fk;
    }

    public void setPersona_fk(int persona_fk) {
        this.persona_fk = persona_fk;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public int getJornada_fk() {
        return jornada_fk;
    }

    public void setJornada_fk(int jornada_fk) {
        this.jornada_fk = jornada_fk;
    }

    public String getNombreJ() {
        return nombreJ;
    }

    public void setNombreJ(String nombreJ) {
        this.nombreJ = nombreJ;
    }

    public int getCargo_fk() {
        return cargo_fk;
    }

    public void setCargo_fk(int cargo_fk) {
        this.cargo_fk = cargo_fk;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
