package com.example.gestioncentrodocente.pantallas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.entidades.Usuario;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AcercaDe extends AppCompatActivity {

    //DatabaseReference dbRef;
    //Usuario datosUsuario;
   // private Bundle usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
        //getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Acerca De");
        //getSupportActionBar().setDisplayShowCustomEnabled(true);

     //   usuario = getIntent().getExtras();
       // datosUsuario=new Usuario();


       // dbRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");


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