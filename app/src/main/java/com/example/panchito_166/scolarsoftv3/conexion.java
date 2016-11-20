package com.example.panchito_166.scolarsoftv3;

import android.os.StrictMode;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class conexion {
    String ip = "scolarsoftv1.esy.es";
    String url_direccion = "http://" + ip + "/login.php";

    public String enviar_consulta(String[] valores_array) throws IOException {
        StrictMode.ThreadPolicy po = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(po);

        String valores = "";
        for (int i = 0; i < valores_array.length; i++) {
            if (i == 0) valores += "?" + valores_array[i];

        else valores += "&" + valores_array[i];
    }
        String concatenar = url_direccion + valores;

        URL url = null;
        System.out.println("-----------"+concatenar);
        url = new URL("" + concatenar.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String msg = getStream(new BufferedReader( new InputStreamReader(connection.getInputStream())));

        return  msg;
    }
    private String getStream(BufferedReader reader){
        String content = "";
        StringBuffer retorno = new StringBuffer();
        try{
            while ((content = reader.readLine()) != null){retorno.append(content);}
        }catch (IOException e) { e.printStackTrace();
        }finally {
            try {reader.close();
            } catch (IOException e) {e.printStackTrace();}
        }
        return retorno.toString();
    }
}



