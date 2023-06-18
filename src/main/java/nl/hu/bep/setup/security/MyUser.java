package nl.hu.bep.setup.security;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class MyUser implements Principal {
    private static List<MyUser> allUsers = new ArrayList<>();
    private String username;
    private String password;
    private String role;

    public static boolean createUser(String username, String password, String role) {
        if (getUserByUsername(username) == null) {
            return allUsers.add(new MyUser(username, password, role));
        }
        return false;
    }


    public static MyUser getUserByUsername(String username) {
        return allUsers.stream()
                .filter(user->user.username.equals(username))
                .findFirst().orElse(null);
    }

    private MyUser(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;

    }

//Controleerd of de user bestaad
    public static String validateLogin(String username, String password) {
        MyUser found = getUserByUsername(username);

        if (found!=null && found.password.equals(password)) return found.role;
        return null;

    }

    @Override
    public String getName() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
