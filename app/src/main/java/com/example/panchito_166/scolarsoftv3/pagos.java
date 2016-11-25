package com.example.panchito_166.scolarsoftv3;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class pagos extends AppCompatActivity {
    private ListView folio, fecha, total;
    /*private ListView list,lista,totales,folios;
    */
    //private String[] sistemas = {" pago de mes:Enero, Pago de colegiatura de primaria, pago de mes:Febrero,Libro de matematicas, Blusa mujer "," pago de mes:Marzo, Pago de colegiatura de primaria, pago de mes:Abril"};
    private String[] fecha_array ={"Fecha","2016-11-10","2016-11-10"};
    private  String[] total_array ={"Pago","9584","7692"};
    private  String[] folio_array = {"Folio","72","75"};

    private String datos_persona[];
    private String datos_empresa[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);
        folio = (ListView) findViewById(R.id.listView);
        fecha = (ListView) findViewById(R.id.listView2);
        total = (ListView) findViewById(R.id.listView3);

        datos_empresa = getIntent().getStringArrayExtra("datos_empresa");
        datos_persona = getIntent().getStringArrayExtra("datos_persona");

        folio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position>0)ticket(position);
            }
        });

        total.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position>0)ticket(position);
            }
        });

        fecha.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position>0)ticket(position);
            }
        });

        Display display = getWindowManager().getDefaultDisplay();
        //int ancho = display.getWidth();
        int  alto= display.getHeight();

        System.out.println("--------------------- VALOR ROTACION" + display.getRotation());
        int rotacion = display.getRotation();
        if ( rotacion == 1){
            folio.getLayoutParams().width=alto*1/4;
            fecha.getLayoutParams().width=alto*1/2;
            total.getLayoutParams().width=alto*1/2;
        }else{
            folio.getLayoutParams().width=alto*1/8;
            fecha.getLayoutParams().width=alto*1/4;
            total.getLayoutParams().width=alto*1/5;
        }

        ArrayAdapter<String> folio_adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, folio_array);
        folio.setAdapter(folio_adaptador);

        ArrayAdapter<String> fecha_adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fecha_array);
        fecha.setAdapter(fecha_adaptador);

        ArrayAdapter<String> total_adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, total_array);
        total.setAdapter(total_adaptador);

    }

    public void ticket(int posicion){
        String valor_folio = folio_array[posicion];
        System.out.println("-------------------------------Folio: " + valor_folio);
        Intent i =  new Intent(this,ticket.class);
        i.putExtra("datos_empresa",datos_empresa);
        i.putExtra("datos_persona", datos_persona);
        i.putExtra("folio", valor_folio);
        startActivity(i);
    }
}