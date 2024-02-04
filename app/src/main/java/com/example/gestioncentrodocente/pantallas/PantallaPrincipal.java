package com.example.gestioncentrodocente.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.entidades.Usuario;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class PantallaPrincipal extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    private Bundle usuario;
    String rol = null;
    private DatabaseReference dbRef;
    private Usuario usu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        getSupportActionBar().hide();

        /*MaterialToolbar toolbar = findViewById(R.id.encabezadoMenuPrincipal);
        toolbar.setOnMenuItemClickListener(this);*/


        usuario = getIntent().getExtras();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        dbRef.orderByChild("dni").equalTo(usuario.getString("dni")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    usu=ds.getValue(Usuario.class);
                    rol=usu.getRol();
                    // Aquí se verifica el rol del usuario después de obtenerlo de la base de datos
                    verificarRol();

                    // Aquí se verifica el rol del usuario después de obtenerlo de la base de datos
                   // inflateLayoutBasedOnRole(rol);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    // Método para verificar el rol del usuario y realizar las acciones correspondientes
    private void verificarRol() {
        if (rol.equals("Docente")) {
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
                    actividadPantallaNotificaciones.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaNotificaciones);
                }
            });

            textGestionarPermisos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaGestionarPermisos = new Intent(PantallaPrincipal.this, PantallaGestionarPermisos.class);
                    actividadPantallaGestionarPermisos.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaGestionarPermisos);
                }
            });



            botonHorario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaHorario = new Intent(PantallaPrincipal.this, PantallaHorario.class);
                    actividadPantallaHorario.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaHorario);
                }
            });

            textGestionarTareas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaGestionTareas = new Intent(PantallaPrincipal.this, PantallaGestionarTareasAdmin.class);
                    actividadPantallaGestionTareas.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaGestionTareas);
                }
            });

            textNotificarAusencias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaGestionAusencias = new Intent(PantallaPrincipal.this, PantallaGestionarAusencias.class);
                    actividadPantallaGestionAusencias.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaGestionAusencias);
                }
            });

            textCalendarioReuniones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaCalendarioReuniones = new Intent(PantallaPrincipal.this, PantallaCalendarioReuniones.class);
                    actividadPantallaCalendarioReuniones.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaCalendarioReuniones);
                }
            });

        } else if (rol.equals("Coordinador de ciclo")) {
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
                    actividadPantallaNotificaciones.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaNotificaciones);
                }
            });

            textGestionarPermisos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaGestionarPermisos = new Intent(PantallaPrincipal.this, PantallaGestionarPermisos.class);
                    actividadPantallaGestionarPermisos.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaGestionarPermisos);
                }
            });


            botonHorario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaHorario = new Intent(PantallaPrincipal.this, PantallaHorario.class);
                    actividadPantallaHorario.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaHorario);
                }
            });

            textGestionarTareas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaGestionTareas = new Intent(PantallaPrincipal.this, PantallaGestionarTareasAdmin.class);
                    actividadPantallaGestionTareas.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaGestionTareas);
                }
            });

            textNotificarAusencias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaGestionAusencias = new Intent(PantallaPrincipal.this, PantallaGestionarAusencias.class);
                    actividadPantallaGestionAusencias.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaGestionAusencias);
                }
            });

            textCalendarioReuniones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaCalendarioReuniones = new Intent(PantallaPrincipal.this, PantallaCalendarioReuniones.class);
                    actividadPantallaCalendarioReuniones.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaCalendarioReuniones);
                }
            });


            textEnviarAvisos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaEnviarAvisos = new Intent(PantallaPrincipal.this, PantallaEnviarAvisos.class);
                    actividadPantallaEnviarAvisos.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaEnviarAvisos);
                }
            });

        } else if (rol.equals("Jefe de Estudios")) {
            setContentView(R.layout.activity_pantalla_principal_jestudios);
            //CAMBIAR ACTIVITY


            ImageView imgNotificaciones = (ImageView) findViewById(R.id.imagenNotificaciones);
            TextView enviarMensajes = (TextView) findViewById(R.id.enviarMensajes);
            TextView fijarReuniones = (TextView) findViewById(R.id.fijarReunionesJE);
            TextView textGestionGuardias=(TextView)findViewById(R.id.gestionInformeGuardias);
            TextView textAdjudicarTareasAdmin=(TextView)findViewById(R.id.adjudicarTareasAdmin);
            //Button botonCerrarSesion=(Button)findViewById(R.id.botonCerrarSesion);
            //Button botonEditarPerfil = (Button) findViewById(R.id.ppBotonEditarPerfil);
            Button botonHorario = (Button) findViewById(R.id.ppBotonMirarHorario);

            textAdjudicarTareasAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent pantallaGestionAdmin2=new Intent(PantallaPrincipal.this, PantallaGestionarTareasAdminJE.class);
                    pantallaGestionAdmin2.putExtra("dni", usu.getDni());
                    startActivity(pantallaGestionAdmin2);
                }
            });
            imgNotificaciones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaNotificaciones = new Intent(PantallaPrincipal.this, PantallaNotificaciones.class);
                    actividadPantallaNotificaciones.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaNotificaciones);
                }
            });




            botonHorario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaHorario = new Intent(PantallaPrincipal.this, PantallaHorario.class);
                    actividadPantallaHorario.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaHorario);
                }
            });


            textGestionGuardias.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent PantallaGestionGuardias=new Intent(PantallaPrincipal.this, PantallaGestionGuardias.class);
                    PantallaGestionGuardias.putExtra("dni", usu.getDni());
                    startActivity(PantallaGestionGuardias);
                }
            });

            enviarMensajes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaEnvioMensaje = new Intent(PantallaPrincipal.this, PantallaEnviarMensajeJE.class);
                    actividadPantallaEnvioMensaje.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaEnvioMensaje);
                }
            });

            fijarReuniones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent actividadPantallaFijarReuniones = new Intent(PantallaPrincipal.this, PantallaFijarReunion.class);
                    actividadPantallaFijarReuniones.putExtra("dni", usu.getDni());
                    startActivity(actividadPantallaFijarReuniones);
                }
            });

        } else {
            View padre = findViewById(R.id.linearLayoutPantallaPrincipalPadre);
            Snackbar barra= Snackbar.make(padre,"Rol de usuario no definido",Snackbar.LENGTH_SHORT);
            barra.show();
        }



        // Configurar la toolbar después de inflar el layout
        MaterialToolbar toolbar = findViewById(R.id.encabezadoMenuPrincipal);
        toolbar.setOnMenuItemClickListener(this);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        boolean realizado = false;
        if (item.getItemId() == R.id.itemPerfil) {
            realizado = true;
            Intent actividadPerfil = new Intent(PantallaPrincipal.this, Perfil.class);
            actividadPerfil.putExtras(usuario);
            startActivity(actividadPerfil);
        } else if (item.getItemId() == R.id.itemAceraDe) {
            realizado = true;
            Intent actividadAcercaDe = new Intent(PantallaPrincipal.this, AcercaDe.class);
            actividadAcercaDe.putExtras(usuario);
            startActivity(actividadAcercaDe);
        } else if (item.getItemId() == R.id.itemCerrarSesion) {
            realizado = true;
            Intent actividadLogin = new Intent(PantallaPrincipal.this, MenuInicio.class);
            actividadLogin.putExtras(usuario);
            startActivity(actividadLogin);
            finish(); // Para asegurarse de que el usuario no pueda volver atrás
        }
        return realizado;
    }
}
