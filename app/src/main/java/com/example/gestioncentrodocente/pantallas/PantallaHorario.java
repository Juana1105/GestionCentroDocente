package com.example.gestioncentrodocente.pantallas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gestioncentrodocente.R;
import com.google.android.material.appbar.MaterialToolbar;

public class PantallaHorario extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_horario);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("\tHORARIO");
        getSupportActionBar().setDisplayShowCustomEnabled(true);

    }

    @Override
    public void onClick(View v) {
        Intent pantallaPrin=new Intent(this,PantallaPrincipal.class);
        startActivity(pantallaPrin);
    }
}