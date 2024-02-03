package com.example.gestioncentrodocente.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gestioncentrodocente.R;
import com.example.gestioncentrodocente.entidades.Notificacion;

import java.util.ArrayList;

public class AdaptadorNotificacion extends ArrayAdapter<Notificacion> {

    private ArrayList<Notificacion> lista;

    public AdaptadorNotificacion(Context contexto, ArrayList<Notificacion> lista) {

        super(contexto, R.layout.elemento_lista_notificacion, lista);
        this.lista=lista;
    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater mostrado= LayoutInflater.from(getContext());
        View elemento= mostrado.inflate(R.layout.elemento_lista_notificacion, parent, false);


        ImageView imagen =(ImageView) elemento.findViewById(R.id.imagenNotificacionLista);
        TextView textoNombreEmisor= (TextView) elemento.findViewById(R.id.textViewEmisor);
        TextView textoAsunto= (TextView) elemento.findViewById(R.id.tipoNotificacionCard);
        TextView textoMensaje=(TextView) elemento.findViewById(R.id.textViewMensaje);

        imagen.setImageResource(lista.get(position).getFoto());
        textoNombreEmisor.setText(lista.get(position).getNombreEmisor());
        textoAsunto.setText(lista.get(position).getTipoNotificacion());
        textoMensaje.setText(lista.get(position).getMensaje());


        return elemento;
    }

}
