package com.example.guilherme.avaliacaoalimentar;

/**
 * Created by Guilherme on 25/06/2017.
 */
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
public class HttpHandler
{
        private static final String TAG = HttpHandler.class.getSimpleName();

        public HttpHandler()
        {}
        public String makeServiceCall(String reqUrl)
        {
            String response = null;
            try
            {
                URL url = new URL(reqUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(in);
            }
            catch (MalformedURLException e)
            {
                Log.e(TAG, "MalformedURLException: " + e.getMessage());
            }
            catch (ProtocolException e)
            {
                Log.e(TAG, "ProtocolException: " + e.getMessage());
            }
            catch(IOException e)
            {
                Log.e(TAG, "IOException: " + e.getMessage());
            }
            catch(Exception e)
            {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
            return response;
        }

        public String requestPostAluno(String ra, String nome_escola)
        {
            String response = null;
            String data ="";
            String reqUrl = "http://aptwi.herokuapp.com/alunos.json";

            try
            {
                URL url = new URL(reqUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Accept-Charset", "UTF-8");
                conn.connect();

                data = URLEncoder.encode("ra", "UTF-8") +"="+URLEncoder.encode(ra, "UTF-8") +"&"+ URLEncoder.encode("nome_escola", "UTF-8") +"="+URLEncoder.encode(nome_escola, "UTF-8");
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(data);
                wr.flush();
                wr.close();
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(in);
            }
            catch (MalformedURLException e)
            {
                Log.e(TAG, "MalformedURLException: " + e.getMessage());
            }
            catch (ProtocolException e)
            {
                Log.e(TAG, "ProtocolException: " + e.getMessage());
            }
            catch(IOException e)
            {
                Log.e(TAG, "IOException: " + e.getMessage() + e.toString());
            }
            catch(Exception e)
            {
                Log.e(TAG, "Exception: Aqui " + e.getMessage() + e.toString() + data);
            }
            return response;
        }

        public String requestPostAvaliacao(String aluno_id, String sugestao, String resp1, String resp2, String resp3)
        {
            String response = null;
            String data ="";
            String reqUrl1 = "http://aptwi.herokuapp.com/avaliacoes.json";
            try
            {
                URL url = new URL(reqUrl1);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Accept-Charset", "UTF-8");
                conn.connect();

                data = URLEncoder.encode("aluno", "UTF-8") +"="+URLEncoder.encode(aluno_id, "UTF-8")+"&"+ URLEncoder.encode("sugestao", "UTF-8")+"="+URLEncoder.encode(sugestao, "UTF-8")+"&"+ URLEncoder.encode("resp_quest1", "UTF-8")+"="+URLEncoder.encode(resp1, "UTF-8")+"&"+ URLEncoder.encode("resp_quest2", "UTF-8")+"="+URLEncoder.encode(resp2, "UTF-8")+"&"+ URLEncoder.encode("resp_quest3", "UTF-8")+"="+URLEncoder.encode(resp3, "UTF-8");
                Log.e(TAG, "Saída: " + data);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(data);
                wr.flush();
                wr.close();
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(in);
            }
            catch (MalformedURLException e)
            {
                Log.e(TAG, "MalformedURLException: " + e.getMessage());
            }
            catch (ProtocolException e)
            {
                Log.e(TAG, "ProtocolException: " + e.getMessage());
            }
            catch(IOException e)
            {
                Log.e(TAG, "IOException: " + e.getMessage() + e.toString() + response + data);
            }
            catch(Exception e)
            {
                Log.e(TAG, "Exception: Aqui " + e.getMessage() + e.toString() + data);
            }
            Log.e(TAG, "Saída: " + response );
            return response;
        }


        private String convertStreamToString(InputStream is)
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line;
            try
            {
                while((line = reader.readLine()) != null)
                {
                    sb.append(line).append('\n');
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
    }

