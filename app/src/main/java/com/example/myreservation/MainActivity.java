package com.example.myreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    Button btn_ingresar, btn_registrar, btn_soyAdmin;
    TextInputEditText et_usuario, et_contrasena;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

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

        String dato  = et_usuario.getText().toString();
        String usuario  = et_usuario.getText().toString();
        String contraseña  = et_contrasena.getText().toString();

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
                Intent IrVistaPrincipal = new Intent(MainActivity.this, PrincipalView.class);
                IrVistaPrincipal.putExtra(EXTRA_MESSAGE,dato);
                if(!usuario.isEmpty()){
                    if (!contraseña.isEmpty()){
                        startActivity(IrVistaPrincipal);
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

    public void recuperar(){
        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String s1 = prefe.getString("correo","");
        et_usuario.setText(s1);
    }

    public void guardar() {
        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferencias.edit();
        editor.putString("Usuario", et_usuario.getText().toString());
        editor.commit();
    }
}