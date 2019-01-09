package com.example.jonathan.tillaguango_jonathan_5a.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jonathan.tillaguango_jonathan_5a.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class HelperProducto extends SQLiteOpenHelper {

    public HelperProducto(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE producto(id Auto_increment Primary key, nombre VARCHAR(100),precio DOUBLE(9,2))");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void crear(Producto producto){
        ContentValues valores = new ContentValues();
        valores.put("nombre",producto.getNombre());
        valores.put("precio",producto.getPrecio());
        this.getWritableDatabase().insert("producto",null,valores);
    }
    public List<Producto> mostrar(){
        List<Producto> lista = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT *FROM PRODUCTO",null );
        if(cursor.moveToFirst()){
            do{
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                Double precio = cursor.getDouble(cursor.getColumnIndex("precio"));
                Producto producto = new Producto();

                producto.setNombre(nombre);
                producto.setPrecio(precio);

                lista.add(producto);
            }while (cursor.moveToNext());
        }
        return lista;
    }

    }


