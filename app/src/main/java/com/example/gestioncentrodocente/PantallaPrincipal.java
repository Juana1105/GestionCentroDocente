package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PantallaPrincipal extends AppCompatActivity {

    String rol = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        //CAMBIAR ACTIVITY
        Intent intent = getIntent();
        if (intent != null) {
            rol = intent.getStringExtra("ROL_USUARIO");
        }
        if (rol.equals("docente")) {


            ImageView imgNotificaciones = (ImageView) findViewById(R.id.imagenNotificaciones);
            TextView textGestionarPermisos = (TextView) findViewById(R.id.gestionPermisos);
            TextView textGestionarTareas = (TextView) findViewById(R.id.gestionTareasAdmin);
            TextView textNotificarAusencias = (TextView) findViewById(R.id.gestionAusencias);
            TextView textCalendarioReuniones = (TextView) findViewById(R.id.calendarioReuniones);
            Button botonCerrarSesion=(Button)findViewById(R.id.botonCerrarSesion);
            Button botonEditarPerfil = (Button) findViewById(R.id.ppBotonEditarPerfil);
            Button botonHorario = (Button) findViewById(R.id.ppBotonMirarHorario);

            imgNotificaciones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaNotificaciones = new Intent(PantallaPrincipal.this, PantallaNotificaciones.class);
                    startActivity(actividadPantallaNotificaciones);
                }
            });

            textGestionarPermisos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaGestionarPermisos = new Intent(PantallaPrincipal.this, PantallaGestionarPermisos.class);
                    startActivity(actividadPantallaGestionarPermisos);
                }
            });

            botonEditarPerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaEditarPerfil = new Intent(PantallaPrincipal.this, PantallaEditarPerfil.class);
                    startActivity(actividadPantallaEditarPerfil);
                }
            });

            botonHorario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaHorario = new Intent(PantallaPrincipal.this, PantallaHorario.class);
                    startActivity(actividadPantallaHorario);
                }
            });

            textGestionarTareas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaGestionTareas = new Intent(PantallaPrincipal.this, PantallaGestionarTareasAdmin.class);
                    startActivity(actividadPantallaGestionTareas);
                }
            });

            textNotificarAusencias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaGestionAusencias = new Intent(PantallaPrincipal.this, PantallaGestionarAusencias.class);
                    startActivity(actividadPantallaGestionAusencias);
                }
            });

            textCalendarioReuniones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaCalendarioReuniones = new Intent(PantallaPrincipal.this, PantallaCalendarioReuniones.class);
                    startActivity(actividadPantallaCalendarioReuniones);
                }
            });
            botonCerrarSesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaInicio=new Intent(PantallaPrincipal.this,MenuInicio.class);
                    startActivity(actividadPantallaInicio);
                }
            });


        } else if (rol.equals("coordinador")) {


            setContentView(R.layout.activity_pantalla_principal_coordinador);
            //CAMBIAR ACTIVITY


            ImageView imgNotificaciones = (ImageView) findViewById(R.id.imagenNotificaciones);
            TextView textGestionarPermisos = (TextView) findViewById(R.id.gestionPermisos);
            TextView textGestionarTareas = (TextView) findViewById(R.id.gestionTareasAdmin);
            TextView textNotificarAusencias = (TextView) findViewById(R.id.gestionAusencias);
            TextView textCalendarioReuniones = (TextView) findViewById(R.id.calendarioReuniones);
            TextView textEnviarAvisos = (TextView) findViewById(R.id.gestionAvisosCoordinador);


            Button botonCerrarSesion=(Button)findViewById(R.id.botonCerrarSesion);
            Button botonEditarPerfil = (Button) findViewById(R.id.ppBotonEditarPerfil);
            Button botonHorario = (Button) findViewById(R.id.ppBotonMirarHorario);


            imgNotificaciones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaNotificaciones = new Intent(PantallaPrincipal.this, PantallaNotificaciones.class);
                    startActivity(actividadPantallaNotificaciones);
                }
            });

            textGestionarPermisos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaGestionarPermisos = new Intent(PantallaPrincipal.this, PantallaGestionarPermisos.class);
                    startActivity(actividadPantallaGestionarPermisos);
                }
            });

            botonEditarPerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaEditarPerfil = new Intent(PantallaPrincipal.this, PantallaEditarPerfil.class);
                    startActivity(actividadPantallaEditarPerfil);
                }
            });

            botonHorario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaHorario = new Intent(PantallaPrincipal.this, PantallaHorario.class);
                    startActivity(actividadPantallaHorario);
                }
            });

            textGestionarTareas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaGestionTareas = new Intent(PantallaPrincipal.this, PantallaGestionarTareasAdmin.class);
                    startActivity(actividadPantallaGestionTareas);
                }
            });

            textNotificarAusencias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaGestionAusencias = new Intent(PantallaPrincipal.this, PantallaGestionarAusencias.class);
                    startActivity(actividadPantallaGestionAusencias);
                }
            });

            textCalendarioReuniones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaCalendarioReuniones = new Intent(PantallaPrincipal.this, PantallaCalendarioReuniones.class);
                    startActivity(actividadPantallaCalendarioReuniones);
                }
            });


            textEnviarAvisos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaEnviarAvisos = new Intent(PantallaPrincipal.this, PantallaEnviarAvisos.class);
                    startActivity(actividadPantallaEnviarAvisos);
                }
            });
            botonCerrarSesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaInicio=new Intent(PantallaPrincipal.this,MenuInicio.class);
                    startActivity(actividadPantallaInicio);
                }
            });

        }else if (rol.equals("jefeEstudios")) {


            setContentView(R.layout.activity_pantalla_principal_jestudios);
            //CAMBIAR ACTIVITY


            ImageView imgNotificaciones = (ImageView) findViewById(R.id.imagenNotificaciones);
            TextView enviarMensajes = (TextView) findViewById(R.id.enviarMensajes);
            TextView textCalendarioReuniones = (TextView) findViewById(R.id.calendarioReuniones);
            TextView textGestionGuardias=(TextView)findViewById(R.id.gestionInformeGuardias);
            TextView textAdjudicarTareasAdmin=(TextView)findViewById(R.id.adjudicarTareasAdmin);
            Button botonCerrarSesion=(Button)findViewById(R.id.botonCerrarSesion);
            Button botonEditarPerfil = (Button) findViewById(R.id.ppBotonEditarPerfil);
            Button botonHorario = (Button) findViewById(R.id.ppBotonMirarHorario);

            textAdjudicarTareasAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent pantallaGestionAdmin2=new Intent(PantallaPrincipal.this,PantallaGestionAdmin2.class);
                    startActivity(pantallaGestionAdmin2);
                }
            });
            imgNotificaciones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaNotificaciones = new Intent(PantallaPrincipal.this, PantallaNotificaciones.class);
                    startActivity(actividadPantallaNotificaciones);
                }
            });


            botonEditarPerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaEditarPerfil = new Intent(PantallaPrincipal.this, PantallaEditarPerfil.class);
                 /*   Bundle b=getIntent().getExtras();
                    String nombre=b.getString("nombre");
                    String apellidos=b.getString("apellidos");
                    b.putString("nombre",nombre);
                    b.putString("apellidos",apellidos);
                    actividadPantallaEditarPerfil.putExtras(b);*/
                    startActivity(actividadPantallaEditarPerfil);
                }
            });

            botonHorario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaHorario = new Intent(PantallaPrincipal.this, PantallaHorario.class);
                    startActivity(actividadPantallaHorario);
                }
            });


            textGestionGuardias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent PantallaGestionGuardias=new Intent(PantallaPrincipal.this, PantallaGestionGuardias.class);
                    startActivity(PantallaGestionGuardias);
                }
            });

            enviarMensajes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaEnvioMensaje = new Intent(PantallaPrincipal.this, PantallaEnviarMensajeJE.class);
                    startActivity(actividadPantallaEnvioMensaje);
                }
            });

            textCalendarioReuniones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaCalendarioReuniones = new Intent(PantallaPrincipal.this, PantallaCalendarioReuniones.class);
                    startActivity(actividadPantallaCalendarioReuniones);
                }
            });


            botonCerrarSesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaInicio=new Intent(PantallaPrincipal.this,MenuInicio.class);
                    startActivity(actividadPantallaInicio);
                }
            });

        }



    }
}