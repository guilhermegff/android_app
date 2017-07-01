package com.example.guilherme.avaliacaoalimentar;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelaSugestao extends AppCompatActivity
{
    ImageButton botaoProximo;
    TextView text5;
    TextView text6;
    EditText edit3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sugestao);

        botaoProximo = (ImageButton)findViewById(R.id.botaoProximo);
        text5 = (TextView)findViewById(R.id.text5);
        text6 = (TextView)findViewById(R.id.text6);
        edit3 = (EditText)findViewById(R.id.edit3);
    }
    public void pegaSugestao(View v)
    {
        String sugestao = edit3.getText().toString();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(validaSugestao(sugestao))
        {
            extras.putString("sugestao", sugestao);
            Intent telaFinal = new Intent(this, TelaFinal.class);
            telaFinal.putExtras(extras);
            startActivity(telaFinal);
        }
        else
        {
            return;
        }
    }
    public boolean validaSugestao (String sugestao)
    {
        String regex = "^[a-z_A-Z0-9 ]*$";
        CharSequence entrada = sugestao;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(entrada);

        if(sugestao.length() == 0)
        {
            edit3.setError("Escreva a Sugestão");
            return false;
        }
        else if(sugestao.length() > 200)
        {
            edit3.setError("Máx 200 letras");
            return false;
        }
        else if(!matcher.matches())
        {
            edit3.setError("Erro no texto");
            return false;
        }
        return true;
    }
}
