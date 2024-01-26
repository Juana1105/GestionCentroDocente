package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PantallaGestionarPermisos extends AppCompatActivity {
    String seleccionado="";
    String motivoPermiso="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_gestionar_permisos);

        Button botonAceptar=(Button)findViewById(R.id.pantallaGestPAceptar);
        Spinner spinnerCriteriosComunes=(Spinner)findViewById(R.id.spinnerCriteriosComunes);
        Spinner spinnerCriteriosEspecificos=(Spinner)findViewById(R.id.spinnerCriteriosEspecificos);

        String[] valores = {"Familia","Nacimiento/Otros", "Asuntos Personales", "Salud Propia","Formación del docente","Deberes Civiles"};

        spinnerCriteriosComunes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores));

        spinnerCriteriosComunes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seleccionado=valores[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        String[] valores2 = new String[]{""};

        if(seleccionado.contains("Familia")){
            valores2= new String[]{"Permiso cuidado hijo menor", "Matrimonio de un familiar", "Permiso parental por hijo menor de 8 años", "Permiso por accidente, enfermedad grave de un familiar", "Permiso por fallecimiento de un familiar", "Permiso por cuidado de hijo menor 16 años"};
        }else if(seleccionado=="Nacimiento/Otros"){
            valores2= new String[]{"Permiso para tratamientos de fecundación asistida", "Permiso por riesgo durante el embarazo", "Permiso por nacimiento para la madre biológica", "Permiso del progenitor distinto de la madre biológica", "Permiso por lactancia","Permiso por adopción,guarda o acogimiento por uno de los progenitores"};
        }else if(seleccionado=="Asuntos Personales"){
            valores2= new String[]{"Permiso por días de libre disposición", "Permiso por razón de violencia de género", "Permiso por traslado de domicilio", "Permiso parcialmente retribuido", "Licencia por matrimonio", "Licencia por asuntos propios"};
        }else if(seleccionado =="Salud propia"){
            valores2= new String[]{"Permiso por enfermedad o accidente", "Permiso por intervención quirúrgica"};
        }else if(seleccionado=="Formación del docente"){
            valores2= new String[]{"Permiso por concurrencia a exámenes", "Licencia para asistir a actividades de formación"};
        }else if(seleccionado=="Deberes Civiles"){
            valores2= new String[]{"Permiso por deber inexcusable", "Permiso por elecciones europeas, generales, autonómicas y locales(APODERADO,MIEMBRO DE MESA,INTERVENTOR)","Permiso por elecciones europeas, generales, autonómicas y locales(CANDIDATO)"};
        }


        spinnerCriteriosEspecificos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,valores2));

        spinnerCriteriosComunes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                motivoPermiso=valores[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });







        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaPrincipal=new Intent(PantallaGestionarPermisos.this, PantallaPrincipal.class);
                startActivity(pantallaPrincipal);
            }
        });
    }
}