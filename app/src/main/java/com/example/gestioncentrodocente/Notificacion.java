package com.example.gestioncentrodocente;

public class Notificacion {

    String nombreEmisor;
    String asunto;
    String mensaje;
    int foto;
    public Notificacion(){

    }
    public Notificacion(String nombreEmisor, String asunto, String mensaje, int foto) {
        this.nombreEmisor = nombreEmisor;
        this.asunto= asunto;
        this.mensaje=mensaje;
        this.foto=foto;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }


    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
