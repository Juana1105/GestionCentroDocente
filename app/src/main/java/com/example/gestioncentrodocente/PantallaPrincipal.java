package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PantallaPrincipal extends AppCompatActivity implements  Toolbar.OnMenuItemClickListener{

    String rol = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        // Ocultar el ActionBar predeterminado
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();



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




        } else if (rol.equals("coordinador")) {


            setContentView(R.layout.activity_pantalla_principal_coordinador);
            //CAMBIAR ACTIVITY


            ImageView imgNotificaciones = (ImageView) findViewById(R.id.imagenNotificaciones);
            TextView textGestionarPermisos = (TextView) findViewById(R.id.gestionPermisos);
            TextView textGestionarTareas = (TextView) findViewById(R.id.gestionTareasAdmin);
            TextView textNotificarAusencias = (TextView) findViewById(R.id.gestionAusencias);
            TextView textCalendarioReuniones = (TextView) findViewById(R.id.calendarioReuniones);
            TextView textEnviarAvisos = (TextView) findViewById(R.id.gestionAvisosCoordinador);

/*CAMBIOS AQUI*/
           // Button botonCerrarSesion=(Button)findViewById(R.id.botonCerrarSesion);
          //  Button botonEditarPerfil = (Button) findViewById(R.id.ppBotonEditarPerfil);
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


        }else if (rol.equals("jefeEstudios")) {


            setContentView(R.layout.activity_pantalla_principal_jestudios);
            //CAMBIAR ACTIVITY


            ImageView imgNotificaciones = (ImageView) findViewById(R.id.imagenNotificaciones);
            TextView enviarMensajes = (TextView) findViewById(R.id.enviarMensajes);
            TextView textCalendarioReuniones = (TextView) findViewById(R.id.calendarioReuniones);
            TextView textGestionGuardias=(TextView)findViewById(R.id.gestionInformeGuardias);
            TextView textAdjudicarTareasAdmin=(TextView)findViewById(R.id.adjudicarTareasAdmin);
            //Button botonCerrarSesion=(Button)findViewById(R.id.botonCerrarSesion);
            //Button botonEditarPerfil = (Button) findViewById(R.id.ppBotonEditarPerfil);
            Button botonHorario = (Button) findViewById(R.id.ppBotonMirarHorario);

            textAdjudicarTareasAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent pantallaGestionAdmin2=new Intent(PantallaPrincipal.this, PantallaGestionarTareasAdminJE.class);
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




        }



    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        boolean realizado = false;
        if (item.getItemId() == R.id.itemPerfil) {
            realizado = true;
            Intent actividadPerfil = new Intent(PantallaPrincipal.this, Perfil.class);
            //actividadPerfil.putExtras(usuario);
            startActivity(actividadPerfil);
        } else if (item.getItemId() == R.id.itemAceraDe) {
            realizado = true;
            Intent actividadAcercaDe = new Intent(PantallaPrincipal.this, AcercaDe.class);
            //actividadAcercaDe.putExtras(usuario);
            startActivity(actividadAcercaDe);
        } else if (item.getItemId() == R.id.itemCerrarSesion) {
            realizado = true;
            /*Esta linea de codigo cierra sesion con el usuario actual para que
            una vez seleccionada la opcion de cerrar sesion, se desvincule y pueda
            dar paso a un nuevo usuario registrado para luego mostrar sus datos en
            el perfil de usuario */
           // FirebaseAuth.getInstance().signOut();
            Intent actividadLogin = new Intent(PantallaPrincipal.this, MenuInicio.class);
            startActivity(actividadLogin);
            finish(); //para asegurarse de que el usuario no pueda volver atras
        }
        return realizado;
    }
}