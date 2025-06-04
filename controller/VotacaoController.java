package controller;

import model.*;
import storage.Database;

import java.time.LocalDateTime;

public class VotacaoController {

    public static boolean votar(Eleitor eleitor, int idCandidato) {
        if (!Database.votacaoAberta || eleitor.isVotou()) return false;

        Voto voto = new Voto();
        voto.setId(Database.votos.size() + 1);
        voto.setIdCandidato(idCandidato);
        voto.setTimestamp(LocalDateTime.now());
        Database.votos.add(voto);

        eleitor.setVotou(true);
        return true;
    }

    public static void iniciarVotacao() {
        Database.votacaoAberta = true;
    }

    public static void encerrarVotacao() {
        Database.votacaoAberta = false;
    }

    public static void mostrarResultados() {
        System.out.println("RESULTADOS:");
        long totalVotos = Database.votos.size();

        Database.candidatos.forEach(c -> {
            long votos = Database.votos.stream()
                    .filter(v -> v.getIdCandidato() == c.getId())
                    .count();
            double percentagem = totalVotos == 0 ? 0 : (votos * 100.0) / totalVotos;
            System.out.printf("%s (%s): %d votos (%.2f%%)%n",
                    c.getNome(), c.getPartido(), votos, percentagem);
        });
    }
}
