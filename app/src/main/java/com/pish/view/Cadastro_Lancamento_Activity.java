package com.pish.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pish.R;
import com.pish.dao.Lancamento_Dao;
import com.pish.helper.Lancamento_Helper;
import com.pish.model.Lancamento;
import com.pish.task.Post_Lancamento_Json;


public class Cadastro_Lancamento_Activity extends AppCompatActivity
{
    private Lancamento_Helper l_helper;
    private Gson gson;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_lancamento);

        l_helper                = new Lancamento_Helper(this);
        Intent intent           = getIntent();
        Lancamento l_tb   = (Lancamento) intent.getSerializableExtra("l_tb");

        if (l_tb != null)
        {
            l_helper.fillLancamento(l_tb);
        }
    }

    //botao para submeter
    public void btnSubmit_Click(View v)
    {
        gson = new Gson();
        Lancamento l_tb = l_helper.getLancamento();

        Lancamento_Dao dao = new Lancamento_Dao(this);

        if (l_tb.getId() != null)
        {
            dao.update(l_tb);
        }
        else
        {
            dao.insert(l_tb);
            l_helper.json 	= gson.toJson(l_tb);
            new Post_Lancamento_Json(Cadastro_Lancamento_Activity.this, ProgressDialog.STYLE_HORIZONTAL).execute();
        }

        dao.close();

        Toast.makeText(Cadastro_Lancamento_Activity.this, "Lancamento efetuado com sucesso!", Toast.LENGTH_SHORT).show();

        finish();
    }
}