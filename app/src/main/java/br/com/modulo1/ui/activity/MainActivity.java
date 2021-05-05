package br.com.modulo1.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.modulo1.R;
import br.com.modulo1.dao.UserDao;

public class MainActivity extends AppCompatActivity {

    private UserDao userDao;
    private ListView listUsers;
    private FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeDao();
        initializeViews();
        configAddButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillUserList();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.gc();
        System.exit(0);
    }

    private void initializeViews() {
        addButton = findViewById(R.id.fab_add_main);
        listUsers = findViewById(R.id.list_users_main);
    }

    private void initializeDao() {
        userDao = new UserDao();
    }

    private void configAddButton() {
        addButton.setOnClickListener(v -> {
            navigateToFormUser();
        });
    }

    private void navigateToFormUser() {
        startActivity(new Intent(this, FormUserActivity.class));
    }

    private void fillUserList() {
        listUsers.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userDao.todos()));
    }
}