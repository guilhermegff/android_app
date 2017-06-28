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

public class Tela4 extends AppCompatActivity
{
    ImageView image3;
    TextView text4;
    ImageButton botaoResp9;
    ImageButton botaoResp10;
    ImageButton botaoResp11;
    ImageButton botaoResp12;

    private String TAG = Tela2.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        image3 = (ImageView)findViewById(R.id.image3);
        text4 = (TextView)findViewById(R.id.text4);
        botaoResp9 = (ImageButton)findViewById(R.id.botaoResp9);
        botaoResp10 = (ImageButton)findViewById(R.id.botaoResp10);
        botaoResp11 = (ImageButton)findViewById(R.id.botaoResp11);
        botaoResp12 = (ImageButton)findViewById(R.id.botaoResp12);
    }
    public void pegaResp(View v)
    {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(v.getId() == R.id.botaoResp9)
        {
            Intent telafinal = new Intent(this, TelaFinal.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest3", "1");
            telafinal.putExtras(extras);
            startActivity(telafinal);

        }
        else if(v.getId() == R.id.botaoResp10)
        {
            Intent telasugestao = new Intent(this, TelaSugestao.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest3", "2");
            telasugestao.putExtras(extras);
            startActivity(telasugestao);
        }

        else if (v.getId() == R.id.botaoResp11)
        {
            Intent telasugestao = new Intent(this, TelaSugestao.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest3", "3");
            telasugestao.putExtras(extras);
            startActivity(telasugestao);
        }
        else
        {
            Intent telafinal = new Intent(this, TelaFinal.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest3", "4");
            telafinal.putExtras(extras);
            startActivity(telafinal);
        }
    }
}
