package com.example.controlpersonal;

public class tipoIdent {
    private int id;
    private String tipoIdent;

    public tipoIdent(int id, String tipoIdent) {
        this.id = id;
        this.tipoIdent = tipoIdent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoIdent() {
        return tipoIdent;
    }

    public void setTipoIdent(String tipoIdent) {
        this.tipoIdent = tipoIdent;
    }

    @Override
    public String toString() {
        return tipoIdent;
    }
}
