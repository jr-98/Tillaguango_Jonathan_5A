package com.example.jonathan.tillaguango_jonathan_5a;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathan.tillaguango_jonathan_5a.helper.HelperProducto;
import com.example.jonathan.tillaguango_jonathan_5a.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class Actividad_Principal extends AppCompatActivity {
    TextView hamburguesa,cerveza,ensalada,salchipapa,total;
    TextView precioHam, precioCerv,precioEnsa,preccioSalchi;
    EditText cantHam, cantCerv, cantEnsa, cantSalchi;
    Button Calcular;
    HelperProducto helperProducto;
    ArrayList<Producto> productos;

    List<Producto> listaProductos;
    Producto producto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helperProducto = new HelperProducto(this, "dbPrueba", null, 1);
        //nombres
        setContentView(R.layout.activity_actividad__principal);
        hamburguesa= (TextView) findViewById(R.id.lblHambuerguesa);
        cerveza= (TextView) findViewById(R.id.lblCerveza);
        ensalada= (TextView) findViewById(R.id.lblEnsalada);
        salchipapa= (TextView) findViewById(R.id.lblSalchipapas);
        //precio
        precioHam= (TextView) findViewById(R.id.lblprecioHam);
        precioCerv= (TextView) findViewById(R.id.lblprecioCerveza);
        precioEnsa= (TextView) findViewById(R.id.lblPrecioEnsalada);
        preccioSalchi = (TextView) findViewById(R.id.lblPrecioSalch);
        //cantidades
        cantHam = (EditText) findViewById(R.id.txtHamburguesa);
        cantCerv = (EditText) findViewById(R.id.txtCerveza);
        cantEnsa = (EditText) findViewById(R.id.txtEmsalada);
        cantSalchi = (EditText) findViewById(R.id.txtSalchipapa);
        //boton y lbl para presentar resultado
        Calcular = (Button) findViewById(R.id.btnComprar);
        total = (TextView) findViewById(R.id.lblResultado);
        obtener();
        //EObtencion de datos
        Calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double Hamburgesa,Cerveza, Ensalada, Salchipapa,resultao;
                Hamburgesa = Double.parseDouble(precioHam.getText().toString());
                Cerveza = Double.parseDouble(precioCerv.getText().toString());
                Ensalada = Double.parseDouble(precioEnsa.getText().toString());
                Salchipapa = Double.parseDouble(preccioSalchi.getText().toString());

                Integer cant1, cant2, cant3, cant4;

                cant1 = Integer.parseInt(cantHam.getText().toString());
                cant2 = Integer.parseInt(cantCerv.getText().toString());
                cant3 = Integer.parseInt(cantEnsa.getText().toString());
                cant4 = Integer.parseInt(cantSalchi.getText().toString());

                Double resultado = (Hamburgesa*cant1)+(Cerveza*cant2)+(Ensalada*cant3)+(Salchipapa*cant4);

                total.setText(String.valueOf(resultado));
                if(resultado>10){
                    Double resultado2 = (resultado*0.12)+resultado;
                    total.setText(String.valueOf(resultado2));
                    Toast.makeText(getApplicationContext(),"Se ha cobrado iva",Toast.LENGTH_LONG).show();
                }
            }
        });
        //Fin obtencion de datos

    }
    private void obtener() {
        productos = new ArrayList<Producto>();
        listaProductos = helperProducto.mostrar();

            producto = listaProductos.get(0);
            hamburguesa.setText(producto.getNombre());
            precioHam.setText(String.valueOf(producto.getPrecio()));
            //
            producto = listaProductos.get(1);
            cerveza.setText(producto.getNombre());
            precioCerv.setText(String.valueOf(producto.getPrecio()));
            //
            producto = listaProductos.get(2);
            ensalada.setText(producto.getNombre());
            precioEnsa.setText(String.valueOf(producto.getPrecio()));
            //
            producto = listaProductos.get(3);
            salchipapa.setText(producto.getNombre());
            preccioSalchi.setText(String.valueOf(producto.getPrecio()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.opcionIngresar:
                final Dialog dialogCrear = new Dialog(Actividad_Principal.this);
                dialogCrear.setContentView(R.layout.dlg_crear);
                dialogCrear.setTitle("Crear Nuevo Producto");
                final EditText cajaNombre = (EditText) dialogCrear.findViewById(R.id.txtNombre);
                final EditText cajaPrecio = (EditText) dialogCrear.findViewById(R.id.txtPrecio);
                Button botonGuardar = (Button) dialogCrear.findViewById(R.id.btnGuardar);
                botonGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Producto p = new Producto();
                        p.setNombre(cajaNombre.getText().toString());
                        p.setPrecio(Double.parseDouble(cajaPrecio.getText().toString()));
                        helperProducto.crear(p);
                        dialogCrear.hide();
                    }
                });
                dialogCrear.show();
                break;
        }
        return true;
    }
}
