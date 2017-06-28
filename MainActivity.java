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

public class MainActivity extends AppCompatActivity
{

    private String TAG = MainActivity.class.getSimpleName();
    ArrayList<HashMap<String, String>> alunoList;
    ArrayList<HashMap<String, String>> avaliacaoList;

    Button botao1;
    EditText edit1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        botao1 = (Button)findViewById(R.id.botao1);
        edit1 = (EditText)findViewById(R.id.edit1);
        alunoList = new ArrayList<>();
        avaliacaoList = new ArrayList<>();



    }


    public class BuscaAluno extends AsyncTask<String, Void, Void>
    {
    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        Toast.makeText(MainActivity.this, "Verificando Aluno!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(String... params)
    {
        HttpHandler sh = new HttpHandler();
        String url = params[0];
        final String jsonStr1 = sh.makeServiceCall(url);
        alunoList.clear();
        Log.e(TAG, "Response from url: " + jsonStr1);

        if (jsonStr1 != null)
        {
            Log.e(TAG, "Dentro if " + jsonStr1);
            try
            {
                final String jsonStr = "{results: ["+jsonStr1+"]}";
                Log.e(TAG, jsonStr);
                final JSONObject jsonObj = new JSONObject(jsonStr);
                Log.e(TAG, jsonObj.toString() + jsonStr);
                final JSONArray results = jsonObj.getJSONArray("results");
                Log.e(TAG, "JsonArray: " + results.toString());
                for (int i = 0; i < results.length(); i++)
                {
                    final JSONObject resultado = results.getJSONObject(i);
                    Log.e(TAG, resultado.toString());
                    final String id = resultado.getString("id");
                    final String ra = resultado.getString("ra");
                    final String nome_escola = resultado.getString("nome_escola");

                    HashMap<String, String> aluno = new HashMap<>();
                    aluno.clear();

                    aluno.put("id", id);
                    aluno.put("ra", ra);
                    aluno.put("nome_escola", nome_escola);
                    Log.e(TAG, aluno.toString());
                    alunoList.add(aluno);
                }

            }

            catch (final JSONException e)
            {
                Log.e(TAG, "Json parsing error: " + e.getMessage() + jsonStr1 );
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }

        }
        else
        {
            Log.e(TAG, "Null Response");
            cadastroAluno();
        }

        return null;
    }
    @Override
    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);
        Toast.makeText(MainActivity.this, "Aluno Verificado!" , Toast.LENGTH_LONG).show();
        if(alunoList.isEmpty() == false)
        {

            String ra = alunoList.get(0).get("id").toString();
            Log.e(TAG, "verificaAluno" + ra);
            verificaAvaliacao(ra);
        }
    }
}

    public class BuscaAvaliacao extends AsyncTask<String, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Verificando Data!", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(String... params)
        {
            HttpHandler sh = new HttpHandler();
            String url = params[0];
            String ra = params[1];
            final String jsonStr = sh.makeServiceCall(url);
            avaliacaoList.clear();
            Log.e(TAG, "Response from url Avaliacao: " + jsonStr);

            if (jsonStr != null)
            {
                Log.e(TAG, "Dentro if " + jsonStr);
                try
                {
                    Log.e(TAG, jsonStr);
                    final JSONObject jsonObj = new JSONObject(jsonStr);
                    Log.e(TAG, jsonObj.toString() + jsonStr);
                    final JSONArray results = jsonObj.getJSONArray("results");
                    Log.e(TAG, "JsonArray: " + results.toString());
                    if (results.length() > 0)
                    {
                        Log.e(TAG, "JsonArray: " + results.toString());
                        for (int i = 0; i < results.length(); i++)
                        {
                            final JSONObject resultado = results.getJSONObject(i);
                            Log.e(TAG, resultado.toString());
                            final String aluno = resultado.getString("aluno");
                            final String sugestao = resultado.getString("sugestao");
                            final String data_hora = resultado.getString("data_hora");
                            final String resp_quest1 = resultado.getString("resp_quest1");
                            final String resp_quest2 = resultado.getString("resp_quest2");
                            final String resp_quest3 = resultado.getString("resp_quest3");

                            HashMap<String, String> avaliacao = new HashMap<>();
                            avaliacao.clear();

                            avaliacao.put("aluno", aluno);
                            avaliacao.put("sugestao", sugestao);
                            avaliacao.put("data_hora", data_hora);
                            avaliacao.put("resp_quest1", resp_quest1);
                            avaliacao.put("resp_quest2", resp_quest2);
                            avaliacao.put("resp_quest3", resp_quest3);
                            avaliacaoList.add(avaliacao);
                            Log.e(TAG, avaliacao.toString());
                        }
                    }
                    else
                    {
                        if(avaliacaoList.isEmpty())
                        {
                            String aviso = "Avaliacao" + avaliacaoList.toString();
                            Log.e(TAG, aviso);
                            Intent tela2 = new Intent(getApplication(), Tela2.class);
                            Bundle extras = new Bundle();
                            extras.putString("ra", ra);
                            extras.putString("aluno", "");
                            extras.putString("sugestao", "");
                            extras.putString("data_hora", "");
                            extras.putString("resp_quest1", "");
                            extras.putString("resp_quest2", "");
                            extras.putString("resp_quest3", "");
                            extras.putString("resp_quest4", "");
                            tela2.putExtras(extras);
                            startActivity(tela2);
                        }
                        else
                        {
                            Log.e(TAG, "Já votou hoje.");
                        }
                    }


                    Log.e(TAG, "Saindo");
                }
                catch (final JSONException e)
                {
                    Log.e(TAG, "Json parsing error: " + e.getMessage() + jsonStr );
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            else
            {
                Log.e(TAG, "Null Response");

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            Toast.makeText(MainActivity.this, "Aluno Verificado!" , Toast.LENGTH_LONG).show();

        }
    }

    public class CadastraAluno extends AsyncTask<String, Void, Void>
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

            String url = "http://aptwi.herokuapp.com/alunos.json";
            try
            {

                resposta = sh.requestPost(url, info[0], info[1]);
            }
            catch (final Exception e)
            {
                Log.e(TAG, "Couldn't get json from server." + e.toString());
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
            //Toast.makeText(MainActivity.this, "Item Classificado!" , Toast.LENGTH_LONG).show();
        }
    }

    public void verificaAluno(View v)
    {
        String ra = edit1.getText().toString();
        String url = "http://aptwi.herokuapp.com/aluno/"+ra+".json";
        BuscaAluno meuBuscaAluno;
        meuBuscaAluno = new BuscaAluno();
        meuBuscaAluno.execute(url);

    }

    public void cadastroAluno()
    {
        String ra = edit1.getText().toString();
        String nome_escola = "Emei1";

        if(alunoList.isEmpty())
        {
            CadastraAluno meuCadastraAluno;
            meuCadastraAluno = new CadastraAluno();
            Log.e(TAG, "Tem que cadastrar" );
            meuCadastraAluno.execute(ra, nome_escola);
            Intent tela2 = new Intent(this, Tela2.class);
            Bundle extras = new Bundle();
            extras.putString("ra", ra);
            extras.putString("aluno", "");
            extras.putString("sugestao", "");
            extras.putString("data_hora", "");
            extras.putString("resp_quest1", "");
            extras.putString("resp_quest2", "");
            extras.putString("resp_quest3", "");
            extras.putString("resp_quest4", "");
            tela2.putExtras(extras);
            startActivity(tela2);
        }
        else
        {
            Log.e(TAG, "Cadastrado");
            verificaAvaliacao(ra);
        }

    }

    public void verificaAvaliacao(String ra)
    {
        String url = "http://aptwi.herokuapp.com/avaliacoesData/"+ra+".json";

        BuscaAvaliacao meuBuscaAvaliacao;
        meuBuscaAvaliacao = new BuscaAvaliacao();
        meuBuscaAvaliacao.execute(url, ra);
    }
}











