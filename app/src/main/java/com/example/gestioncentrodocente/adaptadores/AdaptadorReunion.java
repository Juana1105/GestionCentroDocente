package com.example.gestioncentrodocente.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.entidades.Reunion;

import java.util.ArrayList;

public class AdaptadorReunion extends ArrayAdapter<Reunion> {

    private ArrayList<Reunion> lista;

    public AdaptadorReunion(Context contexto, ArrayList<Reunion> lista) {

        super(contexto, R.layout.elemento_lista_notificacion, lista);
        this.lista=lista;
    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater mostrado= LayoutInflater.from(getContext());
        View elemento= mostrado.inflate(R.layout.elemento_lista_reunion, parent, false);



        TextView textoNombreEmisor= (TextView) elemento.findViewById(R.id.textViewEmisor);
        TextView textoFecha= (TextView) elemento.findViewById(R.id.fechaReunion);
        TextView textoDescripcion=(TextView) elemento.findViewById(R.id.descripcionReunion);


        textoNombreEmisor.setText(lista.get(position).getReceptor());
        textoFecha.setText(lista.get(position).getFecha());
        textoDescripcion.setText(lista.get(position).getMotivo());


        return elemento;
    }

}
