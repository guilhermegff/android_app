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

        public String requestPost(String reqUrl, String ra, String nome_escola)
        {
            String response = null;
            String data ="";
            try
            {
                URL url = new URL(reqUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Accept-Charset", "UTF-8");
                conn.connect();
                /*data = URLEncoder.encode("monit", "UTF-8") +"="+URLEncoder.encode("1", "UTF-8")+"&"+URLEncoder.encode("quali", "UTF-8")+"="+URLEncoder.encode("POS", "UTF-8")+"&"+URLEncoder.encode("data_pub", "UTF-8")
                                +"="+ URLEncoder.encode("2017-05-28", "UTF-8")+"&"+URLEncoder.encode("texto", "UTF-8")+"="+URLEncoder.encode("a", "UTF-8")+"&"+URLEncoder.encode("nome_twi", "UTF-8")+"="+
                                URLEncoder.encode("b", "UTF-8")+"&"+URLEncoder.encode("tag", "UTF-8")+"="+URLEncoder.encode("c", "UTF-8");*/

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

