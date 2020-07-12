package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtName, edtRoll, edtMobile, edtEmail, edtPassword;
    private RadioButton rdMale, rdFemale;
    private Button btnSubmit;

    private String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edtName);
        edtRoll = findViewById(R.id.edtRoll);
        edtMobile = findViewById(R.id.edtMobile);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        rdMale = findViewById(R.id.rdMale);
        rdFemale = findViewById(R.id.rdFemale);

        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudent();
            }
        });

    }

    private void saveStudent() {

        String name = edtName.getText().toString().trim();
        String roll = edtRoll.getText().toString().trim();
        String mobile = edtMobile.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        String mobilePattern = "^[7-9][0-9]{9}$";
        String emailPattern = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";


        //Validation

        if(name.isEmpty()){
            edtName.setError("Required");
            edtName.requestFocus();
            return;
        }

        if(roll.isEmpty()){
            edtRoll.setError("Required");
            edtRoll.requestFocus();
            return;
        }

        if (!mobile.matches(mobilePattern)){
            edtMobile.setError("Invalid Mobile Number");
            edtMobile.requestFocus();
            return;
        }

        if(!email.matches(emailPattern)){
            edtEmail.setError("Invalid Email ID");
            edtEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            edtPassword.setError("Required");
            edtPassword.requestFocus();
            return;
        }else{
            password = Base64.encodeToString(password.getBytes(), Base64.DEFAULT);
        }

        if (gender.isEmpty()){
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
        }


        //Server Call

        Call<ResponsePOJO> call = RetrofitClient.getInstance().getApi().saveStudent(Integer.parseInt(roll),
                name, mobile, email, gender, password);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {

                ResponsePOJO obj = response.body();

                Toast.makeText(SignUpActivity.this, obj.getRemarks(), Toast.LENGTH_SHORT).show();

                if(obj.isStatus()){

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);

                }

            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Network Failed", Toast.LENGTH_SHORT).show();
            }
        });







    }

    public void onRadioButtonClick(View view) {

        switch (view.getId()){

            case R.id.rdMale:
                gender = "Male";
                break;

            case R.id.rdFemale:
                gender = "Female";
                break;

        }


    }
}