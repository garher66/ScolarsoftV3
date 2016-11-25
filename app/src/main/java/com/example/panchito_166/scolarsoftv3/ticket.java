package com.example.panchito_166.scolarsoftv3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ticket extends AppCompatActivity {
    private String datos_persona[];
    private String datos_empresa[];
    private String folio;
    private TextView nombre_empresa, direccion, correo, telefono;
    private TextView alumno, grado, grupo, ciclo, escolaridad, matricula;
    private TextView folio_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        datos_empresa = getIntent().getStringArrayExtra("datos_empresa");
        datos_persona = getIntent().getStringArrayExtra("datos_persona");
        folio = getIntent().getStringExtra("folio");
        System.out.println("---------------ENTRO BIEN-----------");
        //folio_ =   findViewById(R.id.txt_folio);

        llamar_objetos();
        llenar_datos_alumno();
        llenar_datos_empresa();
        llenar_recibo();
    }

    public void llamar_objetos(){
        System.out.println("----------------ENTRO BIEN CLASE-------");
        nombre_empresa = (TextView) findViewById(R.id.txt_nombre_empresa);
        direccion = (TextView) findViewById(R.id.txt_direccion);
        correo = (TextView) findViewById(R.id.txt_correo);
        telefono =  (TextView) findViewById(R.id.txt_telefono);
        //-------------------------------------------
        alumno =  (TextView) findViewById(R.id.txt_nombre);
        grado  = (TextView) findViewById(R.id.txt_grado);
        grupo =  (TextView) findViewById(R.id.txt_grupo);
        ciclo = (TextView) findViewById(R.id.txt_ciclo);
        escolaridad = (TextView) findViewById(R.id.txt_escolaridad);
        matricula =  (TextView) findViewById(R.id.txt_matricula);
        //------------------------------------------
        folio_ =  (TextView) findViewById(R.id.txt_folio);
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

    public void llenar_recibo(){
        folio_.setText(folio);
    }
}
