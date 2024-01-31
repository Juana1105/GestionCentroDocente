package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashSet;
import java.util.Set;

public class PantallaEnviarAvisos extends AppCompatActivity {

    private boolean[] checked = {false, false, false, false, false};
    private Set<String> seleccionados = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_enviar_avisos);
        getSupportActionBar().hide();

        MaterialButton enviarAviso = findViewById(R.id.botonPantallaEAenviarAvisos);
        MaterialButton botonElige = findViewById(R.id.pantallaEAreceptores);
        TextView ponSeleccion = findViewById(R.id.seleccionAvisoEA);
        MaterialToolbar toolbar=findViewById(R.id.encabezadoEnviarAvisos);

        enviarAviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());

                builder.setTitle("Mensaje Informativo");
                builder.setMessage("Para enviar el aviso haz clic en 'aceptar'");
                builder.setIcon(android.R.drawable.btn_star_big_on);

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        View padre=(View) view.getParent();
                        Snackbar barra= Snackbar.make(padre,"Aviso enviado correctamente",Snackbar.LENGTH_SHORT);
                        barra.show();
                        Intent pantallaPrincipal = new Intent(PantallaEnviarAvisos.this, PantallaPrincipal.class);
                        startActivity(pantallaPrincipal);
                    }

                });


                builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        View padre=(View) view.getParent();
                        Snackbar barra= Snackbar.make(padre,"Has cancelado el envío del aviso",Snackbar.LENGTH_SHORT);
                        barra.show();
                    }
                });
                AlertDialog cuadroDialogo = builder.create();
                cuadroDialogo.show();

            }
        });


        botonElige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PantallaEnviarAvisos.this);
                builder.setTitle("Elige a quienes vas a enviar el aviso");
                String[] participantes = {"Ana", "Ricardo", "Lucia", "Juana", "Pepe"};

                builder.setMultiChoiceItems(participantes, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            seleccionados.add(participantes[which]);
                            checked[which] = true;
                        } else {
                            seleccionados.remove(participantes[which]);
                            checked[which] = false;
                        }

                        ponSeleccion.setText(seleccionados.toString());
                    }
                });

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaPrincipal = new Intent(PantallaEnviarAvisos.this, PantallaPrincipal.class);
                startActivity(pantallaPrincipal);
            }
        });
    }
}
