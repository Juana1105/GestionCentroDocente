package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.HashSet;
import java.util.Set;

public class PantallaGestionarTareasAdminJE extends AppCompatActivity {

    private boolean[] checked = {false, false, false, false, false};
    private Set<String> seleccionados = new HashSet<>();

    String seleccionado="";

    String[] valores =null;
    String[] valores2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_gestion_tareas_je);
        getSupportActionBar().hide();

        MaterialButton adjudicar=findViewById(R.id.botonPantallaGestTadjudicar);

        MaterialButton botonElige=findViewById(R.id.pantallaGTJEreceptores);
        TextView ponSeleccion=findViewById(R.id.seleccionTareasGT);
        Spinner spinnerAsignacionTareas=findViewById(R.id.spinnerTipoTarea);
        Spinner spinnerTareaEspecifica=findViewById(R.id.spinnerTareaEspecifica);
        EditText observaciones=findViewById(R.id.observacionesJEtareas);

        String observacionesJefeEstudios=observaciones.getText().toString();



        botonElige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PantallaGestionarTareasAdminJE.this);
                builder.setTitle("Elige a quienes vas a enviar el aviso");
                String[] participantes = {"Ana", "Ricardo", "Lucia", "Juana", "Pepe"};

                builder.setMultiChoiceItems(participantes, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            seleccionados.add(participantes[which]);
                            checked[which] = true;
                        } else {
                            seleccionados.remove(participantes[which]);
                            checked[which] = false;
                        }

                        ponSeleccion.setText(seleccionados.toString());
                    }
                });

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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






        valores = new String[]{"Preparación de Informes","Organización de Eventos","Gestión de Recursos","Supervision de Proyectos Educativos","Comunicación y Correspondencia"};

        spinnerAsignacionTareas.setAdapter(new ArrayAdapter<String>(PantallaGestionarTareasAdminJE.this, android.R.layout.simple_spinner_item,valores));

        spinnerAsignacionTareas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seleccionado=valores[position];

                if(seleccionado.equals("Preparación de Informes")){
                    valores2= new String[]{"Informe de evaluación trimestral","Informe de rendimiento académico","Informe de asistencia"};
                } else if(seleccionado.equals("Organización de Eventos")){
                    valores2= new String[]{"Organización de jornadas a puertas abiertas","PLanificación de eventos culturales/deportivos"};
                } else if(seleccionado.equals("Gestión de Recursos")){
                    valores2= new String[]{"Control de inventario de material educativo","Solicitud de material didáctico"};
                } else if(seleccionado.equals("Supervision de Proyectos Educativos")){
                    valores2= new String[]{"Proyecto de investigación educativa", "Informe de evaluación del programa de formación al docente"};
                } else if(seleccionado.equals("Comunicación y Correspondencia")){
                    valores2= new String[]{"Firma de actas","Circular informartiva a padres y estudiantes"};
                }

                //spinnerde tarea especifica
                spinnerTareaEspecifica.setAdapter(new ArrayAdapter<String>(PantallaGestionarTareasAdminJE.this, android.R.layout.simple_spinner_item,valores2));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });









        adjudicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaPrincipal=new Intent(PantallaGestionarTareasAdminJE.this,PantallaPrincipal.class);
                startActivity(pantallaPrincipal);
            }
        });


    }
}