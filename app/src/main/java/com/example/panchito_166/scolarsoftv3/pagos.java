package com.example.panchito_166.scolarsoftv3;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class pagos extends AppCompatActivity {
    private ListView list,lista,totales,folios;
    private String[] sistemas = {" pago de mes:Enero, Pago de colegiatura de primaria, pago de mes:Febrero,Libro de matematicas, Blusa mujer "," pago de mes:Marzo, Pago de colegiatura de primaria, pago de mes:Abril"};
    private String[] fecha ={"2016-11-10","2016-11-10"};
    private  String[] total ={"9584","7692"};
    private  String[] folio = {"72","75"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);
        list = (ListView)findViewById(R.id.listView);
        lista = (ListView) findViewById(R.id.listView2);
        totales = (ListView) findViewById(R.id.listView3);
        folios = (ListView) findViewById(R.id.listView4);
        System.out.println("-------------------------Entro a pagos.class");
        String datos[] = getIntent().getStringArrayExtra("datos_pago");
        System.out.println("-------------------------DATOS ENVIADOS: : : : : " + datos[0]);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        list.setAdapter(adaptador);

/*
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sistemas);
        list.setAdapter(adaptador);

        ArrayAdapter<String> adaptado = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, fecha);
        lista.setAdapter(adaptado);

        ArrayAdapter<String> tot = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, total);
        totales.setAdapter(tot);

        ArrayAdapter<String> fol = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, folio);
        totales.setAdapter(fol);

*/
    }

}