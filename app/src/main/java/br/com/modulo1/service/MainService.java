package br.com.modulo1.service;

import android.content.Context;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import br.com.modulo1.dao.UserDao;
import br.com.modulo1.model.User;
import br.com.modulo1.ui.activity.adapter.UserListAdapter;

public class MainService {

    private Context context;
    private UserDao userDao;
    private ListView listUsers;
    private UserListAdapter adapter;
    public static final String MESSAGE_CONFIRM_REMOVE = "Deseja mesmo excluir este usuário?";
    public static final String NEGATIVE_CONFIRM = "Não";
    public static final String POSITIVE_CONFIRM = "Sim";

    public MainService(Context context, ListView listUsers) {
        this.context = context;
        this.listUsers = listUsers;
        initDao();
        initUserListAdapter();
    }

    @SuppressWarnings("CodeBlock2Expr")
    public void openRemoveDialog(int position) {
        User user = (User) adapter.getItem(position);
        new AlertDialog.Builder(this.context)
                .setMessage(MESSAGE_CONFIRM_REMOVE)
                .setNegativeButton(NEGATIVE_CONFIRM, null)
                .setPositiveButton(POSITIVE_CONFIRM, (dialog, which) -> {
                    removeUser(user);
                })
                .show();
    }

    private void initDao() {
        userDao = new UserDao();
    }

    private void initUserListAdapter() {
        adapter = new UserListAdapter(context);
        listUsers.setAdapter(adapter);
    }

    public void refreshAdapter() {
        adapter.refreshAdapter(userDao.getAll());
    }

    private void removeUser(User user) {
        userDao.remove(user);
        adapter.remove(user);
    }
}
