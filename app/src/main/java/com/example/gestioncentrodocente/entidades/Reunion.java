package com.example.gestioncentrodocente.entidades;

public class Reunion {

    private String nombreReunion;
    private String receptor;
    private String fecha;
    private String motivo;






    public Reunion() {
    }

    public Reunion(String nombreReunion,String receptor,String fecha, String motivo ) {
        this.nombreReunion=nombreReunion;
        this.receptor = receptor;
        this.fecha = fecha;
        this.motivo = motivo;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNombreReunion() {
        return nombreReunion;
    }

    public void setNombreReunion(String nombreReunion) {
        this.nombreReunion = nombreReunion;
    }
}
