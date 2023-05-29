package com.example.myreservation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myreservation.Utilidades.Utilidades;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterAdmin extends AppCompatActivity {
    TextInputLayout et_usuario, et_contrasena, et_confirmarContrasena;
    Button btn_registrar;
    ImageView img_regresar;
    ConexionSQLiteHelper con;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        //Traemos los elementos del layout
        et_usuario = findViewById(R.id.txt_usuario);
        et_contrasena = findViewById(R.id.txt_contrasena_edit);
        et_confirmarContrasena = findViewById(R.id.txt_confirmar);
        btn_registrar = findViewById(R.id.btn_registrar);
        img_regresar = findViewById(R.id.btn_regresar);

        //Creamos las acciones de los botones
        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrViewPrincipal = new Intent(RegisterAdmin.this, MainActivity.class);
                camposVacios();
                if (camposVacios()){
                    if (verificarContra()){
                        if (seRepite()){
                            ingresar();
                            startActivity(IrViewPrincipal);
                        }else {
                            Toast.makeText(RegisterAdmin.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterAdmin.this, "Verifica de nuevo tu Contraseña", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        img_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(RegisterAdmin.this, MainActivity.class);
                startActivity(regresar);
            }
        });
    }
    public boolean seRepite(){
        //Obtenemos el usuario
        String user = et_usuario.getEditText().getText().toString();
        //Coneccion con la base de datos
        con = new ConexionSQLiteHelper(getApplicationContext(),"bd",null,1);
        SQLiteDatabase bd = con.getWritableDatabase();
        //Realizamos la busqueda
        Cursor file = bd.rawQuery("SELECT "+ Utilidades.CAMPO_ID_ADMIN+" FROM "+Utilidades.TABLA_USUARIO_ADMIN+" WHERE "+Utilidades.CAMPO_NOMBRE_ADMIN+" = '"+user+"' ",null);
        if(file.moveToFirst()){
            return false;
        }else {
            Toast.makeText(this, "Admin nuevo creado", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    public boolean camposVacios(){
        String user = et_usuario.getEditText().getText().toString();
        String contra = et_contrasena.getEditText().getText().toString();
        String ver = et_confirmarContrasena.getEditText().getText().toString();
        if (!user.isEmpty()){
            if (!contra.isEmpty()){
                if (!ver.isEmpty()){
                    return true;
                }else{
                    Toast.makeText(this, "Confirma contraseña esta vacio", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Contraseña esta vacio", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Usuario esta vacio", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    public boolean verificarContra(){
        String contrasena = et_contrasena.getEditText().getText().toString();
        String confirmarcontrasena = et_confirmarContrasena.getEditText().getText().toString();
        if(contrasena.equals(confirmarcontrasena)){
            return true;
        }else{
            return false;
        }
    }

    public void ingresar(){
        //Conección a la base de datos
        con = new ConexionSQLiteHelper(getApplicationContext(),"bd",null,1);
        SQLiteDatabase bd = con.getWritableDatabase();
        //Recibimos los valores que vamos a ingresar
        String nombre = et_usuario.getEditText().getText().toString();
        String contrasena = et_contrasena.getEditText().getText().toString();

        //Creamos un contenedor que almacene los datos que recibimos
        ContentValues registro = new ContentValues();
        registro.put(Utilidades.CAMPO_NOMBRE_ADMIN,nombre);
        registro.put(Utilidades.CAMPO_CONTRASENA_ADMIN,contrasena);

        //Lo insertamos a nuestra base de datos
        bd.insert(Utilidades.TABLA_USUARIO_ADMIN,null,registro);
        bd.close();

        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
    }
}
