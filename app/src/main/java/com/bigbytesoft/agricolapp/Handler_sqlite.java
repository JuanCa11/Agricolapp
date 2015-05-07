package com.bigbytesoft.agricolapp;

/**
 * Created by JuanCa on 10/04/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

public class Handler_sqlite extends SQLiteOpenHelper {

    public Handler_sqlite(Context ctx) {
        super(ctx, "Mibase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE usuarios (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user TEXT NOT NULL,nombre TEXT,password TEXT,correo TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS usuarios");
        onCreate(db);
    }

    public void insertarReg(String user, String name, String pass, String correo) {
        ContentValues valores = new ContentValues();
        valores.put("user", user);
        valores.put("password", pass);
        valores.put("nombre", name);
        valores.put("correo", correo);
        this.getWritableDatabase().insert("usuarios", null, valores);
    }

    public ArrayList<String> leer() {
        ArrayList<String> result = new ArrayList<String>();
        String[] columnas = {_ID, "user","nombre","password","correo"};
        Cursor c = this.getReadableDatabase().query("usuarios", columnas, null, null, null, null, null);

        int id, iu, ip, in, ic;
        id = c.getColumnIndex(_ID);
        iu = c.getColumnIndex("user");
        in = c.getColumnIndex("nombre");
        ip = c.getColumnIndex("password");
        ic = c.getColumnIndex("correo");

        System.out.println(id+"  "+iu+"  "+ip+"  "+in+"  "+ic);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
           result.add(c.getString(id) + "," + c.getString(iu) + "," + c.getString(in) + "," + c.getString(ip) + "," + c.getString(ic));
        }

        return result;
    }

    public void abrir() {
        this.getWritableDatabase();
    }

    public void cerrar() {
        this.close();
    }
}
