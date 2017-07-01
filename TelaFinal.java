package com.example.guilherme.avaliacaoalimentar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class TelaFinal extends AppCompatActivity
{
    ImageView image4;
    TextView text8;
    TextView text9;
    TextView text10;
    ImageButton botaoConcluir;

    private String TAG = TelaFinal.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_final);

        image4 = (ImageView)findViewById(R.id.image4);
        text8 = (TextView)findViewById(R.id.text8);
        text9 = (TextView)findViewById(R.id.text9);
        text10 = (TextView)findViewById(R.id.text10);
        botaoConcluir = (ImageButton)findViewById(R.id.botaoConcluir);
    }

    public class CadastraAvaliacao extends AsyncTask<String, Void, Void>
    {

        @Override
        protected void onPreExecute()
        {

            super.onPreExecute();
            //Toast.makeText(MainActivity.this, "Classificando Item", Toast.LENGTH_LONG).show();
        }
        @Override
        protected Void doInBackground(String... info)
        {
            HttpHandler sh = new HttpHandler();
            String resposta = "";

            try
            {
                resposta = sh.requestPostAvaliacao(info[0], info[1], info[2], info[3], info[4]);
                if(resposta != null)
                {
                    Log.e(TAG, "Response: " + resposta);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(TelaFinal.this, "Avaliação Salva!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else
                {
                    Log.e(TAG, "Response Else: " + resposta);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(TelaFinal.this, "Erro, tente novamente!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            catch (final Exception e)
            {
                Log.e(TAG, "Não recuperou o Json" + e.toString() + resposta);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(getApplicationContext(), "Couldn't get json from server. Check LogCat" + e.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            finishAffinity();
        }
    }

    public void botaoConcluir(View v)
    {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String ra = extras.getString("ra");
        String resp1 = extras.getString("resp_quest1", "");
        Log.e(TAG, "Resp1: " + resp1);
        String resp2 = extras.getString("resp_quest2", "");
        Log.e(TAG, "Resp2: " + resp2);
        String resp3 = extras.getString("resp_quest3", "");
        Log.e(TAG, "Resp3: " + resp3);
        String sugestao = extras.getString("sugestao", "");
        Log.e(TAG, "Sugestão: " + sugestao);

        CadastraAvaliacao meuCadastraAvaliacao;
        meuCadastraAvaliacao = new CadastraAvaliacao();
        Log.e(TAG, "Cadastro Avaliacao" );
        meuCadastraAvaliacao.execute(ra, sugestao, resp1, resp2, resp3);
    }
}
