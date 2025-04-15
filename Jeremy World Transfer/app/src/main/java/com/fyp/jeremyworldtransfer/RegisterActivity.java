package com.fyp.jeremyworldtransfer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText fName;
    private EditText lName;
    private EditText email3;
    private EditText confirmedEmail;
    private EditText password3;
    private EditText confirmedPassword;
    private Button regButton2;
    private Button logButton2;
    //Taylor K, Wong J et al (2019). IoT Enters Home: Automating Household Systems. Nottingham Trent University [unpublished]

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        email3 = findViewById(R.id.registerEmail);
        confirmedEmail = findViewById(R.id.registerEmail2);
        password3 = findViewById(R.id.registerPassword1);
        confirmedPassword = findViewById(R.id.registerPassword2);
        regButton2 = findViewById(R.id.regButton2);
        logButton2 = findViewById(R.id.loginButton2);

        loginAction1();
        registerAction1();
        //Taylor K, Wong J et al (2019). IoT Enters Home: Automating Household Systems. Nottingham Trent University [unpublished]

    }
    public void onStart(){
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if(cUser != null){
            Intent startMainApplication = new Intent(getApplicationContext(), MainActivity.class);
            finish();   //if the user is already logged in they should not be able to go back to this activity, unless they have logged out
            startActivity(startMainApplication);
            //Taylor K, Wong J et al (2019). IoT Enters Home: Automating Household Systems. Nottingham Trent University [unpublished]
        }
    }
    public void createUser(final String userEmail2, String userPassword){
        mAuth.createUserWithEmailAndPassword(userEmail2, userPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent mainPage2 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();   //once the user has registered, they should not be able to return to this page, unless they have logged out
                    startActivity(mainPage2);
                } else {
                    Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                }
                //Taylor K, Wong J et al (2019). IoT Enters Home: Automating Household Systems. Nottingham Trent University [unpublished]

            }
        });
    }

    public void loginAction1(){
        logButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchToLog = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(switchToLog);
                //Taylor K, Wong J et al (2019). IoT Enters Home: Automating Household Systems. Nottingham Trent University [unpublished]
            }
        });

    }

    public void registerAction1(){
        regButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName1 = fName.getText().toString().trim();
                String lName1 = lName.getText().toString().trim();
                String emailAddress = email3.getText().toString().trim();
                String emailAddress1 = confirmedEmail.getText().toString().trim();
                String passCode = password3.getText().toString().trim();
                String passCode1 = confirmedPassword.getText().toString().trim();

                if((TextUtils.isEmpty(fName1)) || (TextUtils.isEmpty(lName1)) ||
                        (TextUtils.isEmpty(emailAddress)) || (TextUtils.isEmpty(emailAddress1)) ||
                        (TextUtils.isEmpty(passCode)) || (TextUtils.isEmpty(passCode1))){
                    Toast.makeText(getApplicationContext(), "The required fields must all be filled", Toast.LENGTH_SHORT).show();
                    return;
                } if (!emailAddress.equals(emailAddress1)){
                    Toast.makeText(getApplicationContext(), "The email addresses must match", Toast.LENGTH_SHORT).show();
                    return;
                } if (!passCode.equals(passCode1)){
                    Toast.makeText(getApplicationContext(), "The passwords much match", Toast.LENGTH_SHORT).show();
                    return;
                }
                createUser(emailAddress, passCode);
                //Taylor K, Wong J et al (2019). IoT Enters Home: Automating Household Systems. Nottingham Trent University [unpublished]
            }
        });
    }
}
