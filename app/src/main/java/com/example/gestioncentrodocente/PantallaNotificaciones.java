package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PantallaNotificaciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_notificaciones);
        getSupportActionBar().hide();


        ArrayList<Notificacion> listaNotificaciones=new ArrayList<>();
        listaNotificaciones.add(new Notificacion("Lucia","Tarea","Nueva tarea para entregar", R.drawable.gestionar_tareas));
        listaNotificaciones.add(new Notificacion("Lucia","Guardia","Te asigno una nueva guardia para mañana", R.drawable.guardias));
        listaNotificaciones.add(new Notificacion("Pepe","Aviso","Este aviso coordinador es de prueba", R.drawable.aviso_coordinador));
        listaNotificaciones.add(new Notificacion("Pepe","Aviso","Esto es un aviso que manda el coordinador", R.drawable.aviso_coordinador));
        listaNotificaciones.add(new Notificacion("Lucia","Mensaje","Esto es un mensaje de prueba", R.drawable.mail));

        ListView vistaLista=(ListView) findViewById(R.id.listadoNotificaciones);

        Adaptador miAdaptador =new Adaptador(this,listaNotificaciones);

        //Ahora juntamos los elementos declarados
        vistaLista.setAdapter(miAdaptador);
    }
}