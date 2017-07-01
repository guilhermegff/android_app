package com.example.guilherme.avaliacaoalimentar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
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
            Intent telaFinal = new Intent(this, TelaFinal.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest1", "1");
            telaFinal.putExtras(extras);
            startActivity(telaFinal);
        }
        else if(v.getId() == R.id.botaoResp2)
        {
            Intent telaFinal = new Intent(this, TelaFinal.class);
            String resp = v.findViewById(v.getId()).toString();
            Log.e(TAG, resp);
            extras.putString("resp_quest1", "2");
            telaFinal.putExtras(extras);
            startActivity(telaFinal);
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

