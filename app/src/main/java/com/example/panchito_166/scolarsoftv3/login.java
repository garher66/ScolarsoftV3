package com.example.panchito_166.scolarsoftv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class login extends AppCompatActivity implements View.OnClickListener {

    EditText usuario;
    EditText password;
    Button btingreso;
    EditText nombre_usuario_panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = (EditText) findViewById(R.id.email2);
        password = (EditText) findViewById(R.id.pass2);
        btingreso = (Button) findViewById(R.id.iniciarS2);
        btingreso.setOnClickListener(this);
        //nombre_usuario_panel = (EditText) findViewById(R.id.nombre_usuario);

        // try {cambiar_datos_usuario();} catch (IOException e) {e.printStackTrace();}
        //catch (JSONException e) {e.printStackTrace();}

    }



    public String enviarDatosGET(String usuario, String password) {
        //usuario = "etzil_ale@hotmail.com";
        //password="alumno01";

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder resul = null;
        try {

            //url = new URL("http://www.scolarsoft.com/movil/login.php?usuario=" + usuario + "&password=" + password + "&opcion=1");
            url = new URL("http://scolarmovil.esy.es/login.php?usuario=" + usuario + "&password=" + password + "&opcion=1");
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            respuesta = conection.getResponseCode();
            resul = new StringBuilder();
            if (respuesta == 200) {
                InputStream in = new BufferedInputStream(conection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((linea = reader.readLine()) != null) {
                    resul.append(linea);
                }
            }
        } catch (Exception e) {
        }
        return resul.toString();
    }

    @Override
    public void onClick(View view) {
        Thread tr = new Thread() {
            @Override
            public void run() {
                final String resultado = enviarDatosGET(usuario.getText().toString(), password.getText().toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        int r = obtDatosJSON(resultado);
                        //int r = 1;
                        if (r > 0) {
                            try {
                                JSONArray json = new JSONArray(resultado);


                                String usuario="",id="";
                                for (int i=0; i < json.length(); i++){
                                    JSONObject oneObject = json.getJSONObject(i);
                                    id = oneObject.getString("ID_USUARIO");
                                }

                                Toast.makeText(getApplicationContext(),"dato:"+id, Toast.LENGTH_SHORT).show();

                             //String id = "6";
                            Intent i = new Intent(getApplicationContext(),menu.class);
                                i.putExtra("dato", id);
                                startActivity(i);

                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        } else {

                            Toast.makeText(getApplicationContext(), "Usted no tiene acceso", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        };

        tr.start();
    }

    public int obtDatosJSON(String response){
        int res=0;
        try {
            JSONArray json = new JSONArray(response);
            if(json.length()>0){
                if(json.length()>0){
                    res=1;
                }
            }
        }catch (Exception e){}
        return res;
    }
}
