package com.example.myreservation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myreservation.Utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO_ADMIN);
        db.execSQL(Utilidades.CREAR_TABLA_MESA);
        db.execSQL(Utilidades.CREAR_TABLA_RESERVA);
        db.execSQL("INSERT INTO "+Utilidades.TABLA_USUARIO+"("+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_USUARIO+","+Utilidades.CAMPO_CORREO+","+Utilidades.CAMPO_CONTRASENA+") VALUES ('Christian','Chris','chris12@gmail.com','chris1234')");
        db.execSQL("INSERT INTO "+Utilidades.TABLA_USUARIO_ADMIN+"("+Utilidades.CAMPO_NOMBRE_ADMIN+","+Utilidades.CAMPO_CONTRASENA_ADMIN+") VALUES ('admin','admin')");
        db.execSQL("INSERT INTO "+Utilidades.TABLA_MESA+"("+Utilidades.CAMPO_ID_MESA_ADMIN+","+Utilidades.CAMPO_NOMBRE_MESA+","+Utilidades.CAMPO_CANTIDAD+","+Utilidades.CAMPO_DESCRIPCION+","+Utilidades.CAMPO_PRECIO+","+Utilidades.CAMPO_DISPONIBILIDA+") VALUES ('admin','Mesa estilo antiguo',4,'Una gran mesa para poder apreciar el arte antiguo',80,'Reservada')");
        db.execSQL("INSERT INTO "+Utilidades.TABLA_MESA+"("+Utilidades.CAMPO_ID_MESA_ADMIN+","+Utilidades.CAMPO_NOMBRE_MESA+","+Utilidades.CAMPO_CANTIDAD+","+Utilidades.CAMPO_DESCRIPCION+","+Utilidades.CAMPO_PRECIO+","+Utilidades.CAMPO_DISPONIBILIDA+") VALUES ('admin','Mesa estilo moderno',8,'Una gran mesa para poder apreciar el arte moderno',90,'Disponible')");
        db.execSQL("INSERT INTO "+Utilidades.TABLA_MESA+"("+Utilidades.CAMPO_ID_MESA_ADMIN+","+Utilidades.CAMPO_NOMBRE_MESA+","+Utilidades.CAMPO_CANTIDAD+","+Utilidades.CAMPO_DESCRIPCION+","+Utilidades.CAMPO_PRECIO+","+Utilidades.CAMPO_DISPONIBILIDA+") VALUES ('admin','Mesa Fiestera',12,'Una gran mesa especialmente diseñada para tus fiestas',120,'Disponible')");
        db.execSQL("INSERT INTO "+Utilidades.TABLA_RESERVA+"("+Utilidades.CAMPO_ID_RESERVA_USER+","+Utilidades.CAMPO_ID_RESERVA_ADMIN+","+Utilidades.CAMPO_NOMBRE_MESA_RESERVA+","+Utilidades.CAMPO_FECHA+","+Utilidades.CAMPO_INICIO+","+Utilidades.CAMPO_ID_FIN+") VALUES ('Christian','admin','Mesa estilo antiguo','12/05/2023','11:45 AM','3:00 PM')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIO_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_MESA);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_RESERVA);
        onCreate(db);
    }
}
