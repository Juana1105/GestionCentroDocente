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
        //getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("REUNIONES");

        listaReuniones=new ArrayList<>();
        ListView vistaLista=(ListView) findViewById(R.id.listadoReuniones);
        AdaptadorReunion miAdaptadorReunion =new AdaptadorReunion(this,listaReuniones);

        usuario = getIntent().getExtras();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");
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








        //dbRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        dbRefReuniones=FirebaseDatabase.getInstance().getReference().child("Reuniones");
        //primero hago una consulta para obtener el email del usuario

        //y aqui entramos en la base de datos y comparamos el nombre de la reunion con el email del usuario para agregarlo a la lista
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


                    //String correoUsuario = usuario.getString("email");

                    Log.d("DEBUG", "Valor de receptor: " + receptor);
                    Log.d("DEBUG", "Valor de correoUsuario: " + correoUsuario);
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



/*
        listaReuniones.add(new Reunion("Reunion 1","Lucia","12/02/2024","Reunion extraordinaria"));
        listaReuniones.add(new Reunion("Reunion 2","Lucia","12/02/2024","Reunion para simulacro de incendio"));
        listaReuniones.add(new Reunion("Reunion 3","Pepe","12/02/2024","Reunion organizacion trimestral"));
        listaReuniones.add(new Reunion("Reunion 4","Pepe","12/02/2024","Reunion excursión de abril"));
        listaReuniones.add(new Reunion("Reunion 5","Lucia","12/02/2024","Reunión fiesta escolar"));
*/


        //Ahora juntamos los elementos declarados
        vistaLista.setAdapter(miAdaptadorReunion);





    }
}
