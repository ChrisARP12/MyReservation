package com.example.myreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myreservation.Adaptadores.ListaMesaAdapter;
import com.example.myreservation.Adaptadores.ListaReservaAdapter;
import com.example.myreservation.Entidades.Mesa;
import com.example.myreservation.Entidades.Reserva;
import com.example.myreservation.Entidades.Usuarios;
import com.example.myreservation.Utilidades.Utilidades;

import java.util.ArrayList;
import java.util.Collection;

public class ReservaUser extends AppCompatActivity {
    ImageView btn_regresar;
    ConexionSQLiteHelper con;
    ArrayList<Reserva> listaReserva;
    ArrayList<Usuarios> listaUser;
    Usuarios user = null;

    RecyclerView recyclerViewReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_user);

        //Recibimos el objeto de la vista principal
        String usuario = getIntent().getStringExtra("Dato");

        con = new ConexionSQLiteHelper(getApplicationContext(),"bd",null,1);

        //Metemos los datos de nuestro usuario en un objeto del mismo tipo
        listaUser= new ArrayList<>();
        conseguirUsuario(usuario);
        user = listaUser.get(0);


        btn_regresar = (ImageView) findViewById(R.id.btn_regresar);


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
                Intent regresar = new Intent(ReservaUser.this, PrincipalView.class);
                String dato = user.getUsuario();
                regresar.putExtra("Dato",dato);
                startActivity(regresar);
            }
        });
    }
    public void conseguirUsuario(String user){
        SQLiteDatabase bd = con.getWritableDatabase();
        Usuarios usuarios = null;
        Cursor file = bd.rawQuery("SELECT * FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.CAMPO_USUARIO+" = '"+user+"' ",null);
        if (file.moveToFirst()){
            usuarios = new Usuarios();
            usuarios.setId(file.getInt(0));
            usuarios.setNombre(file.getString(1));
            usuarios.setUsuario(file.getString(2));
            usuarios.setCorreo(file.getString(3));
            usuarios.setContraseña(file.getString(4));
            listaUser.add(usuarios);
        }else {
            Toast.makeText(this, "Que diablos pasa aqui", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }
    private void consultarListaReserva() {
        SQLiteDatabase db=con.getReadableDatabase();
        Reserva reserva=null;
        String usuario = user.getNombre();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_RESERVA+" WHERE "+Utilidades.CAMPO_ID_RESERVA_USER+"='"+usuario+"'",null);
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