package com.example.gestioncentrodocente;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class PantallaEnviarAvisos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_enviar_avisos);


        Button enviarAviso = (Button) findViewById(R.id.botonPantallaEAenviarAvisos);
        CheckBox checkBox=findViewById(R.id.checkBoxEA);
        enviarAviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pantallaPrincipal = new Intent(PantallaEnviarAvisos.this, PantallaPrincipal.class);
                startActivity(pantallaPrincipal);
            }
        });


    }
}