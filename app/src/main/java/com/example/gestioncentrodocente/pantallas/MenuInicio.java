package com.example.gestioncentrodocente.pantallas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gestioncentrodocente.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuInicio extends AppCompatActivity {

    private EditText textoDni;
    private EditText textoPassword;
    private DatabaseReference dbRef;
    private String dni, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);
        getSupportActionBar().hide();

        MaterialButton botonInicioSesion =findViewById(R.id.menuInicioBotonInicioSesion);
        textoDni=(EditText)findViewById(R.id.menuInicioEditTextDni);
        textoPassword=(EditText)findViewById(R.id.menuInicioEditTextPassword);
        TextView textoRegistro=(TextView)findViewById(R.id.menuInicioTextRegistro);

        dbRef= FirebaseDatabase.getInstance().getReference().child("Usuarios");

        botonInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View padre=(View) view.getParent();
                dni= textoDni.getText().toString();
                password= textoPassword.getText().toString();

                if (dni.isEmpty() || password.isEmpty()) {
                    Snackbar.make(padre, "No puedes dejar ningún campo vacío", Snackbar.LENGTH_SHORT).show();
                }else{
                    //Mira en la base de datos si existe algún usuario con el nombre insertado
                    dbRef.orderByChild("dni").equalTo(dni).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //Si hay algún usuario con ese dni se ejecutará esta condición
                            if (snapshot.exists()) {
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    //recoge de la base de datos el password
                                    String passwordDB = ds.child("password").getValue(String.class);
                                    //compara y si la contraseña es la misma te muestra la activity principal
                                    if (passwordDB.equals(password)) {
                                        Intent actividadMenuPrincipal = new Intent(MenuInicio.this, PantallaPrincipal.class);
                                        actividadMenuPrincipal.putExtra("dni", dni);
                                        startActivity(actividadMenuPrincipal);
                                    } else {
                                        Snackbar.make(padre, "Contraseña Incorrecta", Snackbar.LENGTH_SHORT).show();
                                    }
                                }
                            }else{
                                //Sino mostrará un mensaje de que no existe ese usuario y que se registre
                                Snackbar.make(padre, "No existe un usuario con ese dni", Snackbar.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}
                    });
                }
            }
        });

        //PARA REGISTRARSE
        textoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actividadPantallaRegistro=new Intent(MenuInicio.this, PantallaRegistro.class);
                startActivity(actividadPantallaRegistro);
            }
        });
    }
}