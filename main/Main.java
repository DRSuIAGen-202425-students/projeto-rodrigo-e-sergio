package main;

import model.*;
import service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Criar listas de administradores e eleitores para autenticação
        List<Administrador> admins = new ArrayList<>();
        List<Eleitor> eleitores = new ArrayList<>();

        // Criar utilizadores
        Administrador admin = new Administrador("admin1", "admin", "1234");
        Eleitor eleitor1 = new Eleitor("e1", "joao", "pass1");
        Eleitor eleitor2 = new Eleitor("e2", "maria", "pass2");

        // Adicionar aos respetivos arrays para autenticação
        admins.add(admin);
        eleitores.add(eleitor1);
        eleitores.add(eleitor2);

        // Instanciar serviços
        AuthenticationService authService = new AuthenticationService(admins, eleitores);
        VotacaoService votacaoService = new VotacaoService();
        EleitorService eleitorService = new EleitorService();
        CandidatoService candidatoService = new CandidatoService();

        // Adicionar eleitores ao serviço de eleitores
        eleitorService.adicionarEleitor(eleitor1);
        eleitorService.adicionarEleitor(eleitor2);

        // Criar candidatos
        Candidato candidato1 = new Candidato("c1", "Ana Silva", "Partido A");
        Candidato candidato2 = new Candidato("c2", "Pedro Santos", "Partido B");

        // Adicionar candidatos
        candidatoService.adicionarCandidato(candidato1, votacaoService);
        candidatoService.adicionarCandidato(candidato2, votacaoService);

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Sistema de Votação ===");

        while (true) {
            System.out.print("\n[Login] Username (ou 'sair' para terminar): ");
            String username = scanner.nextLine();
            if (username.equalsIgnoreCase("sair")) {
                System.out.println("A terminar o sistema...");
                break;
            }

            System.out.print("Password: ");
            String password = scanner.nextLine();

            Utilizador user = authService.autenticar(username, password);

            if (user == null) {
                System.out.println("❌ Falha na autenticação.");
                continue;
            }

            if (user instanceof Administrador) {
                System.out.println("🔐 Bem-vindo, Administrador " + user.getUsername());

                boolean sairMenuAdmin = false;
                while (!sairMenuAdmin) {
                    System.out.println("\nMenu Administrador:");
                    System.out.println("1 - Iniciar votação");
                    System.out.println("2 - Encerrar votação");
                    System.out.println("3 - Ver resultados");
                    System.out.println("4 - Terminar sessão");
                    System.out.print("Escolha uma opção: ");

                    String opcao = scanner.nextLine();

                    switch (opcao) {
                        case "1":
                            if (votacaoService.iniciarVotacao()) {
                                System.out.println("✅ Votação iniciada!");
                            } else {
                                System.out.println("⚠️ A votação já está iniciada.");
                            }
                            break;

                        case "2":
                            if (votacaoService.isVotacaoIniciada()) {
                                votacaoService.encerrarVotacao();
                                System.out.println("✅ Votação encerrada.");
                            } else {
                                System.out.println("⚠️ A votação ainda não foi iniciada.");
                            }
                            break;

                        case "3":
                            ResultadoVotacao resultado = ((Administrador) user).obterResultados(votacaoService);

                            System.out.println("\n--- Resultados da Votação ---");
                            System.out.println("Total de votos: " + resultado.getTotalVotos());

                            for (Candidato c : candidatoService.listarCandidatos()) {
                                int votos = resultado.getVotosPorCandidato().getOrDefault(c.getId(), 0);
                                double percentual = resultado.getTotalVotos() == 0 ? 0 : (votos * 100.0 / resultado.getTotalVotos());
                                System.out.printf("Candidato %s: %d votos (%.2f%%)%n", c.getNome(), votos, percentual);
                            }

                            System.out.println("Vencedor: " + (resultado.getVencedorId() != null ? resultado.getVencedorId() : "Nenhum"));
                            break;

                        case "4":
                            sairMenuAdmin = true;
                            System.out.println("🔙 Sessão terminada.");
                            break;

                        default:
                            System.out.println("Opção inválida.");
                    }
                }

            } else if (user instanceof Eleitor) {
                Eleitor eleitor = (Eleitor) user;
                System.out.println("👋 Bem-vindo, Eleitor " + user.getUsername());

                if (!votacaoService.isVotacaoIniciada()) {
                    System.out.println("⚠️ A votação ainda não começou. Aguarde o administrador iniciar.");
                    continue;
                }

                if (eleitor.jaVotou()) {
                    System.out.println("✅ Você já votou. Obrigado!");
                    continue;
                }

                System.out.println("\nCandidatos disponíveis:");
                for (Candidato c : candidatoService.listarCandidatos()) {
                    System.out.println(c.getId() + " - " + c.getNome());
                }

                System.out.print("Digite o ID do candidato que deseja votar: ");
                String candidatoId = scanner.nextLine();

                boolean sucesso = eleitorService.votar(eleitor.getId(), candidatoId, votacaoService);

                if (sucesso) {
                    System.out.println("✅ Voto registado com sucesso!");
                } else {
                    System.out.println("❌ Falha ao registar voto. Verifique o ID do candidato ou se já votou.");
                }

            } else {
                System.out.println("❌ Tipo de utilizador não reconhecido.");
            }
        }

        scanner.close();
    }
}
