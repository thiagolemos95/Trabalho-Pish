package com.pish.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pish.model.Lancamento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pc-Joao on 02/10/2017.
 */

public class Lancamento_Dao extends SQLiteOpenHelper
{
    static final String DATABASE    = "BDPish";
    static final int VERSION        = 1;

    public Lancamento_Dao(Context context)
    {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE 'Lancamento' ("
                + "'id'			            INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + "'data_cadastro'          varchar(45),"
                + "'data_lancamento'        varchar(45),"
                + "'tipo_lancamento'        varchar(45),"
                + "'hora'                   INTEGER,"
                + "'minuto'                 INTEGER,"
                + "'quantidade_prevista'    INTEGER,"
                + "'quantidade_realizado'   INTEGER,"
                + "'status'	                varchar(45)"
                + " );";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }

    public void insert(Lancamento l_tb)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getDados(l_tb);

        db.insert("Lancamento", null, dados);
    }

    @NonNull
    private ContentValues getDados(Lancamento l_tb)
    {
        ContentValues dados = new ContentValues();
        dados.put("data_cadastro", l_tb.getDataCadastro());
        dados.put("data_lancamento", l_tb.getDataLancamento());
        dados.put("tipo_lancamento", l_tb.getTipoLancamento());
        dados.put("hora", l_tb.getHora());
        dados.put("minuto", l_tb.getMinutos());
        dados.put("quantidade_prevista", l_tb.getQuantidadePrevista());
        dados.put("quantidade_realizado", l_tb.getQuantidadeRealizada());
        dados.put("status", l_tb.getStatus());
        return dados;
    }

    public List<Lancamento> getLancamentos()
    {
        String sql = "SELECT * FROM Lancamento;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Lancamento> lancamentos = new ArrayList<Lancamento>();

        while (c.moveToNext())
        {
            Lancamento l_tb = new Lancamento();
            l_tb.setId(c.getLong(c.getColumnIndex("id")));
            l_tb.setDataCadastro(c.getString(c.getColumnIndex("data_cadastro")));
            l_tb.setDataLancamento(c.getString(c.getColumnIndex("data_lancamento")));
            l_tb.setTipoLancamento(c.getString(c.getColumnIndex("tipo_lancamento")));
            l_tb.setHora(c.getInt(c.getColumnIndex("hora")));
            l_tb.setMinutos(c.getInt(c.getColumnIndex("minuto")));
            l_tb.setQuantidadePrevista(c.getString(c.getColumnIndex("quantidade_prevista")));
            l_tb.setQuantidadeRealizada(c.getString(c.getColumnIndex("quantidade_realizado")));
            l_tb.setStatus(c.getString(c.getColumnIndex("status")));

            lancamentos.add(l_tb);
        }
        c.close();

        return lancamentos;
    }

    public void delete(Lancamento l_tb)
    {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {String.valueOf(l_tb.getId())};
        db.delete("Lancamento", "id = ?", params);
    }

    public void update(Lancamento l_tb)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getDados(l_tb);

        String[] params = {String.valueOf(l_tb.getId())};
        db.update("Lancamento", dados, "id = ?", params);
    }
}
