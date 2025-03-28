package br.com.agi.utils;

import br.com.agi.model.Usuario;

public class SessaoLogon {

    private static Usuario loggedUser;

    public static void setLoggedUser(Usuario user) {
        loggedUser = user;
    }

    public static Usuario getLoggedUser() {
        return loggedUser;
    }

    public static boolean isUserLoggedIn() {
        return loggedUser != null;
    }
}
