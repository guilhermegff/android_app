package com.example.guilherme.avaliacaoalimentar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.StrictMode;
import android.content.Intent;

import android.content.Intent;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class TelaFinal extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_final);
    }

    /*private class PostDados extends AsyncTask<String, Void, Void>
    {

        @Override
        protected void onPreExecute()
        {

            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Classificando Item", Toast.LENGTH_LONG).show();
        }
        @Override
        protected Void doInBackground(String... quali)
        {
            HttpHandler sh = new HttpHandler();
            String resposta = "";

            String url = "http://monitwi.herokuapp.com/monitoramentos/1/";
            try
            {

                resposta = sh.requestPost(url, quali[0]);
            }
            catch (final Exception e)
            {
                Log.e(TAG, "Couldn't get json from server." + e.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Couldn't get json from server. Check LogCat" + e.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            Toast.makeText(MainActivity.this, "Item Classificado!" , Toast.LENGTH_LONG).show();
        }
    }*/


}
