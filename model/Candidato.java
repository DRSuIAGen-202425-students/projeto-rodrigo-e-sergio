package model;

public class Candidato {
    private int id;
    private String nome;
    private String partido;
    private int numero;

    public Candidato(int id, String nome, String partido, int numero) {
        this.id = id;
        this.nome = nome;
        this.partido = partido;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPartido() {
        return partido;
    }

    public int getNumero() {
        return numero;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return String.format("Candidato{id=%d, nome='%s', partido='%s', numero=%d}",
                id, nome, partido, numero);
    }
}
