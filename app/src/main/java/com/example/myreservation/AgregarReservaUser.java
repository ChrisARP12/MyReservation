package com.example.myreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myreservation.Entidades.Mesa;
import com.example.myreservation.Utilidades.Utilidades;

import java.util.ArrayList;

public class AgregarReservaUser extends AppCompatActivity {
    ImageView btn_regresar;
    EditText fecha,horaInicio,horaFin;
    TextView seleccion;
    Button btn_guardar;
    Spinner comboMesas;
    ConexionSQLiteHelper con;
    ArrayList<String> listaPersonas;
    ArrayList<Mesa> listaMesa;
    String nombreUser;
    String User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_reserva_user);

        //Traemos elementos del layaout
        btn_regresar = (ImageView) findViewById(R.id.btn_regresar);
        btn_guardar = (Button) findViewById(R.id.btn_guardar);
        seleccion = (TextView)findViewById(R.id.txt_seleccion);
        comboMesas = (Spinner) findViewById(R.id.spinner);
        fecha = (EditText) findViewById(R.id.txt_fecha);
        horaInicio = (EditText) findViewById(R.id.txt_horaInicio);
        horaFin = (EditText)findViewById(R.id.txt_horaFin);

        // Obtenga el intent que se inicio en MainActivity y extraiga la cadena
        nombreUser = getIntent().getStringExtra("Dato");
        User = getIntent().getStringExtra("Dat");

        //Coneccion base de datos
        con = new ConexionSQLiteHelper(getApplicationContext(),"bd",null,1);
        consultarListaPersonas();
        ArrayAdapter <CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaMesa);
        comboMesas.setAdapter(adaptador);

        comboMesas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                if (position!=0){
                    seleccion.setText(listaMesa.get(position-1).getNombre());
                }else{
                    seleccion.setText("");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Acciones boton guardar
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vacios()){
                    guardar();
                    Intent volver = new Intent(getApplicationContext(),PrincipalView.class);
                    volver.putExtra("Dato",nombreUser);
                    startActivity(volver);
                }
            }
        });

        //Acciones boton regresar
        btn_regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regresar = new Intent(getApplicationContext(),PrincipalView.class);
                regresar.putExtra("Dato",User);
                startActivity(regresar);
            }
        });
    }
    public void guardar(){
        //Conección a la base de datos
        con = new ConexionSQLiteHelper(getApplicationContext(),"bd",null,1);
        SQLiteDatabase bd = con.getWritableDatabase();
        //Recibimos los valores que vamos a ingresar
        String select = seleccion.getText().toString();
        String fe = fecha.getText().toString();
        String ini = horaInicio.getText().toString();
        String fi = horaFin.getText().toString();
        String nu= nombreUser;

        //Creamos un contenedor que almacene los datos que recibimos
        ContentValues registro = new ContentValues();
        registro.put(Utilidades.CAMPO_ID_RESERVA_USER,nu);
        registro.put(Utilidades.CAMPO_NOMBRE_MESA_RESERVA,select);
        registro.put(Utilidades.CAMPO_FECHA,fe);
        registro.put(Utilidades.CAMPO_INICIO,ini);
        registro.put(Utilidades.CAMPO_ID_FIN,fi);

        //Lo insertamos a nuestra base de datos
        bd.insert(Utilidades.TABLA_RESERVA,null,registro);
        bd.close();

        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
    }
    private boolean vacios(){
        String select = seleccion.getText().toString();
        String fe = fecha.getText().toString();
        String ini = horaInicio.getText().toString();
        String fi = horaFin.getText().toString();
        if (!select.isEmpty()){
            if (!fe.isEmpty()){
                if (!ini.isEmpty()){
                    if (!fi.isEmpty()){
                        return true;
                    }else{
                        Toast.makeText(this, "Seleccione una fecha de fin", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Seleccione una hora de inicio", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Seleccione una fecha", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Selecione una mesa", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    private void consultarListaPersonas() {
        SQLiteDatabase db=con.getReadableDatabase();

        Mesa mesa=null;
        listaMesa =new ArrayList<Mesa>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_MESA,null);

        while (cursor.moveToNext()){
            mesa=new Mesa();
            mesa.setId(cursor.getInt(0));
            mesa.setIdAdmin(cursor.getInt(1));
            mesa.setNombre(cursor.getString(2));

            Log.i("Nombre",mesa.getNombre());

            listaMesa.add(mesa);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaPersonas=new ArrayList<String>();
        listaPersonas.add("Seleccione");

        for(int i=0;i<listaMesa.size();i++){
            listaPersonas.add(listaMesa.get(i).getNombre());
        }

    }
}