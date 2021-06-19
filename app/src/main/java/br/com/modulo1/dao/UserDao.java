package br.com.modulo1.dao;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import br.com.modulo1.model.User;

public class UserDao {

    private static final List<User> userList;

    public static final String TAG_ADD = "INSERT";
    public static final String TAG_UPDATE = "UPDATE";
    public static final String TAG_REMOVE = "REMOVE";

    static {
        userList = new LinkedList<>();
    }

    public List<User> getAll() {
        return new LinkedList<>(userList);
    }

    private void replaceValues(User userDao, User user) {
        if (userDao != null) {
            userDao.setName(user.getName());
            userDao.setEmail(user.getEmail());
            userDao.setPassword(user.getPassword());
        }
    }

    @Nullable
    private User findById(UUID id) {
        for (User userDao : userList) {
            if(userDao.getId().equals(id)) {
                return userDao;
            }
        }
        return null;
    }

    public void saveOrUpdate(User user) {
        if (user.getId() == null) {
            save(user);
        } else {
            update(user);
        }
    }

    public void save(User user) {
        user.setId(UUID.randomUUID());
        userList.add(user);
        Log.i(TAG_ADD, user.toPrivateString());
    }

    public void update(User user) {
        User userDao = findById(user.getId());
        replaceValues(userDao, user);
        Log.i(TAG_UPDATE, user.toPrivateString());
    }

    public void remove(User user) {
        if (user != null && user.getId() != null) {
            User userDao = findById(user.getId());
            userList.remove(userDao);
            Log.i(TAG_REMOVE, user.toPrivateString());
        }
    }
}
