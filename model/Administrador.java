package model;

import service.VotacaoService;

public class Administrador extends Utilizador {

    public Administrador(String id, String username, String password) {
        super(id, username, password);
    }

    public boolean iniciarVotacao(VotacaoService votacaoService) {
        return votacaoService.iniciarVotacao();
    }

    public boolean encerrarVotacao(VotacaoService votacaoService) {
        return votacaoService.encerrarVotacao();
    }

    public ResultadoVotacao obterResultados(VotacaoService votacaoService) {
        return votacaoService.apurarResultados();
    }
}
