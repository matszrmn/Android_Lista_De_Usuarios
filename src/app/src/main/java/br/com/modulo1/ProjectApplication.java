package br.com.modulo1;

import android.app.Application;

import br.com.modulo1.dao.UserDao;
import br.com.modulo1.model.User;

public class ProjectApplication extends Application {

    private UserDao userDao;

    @Override
    public void onCreate() {
        super.onCreate();
        userDao = new UserDao();
        createTestUsers();
    }

    private void createTestUsers() {
        for (int i = 0; i < 2; i++) {
            User test = new User("Nom" + i, "nom" + i + "@nom.com", "password" + i);
            userDao.save(test);
        }
    }
}
