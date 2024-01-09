package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class PantallaEnviarAvisos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_enviar_avisos);
        LinearLayout linearPadre=(LinearLayout)findViewById(R.id.lineaLayoutPantallaAvisos);

        Spinner spinnerSimple=(Spinner) findViewById(R.id.spinnerEA);
        String[] valores = {"Urgente","Normal", "Otro"};

        spinnerSimple.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores));

        spinnerSimple.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button enviarAviso=(Button)findViewById(R.id.botonPantallaEAenviarAvisos);
        enviarAviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaPrincipal=new Intent(PantallaEnviarAvisos.this,PantallaPrincipal.class);
                startActivity(pantallaPrincipal);
            }
        });
        Button botonCalendario=(Button) findViewById(R.id.pantallaEAeligeFechaBoton);
        TextView fechaEscrita=(TextView)findViewById(R.id.pantallaEAfecha);
        botonCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendario=Calendar.getInstance();
                int year= calendario.get(Calendar.YEAR);
                int month=calendario.get(Calendar.MONTH);
                int day=calendario.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog selectorFecha = new DatePickerDialog(PantallaEnviarAvisos.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        String fechaElegidaCadena=dayOfMonth+"/"+month+"/"+year;
                        fechaEscrita.setText(fechaElegidaCadena);
                        Snackbar barra=Snackbar.make(linearPadre,fechaElegidaCadena,Snackbar.LENGTH_SHORT);
                        barra.show();
                    }
                },year,month,day);
                selectorFecha.show();
            }
        });

    }


}