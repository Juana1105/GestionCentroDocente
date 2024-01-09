package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaGestionarPermisos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_gestionar_permisos);

        Button botonAceptar=(Button)findViewById(R.id.pantallaGestPAceptar);

        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaPrincipal=new Intent(PantallaGestionarPermisos.this, PantallaPrincipal.class);
                startActivity(pantallaPrincipal);
            }
        });
    }
}