package com.example.myreservation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

import com.example.myreservation.Adaptadores.ListaMesaAdapter;
import com.example.myreservation.Entidades.Mesa;
import com.example.myreservation.Utilidades.Utilidades;

import java.util.ArrayList;

public class VistaAdmin extends AppCompatActivity {
    ConexionSQLiteHelper con;
    ArrayList<Mesa> listaMesa;
    RecyclerView recyclerViewMesa;

    ImageView et_salir,et_aceptar,et_agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_admin);

        //Traer elementos del layaout
        et_salir = (ImageView)findViewById(R.id.btn_salir);
        et_agregar = (ImageView) findViewById(R.id.btn_agregar);
        et_aceptar = (ImageView) findViewById(R.id.btn_aceptar);

        //Implementacion del ReciclerView
        con = new ConexionSQLiteHelper(getApplicationContext(),"bd",null,1);
        listaMesa=new ArrayList<>();

        recyclerViewMesa = (RecyclerView) findViewById(R.id.ViewMesa);
        recyclerViewMesa.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        ListaMesaAdapter adapter=new ListaMesaAdapter(listaMesa);
        recyclerViewMesa.setAdapter(adapter);

        //Acciones del boton aceptar
        et_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Aceptar = new Intent(getApplicationContext(), ReservasAdmin.class);
                startActivity(Aceptar);
            }
        });

        //Acciones del boton agregar
        et_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrar = new Intent(getApplicationContext(), AdminAgregarMesa.class);
                startActivity(registrar);
            }
        });

        //Acciones del boton salir
        et_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent salir = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(salir);
            }
        });
    }
    private void consultarListaPersonas() {
        SQLiteDatabase db=con.getReadableDatabase();

        Mesa mesa=null;
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_MESA,null);

        while (cursor.moveToNext()){
            mesa=new Mesa();
            mesa.setId(cursor.getInt(0));
            mesa.setIdAdmin(cursor.getInt(1));
            mesa.setNombre(cursor.getString(2));
            mesa.setCantidad(cursor.getInt(3));
            mesa.setDescripcion(cursor.getString(4));
            mesa.setPrecio(cursor.getInt(5));
            mesa.setDisponibilidad(cursor.getString(6));

            listaMesa.add(mesa);
        }
        db.close();
    }
}