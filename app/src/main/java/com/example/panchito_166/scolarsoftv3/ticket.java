package com.example.panchito_166.scolarsoftv3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ticket extends AppCompatActivity {
    private String datos_persona[];
    private String datos_empresa[];
    private String folio;
    private EditText nombre_empresa, direccion, correo, telefono;
    private EditText alumno, grado, grupo, ciclo, escolaridad, matricula;
    private EditText folio_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        datos_empresa = getIntent().getStringArrayExtra("datos_empresa");
        datos_persona = getIntent().getStringArrayExtra("datos_persona");
        folio = getIntent().getStringExtra("folio");
        System.out.println("---------------ENTRO BIEN-----------");
       // llamar_objetos();
        //llenar_datos_alumno();
        //llenar_datos_empresa();
    }

    public void llamar_objetos(){
        System.out.println("----------------ENTRO BIEN CLASE-------");
        nombre_empresa = (EditText) findViewById(R.id.txt_nombre_empresa);
        direccion = (EditText) findViewById(R.id.txt_direccion);
        correo = (EditText) findViewById(R.id.txt_correo);
        telefono =  (EditText) findViewById(R.id.txt_telefono);
        //-------------------------------------------
        alumno =  (EditText) findViewById(R.id.txt_nombre);
        grado  = (EditText) findViewById(R.id.txt_grado);
        grupo =  (EditText) findViewById(R.id.txt_grupo);
        ciclo = (EditText) findViewById(R.id.txt_ciclo);
        escolaridad = (EditText) findViewById(R.id.txt_escolaridad);
        matricula =  (EditText) findViewById(R.id.txt_matricula);
        //------------------------------------------
        folio_ =  (EditText) findViewById(R.id.txt_folio);
    }

    public void llenar_datos_empresa(){
        nombre_empresa.setText(datos_empresa[0]);
        direccion.setText(datos_empresa[1]);
        correo.setText(datos_empresa[2]);
        telefono.setText(datos_empresa[3]);
    }

    public void llenar_datos_alumno(){
        alumno.setText(datos_persona[0]);
        grado.setText(datos_persona[1]);
        grupo.setText(datos_persona[2]);
        ciclo.setText(datos_persona[3]);
        escolaridad.setText(datos_persona[4]);
        matricula.setText(datos_persona[5]);
    }
}
