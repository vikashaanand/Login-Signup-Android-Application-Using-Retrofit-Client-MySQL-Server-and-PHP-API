package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText edtRoll, edtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtRoll = findViewById(R.id.edtUserRoll);
        edtPassword = findViewById(R.id.edtUserPassword);
        btnLogin = findViewById(R.id.btnUserLogin);
    }
}