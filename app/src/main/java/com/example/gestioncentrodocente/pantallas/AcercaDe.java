package com.example.gestioncentrodocente.pantallas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gestioncentrodocente.R;
import com.google.android.material.appbar.MaterialToolbar;

public class AcercaDe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
        //getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Acerca De");
        //getSupportActionBar().setDisplayShowCustomEnabled(true);

/*
        MaterialToolbar toolbar= findViewById(R.id.encabezadoAcercaDe);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaPrin=new Intent(AcercaDe.this,PantallaPrincipal.class);
                startActivity(pantallaPrin);
            }
        });*/
    }
}