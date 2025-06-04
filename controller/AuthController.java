package controller;

import model.Eleitor;
import storage.Database;
import util.HashUtil;

public class AuthController {
    public static Eleitor loginEleitor(String username, String password) {
        return Database.eleitores.stream()
                .filter(e -> e.getUsername().equals(username) &&
                        e.getPasswordHash().equals(HashUtil.hash(password)))
                .findFirst().orElse(null);
    }

    public static boolean loginAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin123");
    }
}
