package com.pish.model;

import android.icu.text.SimpleDateFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Pc-Joao on 02/10/2017.
 */

public class Lancamento  implements Serializable
{
    public Long id;

    public String data_cadastro;

    public String data_lancamento;

    public String tipo_lancamento;

    public int hora;

    public int minutos;

    public String quantidade_prevista;

    public String quantidade_realizada;

    public String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(String data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public String getTipo_lancamento() {
        return tipo_lancamento;
    }

    public void setTipo_lancamento(String tipo_lancamento) {
        this.tipo_lancamento = tipo_lancamento;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public String getQuantidade_prevista() {
        return quantidade_prevista;
    }

    public void setQuantidade_prevista(String quantidade_prevista) {
        this.quantidade_prevista = quantidade_prevista;
    }

    public String getQuantidade_realizada() {
        return quantidade_realizada;
    }

    public void setQuantidade_realizada(String quantidade_realizada) {
        this.quantidade_realizada = quantidade_realizada;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getId() + " - " + getData_lancamento();
    }
}
