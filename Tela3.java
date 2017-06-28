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

public class Tela3 extends AppCompatActivity {

    ImageView image2;
    TextView text2;
    TextView text3;
    ImageButton botaoResp5;
    ImageButton botaoResp6;
    ImageButton botaoResp7;
    ImageButton botaoResp8;

    private String TAG = Tela2.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela3);

        image2 = (ImageView)findViewById(R.id.image2);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);
        botaoResp5 = (ImageButton)findViewById(R.id.botaoResp5);
        botaoResp6 = (ImageButton)findViewById(R.id.botaoResp6);
        botaoResp7 = (ImageButton)findViewById(R.id.botaoResp7);
        botaoResp8 = (ImageButton)findViewById(R.id.botaoResp8);
    }

    public void pegaResp(View v)
    {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(v.getId() == R.id.botaoResp5)
        {
            Intent telafinal = new Intent(this, TelaFinal.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest2", "1");
            telafinal.putExtras(extras);
            startActivity(telafinal);

        }
        else if(v.getId() == R.id.botaoResp6)
        {
            Intent telafinal = new Intent(this, TelaFinal.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest2", "2");
            telafinal.putExtras(extras);
            startActivity(telafinal);
        }

        else if (v.getId() == R.id.botaoResp7)
        {
            Intent telafinal = new Intent(this, TelaFinal.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest2", "3");
            telafinal.putExtras(extras);
            startActivity(telafinal);
        }
        else
        {
            Intent tela4 = new Intent(this, Tela4.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest2", "4");
            tela4.putExtras(extras);
            startActivity(tela4);
        }
    }
}
