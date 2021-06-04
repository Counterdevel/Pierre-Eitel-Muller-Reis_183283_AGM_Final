package com.example.agenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

//CONSTRUTOR PARA PERSISTÊNCIA DE DADOS.
public class Pessoa implements Serializable {

    private String nome;
    private String endereco;
    private String cep;
    private String rg;
    private String telefone;
    private String genero;
    private String altura;
    private String nascimento;

    private int id = 0;

    public Pessoa(String nome, String endereco, String cep, String rg, String telefone, String genero, String altura, String nascimento) {

        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.rg = rg;
        this.telefone = telefone;
        this.genero = genero;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    public Pessoa(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public void setId(int id){                                                                      //Seta o id quando precisa acrescentar um id a mais.
        this.id = id;
    }

    public int getId(){

        return id;
    }

    public boolean idValido(){
        return id > 0;
    }
}
