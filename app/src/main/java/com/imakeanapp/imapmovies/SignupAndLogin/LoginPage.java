package com.imakeanapp.imapmovies.SignupAndLogin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.imakeanapp.imapmovies.MainActivity;
import com.imakeanapp.imapmovies.R;

public class LoginPage extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private Button loginSubmit, loginClear;
    private DatabaseHelper dbL;
    private EmailValidator emailValidatorL;
    private TextView moveToSignUpPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginEmail = findViewById(R.id.idTV_LoginEmail);
        loginPassword = findViewById(R.id.idTV_LoginPass);
        loginSubmit = findViewById(R.id.idB_LoginSubmit);
        loginClear = findViewById(R.id.idB_LoginClear);
        moveToSignUpPage = findViewById(R.id.id_TV_activityMain_CREATEONE);


        dbL = new DatabaseHelper(this);

        emailValidatorL = new EmailValidator();
        loginEmail.addTextChangedListener(emailValidatorL);


        loginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(loginEmail) || isEmpty(loginPassword)) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                    loginEmail.setError("Please fill !");
                    loginPassword.setError("Please fill !");
                } else if (!emailValidatorL.isValid()) {
                    loginEmail.setError("Invalid email !");
                    Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT).show();
                } else {
                    String s = loginEmail.getText().toString().trim();
                    Cursor cursor = dbL.checkForData(s);
                    if (cursor.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "Account does not exists", Toast.LENGTH_SHORT).show();
                    } else {
                        cursor.moveToNext();
                        String pass = cursor.getString(3);
                        if (loginPassword.getText().toString().equals(pass)) {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        moveToSignUpPage.setClickable(true);
        moveToSignUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registration = new Intent(LoginPage.this, SignUpPage.class);
                startActivity(registration);
            }
        });
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }
}
