package service;

import model.Candidato;

import java.util.*;

public class CandidatoService {
    private final Map<String, Candidato> candidatos = new HashMap<>();
    private boolean votacaoIniciada = false;

    public boolean adicionarCandidato(Candidato candidato, VotacaoService votacaoService) {
        if (votacaoService.isVotacaoIniciada() || candidatos.containsKey(candidato.getId())) {
            return false; // Não pode adicionar se a votação estiver a decorrer ou o candidato já existir
        }
        candidatos.put(candidato.getId(), candidato);
        return true;
    }


    public boolean editarCandidato(String id, String novoNome, String novoPartido) {
        if (votacaoIniciada || !candidatos.containsKey(id)) return false;
        Candidato c = candidatos.get(id);
        c.setNome(novoNome);
        c.setPartido(novoPartido);
        return true;
    }

    public boolean removerCandidato(String id) {
        if (votacaoIniciada || !candidatos.containsKey(id)) return false;
        candidatos.remove(id);
        return true;
    }

    public List<Candidato> listarCandidatos() {
        return new ArrayList<>(candidatos.values());
    }

    public void iniciarVotacao() {
        votacaoIniciada = true;
    }

    public void encerrarVotacao() {
        votacaoIniciada = false;
    }

    public boolean isVotacaoIniciada() {
        return votacaoIniciada;
    }
}
