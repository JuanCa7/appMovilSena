package com.example.controlpersonal.ClasesBd;

public class ContadorHoras {
    private int idContadorH;
    private int Persona_id;
    private int tiempoTrans;
    private String nombreP;
    private String apellidoP;
    private String programa;
    private String codigo;

    public ContadorHoras() {}

    public ContadorHoras(int idContadorH, int persona_id, int tiempoTrans, String nombreP, String apellidoP, String programa, String codigo) {
        this.idContadorH = idContadorH;
        Persona_id = persona_id;
        this.tiempoTrans = tiempoTrans;
        this.nombreP = nombreP;
        this.apellidoP = apellidoP;
        this.programa = programa;
        this.codigo = codigo;
    }

    public int getIdContadorH() {
        return idContadorH;
    }

    public void setIdContadorH(int idContadorH) {
        this.idContadorH = idContadorH;
    }

    public int getPersona_id() {
        return Persona_id;
    }

    public void setPersona_id(int persona_id) {
        Persona_id = persona_id;
    }

    public int getTiempoTrans() {
        return tiempoTrans;
    }

    public void setTiempoTrans(int tiempoTrans) {
        this.tiempoTrans = tiempoTrans;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
