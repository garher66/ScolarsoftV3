package com.example.panchito_166.scolarsoftv3;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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

    String tmp_folio[];
    String tmp_fecha[];
    String tmp_total[];


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

        conexion instancia_conexion = new conexion();
        //String enviar_parametros[] = {"ID_USUARIO=" + datos_persona[7], "opcion=5"};
        String enviar_parametros[] = {"ID_USUARIO=" + datos_persona[6], "opcion=5"};
        System.out.println("---------------------ID" + datos_persona[6]);
        String respuest_metodo = null;
        try {
            respuest_metodo = instancia_conexion.enviar_consulta(enviar_parametros);
            JSONArray json = new JSONArray(respuest_metodo);
            tmp_folio = new String[json.length()+1];
            tmp_fecha = new String[json.length()+1];
            tmp_total = new String[json.length()+1];

            tmp_fecha[0]="FECHA"; tmp_folio[0]="FOLIO"; tmp_total[0]="TOTAL";
            if (json.length()>0){
            for (int i = 0; i < json.length(); i++) {
                JSONObject oneObject = json.getJSONObject(i);
                tmp_fecha[i+1] = oneObject.getString("fecha");
                tmp_folio[i+1] = oneObject.getString("id_folio");
                tmp_total[i+1] = oneObject.getString("total");
            }
            }else{
                tmp_fecha[1]=""; tmp_folio[1]=""; tmp_total[1]="";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



        ArrayAdapter<String> folio_adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tmp_folio);
        folio.setAdapter(folio_adaptador);

        ArrayAdapter<String> fecha_adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tmp_fecha);
        fecha.setAdapter(fecha_adaptador);

        ArrayAdapter<String> total_adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tmp_total);
        total.setAdapter(total_adaptador);

    }

    public void ticket(int posicion){
        String valor_folio = tmp_folio[posicion];
        if (tmp_folio[posicion]!=""){
        System.out.println("-------------------------------Folio: " + valor_folio);
        Intent i =  new Intent(this,ticket.class);
        i.putExtra("datos_empresa",datos_empresa);
        i.putExtra("datos_persona", datos_persona);
        i.putExtra("folio", valor_folio);
        i.putExtra("fecha", tmp_fecha[posicion]);
        startActivity(i);
        }
    }
}