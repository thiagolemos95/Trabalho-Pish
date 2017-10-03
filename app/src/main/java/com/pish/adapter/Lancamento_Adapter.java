package com.pish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pish.R;
import com.pish.model.Lancamento;

import java.util.List;

/**
 * Created by Pc-Joao on 03/10/2017.
 */

public class Lancamento_Adapter extends BaseAdapter
{
    private final List<Lancamento> l_list;
    private final Context ctx;

    public Lancamento_Adapter(Context ctx, List<Lancamento> l_list)
    {
        this.ctx = ctx;
        this.l_list = l_list;
    }

    @Override
    public int getCount()
    {
        return l_list.size();
    }

    @Override
    public Object getItem(int position) {
        return l_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return l_list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Lancamento l_tb = l_list.get(position);

        LayoutInflater inflater = LayoutInflater.from(ctx);

        // Aqui reaproveitamos a convertView caso ela exista
        View view = convertView;

        if (view == null)
        {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView data_lancamento = (TextView) view.findViewById(R.id.item_data);
        data_lancamento.setText(l_tb.getDataLancamento());

        TextView status = (TextView) view.findViewById(R.id.item_status);
        status.setText(l_tb.getStatus());

        TextView qnt = (TextView) view.findViewById(R.id.item_qnt);
        qnt.setText(l_tb.getQuantidadeRealizada());

        return view;
    }
}
