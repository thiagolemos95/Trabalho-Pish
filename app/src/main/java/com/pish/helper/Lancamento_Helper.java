package com.pish.helper;

import android.app.Activity;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.pish.util.CustomOnItemSelectedListener;
import com.pish.R;
import com.pish.model.Lancamento;
import com.pish.view.Cadastro_Lancamento_Activity;
import com.squareup.picasso.Picasso;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Pc-Joao on 03/10/2017.
 */

public class Lancamento_Helper
{
    public static String json;
    private Spinner cb_gramas;
    private Button btnSubmit;
    private String data_atual;
    private static String qnt;
    /*private final Spinner spinner3;
    private final Spinner spinner4;
    private final Button btnAgendar;*/

    private Lancamento l_tb;
    private SimpleDateFormat data;
    private int ano;
    private int mes;
    private int semana;
    private int dia;
    private int hora;
    private int minuto;
    private int segundo;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Lancamento_Helper(Cadastro_Lancamento_Activity activity)
    {
        long date   = System.currentTimeMillis();
        data        = new SimpleDateFormat("dd/MM/yyyy");
        data_atual  = data.format(date);

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));

        hora    = calendar.get(Calendar.HOUR_OF_DAY);
        minuto  = calendar.get(Calendar.MINUTE);

        loadComboBox(activity);
        loadImagem(activity);
        //addListenerOnButton(activity);
        addListenerOnSpinnerItemSelection(activity);

        changeGramas(activity);

        l_tb = new Lancamento();
    }

    public Lancamento_Helper(Context ctx)
    {
    }

    public Lancamento getLancamento()
    {
        l_tb.setDataCadastro(data_atual);
        l_tb.setDataLancamento(data_atual);
        l_tb.setTipoLancamento("Estantaneo");
        l_tb.setHora(hora);
        l_tb.setMinutos(minuto);
        l_tb.setQuantidadePrevista(qnt);
        l_tb.setQuantidadeRealizada(qnt);
        l_tb.setStatus("Aguardando");

        return l_tb;
    }

    public void fillLancamento(Lancamento l_tb)
    {
        for (int i = 0; i < cb_gramas.getCount(); i++)
        {
            if (cb_gramas.getItemAtPosition(i).toString().equals(l_tb.getQuantidadeRealizada()))
            {
                cb_gramas.setSelection(i);
            }
        }

        this.l_tb = l_tb;
    }

    public void loadImagem(Activity activity)
    {
        ImageView  vImage = (ImageView) activity.findViewById(R.id.vImage);
        Picasso.with(activity).load("https://raw.githubusercontent.com/BernardoM/Trabalho-Pish/master/app/src/main/res/drawable/img.png").into(vImage);
    }

    public void loadComboBox(Activity activity)
    {
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        /*list.add("list 2");
        list.add("list 3");*/
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public void addListenerOnSpinnerItemSelection(Activity activity)
    {
        cb_gramas = (Spinner) activity.findViewById(R.id.cb_gramas);
        cb_gramas.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        //spinner3 = (Spinner) findViewById(R.id.spinner3);
        //spinner3.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        //spinner4 = (Spinner) findViewById(R.id.spinner4);
        //spinner4.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // Obter o valor do spinner selecionado
    public void addListenerOnButton(final Activity activity)
    {
        cb_gramas = (Spinner) activity.findViewById(R.id.cb_gramas);
        //spinner3 = (Spinner) findViewById(R.id.spinner3);
        ///spinner4 = (Spinner) findViewById(R.id.spinner4);
        btnSubmit = (Button) activity.findViewById(R.id.btnSubmit);
        //btnAgendar = (Button) findViewById(R.id.button2);

        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(activity,
                        "Abastecido com \n "
                                + String.valueOf(cb_gramas.getSelectedItem()) + " de ração",
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void changeGramas(Activity activity)
    {
        cb_gramas = (Spinner) activity.findViewById(R.id.cb_gramas);

        cb_gramas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                qnt = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }
}
