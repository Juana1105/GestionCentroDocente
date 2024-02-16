package com.example.gestioncentrodocente.entidades;

public class TareaAdministrativa {

    private String receptor;

    private String descripcion;
    private String observaciones;
    private String estado;

    public TareaAdministrativa() {
    }

    public TareaAdministrativa(String receptor, String descripcion, String observaciones,String estado) {
        this.receptor = receptor;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.estado=estado;
    }

    public String getReceptor() {
        return receptor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
