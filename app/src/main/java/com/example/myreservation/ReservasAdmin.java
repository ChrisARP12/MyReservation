package com.example.myreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myreservation.Adaptadores.ListaReservaAdapter;
import com.example.myreservation.Entidades.Reserva;
import com.example.myreservation.Utilidades.Utilidades;

import java.util.ArrayList;

public class ReservasAdmin extends AppCompatActivity {
    ImageView btn_regresar;
    ConexionSQLiteHelper con;
    ArrayList<Reserva> listaReserva;
    RecyclerView recyclerViewReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas_admin);

        btn_regresar = (ImageView) findViewById(R.id.btn_regresar);

        con = new ConexionSQLiteHelper(getApplicationContext(),"bd",null,1);
        //Implementacion del ReciclerView
        listaReserva=new ArrayList<>();

        recyclerViewReserva = (RecyclerView) findViewById(R.id.recyclerViewReserva);
        recyclerViewReserva.setLayoutManager(new LinearLayoutManager(this));

        consultarListaReserva();

        ListaReservaAdapter adapter=new ListaReservaAdapter(listaReserva);
        recyclerViewReserva.setAdapter(adapter);

        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(ReservasAdmin.this, VistaAdmin.class);
                /*String dato = user.getUsuario();
                regresar.putExtra("Dato",dato);*/
                startActivity(regresar);
            }
        });
    }

    private void consultarListaReserva() {
        SQLiteDatabase db=con.getReadableDatabase();
        Reserva reserva=null;
        //String usuario = user.getNombre();
        //WHERE "+Utilidades.CAMPO_ID_RESERVA_USER+"='"+usuario+"'
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_RESERVA+"",null);
        while (cursor.moveToNext()){
            reserva=new Reserva();
            reserva.setId(cursor.getInt(0));
            reserva.setIdUser(cursor.getString(1));
            reserva.setIdAdmin(cursor.getString(2));
            reserva.setNombreMesa(cursor.getString(3));
            reserva.setFecha(cursor.getString(4));
            reserva.setInicio(cursor.getString(5));
            reserva.setFin(cursor.getString(6));

            listaReserva.add(reserva);
        }
        db.close();
    }
}