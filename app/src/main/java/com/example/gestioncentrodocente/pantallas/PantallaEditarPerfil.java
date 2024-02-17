package com.example.gestioncentrodocente.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.entidades.Usuario;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PantallaEditarPerfil extends AppCompatActivity {

    private Bundle usuario;
    private DatabaseReference dbRef;
    EditText nombreE,dniE,emailE,passwordE,titulacionE,telefonoE;
    String rol=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_editar_perfil);
        //getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("EDITAR PERFIL");

        usuario = getIntent().getExtras();
        nombreE=findViewById(R.id.pep_nombre);
        dniE=findViewById(R.id.pep_dni);
        emailE=findViewById(R.id.pep_email);
        passwordE=findViewById(R.id.pep_password);
        titulacionE=findViewById(R.id.pep_titulacion);
        telefonoE=findViewById(R.id.pep_telefono);
        Spinner spinnerRol = (Spinner)findViewById(R.id.pep_rol);
        String[] valores = {"Docente","Jefe de Estudios", "Coordinador de ciclo"};
        spinnerRol.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores));
        spinnerRol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rol = valores[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Vamos a consultar en la base de datos al usuario que corresponda con nuestro DNI, y vamos a capturar en variables toda la información para mostrarla en los campos de texto(EditText) y que el usuario pueda modificar algun dato si quiere
        dbRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        dbRef.orderByChild("dni").equalTo(usuario.getString("dni")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    Usuario u=ds.getValue(Usuario.class);
                    nombreE.setText(u.getNombre());
                    dniE.setText(u.getDni());
                    emailE.setText(u.getEmail());
                    passwordE.setText(u.getPassword());
                    titulacionE.setText(u.getTitulacion());
                    telefonoE.setText(u.getTelefono());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });



        MaterialButton botonGuardarCambios=findViewById(R.id.EPBotonRegistrarse);
        botonGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volverPerfil = new Intent(PantallaEditarPerfil.this, Perfil.class);
                View padre=(View) view.getParent();
                String nombre=nombreE.getText().toString();
                String dni = dniE.getText().toString();
                String email = emailE.getText().toString();
                String password=passwordE.getText().toString();
                String titulacion=titulacionE.getText().toString();
                String telefono=telefonoE.getText().toString();
                if (dni.isEmpty() || password.isEmpty()) {
                    Snackbar.make(padre, "No puedes dejar dni o contraseña vacios", Snackbar.LENGTH_SHORT).show();
                } else {
                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String dniOriginal=usuario.getString("dni");
                            if(snapshot.exists()){
                                //AlertDialog para confirmar los cambios
                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                builder.setTitle("Mensaje Informativo");
                                builder.setMessage("Estás a punto de modificar tus datos, si estás seguro haz clic en 'aceptar'");
                                builder.setIcon(android.R.drawable.ic_dialog_info);
                                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dbRef.child(dniOriginal).child("dni").setValue(dni);
                                        dbRef.child(dniOriginal).child("password").setValue(password);
                                        dbRef.child(dniOriginal).child("nombre").setValue(nombre);
                                        dbRef.child(dniOriginal).child("rol").setValue(rol);
                                        dbRef.child(dniOriginal).child("email").setValue(email);
                                        dbRef.child(dniOriginal).child("titulacion").setValue(titulacion);
                                        dbRef.child(dniOriginal).child("telefono").setValue(telefono);
                                        volverPerfil.putExtras(usuario);
                                        startActivity(volverPerfil);
                                    }
                                });
                                builder.setNegativeButton("No aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Snackbar.make(padre, "Si no aceptas modifica algún campo", Snackbar.LENGTH_SHORT).show();
                                    }
                                });
                                builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Snackbar.make(padre, "Has cancelado el registro de usuario", Snackbar.LENGTH_SHORT).show();
                                    }
                                });
                                AlertDialog cuadroDialogo = builder.create();
                                cuadroDialogo.show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}
                    });

                }

            }
        });


    }
}