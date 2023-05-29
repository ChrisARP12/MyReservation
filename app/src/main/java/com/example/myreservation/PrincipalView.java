package com.example.myreservation;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myreservation.Adaptadores.ListaMesaAdapter;
import com.example.myreservation.Entidades.Mesa;
import com.example.myreservation.Entidades.Usuarios;
import com.example.myreservation.Utilidades.Utilidades;

import java.util.ArrayList;

public class PrincipalView extends AppCompatActivity {
    TextView et_prueba;
    ConexionSQLiteHelper con;
    ArrayList<Mesa> listaMesa;
    ArrayList<Usuarios> listaUser;
    RecyclerView recyclerViewMesa;
    ImageView et_reserva,et_agregar,et_salir;
    Usuarios user = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_principal);

        //Traemos los elementos del layout
        et_prueba = (TextView) findViewById(R.id.txt_ver);
        et_agregar = (ImageView) findViewById(R.id.btn_agregarReserva);
        et_reserva = (ImageView)findViewById(R.id.btn_reserva);
        et_salir = (ImageView)findViewById(R.id.btn_salir);

        //Coneccion base de datos
        con = new ConexionSQLiteHelper(getApplicationContext(),"bd",null,1);

        // Obtenga el intent que se inicio en MainActivity y extraiga la cadena
        Intent intent = getIntent();
        String message = intent.getStringExtra("Dato");

        //Metemos los datos de nuestro usuario en un objeto del mismo tipo
        listaUser= new ArrayList<>();
        conseguirUsuario(message);
        user = listaUser.get(0);
        et_prueba.setText("Bienvenido "+user.getNombre());

        //Implementacion del ReciclerView
        listaMesa=new ArrayList<>();

        recyclerViewMesa = (RecyclerView) findViewById(R.id.recyclerMesa);
        recyclerViewMesa.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        ListaMesaAdapter adapter=new ListaMesaAdapter(listaMesa);
        recyclerViewMesa.setAdapter(adapter);

        //Acciones del boton Agregar Reserva
        et_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agregar = new Intent(getApplicationContext(), AgregarReservaUser.class);
                String dato = user.getNombre();
                String dat = user.getUsuario();
                agregar.putExtra("Dato",dato);
                agregar.putExtra("Dat",dat);
                startActivity(agregar);
            }
        });

        //Acciones del boton Ver Reservas
        et_reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent VerReservas = new Intent(getApplicationContext(), ReservaUser.class);
                String dato = user.getUsuario();
                VerReservas.putExtra("Dato", dato);
                startActivity(VerReservas);
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

    //Conseguir Usuario
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
    //Consultar con nuestra lista
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
