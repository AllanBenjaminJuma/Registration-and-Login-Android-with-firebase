package com.example.instructor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {

    EditText etxtEmail, etxtPassword;
    Button btnLogin;
    TextView txtRegister;
    ProgressBar logInProgressBar;
    FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etxtEmail = findViewById(R.id.etxtEmail);
        etxtPassword = findViewById(R.id.etxtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.txtRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;

                email = etxtEmail.getText().toString().trim();
                password = etxtPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LogIn.this, "Email Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (password.equals("")){
                    Toast.makeText(LogIn.this, "Password Required", Toast.LENGTH_SHORT).show();
                    return;
                }
//                else{
//                    Intent routing_slip_intent = new Intent(LogIn.this, RoutingSlip.class);
//                    startActivity(routing_slip_intent);
//                }
                //logInProgressBar.setVisibility(View.VISIBLE);

                fauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(dLogIn.this, "Logged in Successfully", Toast.LENGTH_SHORT);
                            startActivity(new Intent(getApplicationContext(),Registration.class));
                        }else {
                            Toast.makeText(LogIn.this, "Error occurred" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            logInProgressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogIn.this, Registration.class);
                startActivity(i);
                finish();
            }
        });
    }
}
