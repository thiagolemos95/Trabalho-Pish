package com.pish.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.pish.R;
import com.pish.adapter.Lancamento_Adapter;
import com.pish.connection.Connection;
import com.pish.dao.Lancamento_Dao;
import com.pish.helper.Lancamento_Helper;
import com.pish.model.Lancamento;
import com.pish.task.Get_Lacamento_Json;
import com.pish.task.Post_Lancamento_Json;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class Lancamento_List_Activity extends AppCompatActivity
{
    private ListView list_lancamento;
    private Lancamento_Helper l_helper;
    private Gson gson;
    private Lancamento_Dao l_dao;
    public static Lancamento l_tb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento_list);

        list_lancamento = (ListView) findViewById(R.id.lancamento_list);

        list_lancamento.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id)
            {
                Lancamento l_tb = (Lancamento) list_lancamento.getItemAtPosition(position);

                Intent intentVaiProFormulario = new Intent(Lancamento_List_Activity.this, Cadastro_Lancamento_Activity.class);
                intentVaiProFormulario.putExtra("l_tb", l_tb);
                startActivity(intentVaiProFormulario);
            }
        });

        Button new_lancamento = (Button) findViewById(R.id.new_lancamento);
        new_lancamento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //new Get_Lacamento_Json(Lancamento_List_Activity.this, ProgressDialog.STYLE_HORIZONTAL).execute();
                Intent intentVaiProFormulario = new Intent(Lancamento_List_Activity.this, Cadastro_Lancamento_Activity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(list_lancamento);
    }

    public void loadLancamentoList()
    {
        l_dao                           = new Lancamento_Dao(this);
        List<Lancamento> lancamentos    = l_dao.getLancamentos(this);
        l_dao.close();

        Lancamento_Adapter adapter = new Lancamento_Adapter(this, lancamentos);
        list_lancamento.setAdapter(adapter);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        loadLancamentoList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        l_tb = (Lancamento) list_lancamento.getItemAtPosition(info.position);

        MenuItem sincronizar = menu.add("Sincronizar com Servidor");

        sincronizar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            public boolean onMenuItemClick(MenuItem item)
            {
                l_dao = new Lancamento_Dao(Lancamento_List_Activity.this);

                l_dao.deleteAll();

                l_dao.close();

                new Get_Lacamento_Json(Lancamento_List_Activity.this, ProgressDialog.STYLE_HORIZONTAL).execute();

                loadLancamentoList();

                return false;
            }
        });

        MenuItem send = menu.add("Enviar Lançamento");

        send.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                gson            = new Gson();

                updateStatus(l_tb);

                l_helper.json 	= gson.toJson(l_tb);

                new Post_Lancamento_Json(Lancamento_List_Activity.this, ProgressDialog.STYLE_HORIZONTAL).execute();

                return false;
            }
        });

        MenuItem delete = menu.add("Excluir Lancamento");

        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                l_dao = new Lancamento_Dao(Lancamento_List_Activity.this);
                l_dao.delete(l_tb);
                l_dao.close();

                loadLancamentoList();
                return false;
            }
        });
    }

    public void updateStatus(Lancamento l_tb)
    {
        if (Connection.servResultPost == HttpsURLConnection.HTTP_OK)
        {
            l_dao = new Lancamento_Dao(this);
            l_tb.setStatus("Enviado");
            l_dao.update(l_tb);
            l_dao.close();
            loadLancamentoList();
        }
    }
}
