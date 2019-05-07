package com.imakeanapp.imapmovies.SignupAndLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.imakeanapp.imapmovies.R;

public class SignUpPage extends AppCompatActivity {

    private EditText name, email, pass, conPass;
    private Button submit, clear, viewData;
    private DatabaseHelper db;
    private EmailValidator emailValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        name = findViewById(R.id.idTV_name);
        email = findViewById(R.id.idTV_email);
        pass = findViewById(R.id.idTV_pass);
        conPass = findViewById(R.id.idTV_conPass);
        submit = findViewById(R.id.idB_submit);
        clear = findViewById(R.id.idB_clear);
        //viewData = findViewById(R.id.idB_view);

        db = new DatabaseHelper(this);

        emailValidator = new EmailValidator();
        email.addTextChangedListener(emailValidator);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isEmpty(name) || isEmpty(email) || isEmpty(pass) || isEmpty(conPass)) {
                    Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_LONG).show();
                    name.setError("Please fill !");
                    email.setError("Please fill !");
                    pass.setError("Please fill !");
                    conPass.setError("Please fill !");
                } else if (db.getData(email.getText().toString().trim()).moveToFirst()) {
                    //boolean i = db.getData(email.getText().toString().trim()).moveToFirst();
                    Toast.makeText(getApplicationContext(), "Email id already in use", Toast.LENGTH_LONG).show();
                    email.setError("Email already in use !");
                }

                // Don't save if the fields do not validate.
                else if (!emailValidator.isValid()) {
                    email.setError("Invalid email !");
                    Toast.makeText(SignUpPage.this, "Not saving personal information: Invalid email", Toast.LENGTH_SHORT).show();
                } else if (!pass.getText().toString().trim().equals(conPass.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "Passwords didn't match", Toast.LENGTH_LONG).show();
                    pass.setError("Password not matching !");
                    conPass.setError("Password not matching !");
                } else {
                    boolean result = db.insertData(name, email, pass);
                    if (result) {
                        Toast.makeText(SignUpPage.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(getApplicationContext(), LoginPage.class);
                        startActivity(moveToLogin);
                    } else
                        Toast.makeText(SignUpPage.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                email.setText("");
                pass.setText("");
                conPass.setText("");
            }
        });

        /*viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = db.fetchData();
                if (cursor.getCount() == 0)
                    Toast.makeText(SignUpPage.this, "No Data Available", Toast.LENGTH_SHORT).show();
                else {
                    String data = "";
                    while (cursor.moveToNext()) {
                        StringBuffer sb = new StringBuffer();
                        sb.append("Name: " + cursor.getString(1));
                        sb.append(" Email: " + cursor.getString(2) + "\n");
                        data = data + sb;
                    }
                    Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }
}
