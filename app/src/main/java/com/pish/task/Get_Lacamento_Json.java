package com.pish.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.pish.connection.Connection;
import com.pish.dao.Lancamento_Dao;
import com.pish.model.Lancamento;
import com.pish.view.Lancamento_List_Activity;

import java.util.List;

/**
 * Created by Pc-Joao on 04/10/2017.
 */

public class Get_Lacamento_Json extends AsyncTask<Void, Integer, List<Lancamento>>
{
    private List<Lancamento> l_list;
    private Context ctx;
    private Lancamento_Dao l_dao;
    public Connection comm_http;
    public ProgressDialog mProgress;
    private int mProgressDialog = 0;

    private String msg;

    public Get_Lacamento_Json(Context ctx, int progressDialog)
    {
        this.ctx = ctx;
        this.mProgressDialog = progressDialog;
        source();
    }

    private void source()
    {
        l_dao 	    = new Lancamento_Dao(ctx);
        comm_http	= new Connection();
    }

    @Override
    protected void onPreExecute()
    {
        mProgress = new ProgressDialog(ctx);
        mProgress.setTitle("Aguarde ...");
        mProgress.setMessage("Recebendo dados do servidor ...");

        if (mProgressDialog==ProgressDialog.STYLE_HORIZONTAL)
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
    protected List<Lancamento> doInBackground(Void... params)
    {
        String url = "";
        Get_Json get_json = null;

        l_dao = new Lancamento_Dao(ctx);

        get_json = new Get_Json("http://alimentador01.herokuapp.com/api/lancamentos", ctx);


        l_list 				= get_json.GetLancamentos();
        int i 				= 0;

        if (l_list != null)
        {
            mProgress.setMax(l_list.size());

            for (Lancamento lancamento : l_list)
            {
                if (l_list.size() > 0)
                {
                    l_dao.insert(lancamento);
                    publishProgress(i * 1);
                }
                i++;
            }
        }

        return l_list;
    }

    @Override
    protected void onPostExecute(List<Lancamento> result)
    {
        if (result != null)
        {
            mProgress.dismiss();
            Toast.makeText(ctx, "Dados sincronizado com sucesso!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(ctx, "Erro ao sincronizar com servidor tente novamente mais tarde!", Toast.LENGTH_SHORT).show();
        }
    }
}
