package com.pish.connection;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import cz.msebera.android.httpclient.HttpResponse;

/**
 * Created by Pc-Joao on 04/10/2017.
 */

public class Connection
{
    private String url;
    private Context ctx;
    public static int servResultPost;
    public static int servResultGet;
    private HttpResponse resposta;

    public Connection() {	}

    public Connection(String url, Context ctx)
    {
        this.url = url;
        this.ctx = ctx;
    }

    public String getJson(String urlServico) throws IOException, TimeoutException
    {
        String dados = "";
        InputStream objDadosInputStream 	= null;
        HttpURLConnection objUrlConnection 	= null;

        try
        {
            URL url 			= new URL(urlServico);
            objUrlConnection 	= (HttpURLConnection) url.openConnection();

            objUrlConnection.connect();

            this.servResultGet 	= objUrlConnection.getResponseCode();
            objDadosInputStream = objUrlConnection.getInputStream();
            BufferedReader br 	= new BufferedReader(new InputStreamReader(objDadosInputStream));
            StringBuffer sb 	= new StringBuffer();
            String linha 		= "";

            while ((linha = br.readLine()) != null)
            {
                sb.append(linha);
            }

            dados = sb.toString();
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.i("TAG", e.toString());
        }
        finally
        {
            objDadosInputStream.close();
            objUrlConnection.disconnect();
        }
        return dados;
    }

    public String postJson(String json)
    {
        try
        {
            URL _url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);

            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            String resposta = scanner.next();

            this.servResultPost = connection.getResponseCode();

            return resposta;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return String.valueOf(resposta);
    }
}
