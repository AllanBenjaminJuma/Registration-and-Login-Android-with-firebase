package com.example.instructor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    EditText etxtNameRegistration, etxtEmailRegistration, etxtPasswordRegistrationOne, etxtPasswordRegistrationTwo;
    TextView txtLogIn;
    Button btnRegister;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        etxtNameRegistration = findViewById(R.id.etxtNameRegistration);
        etxtEmailRegistration = findViewById(R.id.etxtEmailRegistration);
        etxtPasswordRegistrationOne = findViewById(R.id.etxtPasswordRegistrationOne);
        etxtPasswordRegistrationTwo = findViewById(R.id.etxtPasswordRegistrationTwo);
        txtLogIn = findViewById(R.id.txt_log_in);


        btnRegister = findViewById(R.id.btnRegister);
        fAuth = FirebaseAuth.getInstance();

        //if(fAuth != null){
          //  startActivity(new Intent(getApplicationContext(), MainActivity.class));
            //finish();

       // }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, email, password, confirmPassword;

                name = etxtNameRegistration.getText().toString();
                email = etxtEmailRegistration.getText().toString();
                password = etxtPasswordRegistrationOne.getText().toString();
                confirmPassword = etxtPasswordRegistrationTwo.getText().toString();


                if(name.equals("")){
                    Toast.makeText(Registration.this, "Name Required", Toast.LENGTH_SHORT).show();
                }
                else
                if (email.equals("")) {
                    Toast.makeText(Registration.this, "Email Required", Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(Registration.this, "Password Required", Toast.LENGTH_SHORT).show();
                }
                // else if (!confirmPassword.equals(password)){
                //     Toast.makeText(Registration.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                // }
               else {
                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Registration.this, "User Created", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                            else {
                                Toast.makeText(Registration.this, "Error occurred" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        txtLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LogIn.class));
                finish();
            }
        });
    }

}