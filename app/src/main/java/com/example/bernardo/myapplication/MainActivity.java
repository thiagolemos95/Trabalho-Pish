package com.example.bernardo.myapplication;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private Spinner spinner1;
    private Button btnSubmit;
    private Spinner spinner3;
    private Spinner spinner4;
    private Button btnAgendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CarregarImagem();
        addItemsOnSpinner2();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

    }


    public void CarregarImagem() {

        ImageView  vImage = (ImageView) findViewById(R.id.vImage);
        Picasso.with(MainActivity.this).load("https://raw.githubusercontent.com/BernardoM/Trabalho-Pish/master/app/src/main/res/drawable/img.png").into(vImage);
    }
    public void addItemsOnSpinner2() {


        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner4.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // Obter o valor do spinner selecionado
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner4 = (Spinner) findViewById(R.id.spinner4);
        btnSubmit = (Button) findViewById(R.id.button);
        btnAgendar = (Button) findViewById(R.id.button2);
        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,
                        "Abastecido com \n "
                                + String.valueOf(spinner1.getSelectedItem()) + " de ração",
                        Toast.LENGTH_SHORT).show();
            }

        });
        btnAgendar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,
                        "Agendado para  : " +
                                "\n  "+ String.valueOf(spinner3.getSelectedItem())
                        + " e " + String.valueOf(spinner4.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
}