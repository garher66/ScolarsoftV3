package com.example.panchito_166.scolarsoftv3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ticket extends AppCompatActivity {
    private String datos_persona[];
    private String datos_empresa[];
    private String folio, fecha, subtotal, recargo, descuento, total ;
    private TextView nombre_empresa, direccion, correo, telefono;
    private TextView alumno, grado, grupo, ciclo, escolaridad, matricula;
    private TextView folio_, fecha_, subtotal_, recargo_, descuento_, total_;
    private ListView ln_descripcion, ln_cantidad, ln_total;

    private String ln_descripcion_arreglo[];
    private String ln_total_arreglo[];
    private String ln_cantidad_arreglo[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        datos_empresa = getIntent().getStringArrayExtra("datos_empresa");
        datos_persona = getIntent().getStringArrayExtra("datos_persona");
        folio = getIntent().getStringExtra("folio");
        fecha = getIntent().getStringExtra("fecha");
        System.out.println("---------------ENTRO BIEN-----------");
        //folio_ =   findViewById(R.id.txt_folio);

        llamar_objetos();
        llenar_datos_alumno();

        llenar_recibo();
       // llenar_datos_empresa();

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
        fecha_ = (TextView) findViewById(R.id.txt_fecha);
        subtotal_ =  (TextView) findViewById(R.id.txt_subtotal);
        total_ =  (TextView) findViewById(R.id.txt_total);
        descuento_ =  (TextView) findViewById(R.id.txt_descuento);
        recargo_ =  (TextView) findViewById(R.id.txt_recargo);
        ln_descripcion = (ListView) findViewById(R.id.ln_descripcion);
        ln_cantidad = (ListView) findViewById(R.id.ln_cantidad);
        ln_total = (ListView) findViewById(R.id.ln_total);
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
        fecha_.setText(fecha);
        conexion instancia_conexion = new conexion();
        //String enviar_parametros[] = {"ID_USUARIO=" + datos_persona[7], "opcion=5"};
        String enviar_parametros[] = {"ID_FOLIO=" + folio, "opcion=4"};
        String respuest_metodo = null;
        try {
            respuest_metodo = instancia_conexion.enviar_consulta(enviar_parametros);
            JSONArray json = new JSONArray(respuest_metodo);

            ln_cantidad_arreglo = new String[json.length() + 1];
            ln_descripcion_arreglo =  new String[json.length() + 1];
            ln_total_arreglo = new String[json.length() + 1];

            ln_descripcion_arreglo[0]="DESCRIPCIÓN";
            ln_total_arreglo[0]="PRECIO";
            ln_cantidad_arreglo[0]="CANTIDAD";


            for (int i = 0; i < json.length(); i++) {
                JSONObject oneObject = json.getJSONObject(i);
                ln_cantidad_arreglo[i+1] = oneObject.getString("cantidad");
                ln_total_arreglo[i+1] = oneObject.getString("precio");
                ln_descripcion_arreglo[i+1] = oneObject.getString("descripcion");
                descuento = oneObject.getString("descuento");
                total = oneObject.getString("total");
                subtotal = oneObject.getString("subtotal");
                recargo = oneObject.getString("recargo");

            }

            //----
            String enviar_parametros_[] = {"ID_FOLIO=" + folio, "opcion=7"};
            respuest_metodo = instancia_conexion.enviar_consulta(enviar_parametros_);
            JSONArray json1 = new JSONArray(respuest_metodo);
            for (int i = 0; i < json1.length(); i++) {
                JSONObject oneObject = json1.getJSONObject(i);
                datos_empresa[0]  = oneObject.getString("COLEGIO");
                System.out.println("-------------------------------" + datos_empresa[0]);
                datos_empresa[1]  = oneObject.getString("COLEGIO_DIRECCION");
                datos_empresa[2]  = oneObject.getString("CORREO");
                datos_empresa[3]  = oneObject.getString("COLEGIO_CONTACTO");

            }
            System.out.println("---------------------------------- LLAMARÁ AL METODO");

            llenar_datos_empresa();

            descuento_.setText(descuento);
            total_.setText(total);
            subtotal_.setText(subtotal);
            recargo_.setText(recargo);

            ArrayAdapter<String> ln_descripcion_adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ln_descripcion_arreglo);
            ln_descripcion.setAdapter(ln_descripcion_adaptador);

            ArrayAdapter<String> ln_total_adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ln_total_arreglo);
            ln_total.setAdapter(ln_total_adaptador);

            ArrayAdapter<String> ln_cantidad_adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ln_cantidad_arreglo);
            ln_cantidad.setAdapter(ln_cantidad_adaptador);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
