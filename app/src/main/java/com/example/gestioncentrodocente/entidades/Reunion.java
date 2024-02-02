package com.example.gestioncentrodocente.entidades;

public class Reunion {

    private String nombreEmisor;
    private String fecha;
    private String motivo;

    public Reunion() {
    }

    public Reunion(String nombreEmisor, String fecha, String motivo) {
        this.nombreEmisor = nombreEmisor;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
