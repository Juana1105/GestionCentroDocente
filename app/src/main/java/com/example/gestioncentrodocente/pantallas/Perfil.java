package com.example.gestioncentrodocente.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.entidades.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Perfil extends AppCompatActivity {

    TextView nombreE,dniE,emailE,passwordE,rolE,titulacionE,telefonoE;
    private Bundle usuario;
    private DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        //getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("\tPERFIL");

        usuario = getIntent().getExtras();
        nombreE=findViewById(R.id.textViewNombre);
        dniE=findViewById(R.id.textViewDni);
        emailE=findViewById(R.id.textViewEmail);
        passwordE=findViewById(R.id.textViewPassword);
        rolE=findViewById(R.id.textViewRol);
        titulacionE=findViewById(R.id.textViewTitulacion);
        telefonoE=findViewById(R.id.textViewTelefono);


        dbRef= FirebaseDatabase.getInstance().getReference().child("Usuarios");

        dbRef.orderByChild("dni").equalTo(usuario.getString("dni")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    Usuario u=ds.getValue(Usuario.class);
                    nombreE.setText(u.getNombre());
                    dniE.setText(u.getDni());
                    emailE.setText(u.getEmail());
                    passwordE.setText(u.getPassword());
                    rolE.setText(u.getRol());
                    titulacionE.setText(u.getTitulacion());
                    telefonoE.setText(u.getTelefono());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        FloatingActionButton editarPerfil=findViewById(R.id.floatingEditarPerfil);

        editarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaEditarPerfil=new Intent(Perfil.this, PantallaEditarPerfil.class);
                pantallaEditarPerfil.putExtras(usuario);

                startActivity(pantallaEditarPerfil);
            }
        });

    }
}