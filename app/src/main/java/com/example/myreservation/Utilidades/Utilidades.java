package com.example.myreservation.Utilidades;

public class Utilidades {
    //Constantes campos tabla usuario
    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_USUARIO="usuario";
    public static final String CAMPO_CORREO="correo";
    public static final String CAMPO_CONTRASENA="contraseña";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE " +
            ""+TABLA_USUARIO+" ("+CAMPO_ID+" " +
            "INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_USUARIO+" TEXT, "+CAMPO_CORREO+" TEXT, "+CAMPO_CONTRASENA+" TEXT)";

    //Constantes campos tabla usuarioAdmin
    public static final String TABLA_USUARIO_ADMIN="admin";
    public static final String CAMPO_ID_ADMIN="id";
    public static final String CAMPO_NOMBRE_ADMIN="nombre";
    public static final String CAMPO_CONTRASENA_ADMIN="contraseña";

    public static final String CREAR_TABLA_USUARIO_ADMIN="CREATE TABLE " +
            ""+TABLA_USUARIO_ADMIN+" ("+CAMPO_ID_ADMIN+" " +
            "INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOMBRE_ADMIN+" TEXT,"+CAMPO_CONTRASENA_ADMIN+" TEXT)";

    //Constantes campos tabla Mesa
    public static final String TABLA_MESA="mesa";
    public static final String CAMPO_ID_MESA="id";
    public static final String CAMPO_ID_MESA_ADMIN="dueño";
    public static final String CAMPO_NOMBRE_MESA="nombre";
    public static final String CAMPO_CANTIDAD="cantidad";
    public static final String CAMPO_DESCRIPCION="descripcion";
    public static final String CAMPO_PRECIO="precio";
    public static final String CAMPO_DISPONIBILIDA="disponibilidad";
    public static final String CREAR_TABLA_MESA="CREATE TABLE " +
            ""+TABLA_MESA+" ("+CAMPO_ID_MESA+" " +
            "INTEGER PRIMARY KEY,"+CAMPO_ID_MESA_ADMIN+" TEXT, "+CAMPO_NOMBRE_MESA+" TEXT,"+CAMPO_CANTIDAD+" INTEGER," +
            ""+CAMPO_DESCRIPCION+" TEXT,"+CAMPO_PRECIO+" INTEGER,"+CAMPO_DISPONIBILIDA+" TEXT )";

    //Constantes campos tabla reserva
    public static final String TABLA_RESERVA="reserva";
    public static final String CAMPO_ID_RESERVA="id_reserva";
    public static final String CAMPO_ID_RESERVA_USER="id_reserva_user";
    public static final String CAMPO_ID_RESERVA_ADMIN="id_admin";

    public static final String CAMPO_NOMBRE_MESA_RESERVA="nombre_mesa";
    public static final String CAMPO_FECHA="fecha";
    public static final String CAMPO_INICIO="inicio";
    public static final String CAMPO_ID_FIN="fin";

    public static final String CREAR_TABLA_RESERVA="CREATE TABLE " +
            ""+TABLA_RESERVA+" ("+CAMPO_ID_RESERVA+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_ID_RESERVA_USER+" TEXT, "+CAMPO_ID_RESERVA_ADMIN+" INTEGER,"+CAMPO_NOMBRE_MESA_RESERVA+" TEXT" +
            ","+CAMPO_FECHA+" TEXT,"+CAMPO_INICIO+" TEXT,"+CAMPO_ID_FIN+" TEXT)";

}
