package com.example.controlpersonal;

import java.time.LocalDate;

public class Ficha{
    private int id_Ficha;
    private int programa_id;
    private String codigo;
    private LocalDate inicioEtapaP;
    private LocalDate finEtapaP;

    public Ficha(int id_Ficha, String codigo) {
        this.id_Ficha = id_Ficha;
        this.codigo = codigo;
    }

    public int getId_Ficha() {
        return id_Ficha;
    }

    public void setId_Ficha(int id_Ficha) {
        this.id_Ficha = id_Ficha;
    }

    public int getPrograma_id() {
        return programa_id;
    }

    public void setPrograma_id(int programa_id) {
        this.programa_id = programa_id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getInicioEtapaP() {
        return inicioEtapaP;
    }

    public void setInicioEtapaP(LocalDate inicioEtapaP) {
        this.inicioEtapaP = inicioEtapaP;
    }

    public LocalDate getFinEtapaP() {
        return finEtapaP;
    }

    public void setFinEtapaP(LocalDate finEtapaP) {
        this.finEtapaP = finEtapaP;
    }

    @Override
    public String toString() {
        return codigo;
    }
}