package storage;

import model.*;

import java.util.*;

public class Database {
    public static List<Eleitor> eleitores = new ArrayList<>();
    public static List<Candidato> candidatos = new ArrayList<>();
    public static List<Voto> votos = new ArrayList<>();

    public static boolean votacaoAberta = false;
}
