package com.example.tt_logging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btn_olvidecontra;
    private ProgressDialog progressDialog;
    private EditText TextEmail;
    private EditText TextPassword;

    private String email="";
    private  String password="";
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        TextEmail = (EditText) findViewById(R.id.correo);
        TextPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.iniciar_sesion);
        btn_olvidecontra = (Button) findViewById(R.id.recuperar_contraseña);

        progressDialog = new ProgressDialog(this);

        btn_olvidecontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,Reset_ContrasenaActivity2.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    System.out.println("****El email: "+TextEmail.getText().toString());
                    email = TextEmail.getText().toString();
                    password = TextPassword.getText().toString();
                    System.out.println("****El password : "+TextPassword.getText().toString());
                }catch (Exception e){
                    System.out.println("*******Error"+e.getMessage());
                }



                if (!email.isEmpty() && !password.isEmpty()) {
                    loginUser();
                } else {
                    Toast.makeText(LoginActivity.this, "Completar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUser() {
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                         startActivity(new Intent(LoginActivity.this,Perfil_Usuario.class));
                         finish();
                    }else {
                        Toast.makeText(LoginActivity.this,"Comprobar email y/o contraseña",Toast.LENGTH_SHORT);
                    }
                }
            });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this, Perfil_Usuario.class));
            finish();
        }
    }


}