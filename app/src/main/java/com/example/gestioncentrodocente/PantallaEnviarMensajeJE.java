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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class PantallaEnviarMensajeJE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_enviar_mensaje_je);

        LinearLayout linearPadre=(LinearLayout)findViewById(R.id.lineaLayoutPantallaEnvioMensaje);

        Button botonEnviar=(Button)findViewById(R.id.botonPantallaEMenviar);
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaP=new Intent(PantallaEnviarMensajeJE.this,PantallaPrincipal.class);
                startActivity(pantallaP);
            }
        });
        Spinner envioMensaje=(Spinner)findViewById(R.id.spinnerEM);
        String[] valores = {"Urgente","Informativo","Normal", "Otro"};
        envioMensaje.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores));
        envioMensaje.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button eligeFecha=(Button)findViewById(R.id.pantallaEMeligeFechaBoton);
        TextView fechaSeleccionada=(TextView)findViewById(R.id.pantallaEMfechaElegida);
        eligeFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendario=Calendar.getInstance();
                int year= calendario.get(Calendar.YEAR);
                int month=calendario.get(Calendar.MONTH);
                int day=calendario.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog selectorFecha = new DatePickerDialog(PantallaEnviarMensajeJE.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        String fechaElegidaCadena=dayOfMonth+"/"+month+"/"+year;
                        fechaSeleccionada.setText(fechaElegidaCadena);
                        Snackbar barra=Snackbar.make(linearPadre,fechaElegidaCadena,Snackbar.LENGTH_SHORT);
                        barra.show();
                    }
                },year,month,day);
                selectorFecha.show();
            }
        });


    }
}