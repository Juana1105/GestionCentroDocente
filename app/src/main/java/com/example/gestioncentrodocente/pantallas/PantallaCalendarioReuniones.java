package com.example.gestioncentrodocente.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.adaptadores.AdaptadorReunion;
import com.example.gestioncentrodocente.entidades.Reunion;
import com.example.gestioncentrodocente.entidades.Usuario;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PantallaCalendarioReuniones extends AppCompatActivity {


    private Bundle usuario;
    private DatabaseReference dbRef,dbRefReuniones;
    private String correoUsuario;
    private Usuario usu;
    private ArrayList<Reunion> listaReuniones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_calendario_reuniones);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("REUNIONES");
        listaReuniones=new ArrayList<>();
        ListView vistaLista=(ListView) findViewById(R.id.listadoReuniones);
        AdaptadorReunion miAdaptadorReunion =new AdaptadorReunion(this,listaReuniones);

        usuario = getIntent().getExtras();
        //CONEXION A LA BASE DE DATOS
        dbRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        //con el campo del DNI del bundle, vamos a capturar el objeto y meter en una variable su email
        dbRef.orderByChild("dni").equalTo(usuario.getString("dni")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    usu=ds.getValue(Usuario.class);
                    correoUsuario=usu.getEmail();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });





        //Para crear la reunion instanciamos una nueva conexion a la base de datos
        dbRefReuniones=FirebaseDatabase.getInstance().getReference().child("Reuniones");
        dbRefReuniones.orderByChild("nombreReunion").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaReuniones.clear(); // Limpiar la lista antes de agregar nuevas reuniones
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Reunion reunion=ds.getValue(Reunion.class);
                    String nombreReunion = ds.child("nombreReunion").getValue(String.class);
                    String receptor = ds.child("receptor").getValue(String.class);
                    String fecha = ds.child("fecha").getValue(String.class);
                    String motivo = ds.child("motivo").getValue(String.class);
                    if (reunion.getReceptor().equals(correoUsuario)) {
                        // Agregar la reunión a la lista
                        listaReuniones.add(new Reunion(nombreReunion, receptor, fecha, motivo));
                    }
                }
                // Notificar al adaptador que los datos han cambiado
                miAdaptadorReunion.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //Ahora juntamos los elementos declarados
        vistaLista.setAdapter(miAdaptadorReunion);

    }
}
