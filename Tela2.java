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
public class Tela2 extends AppCompatActivity
{
    ImageView image1;
    TextView text1;
    ImageButton botaoResp1;
    ImageButton botaoResp2;
    ImageButton botaoResp3;
    ImageButton botaoResp4;

    private String TAG = Tela2.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        image1 = (ImageView)findViewById(R.id.image1);
        text1 = (TextView)findViewById(R.id.text1);
        botaoResp1 = (ImageButton)findViewById(R.id.botaoResp1);
        botaoResp2 = (ImageButton)findViewById(R.id.botaoResp2);
        botaoResp3 = (ImageButton)findViewById(R.id.botaoResp3);
        botaoResp4 = (ImageButton)findViewById(R.id.botaoResp4);
    }

    public void pegaResp(View v)
    {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String ra = extras.getString("ra");
        extras.clear();
        extras.putString("ra", ra);

        if(v.getId() == R.id.botaoResp1)
        {
            Intent telafinal = new Intent(this, TelaFinal.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest1", "1");
            telafinal.putExtras(extras);
            startActivity(telafinal);
        }
        else if(v.getId() == R.id.botaoResp2)
        {
            Intent telafinal = new Intent(this, TelaFinal.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest1", "2");
            telafinal.putExtras(extras);
            startActivity(telafinal);
        }

        else if (v.getId() == R.id.botaoResp3)
        {
            Intent tela3 = new Intent(this, Tela3.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest1", "3");
            tela3.putExtras(extras);
            startActivity(tela3);
        }
        else
        {
            Intent tela4 = new Intent(this, Tela4.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest1", "4");
            tela4.putExtras(extras);
            startActivity(tela4);
        }
    }
}

