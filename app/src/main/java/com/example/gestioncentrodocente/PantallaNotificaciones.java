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


        ArrayList<Notificacion> listaNotificaciones=new ArrayList<>();
        listaNotificaciones.add(new Notificacion("Lucia","urgente","Cambio horario","Se cambia la hora", R.drawable.urgente));
        listaNotificaciones.add(new Notificacion("Juana","urgente","Baja laboral","Me voy al médico", R.drawable.urgente));
        listaNotificaciones.add(new Notificacion("Pepe","normal","Saludo","Hola", R.drawable.normal));

        ListView vistaLista=(ListView) findViewById(R.id.listadoNotificaciones);

        Adaptador miAdaptador =new Adaptador(this,listaNotificaciones);

        //Ahora juntamos los elementos declarados
        vistaLista.setAdapter(miAdaptador);
    }
}