package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class PantallaHorario extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_horario);
        getSupportActionBar().hide();

        MaterialToolbar toolbarcito=findViewById(R.id.encabezadoHorario);

        toolbarcito.setNavigationOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent pantallaPrin=new Intent(this,PantallaPrincipal.class);
        startActivity(pantallaPrin);
    }
}