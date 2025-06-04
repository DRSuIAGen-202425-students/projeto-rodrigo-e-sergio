package service;

import model.ResultadoVotacao;
import model.Voto;

import java.util.*;

public class VotacaoService {
    private boolean votacaoIniciada = false;
    private final Map<String, Voto> votos = new HashMap<>(); // eleitorId -> Voto

    public boolean iniciarVotacao() {
        if (votacaoIniciada) {
            return false;
        }
        votacaoIniciada = true;
        votos.clear();  // Limpar votos anteriores, se houver
        return true;
    }

    public boolean encerrarVotacao() {
        if (!votacaoIniciada) {
            return false;
        }
        votacaoIniciada = false;
        return true;
    }

    public boolean isVotacaoIniciada() {
        return votacaoIniciada;
    }

    public boolean registarVoto(String eleitorId, String candidatoId) {
        if (!votacaoIniciada || votos.containsKey(eleitorId)) {
            return false; // Votação não ativa ou eleitor já votou
        }
        votos.put(eleitorId, new Voto(eleitorId, candidatoId));
        return true;
    }

    public Map<String, Voto> getVotos() {
        return Collections.unmodifiableMap(votos);
    }

    public ResultadoVotacao apurarResultados() {
        if (votacaoIniciada) {
            throw new IllegalStateException("Votação ainda não encerrada.");
        }

        Map<String, Integer> contagem = new HashMap<>();

        // Contar votos por candidato
        for (Voto voto : votos.values()) {
            contagem.put(voto.getCandidatoId(), contagem.getOrDefault(voto.getCandidatoId(), 0) + 1);
        }

        int totalVotos = votos.size();

        // Encontrar o vencedor (quem tem mais votos)
        String vencedorId = null;
        int maxVotos = 0;
        for (Map.Entry<String, Integer> entry : contagem.entrySet()) {
            if (entry.getValue() > maxVotos) {
                maxVotos = entry.getValue();
                vencedorId = entry.getKey();
            }
        }

        return new ResultadoVotacao(contagem, totalVotos, vencedorId);
    }
}
