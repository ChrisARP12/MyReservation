package com.example.myreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myreservation.Entidades.Usuarios;
import com.example.myreservation.Utilidades.Utilidades;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    Button btn_ingresar, btn_registrar, btn_soyAdmin;
    TextInputLayout et_usuario, et_contrasena;
    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Traemos los elementos de nuestra vista.
        btn_ingresar = findViewById(R.id.btn_ingresar);
        btn_registrar = findViewById(R.id.btn_registrar);
        btn_soyAdmin = findViewById(R.id.btn_quieroAdmin);
        et_usuario =  findViewById(R.id.txt_usuario);
        et_contrasena = findViewById(R.id.txt_contrasena);



        recuperar();
        /*if (getIntent().getStringExtra("correo") != null) {
            String correo = getIntent().getStringExtra("correo");
            String contrasenia = getIntent().getStringExtra("contrasena");

            et_usuario.setText(correo);
            et_contrasena.setText(contrasenia);
            guardar();
        }*/

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent para ir a las vista usuario
                Intent IrVistaPrincipal = new Intent(MainActivity.this, PrincipalView.class);
                IrVistaPrincipal.putExtra("Dato",et_usuario.getEditText().getText().toString());

                //Intent para ir a las vistas Admin
                Intent IrVistaAdmin = new Intent(MainActivity.this, VistaAdmin.class);

                String usuario  = et_usuario.getEditText().getText().toString();
                String contraseña  = et_contrasena.getEditText().getText().toString();
                guardar();

                //Verificar campos vacios asi como el usuario y la contraseña
                if(!usuario.isEmpty()){
                    if (!contraseña.isEmpty()){
                        int opciones = buscar();
                        switch (opciones){
                            case 0:
                                Toast.makeText(MainActivity.this, "Usuario y contraseña no encontrados", Toast.LENGTH_SHORT).show();
                                break;
                            case 1: startActivity(IrVistaPrincipal);
                                break;
                            case 2: startActivity(IrVistaAdmin);
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "jjj no debo de aparecer", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Contraseña Vacia", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Usuario vacio", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrRegistro = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(IrRegistro);
            }
        });

        btn_soyAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrRegistro = new Intent(MainActivity.this, RegisterAdmin.class);
                startActivity(IrRegistro);
            }
        });
    }

    public int buscar(){
        String user  = et_usuario.getEditText().getText().toString();
        String cont  = et_contrasena.getEditText().getText().toString();
        con = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);
        SQLiteDatabase bd = con.getWritableDatabase();

        Cursor file = bd.rawQuery("SELECT "+ Utilidades.CAMPO_ID+" FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.CAMPO_USUARIO+" = '"+user+"' AND "+Utilidades.CAMPO_CONTRASENA+"='"+cont+"'",null);
        Cursor file2 = bd.rawQuery("SELECT "+ Utilidades.CAMPO_ID_ADMIN+" FROM "+Utilidades.TABLA_USUARIO_ADMIN+" WHERE "+Utilidades.CAMPO_NOMBRE_ADMIN+" = '"+user+"' AND "+Utilidades.CAMPO_CONTRASENA_ADMIN+"='"+cont+"'",null);
        if (file.moveToFirst()){
            return 1;
        }else{
            if (file2.moveToFirst()){
                return 2;
            }else{
                return 0;
            }
        }
    }

    public void recuperar(){
        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String s1 = prefe.getString("Usuario","");
        et_usuario.getEditText().setText(s1);
    }

    public void guardar() {
        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferencias.edit();
        editor.putString("Usuario", et_usuario.getEditText().getText().toString());
        editor.commit();
    }
}