package com.example.gestioncentrodocente.pantallas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.gestioncentrodocente.R;
import com.google.android.material.appbar.MaterialToolbar;

public class PantallaGestionarTareasAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_gestionar_tareas_admin);

        //getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("GESTIÓN TAREAS ADMINISTRATIVAS");

        //MaterialToolbar toolbar= findViewById(R.id.encabezadoGestionTareasD);

        Spinner spinnerEstadoTarea=findViewById(R.id.spinnerEstadoTarea);
        Spinner spinnerNombreTarea=(Spinner) findViewById(R.id.spinnerTipoTarea);
        String[] valores = {"Firma de actas","Circular informartiva a padres y estudiantes"};
        spinnerNombreTarea.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores));
        spinnerNombreTarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Button aceptar=(Button)findViewById(R.id.pantallaGestTAceptar);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaPrincipal=(new Intent(PantallaGestionarTareasAdmin.this,PantallaPrincipal.class));
                startActivity(pantallaPrincipal);
            }
        });

        String[] estado = {"Pendiente","En Proceso","Teminada"};
        spinnerEstadoTarea.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,estado));
        spinnerEstadoTarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


/*
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaPrincipal=new Intent(PantallaGestionarTareasAdmin.this,PantallaPrincipal.class);
                startActivity(pantallaPrincipal);
            }
        });*/
    }
}