package com.example.gestioncentrodocente.entidades;

public class TareaAdministrativa {

    private String receptor;
    private String tipoTarea;
    private String descripcion;
    private String observaciones;

    public TareaAdministrativa() {
    }

    public TareaAdministrativa(String receptor, String tipoTarea, String descripcion, String observaciones) {
        this.receptor = receptor;
        this.tipoTarea = tipoTarea;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(String tipoTarea) {
        this.tipoTarea = tipoTarea;
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
