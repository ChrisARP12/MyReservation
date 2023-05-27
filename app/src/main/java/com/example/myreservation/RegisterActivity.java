package com.example.myreservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    ImageView img_regresar;
    Button btn_registrarse;
    TextInputLayout et_nombre,et_usuario,et_contrasena,et_confirmaContrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Traemos los elementos de nuestra vista.
        img_regresar = findViewById(R.id.btn_regresar);
        btn_registrarse = findViewById(R.id.btn_registrar);
        et_nombre = findViewById(R.id.txt_nombre);
        et_usuario = findViewById(R.id.txt_edit_usuario);
        et_contrasena= findViewById(R.id.txt_contrasena);
        et_confirmaContrasena = findViewById(R.id.txt_fill_confirmarContrasena);



        img_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(regresar);
            }
        });
    }

}