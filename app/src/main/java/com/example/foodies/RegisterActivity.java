package com.example.foodies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText name;
    EditText pass1;
    EditText pass2;
    EditText email;
    Button btn_register;

    ImageView login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        name = findViewById(R.id.et_name);
        pass1 = findViewById(R.id.et_password);
        pass2 = findViewById(R.id.et_repassword);
        email = findViewById(R.id.et_email);
        btn_register = findViewById(R.id.btn_register);
       login = findViewById(R.id.login_move);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(); //need firebase authentication instance

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });





        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(pass1.getText().toString().equals(pass2.getText().toString()))) {
                    Toast.makeText(RegisterActivity.this, getString(R.string.incorrect_pass), Toast.LENGTH_SHORT).show();
                    return;
                }
                if(email.getText().toString().equals("") || email.getText().toString() == null) {
                    Toast.makeText(RegisterActivity.this, getString(R.string.email_empty), Toast.LENGTH_SHORT).show();
                    return;
                }
                //firebaseAuth.
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),pass1.getText().toString())
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("REGISTER_TEST", "New user registration: " + task.isSuccessful());

                                if (!task.isSuccessful()) {
                                    if (pass1.getText().length() < 7) {
                                        Toast.makeText(RegisterActivity.this, getString(R.string.bad_pass), Toast.LENGTH_SHORT).show();
                                    }
                                    Toast.makeText(RegisterActivity.this, getString(R.string.failed_register), Toast.LENGTH_SHORT).show();
                                } else {
                                    RegisterActivity.this.startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    RegisterActivity.this.finish();
                                }
                            }
                        });
            }
        });

    }
}