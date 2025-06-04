import controller.*;
import model.*;
import storage.Database;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Adiciona dados de teste aqui
        // ...

        System.out.println("Tipo de acesso (admin/eleitor): ");
        String tipo = in.nextLine();

        if (tipo.equals("admin")) {
            System.out.print("Username: ");
            String user = in.nextLine();
            System.out.print("Password: ");
            String pass = in.nextLine();

            if (AuthController.loginAdmin(user, pass)) {
                System.out.println("Admin logado!");
                // opções: gerir candidatos/eleitores, iniciar, encerrar, ver resultados
            }
        } else if (tipo.equals("eleitor")) {
            System.out.print("Username: ");
            String user = in.nextLine();
            System.out.print("Password: ");
            String pass = in.nextLine();

            Eleitor e = AuthController.loginEleitor(user, pass);
            if (e != null) {
                System.out.println("Bem-vindo " + e.getNome());
                // votar se permitido
            } else {
                System.out.println("Credenciais inválidas.");
            }
        }
    }
}
