package br.com.modulo1.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.modulo1.R;
import br.com.modulo1.dao.UserDao;
import br.com.modulo1.model.User;
import br.com.modulo1.ui.activity.adapters.UserListAdapter;

import static br.com.modulo1.ui.activity.util.Constants.TITLE_MAP;
import static br.com.modulo1.ui.activity.util.Constants.USER_MAP;

public class MainActivity extends AppCompatActivity {

    public static final String TITLE_ADD_USER = "Novo Usuário";
    public static final String TITLE_UPDATE_USER = "Editar Usuário";
    private UserDao userDao;
    private ListView listUsers;
    private FloatingActionButton addButton;
    private UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initDao();
        initViews();
        initAddButtonListener();
        initUserListAdapter();
        initUserClickListener();
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.refreshAdapter(userDao.getAll());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.remove_main_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.remove_form_user_menu) {
            User user = (User) adapter.getItem(menuInfo.position);
            removeUser(user);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.gc();
        System.exit(0);
    }

    private void initDao() {
        userDao = new UserDao();
    }

    private void initViews() {
        addButton = findViewById(R.id.fab_add_main);
        listUsers = findViewById(R.id.list_users_main);
    }

    @SuppressWarnings("CodeBlock2Expr")
    private void initAddButtonListener() {
        addButton.setOnClickListener(v -> {
            navigateToFormUserAdd();
        });
    }

    private void initUserListAdapter() {
        adapter = new UserListAdapter(this);
        listUsers.setAdapter(adapter);
        registerForContextMenu(listUsers);
    }

    private void initUserClickListener() {
        setOnUpdateUser();
    }

    private void navigateToFormUserAdd() {
        Intent intent = new Intent(this, FormUserActivity.class);
        intent.putExtra(TITLE_MAP, TITLE_ADD_USER);
        startActivity(intent);
    }

    private void navigateToFormUserUpdate(User user) {
        Intent intent = new Intent(this, FormUserActivity.class);
        intent.putExtra(TITLE_MAP, TITLE_UPDATE_USER);
        intent.putExtra(USER_MAP, user);
        startActivity(intent);
    }

    @SuppressWarnings("CodeBlock2Expr")
    private void setOnUpdateUser() {
        listUsers.setOnItemClickListener((parent, view, position, id) -> {
            navigateToFormUserUpdate((User) listUsers.getItemAtPosition(position));
        });
    }

    private void removeUser(User user) {
        adapter.remove(user);
        userDao.remove(user);
    }
}