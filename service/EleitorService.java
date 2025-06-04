package service;

import model.Eleitor;

import java.util.*;

public class EleitorService {
    private final Map<String, Eleitor> eleitores = new HashMap<>();
    private boolean votacaoIniciada = false;

    public boolean adicionarEleitor(Eleitor eleitor) {
        if (votacaoIniciada || eleitores.containsKey(eleitor.getId())) {
            return false;
        }
        eleitores.put(eleitor.getId(), eleitor);
        return true;
    }

    public boolean editarEleitor(String id, String novoUsername, String novaPassword) {
        if (votacaoIniciada || !eleitores.containsKey(id)) {
            return false;
        }
        Eleitor eleitor = eleitores.get(id);
        // Atualizar os dados do eleitor
        eleitor.setUsername(novoUsername);
        eleitor.setPassword(novaPassword);
        return true;
    }

    public boolean removerEleitor(String id) {
        if (votacaoIniciada || !eleitores.containsKey(id)) {
            return false;
        }
        eleitores.remove(id);
        return true;
    }

    public List<Eleitor> listarEleitores() {
        return new ArrayList<>(eleitores.values());
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

    public boolean votar(String eleitorId, String candidatoId, VotacaoService votacaoService) {
        Eleitor eleitor = eleitores.get(eleitorId);

        if (eleitor == null || eleitor.jaVotou()) {
            return false;  // Eleitor não existe ou já votou
        }

        boolean sucesso = votacaoService.registarVoto(eleitorId, candidatoId);
        if (sucesso) {
            eleitor.setVotou(true);
        }
        return sucesso;
    }

}
