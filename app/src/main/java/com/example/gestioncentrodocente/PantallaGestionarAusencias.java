package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class PantallaGestionarAusencias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_gestionar_ausencias);

        LinearLayout linearPadre=(LinearLayout)findViewById(R.id.lineaLayoutPantallaGestionAusencias);

        Button botonFechaInicio=(Button)findViewById(R.id.pantallaGAeligeFecha);
        Button botonFechaFinal=(Button)findViewById(R.id.pantallaGAeligeFechaFin);
        Button botonHoraInicio=(Button)findViewById(R.id.pantallaGAeligehorasInicio);
        Button botonHoraFinal=(Button)findViewById(R.id.pantallaGAeligehorasFinal);
        Button botonAceptar=(Button)findViewById(R.id.PAbotonAceptar);
        TextView fechaInicioElegida=(TextView)findViewById(R.id.GAfechaElegida);
        TextView fechaFinalElegida=(TextView)findViewById(R.id.GAfechaElegidaFinal);
        TextView horaInicioElegida=(TextView)findViewById(R.id.GAhoraElegidaInicio);
        TextView horaFinalElegida=(TextView)findViewById(R.id.GAhoraElegidaFinal);
        Spinner spinnerSimple=(Spinner)findViewById(R.id.spinnerPantallaGA);

        String[] valores = {"Baja","Enfermedad", "Consulta Médica", "Otro"};

        spinnerSimple.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores));

        spinnerSimple.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
                        String fechaElegidaCadena="Fecha elegida: "+dayOfMonth+"/"+month+"/"+year;
                        fechaInicioElegida.setText(fechaElegidaCadena);
                        Snackbar barra=Snackbar.make(linearPadre,fechaElegidaCadena,Snackbar.LENGTH_SHORT);
                        barra.show();
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
                        String fechaElegidaCadena="Fecha elegida: "+dayOfMonth+"/"+month+"/"+year;
                        fechaFinalElegida.setText(fechaElegidaCadena);
                        Snackbar barra=Snackbar.make(linearPadre,fechaElegidaCadena,Snackbar.LENGTH_SHORT);
                        barra.show();
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

        botonAceptar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());

                builder.setTitle("Mensaje Informativo");
                builder.setMessage("Para mandar tu ausencia tienes que hacer clic en 'aceptar'");
                builder.setIcon(android.R.drawable.btn_star_big_on);

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        View padre=(View) view.getParent();
                        Snackbar barra= Snackbar.make(padre,"Ausencia notificada satisfactoriamente",Snackbar.LENGTH_SHORT);
                        barra.show();
                        Intent pantallaPrincipal= new Intent(PantallaGestionarAusencias.this, PantallaPrincipal.class);
                        startActivity(pantallaPrincipal);
                    }

                });

                builder.setNegativeButton("No aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        View padre=(View) view.getParent();
                        Snackbar barra= Snackbar.make(padre,"Si no aceptas modifica algún campo",Snackbar.LENGTH_SHORT);
                        barra.show();
                    }
                });

                builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        View padre=(View) view.getParent();
                        Snackbar barra= Snackbar.make(padre,"Has cancelado la gestión de ausencia",Snackbar.LENGTH_SHORT);
                        barra.show();
                    }
                });
                AlertDialog cuadroDialogo = builder.create();
                cuadroDialogo.show();
            }
        });
    }
}