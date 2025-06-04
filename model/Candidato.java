package model;

public class Candidato {
    private String id;
    private String nome;
    private String partido;

    public Candidato(String id, String nome, String partido) {
        this.id = id;
        this.nome = nome;
        this.partido = partido;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPartido() {
        return partido;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " - " + partido;
    }
}
