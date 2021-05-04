package br.com.modulo1.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.modulo1.R;
import br.com.modulo1.dao.UserDao;
import br.com.modulo1.model.User;
import br.com.modulo1.ui.activity.validator.FormUserValidator;

public class FormUserActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private Button saveButton;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_user);

        initializeAttributes();
        configSaveButton(userDao);
    }

    private void configSaveButton(UserDao userDao) {
        saveButton.setOnClickListener(v -> {
            saveUserAndClose(userDao);
        });
    }

    private void saveUserAndClose(UserDao userDao) {
        if (validateInputData()) {
            userDao.save(new User(name.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString()));
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
        saveButton = findViewById(R.id.saveButton_form_user);
    }
}