package com.example.gestioncentrodocente.pantallas;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.entidades.Reunion;
import com.example.gestioncentrodocente.entidades.Usuario;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class PantallaFijarReunion extends AppCompatActivity {
    private Bundle usuario;
    private DatabaseReference dbRef,dbRef2;
    ArrayList<Usuario> listaPosibles=new ArrayList<>();;
    Reunion crearReunion;

    private boolean[] checked = {false, false, false, false, false};
    private Set<String> seleccionados = new HashSet<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_fijar_reunion);
        //getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("CREAR REUNIÓN");

       // MaterialToolbar toolbar=findViewById(R.id.encabezadoFijarReuniones);
        MaterialButton botonElige=findViewById(R.id.pantallaFRreceptores);
        MaterialButton eligeFecha=findViewById(R.id.pantallaJEreunionEligeFecha);
        TextView ponSeleccion=findViewById(R.id.seleccionAvisoEA);
        TextView fechaElegida=findViewById(R.id.GAfechaElegida);
        LinearLayout linearPadre=findViewById(R.id.contenedorElementosFijarReunion);
        MaterialButton botonEnviar=findViewById(R.id.botonFijarReunion);


        usuario = getIntent().getExtras();
        dbRef= FirebaseDatabase.getInstance().getReference().child("Usuarios");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {//para coger a todos los usuarios, no solamente el mio
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Usuario u = ds.getValue(Usuario.class);
                    listaPosibles.add(u);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar errores de base de datos
            }
        });



        botonElige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PantallaFijarReunion.this);
                builder.setTitle("Elige a quienes vas a enviar el aviso de la reunion");

                // Crear un array para almacenar los correos electrónicos de los usuarios
                String[] correosElectronicos = new String[listaPosibles.size()];

                // Llenar el array con los correos electrónicos de los usuarios
                for (int i = 0; i < listaPosibles.size(); i++) {
                    correosElectronicos[i] = listaPosibles.get(i).getEmail();
                }

                // Usar el array de correos electrónicos para construir el diálogo de selección
                builder.setMultiChoiceItems(correosElectronicos, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            seleccionados.add(correosElectronicos[which]);
                            checked[which] = true;
                        } else {
                            seleccionados.remove(correosElectronicos[which]);
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


        eligeFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Calendar calendario = Calendar.getInstance();
                int year = calendario.get(Calendar.YEAR);
                int month = calendario.get(Calendar.MONTH);
                int day = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog selectorFecha = new DatePickerDialog(PantallaFijarReunion.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month++; // Ajusta el valor del mes porque en Java los meses van de 0 a 11
                        // Formatea la fecha utilizando SimpleDateFormat
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar fecha = Calendar.getInstance();
                        fecha.set(year, month, dayOfMonth);
                        String fechaFormateada = formato.format(fecha.getTime());

                        // Actualiza el TextView con la fecha formateada
                        fechaElegida.setText(fechaFormateada);
                    }
                }, year, month, day);
                selectorFecha.show();


            }
        });



        crearReunion=new Reunion();
        TextView fechaE=findViewById(R.id.GAfechaElegida);
        EditText motivoE=findViewById(R.id.motivoReunion);
        EditText nombreReunionE=findViewById(R.id.fr_nombreReunion);


        dbRef2= FirebaseDatabase.getInstance().getReference().child("Reuniones");




        botonEnviar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                View padre=(View) v.getParent();

                String fecha=fechaE.getText().toString();
                String motivo=motivoE.getText().toString();
                String nombre=nombreReunionE.getText().toString();



                dbRef2.orderByChild("nombre").equalTo(nombre).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Snackbar.make(padre, "Este nombre de reunion ya existe", Snackbar.LENGTH_SHORT).show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            builder.setTitle("Mensaje Informativo");
                            builder.setMessage("Para fijar la reunión tienes que hacer clic en 'aceptar'");
                            builder.setIcon(android.R.drawable.ic_dialog_info);

                            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    for (String participante : seleccionados) {
                                        // Crear un nombre único para la reunión que incluya el nombre y el participante
                                        String nombreUnicoReunion = nombre + "_" + participante.replace(".", "");

                                        // Crear un nuevo objeto Reunion para cada participante
                                        Reunion nuevaReunion = new Reunion();
                                        nuevaReunion.setNombreReunion(nombreUnicoReunion);
                                        nuevaReunion.setFecha(fecha);
                                        nuevaReunion.setMotivo(motivo);
                                        nuevaReunion.setReceptor(participante);

                                        // Guardar el objeto Reunion en la base de datos
                                        dbRef2.child(nombreUnicoReunion).setValue(nuevaReunion);
                                    }

                                    Intent pantallaPrincipal= new Intent(PantallaFijarReunion.this, PantallaPrincipal.class);
                                    pantallaPrincipal.putExtras(usuario);
                                    startActivity(pantallaPrincipal);
                                }
                            });

                            builder.setNegativeButton("No aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Snackbar.make(padre, "Si no aceptas modifica algún campo", Snackbar.LENGTH_SHORT).show();
                                }
                            });

                            builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Snackbar.make(padre, "Has cancelado el registro de usuario", Snackbar.LENGTH_SHORT).show();
                                }
                            });

                            AlertDialog cuadroDialogo = builder.create();
                            cuadroDialogo.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });




            }

        });
    }
}