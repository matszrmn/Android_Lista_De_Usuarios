package br.com.modulo1.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {

    private UUID id;

    private String name;

    private String email;
    private String password;
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {}

    public User(String name, String email, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return name + ", " + email;
    }

    public String toPrivateString() { return name + ", " + email + ", " + password; }
}
