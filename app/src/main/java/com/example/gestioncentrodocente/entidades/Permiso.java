package com.example.gestioncentrodocente.entidades;

public class Permiso {

    private int id;
    private String email;
    private String criterioComun;
    private String criterioEspecifico;

    public Permiso() {
    }

    public Permiso(int id, String email, String criterioComun, String criterioEspecifico) {
        this.id = id;
        this.email = email;
        this.criterioComun = criterioComun;
        this.criterioEspecifico = criterioEspecifico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCriterioComun() {
        return criterioComun;
    }

    public void setCriterioComun(String criterioComun) {
        this.criterioComun = criterioComun;
    }

    public String getCriterioEspecifico() {
        return criterioEspecifico;
    }

    public void setCriterioEspecifico(String criterioEspecifico) {
        this.criterioEspecifico = criterioEspecifico;
    }
}
