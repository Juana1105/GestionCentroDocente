package com.example.gestioncentrodocente.pantallas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.adaptadores.AdaptadorReunion;
import com.example.gestioncentrodocente.entidades.Reunion;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class PantallaCalendarioReuniones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_calendario_reuniones);
        //getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("REUNIONES");

        //MaterialToolbar toolbar=findViewById(R.id.encabezadoReuniones);

        ArrayList<Reunion> listaReuniones=new ArrayList<>();
        listaReuniones.add(new Reunion("Reunion 1","Lucia","12/02/2024","Reunion extraordinaria"));
        listaReuniones.add(new Reunion("Reunion 2","Lucia","12/02/2024","Reunion para simulacro de incendio"));
        listaReuniones.add(new Reunion("Reunion 3","Pepe","12/02/2024","Reunion organizacion trimestral"));
        listaReuniones.add(new Reunion("Reunion 4","Pepe","12/02/2024","Reunion excursión de abril"));
        listaReuniones.add(new Reunion("Reunion 5","Lucia","12/02/2024","Reunión fiesta escolar"));

        ListView vistaLista=(ListView) findViewById(R.id.listadoReuniones);

        AdaptadorReunion miAdaptadorReunion =new AdaptadorReunion(this,listaReuniones);

        //Ahora juntamos los elementos declarados
        vistaLista.setAdapter(miAdaptadorReunion);





/*
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaPrin=new Intent(PantallaCalendarioReuniones.this, PantallaPrincipal.class);
                startActivity(pantallaPrin);
            }
        });*/
    }
}