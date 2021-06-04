package com.example.agenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.PessoaDAO;
import com.example.agenda.model.Pessoa;

import static com.example.agenda.ui.activities.ConstantesActivities.CHAVE_PESSOA;

public class FormularioPessoaActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Formulário";                                        //Constantes
    private static final String TITULO_APPBAR_EDITA_PESSOA = "Editar Formulário";
    private static final String TITULO_APPBAR_NOVA_PESSOA = "Nova Pessoa";

    private EditText campoNome;
    private EditText campoEndereco;
    private EditText campoCEP;
    private EditText campoRG;
    private EditText campoTelefone;
    private EditText campoGenero;
    private EditText campoAltura;
    private EditText campoNascimento;

    private String nome;
    private String endereco;
    private String CEP;
    private String RG;
    private String Telefone;
    private String Genero;
    private String Altura;
    private String Nascimento;

    private final PessoaDAO dao = new PessoaDAO();                                                  //Classe para persistência de dados.
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_pessoa);

        inicializaCampos();
        configuraBotao();
        carregaPessoa();
    }

    private void carregaPessoa() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_PESSOA)) {
            setTitle(TITULO_APPBAR_EDITA_PESSOA);
            pessoa = (Pessoa) dados.getSerializableExtra(CHAVE_PESSOA);                             //Serializa as informações para dentro do formulário.
            preencheCampos();
        }else{
            setTitle(TITULO_APPBAR_NOVA_PESSOA);
            pessoa = new Pessoa();
        }
    }

    private void preencheCampos() {
        campoNome.setText(pessoa.getNome());
        campoEndereco.setText(pessoa.getEndereco());
        campoCEP.setText(pessoa.getCep());
        campoRG.setText(pessoa.getRg());
        campoTelefone.setText(pessoa.getTelefone());
        campoGenero.setText(pessoa.getGenero());
        campoAltura.setText(pessoa.getAltura());
        campoNascimento.setText(pessoa.getNascimento());
    }

    private void configuraBotao() {
        Button botaoSalvar = findViewById(R.id.button_salvar);                                      //Encontra o botão do formulário pelo id.
        botaoSalvar.setOnClickListener(new View.OnClickListener() {                                 //Recebe o click do botão e da um retorno.
            @Override
            public void onClick(View view) {

                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() {
        preenchePessoa();
        if(pessoa.idValido()){
            dao.edita(pessoa);
        } else{
            dao.salva(pessoa);
        }
        finish();
    }

    private void preenchePessoa() {
        nome = campoNome.getText().toString();
        endereco = campoEndereco.getText().toString();
        CEP = campoCEP.getText().toString();
        RG = campoRG.getText().toString();
        Telefone = campoTelefone.getText().toString();                                              //Pega as informações passadas no EditText e armazena nas variaveis do tipo Sting.
        Genero = campoGenero.getText().toString();
        Altura = campoAltura.getText().toString();
        Nascimento = campoNascimento.getText().toString();

        pessoa.setNome(nome);
        pessoa.setEndereco(endereco);
        pessoa.setCep(CEP);
        pessoa.setRg(RG);
        pessoa.setTelefone(Telefone);                                                          //Setando as informações para o pessoaSalvo e aplicando o método edita.
        pessoa.setGenero(Genero);
        pessoa.setAltura(Altura);
        pessoa.setNascimento(Nascimento);
    }

    private void inicializaCampos() {
        campoNome = findViewById(R.id.editText_nome);
        campoEndereco = findViewById(R.id.editText_endereco);
        campoCEP = findViewById(R.id.editText_cep);
        campoRG = findViewById(R.id.editText_rg);                                                   //Pega os EditText do formulário pelos ids.
        campoTelefone = findViewById(R.id.editText_telefone);
        campoGenero = findViewById(R.id.editText_genero);
        campoAltura = findViewById(R.id.editText_altura);
        campoNascimento = findViewById(R.id.editText_nascimento);
    }
}