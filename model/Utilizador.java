package model;

public abstract class Utilizador {
    protected String id;
    protected String username;
    protected String password;

    public Utilizador(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }

    public boolean autenticar(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
