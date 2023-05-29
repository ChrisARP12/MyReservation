package com.example.myreservation.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myreservation.Entidades.Mesa;
import com.example.myreservation.R;

import java.util.ArrayList;

public class ListaMesaAdapter extends RecyclerView.Adapter<ListaMesaAdapter.MesaViewHolder> {
    ArrayList<Mesa> listaMesa;

    public ListaMesaAdapter(ArrayList<Mesa> listaUsuario) {
        this.listaMesa = listaUsuario;
    }

    @Override
    public MesaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_mesa,null,false);
        return new MesaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MesaViewHolder holder, int position) {
        holder.nombre.setText(listaMesa.get(position).getNombre());
        holder.cantidad.setText(listaMesa.get(position).getCantidad().toString());
        holder.precio.setText(listaMesa.get(position).getPrecio().toString());
        holder.disponibilidad.setText(listaMesa.get(position).getDisponibilidad());
    }

    @Override
    public int getItemCount() {
        return listaMesa.size();
    }

    public class MesaViewHolder extends RecyclerView.ViewHolder {

        TextView nombre,cantidad,precio,disponibilidad;

        public MesaViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.textNombre);
            cantidad = (TextView) itemView.findViewById(R.id.textCantidad);
            precio = (TextView) itemView.findViewById(R.id.textPrecio);
            disponibilidad = (TextView) itemView.findViewById(R.id.textDisponibilidad);
        }
    }
}
