package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PantallaRegistro extends AppCompatActivity {

    EditText nombreE,emailE,telefonoE,passwordE,dniE,titulacionE;

    DatabaseReference dbRef;
    Usuario datosUsuario;

    String rol = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro);
        getSupportActionBar().hide();


        MaterialButton botonRegistroCompletado=findViewById(R.id.registroBotonRegistrarse);
        MaterialToolbar toolbar=findViewById(R.id.encabezadoRegistro);


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







        //Usuario u=new Usuario(dni,nombre,apellidos,email,password, rol[0],titulacion,telefono);





        botonRegistroCompletado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //ALERT DIALOG
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());

                builder.setTitle("Mensaje Informativo");
                builder.setMessage("Estás a punto de completar tu registro, para guardar tus datos tienes que hacer clic en 'aceptar'");
                builder.setIcon(android.R.drawable.btn_star_big_on);

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        datosUsuario=new Usuario();

                        nombreE=(EditText)findViewById(R.id.pr_nombre);

                        emailE=(EditText)findViewById(R.id.pr_email);
                        telefonoE=(EditText)findViewById(R.id.pr_telefono);
                        passwordE=(EditText)findViewById(R.id.pr_password);
                        titulacionE=(EditText)findViewById(R.id.pr_titulacion);


                        String nombre= String.valueOf(nombreE.getText());

                        String email=String.valueOf(emailE.getText());
                        String telefono=String.valueOf(telefonoE.getText());
                        String titulacion=String.valueOf(titulacionE.getText());
                        String password=String.valueOf(passwordE.getText());




                        //inserta los datos poniendo como clave el nombre del usuario

                        datosUsuario.setNombre(nombre);
                        datosUsuario.setEmail(email);
                        datosUsuario.setTelefono(telefono);
                        datosUsuario.setTitulacion(titulacion);
                        datosUsuario.setPassword(password);
                        datosUsuario.setRol(rol);

                        //dbRef.child(dni);
                        //conexion con base de datos para crear usuario
                        dbRef= FirebaseDatabase.getInstance().getReference().child("Usuarios");

                        dbRef.push().setValue(datosUsuario);

                        Intent pantallaInicio=new Intent(PantallaRegistro.this, MenuInicio.class);
                        startActivity(pantallaInicio);
                    }
                });

                builder.setNegativeButton("No aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        View padre=(View) view.getParent();
                        Snackbar barra= Snackbar.make(padre,"Si no aceptas modifica algún campo",Snackbar.LENGTH_SHORT);
                        barra.show();
                    }
                });

                builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        View padre=(View) view.getParent();
                        Snackbar barra= Snackbar.make(padre,"Has cancelado el registro de usuario",Snackbar.LENGTH_SHORT);
                        barra.show();
                    }
                });
                AlertDialog cuadroDialogo = builder.create();
                cuadroDialogo.show();

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaInicio=new Intent(PantallaRegistro.this, MenuInicio.class);
                startActivity(pantallaInicio);
            }
        });
    }
}