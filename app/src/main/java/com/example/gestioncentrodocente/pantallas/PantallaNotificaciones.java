package com.example.gestioncentrodocente.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.adaptadores.AdaptadorNotificacion;
import com.example.gestioncentrodocente.entidades.Guardia;
import com.example.gestioncentrodocente.entidades.Notificacion;
import com.example.gestioncentrodocente.entidades.Reunion;
import com.example.gestioncentrodocente.entidades.Usuario;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PantallaNotificaciones extends AppCompatActivity {
    private Bundle usuario;
    private DatabaseReference dbRef,dbRefGuardias,dbRefReuniones;
    private String correoUsuario,nombreUsuario;
    private Usuario usu;
    ArrayList<Notificacion> listaNotificaciones;
    private boolean guardiasCargadas = false;
    private boolean reunionesCargadas = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_notificaciones);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("\tNOTIFICACIONES");


        usuario = getIntent().getExtras();
        listaNotificaciones = new ArrayList<>();

        //PARA CONSEGUIR EL NOMBRE Y EMAIL DEL USUARIO
        dbRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        dbRef.orderByChild("dni").equalTo(usuario.getString("dni")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    usu=ds.getValue(Usuario.class);
                    correoUsuario=usu.getEmail();
                    nombreUsuario=usu.getNombre();
                    Log.d("DEBUG", "Valor de NOMBRE: " + nombreUsuario);
                    Log.d("DEBUG", "Valor de correoUsuario: " + correoUsuario);






                    //COGEMOS LAS GUARDIAS DE LA BASE DE DATOS QUE COINCIDAN CON EL NOMBRE DEL USUARIO COMO RECEPTOR
                    dbRefGuardias = FirebaseDatabase.getInstance().getReference().child("Guardias");
                    Log.d("DEBUG", "Valor de receptor: " + nombreUsuario);

                    dbRefGuardias.orderByChild("receptor").equalTo(nombreUsuario).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot guardiasSnapshot) {
                            for (DataSnapshot guardiaSnapshot : guardiasSnapshot.getChildren()) {
                                Guardia guardia = guardiaSnapshot.getValue(Guardia.class);
                                if (guardia != null) {
                                    Notificacion notificacion = new Notificacion(guardia.getReceptor(), "Guardia", "Te asignaron una guardia para " + guardia.getFecha(), R.drawable.guardias);
                                    listaNotificaciones.add(notificacion);
                                }
                            }
                            guardiasCargadas = true;
                            actualizarListaNotificacionesSiEsNecesario();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                    //COGEMOS LAS REUNIONES DE LAS BASES DE DATOS QUE CONTENGAN NUESTRO EMAIL COMO RECEPTOR
                    dbRefReuniones = FirebaseDatabase.getInstance().getReference().child("Reuniones");
                    Log.d("DEBUG", "Valor de correoUsuario: " + correoUsuario);
                    dbRefReuniones.orderByChild("receptor").equalTo(correoUsuario).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot reunionesSnapshot) {
                            for (DataSnapshot reunionSnapshot : reunionesSnapshot.getChildren()) {
                                Reunion reunion = reunionSnapshot.getValue(Reunion.class);
                                if (reunion != null) {
                                    Notificacion notificacion = new Notificacion(reunion.getReceptor(), "Reunión", "Tienes una reunión el " + reunion.getFecha(), R.drawable.reunion);
                                    listaNotificaciones.add(notificacion);
                                }
                            }
                            reunionesCargadas = true;
                            actualizarListaNotificacionesSiEsNecesario();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });






                }
                actualizarListaNotificacionesSiEsNecesario();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });



    }

    // Método para actualizar la lista de notificaciones solo si tanto las guardias como las reuniones han sido cargadas
    private void actualizarListaNotificacionesSiEsNecesario() {
        if (guardiasCargadas && reunionesCargadas) {
            // Ambas consultas han sido completadas, entonces podemos actualizar la lista de notificaciones
            actualizarListaNotificaciones(listaNotificaciones);
        }
    }
    // Método para actualizar la lista de notificaciones y mostrarla en el ListView
    private void actualizarListaNotificaciones(ArrayList<Notificacion> listaNotificaciones) {
        ListView vistaLista = findViewById(R.id.listadoNotificaciones);
        AdaptadorNotificacion miAdaptadorNotificacion = new AdaptadorNotificacion(this, listaNotificaciones);
        vistaLista.setAdapter(miAdaptadorNotificacion);
    }
}
