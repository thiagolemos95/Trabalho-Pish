package com.pish.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.pish.R;
import com.pish.adapter.Lancamento_Adapter;
import com.pish.dao.Lancamento_Dao;
import com.pish.model.Lancamento;

import java.util.List;

public class Lancamento_List_Activity extends AppCompatActivity
{
    private ListView list_lancamento;

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
                Intent intentVaiProFormulario = new Intent(Lancamento_List_Activity.this, Cadastro_Lancamento_Activity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(list_lancamento);
    }

    private void loadLancamentoList()
    {
        Lancamento_Dao l_dao = new Lancamento_Dao(this);
        List<Lancamento> lancamentos = l_dao.getLancamentos();
        l_dao.close();

        //ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
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
        final Lancamento l_tb = (Lancamento) list_lancamento.getItemAtPosition(info.position);

        MenuItem itemLigar = menu.add("Ligar");
        itemLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                return false;
            }
        });

        MenuItem deletar = menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                return false;
            }
        });
    }
}
