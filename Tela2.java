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

    ImageView image2;
    TextView text1;
    ImageButton botaoResp1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        image2 = (ImageView)findViewById(R.id.image2);
        text1 = (TextView)findViewById(R.id.text1);
        botaoResp1 = (ImageButton)findViewById(R.id.botaoResp1);

    }

    public void RespQuest1()
    {

    }
}

