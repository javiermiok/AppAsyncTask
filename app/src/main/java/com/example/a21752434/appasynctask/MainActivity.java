package com.example.a21752434.appasynctask;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResultado = findViewById(R.id.tvResultado);
    }

    public void consumirWebService(View v) {
        if(isNetworkAvailable()) {
            String url = "https://pokeapi.co/api/v2/pokemon/";
            new EjecutarWS().execute(url);
        } else {
            Toast.makeText(this, "No hay red disponible", Toast.LENGTH_LONG).show();
        }
    }

    private class EjecutarWS extends AsyncTask<String, Void, Void> {

        private String resultado;

        @Override
        protected Void doInBackground(String... strings) {
            URL url = null;
            HttpURLConnection conexion = null;
            BufferedReader br = null;

            try {
                url = new URL(strings[0]);
                conexion = (HttpURLConnection) url.openConnection();
                conexion.setDoInput(true); //opcional modo lectura
                br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));

                StringBuffer sb = new StringBuffer();
                String linea = null;

                while (((linea = br.readLine())) != null) {
                    sb.append(linea +" ");
                }

                resultado = sb.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            tvResultado.setText(resultado);
        }
    }

    private boolean isNetworkAvailable() {
        boolean isAvailable = false;

        //Gestor de conectividad
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //Objeto que recupera la información de la red
        NetworkInfo nif = cm.getActiveNetworkInfo();
        //Si la información de red no es nula y estamos conectados
        //la red está
        if (nif != null && nif.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }

}
