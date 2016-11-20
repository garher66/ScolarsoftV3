package com.example.panchito_166.scolarsoftv3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class calificaciones extends AppCompatActivity {

    private ListView calificacion;
    private String[] sistemas = {"P1:10", "P2:9", "P3:8", "P4:10", "P5:8",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificaciones);
        calificacion = (ListView) findViewById(R.id.calificacion);
        String valor[] = getIntent().getStringArrayExtra("datos");
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valor);
        calificacion.setAdapter(adaptador);


    }

}
