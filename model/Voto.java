package model;

public class Voto {
    private final String eleitorId;
    private final String candidatoId;

    public Voto(String eleitorId, String candidatoId) {
        this.eleitorId = eleitorId;
        this.candidatoId = candidatoId;
    }

    public String getEleitorId() {
        return eleitorId;
    }

    public String getCandidatoId() {
        return candidatoId;
    }
}
