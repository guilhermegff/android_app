package com.example.guilherme.avaliacaoalimentar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.os.StrictMode;
import android.content.Intent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{

    private String TAG = MainActivity.class.getSimpleName();
    ArrayList<HashMap<String, String>> alunoList;
    ArrayList<HashMap<String, String>> avaliacaoList;

    Button botao1;
    EditText edit1;
    EditText edit2;
    TextView text11;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        botao1 = (Button)findViewById(R.id.botao1);
        edit1 = (EditText)findViewById(R.id.edit1);
        text11 = (TextView)findViewById(R.id.text11);

        edit2 = (EditText)findViewById(R.id.edit2);
        alunoList = new ArrayList<>();
        avaliacaoList = new ArrayList<>();



    }


    public class BuscaAluno extends AsyncTask<String, Void, Void>
    {
    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        Toast.makeText(MainActivity.this, "Verificando Aluno!", Toast.LENGTH_SHORT).show();
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
                final String jsonStr = jsonStr1;
                Log.e(TAG, jsonStr);
                final JSONObject jsonObj = new JSONObject(jsonStr);
                Log.e(TAG, jsonObj.toString() + jsonStr);
                final JSONArray results = jsonObj.getJSONArray("results");
                Log.e(TAG, "JsonArray: " + results.toString());
                if(results.length() > 0)
                {
                    for (int i = 0; i < results.length(); i++) {
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
                else
                {
                    cadastroAluno();
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
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);
        Toast.makeText(MainActivity.this, "Aluno Verificado!" , Toast.LENGTH_SHORT).show();
        if(alunoList.isEmpty() == false)
        {

            String aluno_id = alunoList.get(0).get("id").toString();
            Log.e(TAG, "verificaAluno" + aluno_id);
            verificaAvaliacao(aluno_id);
        }
    }
}

    public class BuscaAvaliacao extends AsyncTask<String, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            //Toast.makeText(MainActivity.this, "Verificando Data!", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(String... params)
        {
            HttpHandler sh = new HttpHandler();
            String url = params[0];
            String aluno_id = params[1];
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
                        /*for (int i = 0; i < results.length(); i++)
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
                        }*/
                    }
                    else
                    {
                        Intent tela2 = new Intent(getApplication(), Tela2.class);
                        Bundle extras = new Bundle();
                        extras.putString("ra", aluno_id);
                        tela2.putExtras(extras);
                        startActivity(tela2);
                        return null;
                    }
                    Log.e(TAG, "Saindo");
                    Log.e(TAG, "Já votou hoje.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text11.setVisibility(View.VISIBLE);
                        }
                    });


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
            //Toast.makeText(MainActivity.this, "Aluno Verificado!" , Toast.LENGTH_LONG).show();
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

            try
            {
                resposta = sh.requestPostAluno(info[0], info[1]);
            }
            catch (final Exception e)
            {
                Log.e(TAG, "Não recuperou o Json " + e.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
        String nome_escola = edit2.getText().toString();
        text11.setVisibility(View.INVISIBLE);
        if(validaEntrada(ra, nome_escola))
        {
            String url = "http://aptwi.herokuapp.com/alunos/" + ra + ".json";
            BuscaAluno meuBuscaAluno;
            meuBuscaAluno = new BuscaAluno();
            meuBuscaAluno.execute(url);
        }
        else
        {
            return;
        }

    }

    public void cadastroAluno()
    {
        String ra = edit1.getText().toString();
        String nome_escola = edit2.getText().toString();


        if(alunoList.isEmpty())
        {
            CadastraAluno meuCadastraAluno;
            meuCadastraAluno = new CadastraAluno();
            Log.e(TAG, "Tem que cadastrar" );
            meuCadastraAluno.execute(ra, nome_escola);

            Intent tela2 = new Intent(this, Tela2.class);
            Bundle extras = new Bundle();
            extras.putString("ra", ra);
            tela2.putExtras(extras);
            startActivity(tela2);
        }
        else
        {
            Log.e(TAG, "Cadastrado");
            verificaAvaliacao(ra);
        }

    }

    public void verificaAvaliacao(String aluno_id)
    {
        String url = "http://aptwi.herokuapp.com/avaliacoesData/"+aluno_id+".json";
        BuscaAvaliacao meuBuscaAvaliacao;
        meuBuscaAvaliacao = new BuscaAvaliacao();
        meuBuscaAvaliacao.execute(url, aluno_id);
    }

    public boolean validaEntrada(String ra, String nome_escola)
    {
        String regex = "^[a-z_A-Z0-9 ]*$";
        CharSequence entrada = nome_escola;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(entrada);

        if(ra.length() == 0)
        {
            edit1.setError("Digite o RA");
            return false;
        }
        else if(ra.length() > 5)
        {
            edit1.setError("Máx 5 números");
            return false;
        }
        if(nome_escola.length() == 0)
        {
            edit2.setError("Digite o Nome da Escola");
            return false;
        }
        else if(!matcher.matches())
        {
            edit2.setError("Erro no texto");
            return false;
        }
        else if(nome_escola.length() > 20)
        {
            edit2.setError("Máx 20 letras");
            return false;
        }
        return true;
    }

}











