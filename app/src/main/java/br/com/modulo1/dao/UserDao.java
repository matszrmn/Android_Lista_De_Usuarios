package br.com.modulo1.dao;

import java.util.LinkedList;
import java.util.List;

import br.com.modulo1.model.User;

public class UserDao {

    private static volatile List<User> userList;

    static {
        userList = new LinkedList<>();
    }

    public List<User> todos() {
        return new LinkedList<>(userList);
    }

    public void save(User user) {
        userList.add(user);
    }
}
