package com.example.mimenu.ui.actividades;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mimenu.R;
import com.example.mimenu.modelo.Actividad;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ActividadAdapter extends RecyclerView.Adapter<ActividadAdapter.ViewHolder>{

    private Context context;
    private List<Actividad> actividades;
    private LayoutInflater inflater;

    public ActividadAdapter(Context context, List<Actividad> actividades, LayoutInflater inflater) {
        this.context = context;
        this.actividades = actividades;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //enterar al adapter que el item es el cardView item_actividad.xml
        View root = inflater.inflate(R.layout.item_actividad, parent, false);
        return new ViewHolder(root);
    }

    //Por cada item
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nombre.setText(actividades.get(position).getNombre());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaCadena = sdf.format(actividades.get(position).getFecha());
        holder.fecha.setText(fechaCadena);

        //Para obtener la hora con solo hora y minutos-------------------
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date hora = actividades.get(position).getHora();
        String horaYMinutos = dateFormat.format(hora);

        holder.hora.setText(horaYMinutos);
        //----------------------------------------------------------------

        //Click del boton
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("actividad", actividades.get(position));
                Navigation.findNavController(view).navigate(R.id.detallesFragment, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return actividades.size();
    }

    //La que conoce nuestro item
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, fecha, hora;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardViewId);

            nombre = itemView.findViewById(R.id.tvNombre);
            fecha = itemView.findViewById(R.id.tvFecha);
            hora = itemView.findViewById(R.id.tvHora);
        }
    }
}
