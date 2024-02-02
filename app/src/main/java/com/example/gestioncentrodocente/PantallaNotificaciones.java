package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.gestioncentrodocente.entidades.Notificacion;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class PantallaNotificaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_notificaciones);
        getSupportActionBar().hide();

        MaterialToolbar toolbar=findViewById(R.id.encabezadoNotificaciones);

        ArrayList<Notificacion> listaNotificaciones=new ArrayList<>();
        listaNotificaciones.add(new Notificacion("Lucia","Tarea","Nueva tarea para entregar", R.drawable.gestionar_tareas));
        listaNotificaciones.add(new Notificacion("Lucia","Guardia","Te asigno una nueva guardia para mañana", R.drawable.guardias));
        listaNotificaciones.add(new Notificacion("Pepe","Aviso","Este aviso coordinador es de prueba", R.drawable.aviso_coordinador));
        listaNotificaciones.add(new Notificacion("Pepe","Aviso","Esto es un aviso que manda el coordinador", R.drawable.aviso_coordinador));
        listaNotificaciones.add(new Notificacion("Lucia","Mensaje","Esto es un mensaje de prueba", R.drawable.mail));

        ListView vistaLista=(ListView) findViewById(R.id.listadoNotificaciones);

        AdaptadorNotificacion miAdaptadorNotificacion =new AdaptadorNotificacion(this,listaNotificaciones);

        //Ahora juntamos los elementos declarados
        vistaLista.setAdapter(miAdaptadorNotificacion);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaPrin=new Intent(PantallaNotificaciones.this, PantallaPrincipal.class);
                startActivity(pantallaPrin);
            }
        });
    }
}