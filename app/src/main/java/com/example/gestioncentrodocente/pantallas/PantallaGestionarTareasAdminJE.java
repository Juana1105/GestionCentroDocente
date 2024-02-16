package com.example.gestioncentrodocente.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.entidades.Reunion;
import com.example.gestioncentrodocente.entidades.TareaAdministrativa;
import com.example.gestioncentrodocente.entidades.Usuario;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PantallaGestionarTareasAdminJE extends AppCompatActivity {
    private Bundle usuario;
    private DatabaseReference dbRef, dbRefTareas;
    private ArrayList<Usuario> listaPosibles = new ArrayList<>();
    private Set<String> seleccionados = new HashSet<>();

    String seleccionado = "",seleccionado2="";
    String[] valores = null;
    String[] valores2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_gestion_tareas_je);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("FIJAR TAREAS ADMINISTRATIVAS");

        MaterialButton botonAdjudicar = findViewById(R.id.botonPantallaGestTadjudicar);
        MaterialButton botonElige = findViewById(R.id.pantallaGTJEreceptores);
        TextView ponSeleccion = findViewById(R.id.seleccionTareasGT);
        Spinner spinnerAsignacionTareas = findViewById(R.id.spinnerTipoTarea);
        Spinner spinnerTareaEspecifica = findViewById(R.id.spinnerTareaEspecifica);


        usuario = getIntent().getExtras();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Usuario u = ds.getValue(Usuario.class);
                    listaPosibles.add(u);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        botonElige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PantallaGestionarTareasAdminJE.this);
                builder.setTitle("Elige a quienes vas a la tarea administrativa");

                String[] correosElectronicos = new String[listaPosibles.size()];

                for (int i = 0; i < listaPosibles.size(); i++) {
                    correosElectronicos[i] = listaPosibles.get(i).getEmail();
                }
                boolean[] checked = new boolean[correosElectronicos.length];
                builder.setMultiChoiceItems(correosElectronicos, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            seleccionados.add(correosElectronicos[which]);
                        } else {
                            seleccionados.remove(correosElectronicos[which]);
                        }
                    }
                });

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ponSeleccion.setText(seleccionados.toString());
                    }
                });

                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        valores = new String[]{"Preparación de Informes", "Organización de Eventos", "Gestión de Recursos", "Supervision de Proyectos Educativos", "Comunicación y Correspondencia"};

        spinnerAsignacionTareas.setAdapter(new ArrayAdapter<String>(PantallaGestionarTareasAdminJE.this, android.R.layout.simple_spinner_item, valores));

        spinnerAsignacionTareas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seleccionado = valores[position];

                if (seleccionado.equals("Preparación de Informes")) {
                    valores2 = new String[]{"Informe de evaluación trimestral", "Informe de rendimiento académico", "Informe de asistencia"};
                } else if (seleccionado.equals("Organización de Eventos")) {
                    valores2 = new String[]{"Organización de jornadas a puertas abiertas", "PLanificación de eventos culturales/deportivos"};
                } else if (seleccionado.equals("Gestión de Recursos")) {
                    valores2 = new String[]{"Control de inventario de material educativo", "Solicitud de material didáctico"};
                } else if (seleccionado.equals("Supervision de Proyectos Educativos")) {
                    valores2 = new String[]{"Proyecto de investigación educativa", "Informe de evaluación del programa de formación al docente"};
                } else if (seleccionado.equals("Comunicación y Correspondencia")) {
                    valores2 = new String[]{"Firma de actas", "Circular informartiva a padres y estudiantes"};
                }

                spinnerTareaEspecifica.setAdapter(new ArrayAdapter<String>(PantallaGestionarTareasAdminJE.this, android.R.layout.simple_spinner_item, valores2));
                spinnerTareaEspecifica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        seleccionado2=valores2[position];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        botonAdjudicar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                View padre=(View) view.getParent();
                EditText observacionesE = findViewById(R.id.observacionesJEtareas);
                String observaciones=observacionesE.getText().toString();

                if (seleccionados.isEmpty()) {
                    Snackbar.make(view, "Debes seleccionar al menos un receptor", Snackbar.LENGTH_SHORT).show();
                    return;
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Mensaje Informativo");
                    builder.setMessage("Para fijar la tarea tienes que hacer clic en 'aceptar'");
                    builder.setIcon(android.R.drawable.ic_dialog_info);

                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dbRefTareas = FirebaseDatabase.getInstance().getReference().child("Tareas");

                            for (String participante : seleccionados) {
                                TareaAdministrativa nuevaTarea = new TareaAdministrativa();
                                nuevaTarea.setReceptor(participante);
                                nuevaTarea.setDescripcion(seleccionado2);
                                nuevaTarea.setEstado("");
                                nuevaTarea.setObservaciones(observaciones);

                                String key = dbRefTareas.push().getKey();
                                dbRefTareas.child(key).setValue(nuevaTarea);
                            }

                            Intent pantallaPrincipal = new Intent(PantallaGestionarTareasAdminJE.this, PantallaPrincipal.class);
                            pantallaPrincipal.putExtras(usuario);
                            startActivity(pantallaPrincipal);
                        }
                    });

                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Snackbar.make(padre, "Has cancelado el proceso", Snackbar.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog cuadroDialogo = builder.create();
                    cuadroDialogo.show();
                }


            }
        });
    }
}
