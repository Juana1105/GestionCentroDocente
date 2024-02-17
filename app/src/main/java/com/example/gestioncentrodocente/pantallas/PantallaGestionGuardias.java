package com.example.gestioncentrodocente.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.SQLite.GestionCentroDocenteDBHelper;
import com.example.gestioncentrodocente.entidades.Guardia;
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
import java.util.ArrayList;
import java.util.Calendar;

public class PantallaGestionGuardias extends AppCompatActivity {

    private Bundle usuario;
    private DatabaseReference dbRef,dbRef2;
    private ArrayList<String> participantes = new ArrayList<>();
    private String seleccionado,tipoGuardia,fecha,idString,observaciones;
    private Guardia crearGuardia;
    private int id=0;
    SQLiteDatabase baseDatos;
    GestionCentroDocenteDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_gestion_guardias);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("GESTIÓN GUARDIAS");

        dbHelper = new GestionCentroDocenteDBHelper(this);
        baseDatos=dbHelper.getWritableDatabase();
        TextView personaSeleccionadaText=findViewById(R.id.personaSeleccionadaGuardia);
        EditText observacionesE=findViewById(R.id.observacionesFijarGuardia);
        MaterialButton botonElige=findViewById(R.id.pantallaGGreceptorGuardia);

        usuario = getIntent().getExtras();
        dbRef= FirebaseDatabase.getInstance().getReference().child("Usuarios");
        //Accedemos a la base de datos para coger a todos los usuarios, no solamente el mio
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Limpiar la lista de participantes
                participantes.clear();
                // Recorrer los datos y agregar los nombres de los usuarios a la lista
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Usuario u = ds.getValue(Usuario.class);
                    if (u != null && u.getNombre() != null) {
                        participantes.add(u.getNombre());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        botonElige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PantallaGestionGuardias.this);
                builder.setTitle("Elige a la persona que hará la guardia");
                // Convertir el ArrayList a un array de Strings para usarlo en setSingleChoiceItems
                String[] participantesArray = participantes.toArray(new String[0]);
                builder.setSingleChoiceItems(participantesArray, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        seleccionado = participantes.get(which);
                    }
                });
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        personaSeleccionadaText.setText(seleccionado);
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

        Spinner spinnerSimple=(Spinner)findViewById(R.id.spinnerTipoGuardia);
        String[] valores = {"Urgente","Predeterminada", "Otro"};
        spinnerSimple.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores));
        spinnerSimple.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipoGuardia=valores[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button botonCalendario=(Button)findViewById(R.id.pantallaEAeligeFechaBoton);
        TextView textoFecha=(TextView)findViewById(R.id.fechaSeleccionadaPantallaG);
        botonCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendario=Calendar.getInstance();
                int year= calendario.get(Calendar.YEAR);
                int month=calendario.get(Calendar.MONTH);
                int day=calendario.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog selectorFecha = new DatePickerDialog(PantallaGestionGuardias.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month++; // Ajusta el valor del mes porque en Java los meses van de 0 a 11
                        // Formatea la fecha utilizando SimpleDateFormat
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar fecha = Calendar.getInstance();
                        fecha.set(year, month, dayOfMonth);
                        String fechaFormateada = formato.format(fecha.getTime());
                        // Actualiza el TextView con la fecha formateada
                        textoFecha.setText(fechaFormateada);
                    }
                },year,month,day);
                selectorFecha.show();
            }
        });

        fecha=textoFecha.getText().toString();
        dbRef2= FirebaseDatabase.getInstance().getReference().child("Guardias");
        Button botonGuardar=(Button)findViewById(R.id.botonPantallaGguardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fecha = textoFecha.getText().toString();
                observaciones = observacionesE.getText().toString();
                View padre=(View) v.getParent();
                dbRef2.orderByChild("id").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Guardia lastGuardia = dataSnapshot.getValue(Guardia.class);
                            id = lastGuardia.getId() + 1;
                        }
                        dbRef2.orderByChild("id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    Snackbar.make(padre, "Este id de guardia ya existe", Snackbar.LENGTH_SHORT).show();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                    builder.setTitle("Mensaje Informativo");
                                    builder.setMessage("Para fijar la guardia tienes que hacer clic en 'aceptar'");
                                    builder.setIcon(android.R.drawable.ic_dialog_info);
                                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            crearGuardia=new Guardia(id,seleccionado,fecha,tipoGuardia,observaciones);
                                            idString=String.valueOf(id);
                                            dbRef2.child(idString).setValue(crearGuardia);
                                            id++;

                                            //SQLITE
                                            ContentValues contenido=new ContentValues();
                                            contenido.put("receptor",seleccionado);
                                            contenido.put("fecha",fecha);
                                            contenido.put("tipoGuardia",tipoGuardia);
                                            contenido.put("observaciones",observaciones);
                                            baseDatos.insert("guardia",null,contenido);
                                            //VOLVER A PANTALLA PRINCIPAL
                                            Intent pantallaP=new Intent(PantallaGestionGuardias.this,PantallaPrincipal.class);
                                            pantallaP.putExtras(usuario);
                                            startActivity(pantallaP);
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

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Manejar el error
                    }
                });
            }
        });

    }
}