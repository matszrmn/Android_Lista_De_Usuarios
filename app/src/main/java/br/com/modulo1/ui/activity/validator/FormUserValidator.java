package br.com.modulo1.ui.activity.validator;

import android.util.Patterns;
import android.widget.EditText;

public enum FormUserValidator {
    NAME {
        @Override
        public void validate(EditText editText) {
            if (editText.getText().toString().length() < 1) {
                editText.setError("Nome não pode estar vazio!");
            } else {
                editText.setError(null);
            }
        }
    }, EMAIL {
        @Override
        public void validate(EditText editText) {
            if (!Patterns.EMAIL_ADDRESS.matcher(editText.getText().toString()).matches()) {
                editText.setError("O email não é válido!");
            } else {
                editText.setError(null);
            }
        }
    }, PASSWORD {
        @Override
        public void validate(EditText editText) {
            if (editText.getText().toString().length() < 1) {
                editText.setError("Senha não pode estar vazia!");
            } else {
                editText.setError(null);
            }
        }
    };

    @SuppressWarnings("unused")
    public abstract void validate(EditText editText);
}