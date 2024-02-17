package com.example.gestioncentrodocente.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GestionCentroDocenteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gestioncentrodocente.db";
    private static final int DATABASE_VERSION = 1;

    public GestionCentroDocenteDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla "usuario"
        db.execSQL("CREATE TABLE usuario (" +
                "dni TEXT PRIMARY KEY, " +
                "nombre TEXT, " +
                "email TEXT, " +
                "password TEXT, " +
                "rol TEXT, " +
                "titulacion TEXT, " +
                "telefono TEXT)");

        // Crear la tabla "tarea"
        db.execSQL("CREATE TABLE tarea (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "receptor TEXT, " +
                "descripcion TEXT, " +
                "observaciones TEXT, " +
                "estado TEXT, " +
                "fecha TEXT)");

        // Crear la tabla "ausencia"
        db.execSQL("CREATE TABLE ausencia (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT, " +
                "asunto TEXT, " +
                "fechaInicio TEXT, " +
                "horaInicio TEXT, " +
                "fechaFin TEXT, " +
                "horaFinal TEXT)");

        // Crear la tabla "guardia"
        db.execSQL("CREATE TABLE guardia (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "receptor TEXT, " +
                "fecha TEXT, " +
                "tipoGuardia TEXT, " +
                "observaciones TEXT)");

        // Crear la tabla "notificacion"
        db.execSQL("CREATE TABLE notificacion (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombreEmisor TEXT, " +
                "tipoNotificacion TEXT, " +
                "mensaje TEXT)");

        // Crear la tabla "permiso"
        db.execSQL("CREATE TABLE permiso (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT, " +
                "criterioComun TEXT, " +
                "criterioEspecifico TEXT)");

        // Crear la tabla "reunion"
        db.execSQL("CREATE TABLE reunion (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombreReunion TEXT, " +
                "receptor TEXT, " +
                "fecha TEXT, " +
                "motivo TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar las tablas si existen
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS tarea");
        db.execSQL("DROP TABLE IF EXISTS ausencia");
        db.execSQL("DROP TABLE IF EXISTS guardia");
        db.execSQL("DROP TABLE IF EXISTS notificacion");
        db.execSQL("DROP TABLE IF EXISTS permiso");
        db.execSQL("DROP TABLE IF EXISTS reunion");

        // Volver a crear la base de datos
        onCreate(db);
    }
}
