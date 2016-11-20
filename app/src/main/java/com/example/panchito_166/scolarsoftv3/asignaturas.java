package com.example.panchito_166.scolarsoftv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import android.content.Intent;
public class asignaturas extends AppCompatActivity {

    private ListView list;
    public Intent i;
     public String id_materias[];
   // datos_personales datos_personales_;

    String id_materias1[];
    String id_materias2[];
    String id_materias3[];
    String id_materias4[];
    String id_materias5[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);
        String resultado[] = getIntent().getStringArrayExtra("materias");
        id_materias = getIntent().getStringArrayExtra("id_materias");
        id_materias1 = getIntent().getStringArrayExtra("PARCIAL1");
        id_materias2 = getIntent().getStringArrayExtra("PARCIAL2");
        id_materias3 = getIntent().getStringArrayExtra("PARCIAL3");
        id_materias4 = getIntent().getStringArrayExtra("PARCIAL4");
        id_materias5 = getIntent().getStringArrayExtra("PARCIAL5");

// enlazamos nuestra lista  con el xm
        list = (ListView) findViewById(R.id.materias);


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resultado);
        list.setAdapter(adaptador);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                valores(position);
            }
            });

    }
    private void valores(int position){
        i = new Intent(this,calificaciones.class);
        String valor[] = new String[5];
        valor[0] = "PRIMER PARCIAL:" + id_materias1[position];
        valor[1] = "SEGUNDO PARCIAL:" + id_materias2[position];
        valor[2] = "TERCERO PARCIAL:" + id_materias3[position];
        valor[3] = "CUARTO PARCIAL:" + id_materias4[position];
        valor[4] = "QUINTO PARCIAL:" + id_materias5[position];
        i.putExtra("datos", valor);
        startActivity(i);
    }
}




