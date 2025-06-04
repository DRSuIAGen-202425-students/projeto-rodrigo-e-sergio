package model;

public class Eleitor {
    private int id;
    private String nome;
    private String numeroEleitor;
    private String username;
    private String passwordHash;
    private boolean votou;

    public Eleitor(int id, String nome, String numeroEleitor, String username, String passwordHash) {
        this.id = id;
        this.nome = nome;
        this.numeroEleitor = numeroEleitor;
        this.username = username;
        this.passwordHash = passwordHash;
        this.votou = false;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroEleitor() {
        return numeroEleitor;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean isVotou() {
        return votou;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroEleitor(String numeroEleitor) {
        this.numeroEleitor = numeroEleitor;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setVotou(boolean votou) {
        this.votou = votou;
    }

    @Override
    public String toString() {
        return String.format("Eleitor{id=%d, nome='%s', numeroEleitor='%s', votou=%b}",
                id, nome, numeroEleitor, votou);
    }
}
