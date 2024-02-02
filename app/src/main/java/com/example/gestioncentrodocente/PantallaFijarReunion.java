package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class PantallaFijarReunion extends AppCompatActivity {

    private boolean[] checked = {false, false, false, false, false};
    private Set<String> seleccionados = new HashSet<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_fijar_reunion);
        getSupportActionBar().hide();

        MaterialToolbar toolbar=findViewById(R.id.encabezadoFijarReuniones);
        MaterialButton botonElige=findViewById(R.id.pantallaFRreceptores);
        MaterialButton eligeFecha=findViewById(R.id.pantallaJEreunionEligeFecha);
        TextView ponSeleccion=findViewById(R.id.seleccionAvisoEA);
        TextView fechaElegida=findViewById(R.id.GAfechaElegida);
        LinearLayout linearPadre=findViewById(R.id.contenedorElementosFijarReunion);
        botonElige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PantallaFijarReunion.this);
                builder.setTitle("Elige a quienes vas a enviar el aviso de la reunion");
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

        eligeFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendario=Calendar.getInstance();
                int year= calendario.get(Calendar.YEAR);
                int month=calendario.get(Calendar.MONTH);
                int day=calendario.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog selectorFecha = new DatePickerDialog(PantallaFijarReunion.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        String fechaElegidaCadena="Fecha elegida: "+dayOfMonth+"/"+month+"/"+year;
                        fechaElegida.setText(fechaElegidaCadena);
                        Snackbar barra=Snackbar.make(linearPadre,fechaElegidaCadena,Snackbar.LENGTH_SHORT);
                        barra.show();
                    }
                },year,month,day);
                selectorFecha.show();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaPrincipal= new Intent(PantallaFijarReunion.this,PantallaPrincipal.class);
                startActivity(pantallaPrincipal);
            }
        });
    }
}