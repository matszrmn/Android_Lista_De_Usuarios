package br.com.modulo1.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.modulo1.R;
import br.com.modulo1.dao.UserDao;
import br.com.modulo1.model.User;
import br.com.modulo1.ui.activity.validator.FormUserValidator;

import static br.com.modulo1.ui.activity.util.Constants.TITLE_MAP;
import static br.com.modulo1.ui.activity.util.Constants.USER_MAP;

public class FormUserActivity extends AppCompatActivity {

    public static final String DEFAULT_TITLE = "Cadastro Usuário";
    private EditText name;
    private EditText email;
    private EditText password;

    private UserDao userDao;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_user_activity);

        initializeAttributes();
        initializeTitleAndTextFields();
        configDoneButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_form_user_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_form_user_menu_id) {
            saveOrUpdateUserAndClose();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeTitleAndTextFields() {
        Intent intent = getIntent();

        setTitle(DEFAULT_TITLE);
        if (intent.hasExtra(TITLE_MAP)) {
            setTitle(intent.getStringExtra(TITLE_MAP));
        }
        user = null;
        if (intent.hasExtra(USER_MAP)) {
            user = (User) intent.getSerializableExtra(USER_MAP);
        }
        fillTextFields();
    }

    private void fillTextFields() {
        if (user != null) {
            name.setText(user.getName());
            email.setText(user.getEmail());
            password.setText(user.getPassword());
        }
    }

    private void fillUserAttributes() {
        if (user == null) {
            user = new User();
        }
        user.setName(name.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void configDoneButton() {
        password.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                saveOrUpdateUserAndClose();
                return true;
            }
            return false;
        });
    }

    private void saveOrUpdateUserAndClose() {
        if (validateInputData()) {
            fillUserAttributes();
            userDao.saveOrUpdate(user);
            finish();
        }
    }

    private boolean validateInputData() {
        FormUserValidator.NAME.validate(name);
        FormUserValidator.EMAIL.validate(email);
        FormUserValidator.PASSWORD.validate(password);

        return (name.getError() == null &&
                email.getError() == null &&
                password.getError() == null); // Em caso de falha, checar se está vazio também
    }

    private void initializeAttributes() {
        userDao = new UserDao();
        name = findViewById(R.id.name_form_user);
        email = findViewById(R.id.email_form_user);
        password = findViewById(R.id.password_form_user);
    }
}