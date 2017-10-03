package com.pish.model;

import android.icu.text.SimpleDateFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Pc-Joao on 02/10/2017.
 */

public class Lancamento  implements Serializable
{
    public Long Id;

    public String DataCadastro;

    public String DataLancamento;

    public String TipoLancamento;

    public int Hora;

    public int Minutos;

    public String QuantidadePrevista;

    public String QuantidadeRealizada;

    public String Status;

    public Long getId()
    {
        return Id;
    }

    public void setId(long id)
    {
        Id = id;
    }

    public String getDataCadastro() {
        return DataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        DataCadastro = dataCadastro;
    }

    public String getDataLancamento() {
        return DataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        DataLancamento = dataLancamento;
    }

    public String getTipoLancamento()
    {
        return TipoLancamento;
    }

    public void setTipoLancamento(String tipoLancamento)
    {
        TipoLancamento = tipoLancamento;
    }

    public int getHora()
    {
        return Hora;
    }

    public void setHora(int hora)
    {
        Hora = hora;
    }

    public int getMinutos()
    {
        return Minutos;
    }

    public void setMinutos(int minutos)
    {
        Minutos = minutos;
    }

    public String getQuantidadePrevista() {
        return QuantidadePrevista;
    }

    public void setQuantidadePrevista(String quantidadePrevista) {
        QuantidadePrevista = quantidadePrevista;
    }

    public String getQuantidadeRealizada() {
        return QuantidadeRealizada;
    }

    public void setQuantidadeRealizada(String quantidadeRealizada) {
        QuantidadeRealizada = quantidadeRealizada;
    }

    public String getStatus()
    {
        return Status;
    }

    public void setStatus(String status)
    {
        Status = status;
    }


    @Override
    public String toString() {
        return getId() + " - " + getDataLancamento();
    }
}
