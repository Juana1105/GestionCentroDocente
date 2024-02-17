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
import com.example.gestioncentrodocente.entidades.Ausencia;
import com.example.gestioncentrodocente.entidades.Guardia;
import com.example.gestioncentrodocente.entidades.Notificacion;
import com.example.gestioncentrodocente.entidades.Permiso;
import com.example.gestioncentrodocente.entidades.Reunion;
import com.example.gestioncentrodocente.entidades.TareaAdministrativa;
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
    private DatabaseReference dbRef,dbRefGuardias,dbRefReuniones,dbRefTareas,dbRefPermisos,dbRefAusencias;
    private String correoUsuario,nombreUsuario,rol;
    private Usuario usu;
    ArrayList<Notificacion> listaNotificaciones;
    private boolean guardiasCargadas = false;
    private boolean reunionesCargadas = false;
    private boolean tareasCargadas = false;
    private boolean tareas2Cargadas = false;
    private boolean permisoCargado = false;
    private boolean ausenciaCargada = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_notificaciones);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("\tNOTIFICACIONES");


        usuario = getIntent().getExtras();
        listaNotificaciones = new ArrayList<>();

        //ACCEDEMOS A LA BASE DE DATOS Y COMPARANDO CON NUESTRO DNI DEL BUNDLE, RECOGEMOS LA INFORMACION QUE NECESITAMOS Y LA GUARDAMOS EN VARIABLES
        dbRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        dbRef.orderByChild("dni").equalTo(usuario.getString("dni")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    usu=ds.getValue(Usuario.class);
                    correoUsuario=usu.getEmail();
                    nombreUsuario=usu.getNombre();
                    rol=usu.getRol();



                    if(rol.equals("Jefe de Estudios")){
                        //NOTICACIONES PARA EL JEFE DE ESTUDIOS PARA MOSTRAR EL ESTADO DE LAS TAREAS
                        dbRefTareas = FirebaseDatabase.getInstance().getReference().child("Tareas");
                        dbRefTareas.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot tareasSnapshot) {
                                for (DataSnapshot tareaSnapshot : tareasSnapshot.getChildren()) {
                                    TareaAdministrativa tarea = tareaSnapshot.getValue(TareaAdministrativa.class);
                                    if (tarea != null && !tarea.getEstado().isEmpty()) {
                                        Notificacion notificacion = new Notificacion(tarea.getReceptor(), "Tarea", "Tarea: " + tarea.getDescripcion()+"\nEstado: "+tarea.getEstado(), R.drawable.gestionar_tareas);
                                        listaNotificaciones.add(notificacion);
                                    }
                                }
                                tareas2Cargadas = true;
                                actualizarListaNotificacionesSiEsNecesario();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });

                        //NOTIFICACIONES PARA MOSTRAR PERMISOS
                        dbRefPermisos = FirebaseDatabase.getInstance().getReference().child("Permisos");
                        dbRefPermisos.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot permisosSnapshot) {
                                for (DataSnapshot permiSnapshot : permisosSnapshot.getChildren()) {
                                    Permiso permiso = permiSnapshot.getValue(Permiso.class);
                                    if (permiso != null) {
                                        Notificacion notificacion = new Notificacion(permiso.getEmail(), "Permiso", "Motivo permiso: " + permiso.getCriterioComun()+"\nEspecificación del permiso: "+permiso.getCriterioEspecifico(), R.drawable.permiso);
                                        listaNotificaciones.add(notificacion);
                                    }
                                }
                                permisoCargado = true;
                                actualizarListaNotificacionesSiEsNecesario();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        //NOTIFICACIONES PARA MOSTRAR AUSENCIAS
                        dbRefAusencias = FirebaseDatabase.getInstance().getReference().child("Ausencias");
                        dbRefAusencias.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot ausenciasSnapshot) {
                                for (DataSnapshot aSnapshot : ausenciasSnapshot.getChildren()) {
                                    Ausencia ausencia = aSnapshot.getValue(Ausencia.class);
                                    if (ausencia != null) {
                                        Notificacion notificacion = new Notificacion(ausencia.getEmail(), "Ausencia", "Día/Hora Inicio: " + ausencia.getFechaInicio()+"_"+ausencia.getHoraInicio()+"\nDía/Hora Fin: "+ausencia.getFechaFin()+"_"+ausencia.getHoraFinal(), R.drawable.ausencia);
                                        listaNotificaciones.add(notificacion);
                                    }
                                }
                                ausenciaCargada = true;
                                actualizarListaNotificacionesSiEsNecesario();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });

                    //EN CASO DE NO SER JEFE DE ESTUDIOS
                    }else {

                        //COGEMOS LAS GUARDIAS DE LA BASE DE DATOS QUE COINCIDAN CON EL NOMBRE DEL USUARIO COMO RECEPTOR
                        dbRefGuardias = FirebaseDatabase.getInstance().getReference().child("Guardias");
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


                        //COGEMOS LAS TAREAS DE LAS BASES DE DATOS QUE CONTENGAN NUESTRO EMAIL COMO RECEPTOR
                        dbRefTareas = FirebaseDatabase.getInstance().getReference().child("Tareas");
                        dbRefTareas.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot tareasSnapshot) {
                                for (DataSnapshot tareaSnapshot : tareasSnapshot.getChildren()) {
                                    TareaAdministrativa tarea = tareaSnapshot.getValue(TareaAdministrativa.class);
                                    if (tarea != null && tarea.getReceptor().equals(correoUsuario)) {
                                        Notificacion notificacion = new Notificacion(tarea.getReceptor(), "Tarea", "Tarea:" + tarea.getDescripcion(), R.drawable.gestionar_tareas);
                                        listaNotificaciones.add(notificacion);
                                    }
                                }
                                tareasCargadas = true;
                                actualizarListaNotificacionesSiEsNecesario();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
                actualizarListaNotificacionesSiEsNecesario();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });



    }

    // Método para actualizar la lista de notificaciones
    private void actualizarListaNotificacionesSiEsNecesario() {
        if (guardiasCargadas && reunionesCargadas && tareasCargadas) {
            // las consultas han sido completadas, entonces podemos actualizar la lista de notificaciones
            actualizarListaNotificaciones(listaNotificaciones);
        }else if(tareas2Cargadas&& permisoCargado&&ausenciaCargada){
            actualizarListaNotificaciones(listaNotificaciones);
        }
    }
    // Método para actualizar la lista de notificaciones y mostrarla en el ListView
    private void actualizarListaNotificaciones(ArrayList<Notificacion> listaNotificaciones) {
        ListView vistaLista = findViewById(R.id.listadoNotificaciones);
        AdaptadorNotificacion miAdaptadorNotificacion = new AdaptadorNotificacion(this, listaNotificaciones);
        vistaLista.setAdapter(miAdaptadorNotificacion);
        miAdaptadorNotificacion.notifyDataSetChanged();
    }
}
