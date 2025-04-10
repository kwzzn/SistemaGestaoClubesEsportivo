package org.example.Entity;

public class Clube {

    private int id;
    private String nome;
    private int treinadorId;
    private int dataFundacao;

    public Clube(int id, String nome, int treinadorId, int dataFundacao) {
        this.id = id;
        this.nome = nome;
        this.treinadorId = treinadorId;
        this.dataFundacao = dataFundacao;
    }

    public Clube(String nome, int treinadorId, int dataFundacao) {
        this.nome = nome;
        this.treinadorId = treinadorId;
        this.dataFundacao = dataFundacao;
    }

    public Clube(String nome, int treinadorId) {
        this.nome = nome;
        this.treinadorId = treinadorId;
    }

    public Clube() {
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

    public int getTreinadorId() {
        return treinadorId;
    }

    public void setTreinadorId(int treinadorId) {
        this.treinadorId = treinadorId;
    }

    public int getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(int dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    @Override
    public String toString() {
        return "Clube{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", treinadorId=" + treinadorId +
                ", dataFundacao=" + dataFundacao +
                '}';
    }
}
