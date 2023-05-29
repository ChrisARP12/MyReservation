package com.example.myreservation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myreservation.Utilidades.Utilidades;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    ImageView img_regresar;
    Button btn_registrarse;
    TextInputLayout et_nombre,et_usuario,et_correo,et_contrasena,et_confirmaContrasena;
    ConexionSQLiteHelper con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Traemos los elementos de nuestra vista.
        img_regresar = findViewById(R.id.btn_regresar);
        btn_registrarse = findViewById(R.id.btn_registrar);
        et_nombre = findViewById(R.id.txt_nombre);
        et_usuario = findViewById(R.id.txt_edit_usuario);
        et_correo = findViewById(R.id.txt_correo);
        et_contrasena= findViewById(R.id.txt_contrasena);
        et_confirmaContrasena = findViewById(R.id.txt_fill_confirmarContrasena);


        //Acciones del boton regresar
        img_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(regresar);
            }
        });

        //Acciones del Boton registrar
        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IrMain = new Intent(getApplicationContext(), MainActivity.class);
                camposVacios();
                if(verificarContra()){
                    if (seRepite()){
                        boolean opcion = validarDatos();
                        if (opcion){
                            guardar();
                            startActivity(IrMain);
                        }else{
                            Toast.makeText(RegisterActivity.this, "Algo a fallado", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Metodo para verificar que no se repitan usuarios
    public boolean seRepite(){
        //Obtenemos el usuario
        String user = et_usuario.getEditText().getText().toString();
        //Coneccion con la base de datos
        con = new ConexionSQLiteHelper(getApplicationContext(),"bd",null,1);
        SQLiteDatabase bd = con.getWritableDatabase();
        //Realizamos la busqueda
        Cursor file = bd.rawQuery("SELECT "+ Utilidades.CAMPO_ID+" FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.CAMPO_USUARIO+" = '"+user+"' ",null);
        if(file.moveToFirst()){
            return false;
        }else {
            Toast.makeText(this, "Usuario nuevo", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    //Metodo para verificar que no haya campos vacios
    public boolean camposVacios(){
        String nombre = et_nombre.getEditText().getText().toString();
        String user = et_usuario.getEditText().getText().toString();
        String correo = et_correo.getEditText().getText().toString();
        String contra = et_contrasena.getEditText().getText().toString();
        String ver = et_confirmaContrasena.getEditText().getText().toString();
        if (!nombre.isEmpty()){
            if (!user.isEmpty()){
                if (!correo.isEmpty()){
                    if (!contra.isEmpty()){
                        if (!ver.isEmpty()){
                            return true;
                        }else{
                            Toast.makeText(this, "Confirma contraseña esta vacio", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "Contraseña esta vacio", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Correo esta vacio", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Usuario esta vacio", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Nombre esta vacio", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    //Metedo para verificar la contraseña
    public boolean verificarContra(){
        String contrasena = et_contrasena.getEditText().getText().toString();
        String confirmarcontrasena = et_confirmaContrasena.getEditText().getText().toString();
        if(contrasena.equals(confirmarcontrasena)){
            return true;
        }else{
            return false;
        }
    }

    //Metodo para guardar nuestros datos en la base de datos
    public void guardar(){
        //Conección a la base de datos
        con = new ConexionSQLiteHelper(getApplicationContext(),"bd",null,1);
        SQLiteDatabase bd = con.getWritableDatabase();
        //Recibimos los valores que vamos a ingresar
        String nombre = et_nombre.getEditText().getText().toString();
        String usuario = et_usuario.getEditText().getText().toString();
        String correo = et_correo.getEditText().getText().toString();
        String contrasena = et_contrasena.getEditText().getText().toString();

        //Creamos un contenedor que almacene los datos que recibimos
        ContentValues registro = new ContentValues();
        registro.put(Utilidades.CAMPO_NOMBRE,nombre);
        registro.put(Utilidades.CAMPO_USUARIO,usuario);
        registro.put(Utilidades.CAMPO_CORREO,correo);
        registro.put(Utilidades.CAMPO_CONTRASENA,contrasena);

        //Lo insertamos a nuestra base de datos
        bd.insert(Utilidades.TABLA_USUARIO,null,registro);
        bd.close();

        Toast.makeText(this, "Se guarda el registro", Toast.LENGTH_LONG).show();
    }

    //Validar nombre
    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            et_nombre.setError("Nombre inválido");
            return false;
        } else {
            et_nombre.setError(null);
        }

        return true;
    }
    /*
    private boolean esTelefonoValido(String telefono) {
        if (!Patterns.PHONE.matcher(telefono).matches()) {
            tilTelefono.setError("Teléfono inválido");
            return false;
        } else {
            tilTelefono.setError(null);
        }

        return true;
    }*/
    //Validar Correo
    private boolean esCorreoValido(String correo) {
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            et_correo.setError("Correo electrónico inválido");
            return false;
        } else {
            et_correo.setError(null);
        }

        return true;
    }

    //Validar datos
    private boolean validarDatos() {
        String nombre = et_nombre.getEditText().getText().toString();
        String correo = et_correo.getEditText().getText().toString();

        boolean a = esNombreValido(nombre);
        boolean b = esCorreoValido(correo);

        if (a && b) {
            return true;
        }
        return false;
    }

}