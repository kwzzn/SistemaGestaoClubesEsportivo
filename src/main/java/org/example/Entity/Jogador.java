package org.example.Entity;

public class Jogador {

    private int id;
    private String nome;
    private int idade;
    private String posicao;
    private int idClube;

    public Jogador(int id, String nome, int idade, String posicao, int idClube) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.idClube = idClube;
    }

    public Jogador(String nome, int idade, String posicao) {
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
    }

    public Jogador(String nome, int idade, String posicao, int idClube) {
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.idClube = idClube;
    }

    public Jogador() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getIdClube() {
        return idClube;
    }

    public void setIdClube(int idClube) {
        this.idClube = idClube;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", posicao='" + posicao + '\'' +
                ", idClube=" + idClube +
                '}';
    }
}
