package com.example.gestioncentrodocente.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.SQLite.GestionCentroDocenteDBHelper;
import com.example.gestioncentrodocente.entidades.Usuario;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PantallaRegistro extends AppCompatActivity {

    EditText nombreE,emailE,telefonoE,passwordE,dniE,titulacionE;

    DatabaseReference dbRef;
    Usuario datosUsuario;
    String rol = null;
    SQLiteDatabase baseDatos;
    GestionCentroDocenteDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("\tREGISTRO");

        dbHelper = new GestionCentroDocenteDBHelper(this);
        baseDatos=dbHelper.getWritableDatabase();
        MaterialButton botonRegistroCompletado=findViewById(R.id.registroBotonRegistrarse);
        Spinner spinnerSimple = (Spinner)findViewById(R.id.pr_rol);
        String[] valores = {"Docente","Jefe de Estudios", "Coordinador de ciclo"};
        spinnerSimple.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores));
        spinnerSimple.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rol = valores[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        datosUsuario=new Usuario();
        nombreE=(EditText)findViewById(R.id.pr_nombre);
        dniE=findViewById(R.id.pr_dni);
        emailE=(EditText)findViewById(R.id.pr_email);
        telefonoE=(EditText)findViewById(R.id.pr_telefono);
        passwordE=(EditText)findViewById(R.id.pr_password);
        titulacionE=(EditText)findViewById(R.id.pr_titulacion);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        botonRegistroCompletado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View padre=(View) view.getParent();
                // Obteniendo los valores de los campos de texto al momento de hacer clic en el botón
                String nombre = nombreE.getText().toString();
                String dni=dniE.getText().toString();
                String email = emailE.getText().toString();
                String telefono = telefonoE.getText().toString();
                String titulacion = titulacionE.getText().toString();
                String password = passwordE.getText().toString();

                //Se verifica si están rellenados todos los datos
                if (nombre.isEmpty() || email.isEmpty() || titulacion.isEmpty() || password.isEmpty()) {
                    Snackbar.make(padre, "No puedes dejar campos vacíos", Snackbar.LENGTH_SHORT).show();
                } else {
                    dbRef.orderByChild("dni").equalTo(dni).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Snackbar.make(padre, "Usuario ya existente", Snackbar.LENGTH_SHORT).show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                builder.setTitle("Mensaje Informativo");
                                builder.setMessage("Estás a punto de completar tu registro, para guardar tus datos tienes que hacer clic en 'aceptar'");
                                builder.setIcon(android.R.drawable.ic_dialog_info);
                                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //FIREBASE
                                        datosUsuario.setNombre(nombre);
                                        datosUsuario.setDni(dni);
                                        datosUsuario.setEmail(email);
                                        datosUsuario.setTelefono(telefono);
                                        datosUsuario.setTitulacion(titulacion);
                                        datosUsuario.setPassword(password);
                                        datosUsuario.setRol(rol);
                                        dbRef.child(dni).setValue(datosUsuario);

                                        //SQLITE
                                        ContentValues contenido=new ContentValues();
                                        contenido.put("nombre",nombre);
                                        contenido.put("dni",dni);
                                        contenido.put("email",email);
                                        contenido.put("telefono",telefono);
                                        contenido.put("titulacion",titulacion);
                                        contenido.put("password",password);
                                        contenido.put("rol",rol);
                                        baseDatos.insert("usuario",null,contenido);

                                        //DEVUELVE A MENU INICIO
                                        Intent pantallaInicio = new Intent(PantallaRegistro.this, MenuInicio.class);
                                        startActivity(pantallaInicio);
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
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });

    }
}