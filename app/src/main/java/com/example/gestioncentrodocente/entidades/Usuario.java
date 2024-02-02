package com.example.gestioncentrodocente.entidades;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String nombre;
    private String email;
    private String password;
    private String rol;
    private String titulacion;
    private String telefono;


    public Usuario() {
    }

    public Usuario(String nombre, String email, String password, String rol, String titulacion, String telefono) {

        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.titulacion = titulacion;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



}
