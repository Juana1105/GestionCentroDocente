package com.example.gestioncentrodocente.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.entidades.Ausencia;
import com.example.gestioncentrodocente.entidades.Guardia;
import com.example.gestioncentrodocente.entidades.Permiso;
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
import java.util.Calendar;

public class PantallaGestionarAusencias extends AppCompatActivity {
    private Bundle usuario;
    private DatabaseReference dbRef,dbRef2;
    String correoUsuario,motivo,idString;
    private int id=0;
    Ausencia crearAusencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_gestionar_ausencias);
        //getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("GESTIÓN AUSENCIAS");

        LinearLayout linearPadre=findViewById(R.id.linearLayoutPantallaGestionAusencias);
        MaterialButton botonFechaInicio=findViewById(R.id.pantallaGAeligeFecha);
        MaterialButton botonFechaFinal=findViewById(R.id.pantallaGAeligeFechaFin);
        MaterialButton botonHoraInicio=findViewById(R.id.pantallaGAeligehorasInicio);
        MaterialButton botonHoraFinal=findViewById(R.id.pantallaGAeligehorasFinal);
        MaterialButton botonAceptar=findViewById(R.id.PAbotonAceptar);
        TextView fechaInicioElegida=(TextView)findViewById(R.id.GAfechaElegida);
        TextView fechaFinalElegida=(TextView)findViewById(R.id.GAfechaElegidaFinal);
        TextView horaInicioElegida=(TextView)findViewById(R.id.GAhoraElegidaInicio);
        TextView horaFinalElegida=(TextView)findViewById(R.id.GAhoraElegidaFinal);
        Spinner spinnerSimple=(Spinner)findViewById(R.id.spinnerPantallaGA);
        //MaterialToolbar toolbar=findViewById(R.id.encabezadoGestionarAusencia);

        usuario = getIntent().getExtras();
        dbRef= FirebaseDatabase.getInstance().getReference().child("Usuarios");
        dbRef.orderByChild("dni").equalTo(usuario.getString("dni")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Usuario u = ds.getValue(Usuario.class);
                    correoUsuario=u.getEmail();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        String[] valores = {"Baja","Enfermedad", "Consulta Médica", "Causa de fuerza mayor"};

        spinnerSimple.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores));

        spinnerSimple.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                motivo=valores[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        botonFechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendario=Calendar.getInstance();
                int year= calendario.get(Calendar.YEAR);
                int month=calendario.get(Calendar.MONTH);
                int day=calendario.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog selectorFecha = new DatePickerDialog(PantallaGestionarAusencias.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month++; // Ajusta el valor del mes porque en Java los meses van de 0 a 11
                        // Formatea la fecha utilizando SimpleDateFormat
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar fecha = Calendar.getInstance();
                        fecha.set(year, month, dayOfMonth);
                        String fechaFormateada = formato.format(fecha.getTime());

                        // Actualiza el TextView con la fecha formateada
                        fechaInicioElegida.setText(fechaFormateada);
                    }
                },year,month,day);
                selectorFecha.show();
            }
        });

        botonHoraInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour=Calendar.HOUR;
                int minute=Calendar.MINUTE;
                TimePickerDialog selectorHora=new TimePickerDialog(PantallaGestionarAusencias.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                        String horaElegidaCadena="Hora elegida:"+ hour+":"+minute;
                        horaInicioElegida.setText(horaElegidaCadena);
                        Snackbar barra2=Snackbar.make(linearPadre,horaElegidaCadena,Snackbar.LENGTH_SHORT);
                        barra2.show();
                    }
                },hour,minute,true);

                selectorHora.show();

            }
        });



        botonFechaFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendario=Calendar.getInstance();
                int year= calendario.get(Calendar.YEAR);
                int month=calendario.get(Calendar.MONTH);
                int day=calendario.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog selectorFecha = new DatePickerDialog(PantallaGestionarAusencias.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month++; // Ajusta el valor del mes porque en Java los meses van de 0 a 11
                        // Formatea la fecha utilizando SimpleDateFormat
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar fecha = Calendar.getInstance();
                        fecha.set(year, month, dayOfMonth);
                        String fechaFormateada = formato.format(fecha.getTime());

                        // Actualiza el TextView con la fecha formateada
                        fechaFinalElegida.setText(fechaFormateada);
                    }
                },year,month,day);
                selectorFecha.show();
            }
        });

        botonHoraFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour=Calendar.HOUR;
                int minute=Calendar.MINUTE;
                TimePickerDialog selectorHora=new TimePickerDialog(PantallaGestionarAusencias.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                        String horaElegidaCadena="Hora elegida:"+ hour+":"+minute;
                        horaFinalElegida.setText(horaElegidaCadena);
                        Snackbar barra2=Snackbar.make(linearPadre,horaElegidaCadena,Snackbar.LENGTH_SHORT);
                        barra2.show();
                    }
                },hour,minute,true);

                selectorHora.show();

            }
        });


        dbRef2= FirebaseDatabase.getInstance().getReference().child("Ausencias");
        botonAceptar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                View padre = (View) view.getParent();
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
                                    Snackbar.make(padre, "Este id de ausencia ya existe", Snackbar.LENGTH_SHORT).show();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                    builder.setTitle("Mensaje Informativo");
                                    builder.setMessage("Para guardar la ausencia haz clic en 'aceptar'");
                                    builder.setIcon(android.R.drawable.ic_dialog_info);

                                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            crearAusencia = new Ausencia(id, correoUsuario, motivo,fechaInicioElegida.getText().toString(),horaInicioElegida.getText().toString(),fechaFinalElegida.getText().toString(),horaFinalElegida.getText().toString() );
                                            idString = String.valueOf(id);
                                            dbRef2.child(idString).setValue(crearAusencia);
                                            id++;

                                            Intent pantallaPrincipal= new Intent(PantallaGestionarAusencias.this, PantallaPrincipal.class);
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

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





            }

        });
    }
}