package com.example.gestioncentrodocente.entidades;

public class Guardia {

    private int id;
    private String receptor;
    private String fecha;
    private String tipoGuardia;
    private String observaciones;

    public Guardia() {
    }

    public Guardia(int id,String receptor, String fecha, String tipoGuardia, String observaciones) {
        this.id=id;
        this.receptor = receptor;
        this.fecha = fecha;
        this.tipoGuardia = tipoGuardia;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoGuardia() {
        return tipoGuardia;
    }

    public void setTipoGuardia(String tipoGuardia) {
        this.tipoGuardia = tipoGuardia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
