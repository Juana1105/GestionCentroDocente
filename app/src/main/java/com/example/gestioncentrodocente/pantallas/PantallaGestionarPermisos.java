package com.example.gestioncentrodocente.pantallas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.gestioncentrodocente.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

public class PantallaGestionarPermisos extends AppCompatActivity {


    String seleccionado="";

    String[] valores =null;
    String[] valores2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_gestionar_permisos);

        //getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("GESTIÓN PERMISOS");


        MaterialButton botonAceptar=findViewById(R.id.pantallaGestPAceptar);
        Spinner spinnerCriteriosComunes=(Spinner)findViewById(R.id.spinnerCriteriosComunes);
        Spinner spinnerCriteriosEspecificos=(Spinner)findViewById(R.id.spinnerCriteriosEspecificos);

        valores = new String[]{"Familia", "Nacimiento/Otros", "Personal", "Formación del docente", "Deberes Civiles"};

        spinnerCriteriosComunes.setAdapter(new ArrayAdapter<String>(PantallaGestionarPermisos.this, android.R.layout.simple_spinner_item,valores));

        spinnerCriteriosComunes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seleccionado=valores[position];

                if(seleccionado.equals("Familia")){
                    valores2= new String[]{"Permiso cuidado hijo menor", "Matrimonio de un familiar", "Permiso parental por hijo menor de 8 años", "Permiso por accidente, enfermedad grave de un familiar", "Permiso por fallecimiento de un familiar", "Permiso por cuidado de hijo menor 16 años"};
                } else if(seleccionado.equals("Nacimiento/Otros")){
                    valores2= new String[]{"Permiso para tratamientos de fecundación asistida", "Permiso por riesgo durante el embarazo", "Permiso por nacimiento para la madre biológica", "Permiso del progenitor distinto de la madre biológica", "Permiso por lactancia","Permiso por adopción,guarda o acogimiento por uno de los progenitores"};
                } else if(seleccionado.equals("Personal")){
                    valores2= new String[]{"Cita médica","Permiso por enfermedad o accidente", "Permiso por intervención quirúrgica","Permiso por días de libre disposición", "Permiso por razón de violencia de género", "Permiso por traslado de domicilio", "Permiso parcialmente retribuido", "Licencia por matrimonio", "Licencia por asuntos propios"};
                } else if(seleccionado.equals("Formación del docente")){
                    valores2= new String[]{"Permiso por concurrencia a exámenes", "Licencia para asistir a actividades de formación"};
                } else if(seleccionado.equals("Deberes Civiles")){
                    valores2= new String[]{"Permiso por deber inexcusable", "Permiso por elecciones europeas, generales, autonómicas y locales(APODERADO,MIEMBRO DE MESA,INTERVENTOR)","Permiso por elecciones europeas, generales, autonómicas y locales(CANDIDATO)"};
                }

                // Actualizar el ArrayAdapter con el nuevo conjunto de valores
                spinnerCriteriosEspecificos.setAdapter(new ArrayAdapter<String>(PantallaGestionarPermisos.this, android.R.layout.simple_spinner_item,valores2));
                //ArrayAdapter<String> adapter = new ArrayAdapter<>(PantallaGestionarPermisos.this, android.R.layout.simple_spinner_item, valores2);
               // spinnerCriteriosEspecificos.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
/*

        MaterialToolbar toolbar=findViewById(R.id.encabezadoGestiPermisos);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaPrincipal=new Intent(PantallaGestionarPermisos.this, PantallaPrincipal.class);
                startActivity(pantallaPrincipal);
            }
        });*/



        botonAceptar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());

                builder.setTitle("Mensaje Informativo");
                builder.setMessage("Para enviar el permiso solicitado haz clic en 'aceptar'");
                builder.setIcon(android.R.drawable.btn_star_big_on);

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        View padre=(View) view.getParent();
                        Snackbar barra= Snackbar.make(padre,"Permiso enviado correctamente",Snackbar.LENGTH_SHORT);
                        barra.show();
                        Intent pantallaPrincipal=new Intent(PantallaGestionarPermisos.this, PantallaPrincipal.class);
                        startActivity(pantallaPrincipal);
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
                        Snackbar barra= Snackbar.make(padre,"Has cancelado la gestión del permiso",Snackbar.LENGTH_SHORT);
                        barra.show();
                    }
                });
                AlertDialog cuadroDialogo = builder.create();
                cuadroDialogo.show();
            }
        });

    }
}