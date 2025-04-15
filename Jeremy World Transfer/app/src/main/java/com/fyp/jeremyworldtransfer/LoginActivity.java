package com.fyp.jeremyworldtransfer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email1;
    private EditText password1;
    private Button logButton1;
    private Button regButton1;
    //Taylor K, Wong J et al (2019). IoT Enters Home: Automating Household Systems. Nottingham Trent University [unpublished]

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        email1 = findViewById(R.id.loginEmail);
        password1 = findViewById(R.id.loginPassword);
        logButton1 = findViewById(R.id.loginButton);
        regButton1 = findViewById(R.id.registerButton);

        loginAction();
        registerAction();
        //Taylor K, Wong J et al (2019). IoT Enters Home: Automating Household Systems. Nottingham Trent University [unpublished]
    }

    public void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if(cUser != null){
            Intent startMainApp1 = new Intent(getApplicationContext(), MainActivity.class);
            finish();                       //if the user is already logged in, they should not be able to go back to login activity
            startActivity(startMainApp1);
            //Taylor K, Wong J et al (2019). IoT Enters Home: Automating Household Systems. Nottingham Trent University [unpublished]
        }
    }

    public void authenticateUser(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent mainPage1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();     //once the user is logged in, they should not be able to return to the login activity, unless they have logged out
                    startActivity(mainPage1);
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
                //Taylor K, Wong J et al (2019). IoT Enters Home: Automating Household Systems. Nottingham Trent University [unpublished]
            }
        });
    }

    public void loginAction(){
        logButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email2 = email1.getText().toString().trim();
                String password2 = password1.getText().toString().trim();
                if((TextUtils.isEmpty(email2)) || (TextUtils.isEmpty(password2))){
                    Toast.makeText(getApplicationContext(), "One or more required fields are empty",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                authenticateUser(email2, password2);
                //Taylor K, Wong J et al (2019). IoT Enters Home: Automating Household Systems. Nottingham Trent University [unpublished]
            }
        });
    }

    public void registerAction(){
        regButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent switchToRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(switchToRegister);
                //Taylor K, Wong J et al (2019). IoT Enters Home: Automating Household Systems. Nottingham Trent University [unpublished]
            }
        });

    }

}
