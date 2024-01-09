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

import com.google.android.material.snackbar.Snackbar;

public class PantallaEditarPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_editar_perfil);


      /*  Bundle b=getIntent().getExtras();
        String nombre= b.getString("nombre");
        String apellidos=b.getString("apellidos");
        EditText campoNombre=(EditText)findViewById(R.id.pantallaEditarPerfilEditTextNombre);
        EditText campoApellidos=(EditText)findViewById(R.id.pantallaEditarPerfilEditTextApellidos);
        campoNombre.setText(nombre);
        campoApellidos.setText(apellidos);*/




        Button botonGuardarCambios= (Button)findViewById(R.id.editarPerfilBotonGuardarCambios);
        Spinner spinnerSimple = (Spinner)findViewById(R.id.SpinnerCategoriaDocente);
        String[] valores = {"Docente","Jefe de Estudios", "Coordinador de ciclo"};
        spinnerSimple.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores));
        spinnerSimple.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // textViewSimple.setText(parent.getItemAtPosition(position).toString());

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        botonGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());

                builder.setTitle("Mensaje Informativo");
                builder.setMessage("Para guardar tus datos tienes que hacer clic en 'aceptar'");
                builder.setIcon(android.R.drawable.btn_star_big_on);

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        View padre=(View) view.getParent();
                        Snackbar barra= Snackbar.make(padre,"Perfil modificado satisfactoriamente",Snackbar.LENGTH_SHORT);
                        barra.show();
                        Intent pantallaInicio=new Intent(PantallaEditarPerfil.this, MenuInicio.class);
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
                        Snackbar barra= Snackbar.make(padre,"Has cancelado la edición de perfil de usuario",Snackbar.LENGTH_SHORT);
                        barra.show();
                    }
                });
                AlertDialog cuadroDialogo = builder.create();
                cuadroDialogo.show();

            }
        });


    }
}