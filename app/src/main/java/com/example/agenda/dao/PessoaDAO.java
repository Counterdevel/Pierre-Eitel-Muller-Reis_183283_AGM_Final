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
        contadorDeId++;                                                                             //Agrega +1 ao id.
    }

    public void edita(Pessoa pessoa){                                                               //Método para editar informações.

        Pessoa pessoaEscolhida = null;
        for (Pessoa p :
                pessoas) {
            if (p.getId() == pessoa.getId()) {                                                      //Compara o id com o item que foi selecionado.
                pessoaEscolhida = p;
            }
        }
        if(pessoaEscolhida != null){                                                                //Edita a informação do item selecionado.
            int posicaoDaPessoa = pessoas.indexOf(pessoaEscolhida);
            pessoas.set(posicaoDaPessoa, pessoa);
        }
    }

    public List<Pessoa> todos() {                                                                   //Método que retorna as informações salvas.

        return new ArrayList<>(pessoas);
    }
}
