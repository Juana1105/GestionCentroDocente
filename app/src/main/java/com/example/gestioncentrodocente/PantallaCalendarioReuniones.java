package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaCalendarioReuniones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_calendario_reuniones);
        getSupportActionBar().hide();

        Button botonVolver=(Button)findViewById(R.id.botonAceptarPantallaCalendario);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaPrincipal=new Intent(PantallaCalendarioReuniones.this,PantallaPrincipal.class);
                startActivity(pantallaPrincipal);
            }
        });
    }
}