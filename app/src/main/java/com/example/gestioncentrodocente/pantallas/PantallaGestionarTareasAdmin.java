package com.example.gestioncentrodocente.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.entidades.Guardia;
import com.example.gestioncentrodocente.entidades.Notificacion;
import com.example.gestioncentrodocente.entidades.Reunion;
import com.example.gestioncentrodocente.entidades.TareaAdministrativa;
import com.example.gestioncentrodocente.entidades.Usuario;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PantallaGestionarTareasAdmin extends AppCompatActivity {

    private Bundle usuario;
    private DatabaseReference dbRef, dbRefTareas;
    Usuario usu;
    String seleccionEstado="", seleccionDescripcion="", correoUsuario="";
    List<String> valores = new ArrayList<>();
    Spinner spinnerEstadoTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_gestionar_tareas_admin);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("GESTIÓN TAREAS ADMINISTRATIVAS");
        Spinner spinnerNombreTarea = findViewById(R.id.spinnerNombreTarea);
        usuario = getIntent().getExtras();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        dbRef.orderByChild("dni").equalTo(usuario.getString("dni")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    usu = ds.getValue(Usuario.class);

                    correoUsuario = usu.getEmail();
                    Log.d("VALORES", "emailUSUARIO: " + correoUsuario);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        dbRefTareas = FirebaseDatabase.getInstance().getReference().child("Tareas");
        dbRefTareas.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    TareaAdministrativa tarea = ds.getValue(TareaAdministrativa.class);
                    if (tarea != null && tarea.getReceptor().equals(correoUsuario)) {
                        valores.add(tarea.getDescripcion());
                    }
                }

                String[] nombreTareas = valores.toArray(new String[0]);
                Log.d("QUE HAY DENTRO", "info: " + Arrays.toString(nombreTareas));

                spinnerNombreTarea.setAdapter(new ArrayAdapter<>(PantallaGestionarTareasAdmin.this, android.R.layout.simple_spinner_item, nombreTareas));
                spinnerNombreTarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        seleccionDescripcion = nombreTareas[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        // Inicialización del Spinner spinnerEstadoTarea después de obtener los valores de las tareas
        spinnerEstadoTarea = findViewById(R.id.spinnerEstadoTarea);
        String[] estado = {"Pendiente", "En Proceso", "Terminada"};
        spinnerEstadoTarea.setAdapter(new ArrayAdapter<String>(PantallaGestionarTareasAdmin.this, android.R.layout.simple_spinner_item, estado));
        spinnerEstadoTarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seleccionEstado = estado[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        MaterialButton aceptar = findViewById(R.id.pantallaGestTAceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Mensaje Informativo");
                builder.setMessage("Para administrar la tarea haz clic en 'aceptar'");
                builder.setIcon(android.R.drawable.ic_dialog_info);

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Obtener la tarea seleccionada del Spinner spinnerNombreTarea
                        String tareaSeleccionada = spinnerNombreTarea.getSelectedItem().toString();

                        // Obtener el estado seleccionado del Spinner spinnerEstadoTarea
                        String estadoSeleccionado = spinnerEstadoTarea.getSelectedItem().toString();

                        // Actualizar el estado de la tarea seleccionada en la base de datos
                        dbRefTareas.addListenerForSingleValueEvent(new ValueEventListener() {
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    TareaAdministrativa tarea = ds.getValue(TareaAdministrativa.class);
                                    if (tarea != null && tarea.getReceptor().equals(correoUsuario) && tarea.getDescripcion().equals(tareaSeleccionada)) {
                                        tarea.setEstado(estadoSeleccionado);
                                        ds.getRef().setValue(tarea);
                                        Snackbar.make(v, "Estado actualizado correctamente", Snackbar.LENGTH_SHORT).show();


                                        // Iniciar la actividad PantallaPrincipal
                                        Intent pantallaPrincipal = new Intent(PantallaGestionarTareasAdmin.this, PantallaPrincipal.class);
                                        pantallaPrincipal.putExtras(usuario);
                                        startActivity(pantallaPrincipal);
                                    }
                                }
                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                // Manejar errores de cancelación si es necesario
                            }
                        });
                    }

                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar.make(v, "Has cancelado el proceso", Snackbar.LENGTH_SHORT).show();
                    }
                });

                AlertDialog cuadroDialogo = builder.create();
                cuadroDialogo.show();
            }
        });


    }
}
