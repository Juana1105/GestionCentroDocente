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
        // Ocultar el ActionBar predeterminado
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        MaterialButton botonInicioSesion =findViewById(R.id.menuInicioBotonInicioSesion);

        textoDni=(EditText)findViewById(R.id.menuInicioEditTextDni);
        textoPassword=(EditText)findViewById(R.id.menuInicioEditTextPassword);

        TextView textoRegistro=(TextView)findViewById(R.id.menuInicioTextRegistro);
        //ImageView imagenLogo=(ImageView) findViewById(R.id.imagenLogoCentro);
        //imagenLogo.setImageResource(@drawable/imagenLogo);


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
                                    String passwordDB = ds.child("password").getValue(String.class);

                                /*Si la contraseña insertada es la misma que la de la base de datos se ejecutará
                                esta condición*/
                                    if (passwordDB.equals(password)) {
                                        Intent actividadMenuPrincipal = new Intent(MenuInicio.this, PantallaPrincipal.class);
                                        actividadMenuPrincipal.putExtra("dni", dni);
                                        startActivity(actividadMenuPrincipal);

                                        //Sino mostrará un mensaje de que la contraseña es incorrecta
                                    } else {
                                        Snackbar.make(padre, "Contraseña Incorrecta", Snackbar.LENGTH_SHORT).show();
                                    }
                                }

                                //Sino mostrará un mensaje de que no existe ese usuario y que se registre
                            }else{
                                Snackbar.make(padre, "No existe un usuario con ese dni", Snackbar.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}
                    });
                }



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