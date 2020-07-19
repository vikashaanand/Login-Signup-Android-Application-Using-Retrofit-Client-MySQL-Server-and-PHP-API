package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyStudent();
            }
        });

    }

    private void verifyStudent() {

        final int roll = Integer.parseInt(edtRoll.getText().toString().trim());
        String password = edtPassword.getText().toString().trim();

        password = Base64.encodeToString(password.getBytes(), Base64.DEFAULT);


        //Server Call

        Call<StudentPOJO> call = RetrofitClient.getInstance().getApi().getStudent(roll, password);


        final String finalPassword = password;

        call.enqueue(new Callback<StudentPOJO>() {
            @Override
            public void onResponse(Call<StudentPOJO> call, Response<StudentPOJO> response) {

                StudentPOJO student = response.body();

                if(student != null){

                    MainActivity.saveStudentPreferences(roll+"", finalPassword, student.getName());

                    Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                    intent.putExtra("STUDENT_NAME", student.getName());
                    startActivity(intent);


                }else{
                    Toast.makeText(LoginActivity.this, "Invalid Details", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<StudentPOJO> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Network Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onBackPressed(){

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        
    }
}