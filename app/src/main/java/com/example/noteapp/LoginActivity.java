package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    static String User;

    Button SignInId, SignUpId;
    EditText UserNameId, PassWordId;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SignInId = findViewById(R.id.signInId);
        SignUpId = findViewById((R.id.signUpId));
        UserNameId = findViewById(R.id.userNameId);
        PassWordId = findViewById((R.id.passWordId));

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LoggedInOrNot", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

//        editor.get

        SignInId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedPreferences.contains(UserNameId.getText().toString())) {
                    if(sharedPreferences.getString(UserNameId.getText().toString(), null).equals(PassWordId.getText().toString())){
                        User = UserNameId.getText().toString();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        SignUpId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedPreferences.contains(UserNameId.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Username already Exist", Toast.LENGTH_SHORT).show();

                } else if (UserNameId.getText().toString().equals(null) || PassWordId.getText().toString().equals(null)) {
                    Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString(UserNameId.getText().toString(), PassWordId.getText().toString()).apply();
                    editor.commit();
                }
                UserNameId.setText("");
                PassWordId.setText("");
            }
        });
    }
}