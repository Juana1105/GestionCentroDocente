package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class PantallaGestionGuardias extends AppCompatActivity {

    private String seleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_gestion_guardias);
        getSupportActionBar().hide();

        LinearLayout linearPadre=(LinearLayout)findViewById(R.id.lineaLayoutPantallaGestionGuardias);
        MaterialButton botonElige=findViewById(R.id.pantallaGGreceptorGuardia);
        MaterialToolbar toolbar=findViewById(R.id.encabezadoGuardias);

        botonElige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PantallaGestionGuardias.this);
                builder.setTitle("Elige a la persona que hará la guardia");
                String[] participantes = {"Julian","Pepe","Jose","Marisa","Ginebra","Antonia"};


                builder.setSingleChoiceItems(participantes, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        seleccionado=participantes[which];

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

        Spinner spinnerSimple=(Spinner)findViewById(R.id.spinnerTipoGuardia);
        String[] valores = {"Urgente","Predeterminada", "Otro"};
        spinnerSimple.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores));

        spinnerSimple.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
                        String fechaElegidaCadena=dayOfMonth+"/"+month+"/"+year;
                        textoFecha.setText(fechaElegidaCadena);
                        Snackbar barra=Snackbar.make(linearPadre,fechaElegidaCadena,Snackbar.LENGTH_SHORT);
                        barra.show();
                    }
                },year,month,day);
                selectorFecha.show();
            }
        });

        Button botonGuardar=(Button)findViewById(R.id.botonPantallaGguardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaP=new Intent(PantallaGestionGuardias.this,PantallaPrincipal.class);
                startActivity(pantallaP);
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaPrincipal=new Intent(PantallaGestionGuardias.this, PantallaPrincipal.class);
                startActivity(pantallaPrincipal);
            }
        });

    }
}