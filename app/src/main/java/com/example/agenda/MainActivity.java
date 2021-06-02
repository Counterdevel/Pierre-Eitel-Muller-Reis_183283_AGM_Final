package com.example.agenda;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override                                                                                                       //@Override: sobreescreve superclasses dentro da IDE.
    protected void onCreate(Bundle savedInstanceState){                                                             //onCreate: aqui é onde fica a maior parte da inicialização.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> pessoa = new ArrayList<>(Arrays.asList("Pierre", "Gabriela"," Bruna"));                        //Um array para criar a lista.

        ListView listaDePessoas = findViewById(R.id.acitivty_main_lista_pessoas);                                   //ListView: Exibe uma lisa de visualização com rlagem vertical.
        listaDePessoas.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pessoa));   //ArrayAdpter: Retorna uma visualização para cada objeto com dados fornecidos.


    }
}
