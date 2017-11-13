package com.pish.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pish.connection.Connection;
import com.pish.helper.Lancamento_Helper;
import com.pish.model.Lancamento;
import com.pish.view.Cadastro_Lancamento_Activity;
import com.pish.view.Lancamento_List_Activity;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import cz.msebera.android.httpclient.HttpResponse;

import static com.pish.connection.Connection.servResultPost;

/**
 * Created by Pc-Joao on 03/10/2017.
 */

public class Post_Lancamento_Json extends AsyncTask<Object, Integer, String>
{
    private Context ctx;
    public ProgressDialog mProgress;
    private int mProgressDialog = 0;
    private Lancamento_Helper l_helper;
    private String status;
    private Lancamento_List_Activity lla;

    public Post_Lancamento_Json(Context ctx, int progressDialog)
    {
        this.ctx = ctx;
        this.mProgressDialog = progressDialog;
        l_helper = new Lancamento_Helper(ctx);
        lla = new Lancamento_List_Activity();
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
            mProgress.setMax(100);
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
        try
        {
            status = new Connection("http://alimentador01.herokuapp.com/api/lancamento/", ctx).postJson(l_helper.json);
        }
        catch (Exception e)
        {
            Log.i("POSTJSON", e.toString());
            e.printStackTrace();
        }
        return status;
    }

    @Override
    protected void onPostExecute(String status)
    {
        if (status != "null" && Connection.servResultPost == HttpsURLConnection.HTTP_OK)
        {
            //lla.updateStatus(Lancamento_List_Activity.l_tb, ctx);
            Toast.makeText(ctx, "Lancamento Enviado com sucesso!", Toast.LENGTH_SHORT).show();
            mProgress.dismiss();
        }
        else
        {
            Toast.makeText(ctx, "Impossível estabelecer conexão com o Banco Dados do Servidor.", Toast.LENGTH_SHORT).show();
            mProgress.dismiss();
        }
    }
}
