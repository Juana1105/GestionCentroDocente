package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PantallaGestionAdmin2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_gestion_admin2);
        Button adjudicar=(Button)findViewById(R.id.botonPantallaGestTadjudicar);
        adjudicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaPrincipal=new Intent(PantallaGestionAdmin2.this,PantallaPrincipal.class);
                startActivity(pantallaPrincipal);
            }
        });

        Spinner spinnerSimple=(Spinner)findViewById(R.id.spinnerTipoTarea);
        String[] valores = {"Entrega Programación","Hoja mensual de Actividad","Firma de actas", "Otro"};
        spinnerSimple.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores));
        spinnerSimple.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}