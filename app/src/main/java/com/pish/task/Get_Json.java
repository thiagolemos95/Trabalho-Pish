package com.pish.task;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.pish.connection.Connection;
import com.pish.dao.Lancamento_Dao;
import com.pish.model.Lancamento;

import java.util.ArrayList;

/**
 * Created by Pc-Joao on 09/10/2017.
 */

public class Get_Json
{
    private String url;
    private Context ctx;

    public Get_Json(String url, Context ctx)
    {
        this.url = url;
        this.ctx = ctx;
    }

    public ArrayList<Lancamento> GetLancamentos()
    {
        Lancamento_Dao l_dao = new Lancamento_Dao(ctx);

        Lancamento[] l_array;

        try
        {
            Gson gson 					    = new Gson();
            ArrayList<Lancamento> l_list 	= null;

                //conexao com a web
            Connection conn                 = new Connection(url, ctx);
            String retur_json               = conn.getJson(url);

            Log.i("URL", retur_json);

            l_array 			            = gson.fromJson(retur_json, Lancamento[].class);
            l_list                          = l_dao.arrayLancamento(l_array);

            return l_list;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
