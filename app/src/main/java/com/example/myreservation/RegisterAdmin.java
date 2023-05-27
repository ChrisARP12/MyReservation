package com.example.myreservation;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterAdmin extends AppCompatActivity {
    TextInputEditText et_usuario, et_contrasena, et_confirmarContrasena;
    Button btn_registrar;
    ImageView img_regresar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        //Traemos los elementos del layout
        et_usuario = findViewById(R.id.txt_usuario);
        et_contrasena = findViewById(R.id.txt_contrasena);
        et_confirmarContrasena = findViewById(R.id.txt_confirmaContrasena);
        btn_registrar = findViewById(R.id.btn_registrar);
        img_regresar = findViewById(R.id.btn_regresar);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrViewPrincipal = new Intent(RegisterAdmin.this, PrincipalView.class);
                String contraseña = et_contrasena.getText().toString();
                String confirmarcontraseña = et_confirmarContrasena.getText().toString();
                if (contraseña == confirmarcontraseña){
                    startActivity(IrViewPrincipal);
                }else {
                    Toast.makeText(RegisterAdmin.this, "Verifica de nuevo tu Contraseña", Toast.LENGTH_SHORT).show();
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
}
