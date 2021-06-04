package com.example.agenda.dao;

import com.example.agenda.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private final static List<Pessoa> pessoas = new ArrayList<>();                                  //Cria um índice para a lista.
    private static int contadorDeId = 1;

    public void salva(Pessoa pessoaSalvo) {

        pessoaSalvo.setId(contadorDeId);                                                            //Seta o id para o pessoaSalvo id.
        pessoas.add(pessoaSalvo);                                                                   //Adiciona a pessoa salva.
        atualizaId();
    }

    private void atualizaId() {
        contadorDeId++;                                                                             //Agrega +1 ao id.
    }

    public void edita(Pessoa pessoa){                                                               //Método para editar informações.

        Pessoa pessoaEscolhida = buscaIdPessoa(pessoa);
        if(pessoaEscolhida != null){                                                                //Edita a informação do item selecionado.
            int posicaoDaPessoa = pessoas.indexOf(pessoaEscolhida);
            pessoas.set(posicaoDaPessoa, pessoa);
        }
    }

    private Pessoa buscaIdPessoa(Pessoa pessoa){
        for (Pessoa p :
                pessoas) {
            if (p.getId() == pessoa.getId()) {
                return p;
            }
        }
        return null;
    }

    public List<Pessoa> todos() {                                                                   //Método que retorna as informações salvas.

        return new ArrayList<>(pessoas);
    }

    public void remove(Pessoa pessoa) {                                                             //Método para remover a pessoa se for diferente de nulo.
        Pessoa pessoaDevolvida = buscaIdPessoa(pessoa);
        if(pessoaDevolvida != null){
            pessoas.remove(pessoaDevolvida);
        }
    }
}
