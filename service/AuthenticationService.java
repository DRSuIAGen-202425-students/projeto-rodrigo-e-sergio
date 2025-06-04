package service;

import model.Administrador;
import model.Eleitor;
import model.Utilizador;

import java.util.List;

public class AuthenticationService {
    private List<Administrador> admins;
    private List<Eleitor> eleitores;

    public AuthenticationService(List<Administrador> admins, List<Eleitor> eleitores) {
        this.admins = admins;
        this.eleitores = eleitores;
    }

    public Utilizador autenticar(String username, String password) {
        for (Administrador admin : admins) {
            if (admin.autenticar(username, password)) return admin;
        }
        for (Eleitor eleitor : eleitores) {
            if (eleitor.autenticar(username, password)) return eleitor;
        }
        return null;
    }
}
