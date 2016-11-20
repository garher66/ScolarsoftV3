package com.example.panchito_166.scolarsoftv3;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

import static com.example.panchito_166.scolarsoftv3.R.id.menu_despegar;
import static com.example.panchito_166.scolarsoftv3.R.id.text;

public class menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText nombre_usuario_panel;
    datos_personales datos_personales_;
    datos_historial datos_historial_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationView navigationView1 = (NavigationView) findViewById(R.id.action_settings);
        navigationView.setNavigationItemSelectedListener(this);

        datos_personales_ = new datos_personales();
        String clave_usuario = getIntent().getStringExtra("dato");
        datos_personales_.setClave_usuario(clave_usuario);

        try {
            asignar_datos_usuario();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        /*datos_historial_ = new datos_historial();
        String clave_usu = getIntent().getStringExtra("dato");
        datos_historial_.setClave_usuario(clave_usu);*/
    }

    private void asignar_datos_usuario() throws IOException, JSONException {
        conexion instancia_conexion = new conexion();
        String enviar_parametros[] = {"ID_USUARIO=" + datos_personales_.getClave_usuario(), "opcion=3"};
        String respuest_metodo = instancia_conexion.enviar_consulta(enviar_parametros);
        JSONArray json = new JSONArray(respuest_metodo);
        String materias[][] = new String[json.length()][7];

        for (int i = 0; i < json.length(); i++) {
            JSONObject oneObject = json.getJSONObject(i);
            materias[i][0] = oneObject.getString("ID_ASIGNATURA");
            materias[i][1] = oneObject.getString("NOMBRE_ASIGNATURA");
            for (int j = 0; j < 5; j++)
                materias[i][j + 2] = oneObject.getString("PARCIAL" + (j + 1));

            datos_personales_.setNombre_completo(oneObject.getString("NOMBRE_USUARIO"));
            datos_personales_.setMatricula(oneObject.getString("MATRICULA"));
            datos_personales_.setGrado(oneObject.getString("GRADO"));
            datos_personales_.setGrupo(oneObject.getString("NOMBRE_GRUPO"));
            datos_personales_.setCiclo_escolar(oneObject.getString("CICLO"));
            datos_personales_.setNombre_nivel(oneObject.getString("NOMBRE_NIVEL"));
            datos_personales_.setCorreo(oneObject.getString("CORREO"));
        }
        datos_personales_.setMateria(materias);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        TextView usuario = (TextView) findViewById(R.id.nombre_usuario);
        usuario.setText(datos_personales_.getNombre_completo());


        TextView correo = (TextView) findViewById(R.id.correo_usuario);
        correo.setText(datos_personales_.getCorreo());

        TextView nombre = (TextView) findViewById(R.id.txt_nombre);
        nombre.setText(datos_personales_.getNombre_completo());

        TextView ciclo = (TextView) findViewById(R.id.txt_ciclo);
        ciclo.setText(datos_personales_.ciclo_escolar);

        TextView escolaridad = (TextView) findViewById(R.id.txt_escolaridad);
        escolaridad.setText(datos_personales_.getNombre_nivel());

        TextView grado = (TextView) findViewById(R.id.txt_grado);
        grado.setText(datos_personales_.getGrado());

        TextView matricula = (TextView) findViewById(R.id.txt_matricula);
        matricula.setText(datos_personales_.getMatricula());

        TextView grupo = (TextView) findViewById(R.id.txt_grupo);
        grupo.setText(datos_personales_.getGrupo());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {


            try {
                conexion instancia_conexion = new conexion();
                String enviar_parametros[] = {"ID_USUARIO=" + datos_personales_.getClave_usuario(), "opcion=3"};
                String respuest_metodo = instancia_conexion.enviar_consulta(enviar_parametros);
                JSONArray json = new JSONArray(respuest_metodo);
                String materias[] = new String[json.length()];
                String id_materias[] = new String[json.length()];
                String calificaciones1[] = new String[json.length()];
                String calificaciones2[] = new String[json.length()];
                String calificaciones3[] = new String[json.length()];
                String calificaciones4[] = new String[json.length()];
                String calificaciones5[] = new String[json.length()];
                for (int i = 0; i < json.length(); i++) {
                    JSONObject oneObject = json.getJSONObject(i);
                    materias[i] = oneObject.getString("NOMBRE_ASIGNATURA");
                    id_materias[i] = oneObject.getString("ID_ASIGNATURA");
                    calificaciones1[i] = oneObject.getString("PARCIAL1");
                    calificaciones2[i] = oneObject.getString("PARCIAL2");
                    calificaciones3[i] = oneObject.getString("PARCIAL3");
                    calificaciones4[i] = oneObject.getString("PARCIAL4");
                    calificaciones5[i] = oneObject.getString("PARCIAL5");

                }

                Intent i = new Intent(this, asignaturas.class);
                i.putExtra("materias", materias);
                System.out.println("*****************ID2" + id_materias[0]);
                i.putExtra("id_materias", id_materias);
                i.putExtra("PARCIAL1", calificaciones1);
                i.putExtra("PARCIAL2", calificaciones2);
                i.putExtra("PARCIAL3", calificaciones3);
                i.putExtra("PARCIAL4", calificaciones4);
                i.putExtra("PARCIAL5", calificaciones5);
                startActivity(i);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if (id == R.id.nav_gallery) {
            //Intent i =  new Intent(getApplicationContext(),pagos.class);
            //startActivity(i);
            System.out.println("Lo preciono");
            try {
                conexion instancia_conexion = new conexion();
                String enviar_parametros[] = {"ID_USUARIO=2", "ID_ALUMNOS=2" , "ID_EMPRESA=1", "ID_CICLO=1", "opcion=4"};
                String respuest_metodo = instancia_conexion.enviar_consulta(enviar_parametros);
                System.out.println(respuest_metodo);
                JSONArray json = new JSONArray(respuest_metodo);
                String datos_pagos[] = new String[json.length()];
                for (int i = 0; i < json.length(); i++) {
                    JSONObject oneObject = json.getJSONObject(i);
                    datos_pagos[i] = "Folio: " + oneObject.getString("id_folio" + "  Historial:  " + oneObject.getString("historial") + " FECHA:  " + oneObject.getString("fecha") + " Estatus: " + oneObject.getString("status"));
                    System.out.println("---------------------------"+datos_pagos[i]);
                }
                System.out.println("---------------------------SALIO DEL CICLO" );
                Intent i = new Intent(this, pagos.class);
                i.putExtra("datos_pago", datos_pagos);

                startActivity(i);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();

            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}