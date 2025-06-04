package model;

import java.util.Map;

public class ResultadoVotacao {
    private final Map<String, Integer> votosPorCandidato; // candidatoId -> total votos
    private final int totalVotos;
    private final String vencedorId;

    public ResultadoVotacao(Map<String, Integer> votosPorCandidato, int totalVotos, String vencedorId) {
        this.votosPorCandidato = votosPorCandidato;
        this.totalVotos = totalVotos;
        this.vencedorId = vencedorId;
    }

    public Map<String, Integer> getVotosPorCandidato() {
        return votosPorCandidato;
    }

    public int getTotalVotos() {
        return totalVotos;
    }

    public String getVencedorId() {
        return vencedorId;
    }
}
