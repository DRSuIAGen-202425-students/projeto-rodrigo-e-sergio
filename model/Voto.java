package model;

import java.time.LocalDateTime;

public class Voto {
    private int id;
    private int idCandidato;
    private LocalDateTime timestamp;

    public Voto() {}

    public Voto(int id, int idCandidato, LocalDateTime timestamp) {
        this.id = id;
        this.idCandidato = idCandidato;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("Voto{id=%d, idCandidato=%d, timestamp=%s}",
                id, idCandidato, timestamp.toString());
    }
}
