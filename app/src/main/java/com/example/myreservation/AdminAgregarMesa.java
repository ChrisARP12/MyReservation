package com.example.myreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myreservation.Utilidades.Utilidades;
import com.google.android.material.textfield.TextInputLayout;

public class AdminAgregarMesa extends AppCompatActivity {
    ImageView btn_regresar;
    Button btn_guardar;
    TextInputLayout nombre,capacidad,descripcion,precio;
    ConexionSQLiteHelper con;
    String admin = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_agregar_mesa);

        btn_regresar = (ImageView)findViewById(R.id.btn_regresar);
        btn_guardar=(Button)findViewById(R.id.btn_guardar);
        nombre = (TextInputLayout)findViewById(R.id.txt_nombre_mesa);
        capacidad = (TextInputLayout)findViewById(R.id.txt_capacidad);
        descripcion = (TextInputLayout)findViewById(R.id.txt_descripcion);
        precio = (TextInputLayout)findViewById(R.id.txt_precio);

        //Acciones para boton guardar
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vacios()){
                    guardar();
                    Intent ir = new Intent(getApplicationContext(),VistaAdmin.class);
                    startActivity(ir);
                }
            }
        });



        //Acciones boton regresar
        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regresar = new Intent(getApplicationContext(),VistaAdmin.class);
                startActivity(regresar);
            }
        });
    }

    public boolean vacios(){
        String nom = nombre.getEditText().getText().toString();
        Integer cap = capacidad.getEditText().getInputType();
        String des = descripcion.getEditText().getText().toString();
        Integer pre = precio.getEditText().getInputType();
        if (!nom.isEmpty()){
            if (cap != null){
                if (!des.isEmpty()){
                    if (pre != null){
                        return true;
                    }else {
                        Toast.makeText(this, "Precio esta vacio", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Descripción esta vaciá", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Capacidad esta vaciá", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Nombre esta vacio", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    public void guardar(){
        //Conección a la base de datos
        con = new ConexionSQLiteHelper(getApplicationContext(),"bd",null,1);
        SQLiteDatabase bd = con.getWritableDatabase();
        //Recibimos los valores que vamos a ingresar
        String nomU = admin;
        String nom = nombre.getEditText().getText().toString();
        Integer cap = capacidad.getEditText().getInputType();
        String des = descripcion.getEditText().getText().toString();
        Integer pre = precio.getEditText().getInputType();
        String dis = "Disponible";

        //Creamos un contenedor que almacene los datos que recibimos
        ContentValues registro = new ContentValues();
        registro.put(Utilidades.CAMPO_ID_MESA_ADMIN,nomU);
        registro.put(Utilidades.CAMPO_NOMBRE_MESA,nom);
        registro.put(Utilidades.CAMPO_CANTIDAD,cap);
        registro.put(Utilidades.CAMPO_DESCRIPCION,des);
        registro.put(Utilidades.CAMPO_PRECIO,pre);
        registro.put(Utilidades.CAMPO_DISPONIBILIDA,dis);

        //Lo insertamos a nuestra base de datos
        bd.insert(Utilidades.TABLA_MESA,null,registro);
        bd.close();

        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
    }

}