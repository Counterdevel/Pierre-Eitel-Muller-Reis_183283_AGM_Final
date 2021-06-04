package com.example.agenda.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
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

import static com.example.agenda.ui.activities.ConstantesActivities.CHAVE_PESSOA;

public class ListaPessoasActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Pessoas";
    private final PessoaDAO dao = new PessoaDAO();                                                  //Instancia a classe de persistência de dados PessoaDAO.
    private ArrayAdapter<Pessoa> adapter;

    @Override                                                                                       //@Override: sobreescreve superclasses dentro da IDE.
    protected void onCreate(@NonNull Bundle savedInstanceState) {                                   //onCreate: aqui é onde fica a maior parte da inicialização.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoas);                                            //Busca o layout no projeto.

        setTitle(TITULO_APPBAR);
        adicionaPessoa();
        configuraLista();
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

        atualizaAdapter();
    }

    private void atualizaAdapter() {
        adapter.clear();                                                                            //Limpa as telas.
        adapter.addAll(dao.todos());                                                                //Trás todos os itens novamente.
    }

    private void remove(Pessoa pessoa){                                                             //Método para remover uma pessoa

        dao.remove(pessoa);
        adapter.remove(pessoa);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_menu, menu);                                //Adiciona a mensagem EXCLUIR referente ao activity+lista_menu ao clicar e segurar um item.
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        configuraMenu(item);
        return super.onContextItemSelected(item);
    }

    private void configuraMenu(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_menu_excluir) {

            new AlertDialog.Builder(this)                                                   //Mostra mensagens ao tentar excluir a pessoa.
                    .setTitle("Excluir Pessoa")
                    .setMessage("Tem certeza que deseja excluir essa pessoa?")
                    .setNegativeButton("Não", null)
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();  //Adiciona a mensagem do menuinfo.
                            Pessoa pessoaEscolhida = adapter.getItem(menuInfo.position);
                            remove(pessoaEscolhida);                                                //Remove a pessoa selecionada.
                        }
                    })
                    .show();
        }
    }

    private void configuraLista() {
        ListView listaDePessoas = findViewById(R.id.acitivty_main_lista_pessoas);                   //ListView: Exibe uma lisa de visualização com rolagem vertical.
        configuraAdapter(listaDePessoas);
        configuraItemClick(listaDePessoas);                                                         //Configura o item ao clicar.
        registerForContextMenu(listaDePessoas);                                                     //Identifica o item selecionado.
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

    private void configuraAdapter(ListView listaDePessoas) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePessoas.setAdapter(adapter);                                                                                   //ArrayAdpter: Retorna uma visualização para cada objeto com os dados fornecidos.
    }
}
