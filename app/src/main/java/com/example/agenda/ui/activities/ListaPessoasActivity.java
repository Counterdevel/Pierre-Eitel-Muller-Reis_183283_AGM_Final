package com.example.agenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.PessoaDAO;
import com.example.agenda.model.Pessoa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.agenda.ui.activities.ConstantesActivities.CHAVE_PESSOA;

public class ListaPessoasActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Pessoas";
    private final PessoaDAO dao = new PessoaDAO();                                                  //Instancia a classe de persistência de dados PessoaDAO.

    @Override                                                                                       //@Override: sobreescreve superclasses dentro da IDE.
    protected void onCreate(@NonNull Bundle savedInstanceState) {                                   //onCreate: aqui é onde fica a maior parte da inicialização.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoas);                                            //Busca o layout no projeto.

        setTitle(TITULO_APPBAR);
        adicionaPessoa();
    }

    private void adicionaPessoa() {
        FloatingActionButton botaoNovo = findViewById(R.id.nova_pessoa);                            //Pega o FloatingActionButton pelo id.
        botaoNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AbreFormulario();
            }
        });
    }

    private void AbreFormulario() {
        startActivity(new Intent(ListaPessoasActivity.this, FormularioPessoaActivity.class));   //Faz a transição de tela da lista para o formulário ao clicar no floatingActionButton.
    }

    @Override
    protected void onResume() {                                                                     //onResume: É o estado em que o aplicativo interage com o usuário.
        super.onResume();

        configuraLista();

    }

    private void configuraLista() {
        ListView listaDePessoas = findViewById(R.id.acitivty_main_lista_pessoas);                   //ListView: Exibe uma lisa de visualização com rlagem vertical.
        final List<Pessoa> pessoas = dao.todos();
        listaPessoas(listaDePessoas, pessoas);
        configuraItemClick(listaDePessoas);
    }

    private void configuraItemClick(ListView listaDePessoas) {
        listaDePessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {               //Pega o click do item selecionado na lista.
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {

                Pessoa pessoaEscolhida = (Pessoa) adapterView.getItemAtPosition(posicao);           //Pega a posição da pessoa selecionada na lista.
                abreFormularioEdita(pessoaEscolhida);
            }
        });
    }

    private void abreFormularioEdita(Pessoa pessoa) {
        Intent vaiParaFormulario = new Intent(ListaPessoasActivity.this, FormularioPessoaActivity.class);
        vaiParaFormulario.putExtra(CHAVE_PESSOA, pessoa);                                                                     //Transaciona informações do item selecionado.
        startActivity(vaiParaFormulario);
    }

    private void listaPessoas(ListView listaDePessoas, List<Pessoa> pessoas) {
        listaDePessoas.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pessoas));            //ArrayAdpter: Retorna uma visualização para cada objeto com os dados fornecidos.
    }
}
