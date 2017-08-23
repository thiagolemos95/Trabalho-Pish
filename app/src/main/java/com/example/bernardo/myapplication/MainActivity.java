package com.example.bernardo.myapplication;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private Spinner spinner1;
    private Button btnSubmit;
    private TextView txtUrl; //Variável privada do tipo TextView
    private ImageView imvImagem; //Variável privada do tipo ImageView
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUrl = (TextView) findViewById(R.id.txtUrl); //Fazemos um CAST da variável txtUrl e associamos ela ao componente txtUrl presente na View.
        imvImagem = (ImageView) findViewById(R.id.imvImagem); //Fazemos um CAST da variável imvImagem e associamos ela ao componente imvImagem presente na View.
        addItemsOnSpinner2();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

    }

    // add items into spinner dynamically
    public void CarregarImagem(View v) {
        Picasso.with(v.getContext()).load(txtUrl.getText().toString()).into(imvImagem);
        /*
        *Picasso.with(Aqui vem o contexto).load(URL da imagem).into(ImageView responsável pelo render);
        *
        * */
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
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);

        btnSubmit = (Button) findViewById(R.id.button);

        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
}