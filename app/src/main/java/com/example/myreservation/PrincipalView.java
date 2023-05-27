package com.example.myreservation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class PrincipalView extends AppCompatActivity {
    TextView et_prueba;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_principal);
        // Obtenga el intent que se inicio en MainActivity y extraiga la cadena
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Traemos los elementos del layout
        et_prueba = (TextView) findViewById(R.id.txt_ver);
        et_prueba.setText("Bienvenido Usuario: "+message);

    }
}
