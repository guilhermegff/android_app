package com.example.guilherme.avaliacaoalimentar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;


public class Tela3 extends AppCompatActivity {

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
            Intent telaFinal = new Intent(this, TelaFinal.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest2", "5");
            telaFinal.putExtras(extras);
            startActivity(telaFinal);

        }
        else if(v.getId() == R.id.botaoResp6)
        {
            Intent telaFinal = new Intent(this, TelaFinal.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest2", "6");
            telaFinal.putExtras(extras);
            startActivity(telaFinal);
        }

        else if (v.getId() == R.id.botaoResp7)
        {
            Intent telaFinal = new Intent(this, TelaFinal.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest2", "7");
            telaFinal.putExtras(extras);
            startActivity(telaFinal);
        }
        else if (v.getId() == R.id.botaoResp8)
        {
            Intent telaSugestao = new Intent(this, TelaSugestao.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest2", "8");
            telaSugestao.putExtras(extras);
            startActivity(telaSugestao);
        }
    }
}
