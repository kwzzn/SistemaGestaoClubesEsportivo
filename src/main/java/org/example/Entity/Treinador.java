package org.example.Entity;

public class Treinador {

    private int id;
    private String nome;
    private int experiencia;

    public Treinador(int id, String nome, int experiencia) {
        this.id = id;
        this.nome = nome;
        this.experiencia = experiencia;
    }

    public Treinador(String nome, int experiencia) {
        this.nome = nome;
        this.experiencia = experiencia;
    }

    public Treinador() {
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

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public String toString() {
        return "Treinador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", experiencia=" + experiencia +
                '}';
    }
}
