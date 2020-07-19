package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnSignUp;

    private static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        preferences = getSharedPreferences("LOGIN_DETAILS", MODE_PRIVATE);


        if(preferences.contains("ROLL") && preferences.contains("PASSWORD") ){

            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            intent.putExtra("STUDENT_NAME", preferences.getString("NAME", ""));
            startActivity(intent);

        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    public static void saveStudentPreferences(String roll, String password, String name){

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("ROLL", roll);
        editor.putString("PASSWORD", password);
        editor.putString("NAME", name);

        editor.commit();

    }

    public static void clearStudentPref(){

        preferences.edit().remove("ROLL").commit();
        preferences.edit().remove("PASSWORD").commit();
        preferences.edit().remove("NAME").commit();

    }

}