package org.example.Entity;

public class Liga {

    private int id;
    private String nome;
    private int anoFundacao;

    public Liga(int id, String nome, int anoFundacao) {
        this.id = id;
        this.nome = nome;
        this.anoFundacao = anoFundacao;
    }

    public Liga(String nome, int anoFundacao) {
        this.nome = nome;
        this.anoFundacao = anoFundacao;
    }

    public Liga() {
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

    public int getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(int anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    @Override
    public String toString() {
        return "Liga{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", anoFundacao='" + anoFundacao + '\'' +
                '}';
    }
}
