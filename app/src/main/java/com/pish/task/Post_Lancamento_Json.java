package com.pish.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pish.helper.Lancamento_Helper;
import com.pish.view.Cadastro_Lancamento_Activity;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import cz.msebera.android.httpclient.HttpResponse;

/**
 * Created by Pc-Joao on 03/10/2017.
 */

public class Post_Lancamento_Json extends AsyncTask<Object, Integer, String>
{
    private Context ctx;
    public ProgressDialog mProgress;
    private int mProgressDialog = 0;
    public static int servResultPost;
    private Lancamento_Helper l_helper;
    public static int servResultGet;
    private HttpResponse resposta;

    public Post_Lancamento_Json(Context ctx, int progressDialog)
    {
        this.ctx = ctx;
        this.mProgressDialog = progressDialog;
        l_helper = new Lancamento_Helper(ctx);
    }

    @Override
    protected void onPreExecute()
    {
        mProgress = new ProgressDialog(ctx);
        mProgress.setTitle("Aguarde ...");
        mProgress.setMessage("Enviando Lançamento ...");

        if (mProgressDialog == ProgressDialog.STYLE_HORIZONTAL)
        {
            mProgress.setIndeterminate(false);
            mProgress.setMax(0);
            mProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgress.setCancelable(false);
        }

        mProgress.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        if (mProgressDialog==ProgressDialog.STYLE_HORIZONTAL)
            mProgress.setProgress(values[0]);
    }

    @Override
    protected String doInBackground(Object... params)
    {
        return postJson(l_helper.json);
    }

    @Override
    protected void onPostExecute(String json)
    {
        if (json != null)
        {
            if (servResultPost != HttpsURLConnection.HTTP_OK)
            {
                Toast.makeText(ctx, "Impossível estabelecer conexão com o Servidor.", Toast.LENGTH_SHORT).show();
                mProgress.dismiss();
            }
            else
            {
                Toast.makeText(ctx, "Lancamento efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                mProgress.dismiss();
            }
        }
    }

    public String postJson(String json)
    {
        try
        {
            URL _url = new URL("http://alimentador01.herokuapp.com/api/lancamento");
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
