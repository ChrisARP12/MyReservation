package com.example.myreservation.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myreservation.Entidades.Reserva;
import com.example.myreservation.R;

import java.util.ArrayList;

public class ListaReservaAdapter extends RecyclerView.Adapter<ListaReservaAdapter.ReservaViewHolder> {
    ArrayList<Reserva> listaReserva;

    public ListaReservaAdapter(ArrayList<Reserva> listaReserva) {
        this.listaReserva = listaReserva;
    }

    @Override
    public ListaReservaAdapter.ReservaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reservation,null,false);
        return new ListaReservaAdapter.ReservaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaReservaAdapter.ReservaViewHolder holder, int position) {
        holder.nombre.setText(listaReserva.get(position).getNombreMesa());
        holder.fecha.setText(listaReserva.get(position).getFecha());
        holder.horaInicio.setText(listaReserva.get(position).getInicio());
        holder.horaFin.setText(listaReserva.get(position).getFin());
    }

    @Override
    public int getItemCount() {
        return listaReserva.size();
    }

    public class ReservaViewHolder extends RecyclerView.ViewHolder {

        TextView nombre,fecha,horaInicio,horaFin;

        public ReservaViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.textNombre);
            fecha = (TextView) itemView.findViewById(R.id.textFecha);
            horaInicio = (TextView) itemView.findViewById(R.id.textHoraInicio);
            horaFin = (TextView) itemView.findViewById(R.id.textHoraFin);
        }
    }
}
