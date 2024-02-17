package com.example.gestioncentrodocente.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.SQLite.GestionCentroDocenteDBHelper;
import com.example.gestioncentrodocente.entidades.Guardia;
import com.example.gestioncentrodocente.entidades.Permiso;
import com.example.gestioncentrodocente.entidades.Reunion;
import com.example.gestioncentrodocente.entidades.Usuario;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class PantallaGestionarPermisos extends AppCompatActivity {

    private Bundle usuario;
    private DatabaseReference dbRef,dbRef2;
    String seleccionado,seleccionado2,correoUsuario,idString;

    String[] valores =null;
    String[] valores2 = null;
    private int id=0;
    Permiso crearPermiso;
    SQLiteDatabase baseDatos;
    GestionCentroDocenteDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_gestionar_permisos);
        dbHelper = new GestionCentroDocenteDBHelper(this);
        baseDatos=dbHelper.getWritableDatabase();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("GESTIÓN PERMISOS");
        usuario = getIntent().getExtras();
        dbRef= FirebaseDatabase.getInstance().getReference().child("Usuarios");
        dbRef.orderByChild("dni").equalTo(usuario.getString("dni")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Usuario u = ds.getValue(Usuario.class);
                    correoUsuario=u.getEmail();
                    Log.d("INFO", "EMAIL: " + correoUsuario);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

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

                spinnerCriteriosEspecificos.setAdapter(new ArrayAdapter<String>(PantallaGestionarPermisos.this, android.R.layout.simple_spinner_item,valores2));
                spinnerCriteriosEspecificos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        seleccionado2=valores2[position];
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //conectar a base de datos para crear permisos
        dbRef2= FirebaseDatabase.getInstance().getReference().child("Permisos");
        botonAceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                View padre = (View) view.getParent();
                dbRef2.orderByChild("id").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Guardia lastGuardia = dataSnapshot.getValue(Guardia.class);
                            id = lastGuardia.getId() + 1;
                        }
                        dbRef2.orderByChild("id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    Snackbar.make(padre, "Este id de permiso ya existe", Snackbar.LENGTH_SHORT).show();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                                    builder.setTitle("Mensaje Informativo");
                                    builder.setMessage("Para guardar el permiso haz clic en 'aceptar'");
                                    builder.setIcon(android.R.drawable.ic_dialog_info);
                                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            crearPermiso = new Permiso(id, correoUsuario, seleccionado, seleccionado2);
                                            idString = String.valueOf(id);
                                            dbRef2.child(idString).setValue(crearPermiso);
                                            id++;

                                            //SQLITE
                                            ContentValues contenido=new ContentValues();
                                            contenido.put("email",correoUsuario);
                                            contenido.put("criterioComun",seleccionado);
                                            contenido.put("criterioEspecifico",seleccionado2);
                                            baseDatos.insert("permiso",null,contenido);
                                            //VOLVER A PANTALLA PRINCIPAL
                                            Intent pantallaPrincipal = new Intent(PantallaGestionarPermisos.this, PantallaPrincipal.class);
                                            pantallaPrincipal.putExtras(usuario);
                                            startActivity(pantallaPrincipal);
                                        }
                                    });

                                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Snackbar.make(padre, "Has cancelado el proceso", Snackbar.LENGTH_SHORT).show();
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

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });
    }
}