package br.com.modulo1.model;

import androidx.annotation.NonNull;

public class User {
    private final String name;
    private final String email;
    private final String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " , " + email + " , " + password;
    }
}
