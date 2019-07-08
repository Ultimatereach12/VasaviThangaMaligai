package com.ultimatereach.vasavithangamaligai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button login_btn;
    EditText mail, pass;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mail = (EditText) findViewById(R.id.mail_id);
        pass = (EditText) findViewById(R.id.pass_word);
        login_btn = (Button) findViewById(R.id.login);

        sp=getSharedPreferences("login",MODE_PRIVATE);

        if(sp.contains("username") && sp.contains("password")){
            startActivity(new Intent(Login.this, Dashboard.class));
            finish();
        }

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mailid = mail.getText().toString();
                final String password = pass.getText().toString();

                SharedPreferences.Editor e=sp.edit();
                e.putString("username",mailid);
                e.putString("password",password);
                e.apply();

                if (mailid.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill Mail Id and try again", Toast.LENGTH_LONG).show();
                }
                else if (password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please fill Password and try again", Toast.LENGTH_LONG).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(mailid, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            SharedPreferences.Editor e=sp.edit();
                            e.putString("username",mailid);
                            e.putString("password",password);
                            e.apply();
                            Intent i = new Intent(Login.this, Dashboard.class);
                            startActivity(i);
                            Toast.makeText(Login.this, "Login success", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, "Mail ID and Password mismatch", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}