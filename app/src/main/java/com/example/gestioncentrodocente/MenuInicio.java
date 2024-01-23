package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MenuInicio extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);
        // Ocultar el ActionBar predeterminado
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        Button botonInicioSesion = (Button)findViewById(R.id.menuInicioBotonInicioSesion);
        EditText editTextNombre=(EditText)findViewById(R.id.menuInicioEditTextNombre);
        EditText editTextPassword=(EditText)findViewById(R.id.menuInicioEditTextPassword);
        TextView textoRegistro=(TextView)findViewById(R.id.menuInicioTextRegistro);
        ImageView imagenLogo=(ImageView) findViewById(R.id.imagenLogoCentro);
        //imagenLogo.setImageResource(@drawable/imagenLogo);



        botonInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actividadPantallaPrincipal= new Intent(MenuInicio.this, PantallaPrincipal.class);
                String nombreUsuario= editTextNombre.getText().toString();
                String passwordUsuario= editTextPassword.getText().toString();
                String rol="";
                if (nombreUsuario.equals("Juana") && passwordUsuario.equals("root")) {
                    rol = "docente";
                } else if (nombreUsuario.equals("Pepe") && passwordUsuario.equals("root")) {
                    rol = "coordinador";
                } else if (nombreUsuario.equals("Lucia") && passwordUsuario.equals("root")) {
                    rol = "jefeEstudios";
                }

                actividadPantallaPrincipal.putExtra("ROL_USUARIO", rol);
              /*  Bundle b=getIntent().getExtras();
                String nombre=b.getString("nombre");
                String apellidos=b.getString("apellidos");
                b.putString("nombre",nombre);
                b.putString("apellidos",apellidos);
                actividadPantallaPrincipal.putExtras(b);*/
                startActivity(actividadPantallaPrincipal);
            }
        });

        textoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actividadPantallaRegistro=new Intent(MenuInicio.this, PantallaRegistro.class);
                startActivity(actividadPantallaRegistro);
            }
        });


    }
}