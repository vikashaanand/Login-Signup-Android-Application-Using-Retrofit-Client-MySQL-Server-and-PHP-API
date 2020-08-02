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

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText edtNewPassword, edtConfirmPassword, edtOTP;
    private Button btnResetPassword;

    private String otp;
    private int roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        otp = getIntent().getExtras().getString("OTP");
        roll = Integer.parseInt(getIntent().getExtras().getString("ROLL"));

        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtOTP = findViewById(R.id.edtOTP);

        btnResetPassword = findViewById(R.id.btnResetPassword);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {

        String password = edtNewPassword.getText().toString().trim();
        String conPass = edtConfirmPassword.getText().toString().trim();

        String confirmOTP = edtOTP.getText().toString().trim();

        if (!password.matches(conPass)){

            edtConfirmPassword.setError("Password not matched");
            edtConfirmPassword.requestFocus();
            return;
        }else{
            password = Base64.encodeToString(password.getBytes(), Base64.DEFAULT);
        }

        if( !otp.matches(confirmOTP)){
            edtOTP.setError("OTP not matched");
            edtOTP.requestFocus();
            return;
        }

        //Server...update password
        Call<ResponsePOJO> call = RetrofitClient.getInstance().getApi().updatePassword(roll, password);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {

                Toast.makeText(ResetPasswordActivity.this, response.body().getRemarks(), Toast.LENGTH_SHORT).show();

                if(response.body().isStatus()){
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(ResetPasswordActivity.this, "NEtwork Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }
}