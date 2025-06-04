package model;

public class Eleitor extends Utilizador {
    private boolean votou = false;

    public Eleitor(String id, String username, String password) {
        super(id, username, password);
    }

    public boolean jaVotou() {
        return votou;
    }

    public void setVotou(boolean votou) {
        this.votou = votou;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
