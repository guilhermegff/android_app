package com.example.guilherme.avaliacaoalimentar;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TelaFinal extends AppCompatActivity
{
    ImageView image4;
    TextView text8;
    TextView text9;
    TextView text10;
    TextView text11;
    ImageButton botaoConcluir;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_final);

        image4 = (ImageView)findViewById(R.id.image4);
        text8 = (TextView)findViewById(R.id.text8);
        text9 = (TextView)findViewById(R.id.text9);
        text10 = (TextView)findViewById(R.id.text10);
        text11 = (TextView)findViewById(R.id.text11);
        botaoConcluir = (ImageButton)findViewById(R.id.botaoConcluir);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String ra = extras.getString("ra");
        String resp1 = extras.getString("resp_quest1");
        String resp2 = extras.getString("resp_quest2");
        String resp3 = extras.getString("resp_quest3");
        String sugestao = extras.getString("sugestao");
        extras.clear();
        text8.setText(resp1 + " resp1");
        text9.setText(ra + " ra " + sugestao);
        text10.setText(resp3 + " resp3");
        text11.setText(resp2 + " resp2");
        String url = "http://aptwi.herokuapp.com/aluno/"+ra+".json";

    }




}
