package com.example.foodies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText email;
        final EditText pass;
        Button login;

        ImageButton register;

        pass = findViewById(R.id.et_password_login);
        email = findViewById(R.id.et_email_login);

        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.register_move);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(); //need firebase authentication instance

        if (firebaseAuth.getCurrentUser() != null) {
            // User is logged in
            startActivity(new Intent(LoginActivity.this, OpenPage.class));
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful());

                                 else {
                                    LoginActivity.this.startActivity(new Intent(LoginActivity.this, OpenPage.class));
                                    LoginActivity.this.finish();
                                }
                            }
                        });
            }
        });
    }
}