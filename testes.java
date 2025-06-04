
import model.*;
import service.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class testes {

    private List<Administrador> admins;
    private List<Eleitor> eleitores;
    private AuthenticationService authService;
    private VotacaoService votacaoService;
    private EleitorService eleitorService;
    private CandidatoService candidatoService;

    private Administrador admin;
    private Eleitor eleitor1, eleitor2;
    private Candidato candidato1, candidato2;

    @BeforeEach
    public void setup() {
        admins = new ArrayList<>();
        eleitores = new ArrayList<>();

        admin = new Administrador("admin1", "admin", "1234");
        eleitor1 = new Eleitor("e1", "joao", "pass1");
        eleitor2 = new Eleitor("e2", "maria", "pass2");

        admins.add(admin);
        eleitores.add(eleitor1);
        eleitores.add(eleitor2);

        authService = new AuthenticationService(admins, eleitores);
        votacaoService = new VotacaoService();
        eleitorService = new EleitorService();
        candidatoService = new CandidatoService();

        eleitorService.adicionarEleitor(eleitor1);
        eleitorService.adicionarEleitor(eleitor2);

        candidato1 = new Candidato("c1", "Ana Silva", "Partido A");
        candidato2 = new Candidato("c2", "Pedro Santos", "Partido B");

        candidatoService.adicionarCandidato(candidato1, votacaoService);
        candidatoService.adicionarCandidato(candidato2, votacaoService);
    }

    @Test
    public void testAutenticacaoValida() {
        Utilizador user = authService.autenticar("admin1", "1234");
        assertNotNull(user);
        assertTrue(user instanceof Administrador);

        user = authService.autenticar("e1", "pass1");
        assertNotNull(user);
        assertTrue(user instanceof Eleitor);
    }

    @Test
    public void testAutenticacaoInvalida() {
        Utilizador user = authService.autenticar("admin1", "wrong");
        assertNull(user);

        user = authService.autenticar("unknown", "1234");
        assertNull(user);
    }

    @Test
    public void testIniciarEncerrarVotacao() {
        assertFalse(votacaoService.isVotacaoIniciada());

        assertTrue(votacaoService.iniciarVotacao());
        assertTrue(votacaoService.isVotacaoIniciada());

        // tentar iniciar novamente retorna false
        assertFalse(votacaoService.iniciarVotacao());

        votacaoService.encerrarVotacao();
        assertFalse(votacaoService.isVotacaoIniciada());
    }

    @Test
    public void testVotoValido() {
        votacaoService.iniciarVotacao();

        boolean voto1 = eleitorService.votar(eleitor1.getId(), candidato1.getId(), votacaoService);
        assertTrue(voto1);
        assertTrue(eleitor1.jaVotou());

        // Verificar votos no resultado
        ResultadoVotacao resultado = admin.obterResultados(votacaoService);
        assertEquals(1, resultado.getTotalVotos());
        assertEquals(1, resultado.getVotosPorCandidato().get(candidato1.getId()).intValue());
    }

    @Test
    public void testVotoDuplo() {
        votacaoService.iniciarVotacao();

        boolean primeiroVoto = eleitorService.votar(eleitor1.getId(), candidato1.getId(), votacaoService);
        assertTrue(primeiroVoto);

        boolean segundoVoto = eleitorService.votar(eleitor1.getId(), candidato2.getId(), votacaoService);
        assertFalse(segundoVoto);  // Não pode votar duas vezes
    }

    @Test
    public void testVotoSemVotacaoIniciada() {
        // A votação não foi iniciada
        boolean voto = eleitorService.votar(eleitor1.getId(), candidato1.getId(), votacaoService);
        assertFalse(voto);
    }

    @Test
    public void testResultadoComVariosVotos() {
        votacaoService.iniciarVotacao();

        eleitorService.votar(eleitor1.getId(), candidato1.getId(), votacaoService);
        eleitorService.votar(eleitor2.getId(), candidato2.getId(), votacaoService);

        ResultadoVotacao resultado = admin.obterResultados(votacaoService);
        assertEquals(2, resultado.getTotalVotos());

        assertEquals(1, resultado.getVotosPorCandidato().get(candidato1.getId()).intValue());
        assertEquals(1, resultado.getVotosPorCandidato().get(candidato2.getId()).intValue());
    }
}
