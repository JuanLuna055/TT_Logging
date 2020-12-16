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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class registro extends AppCompatActivity implements View.OnClickListener {
    private EditText TextEmail;
    private EditText TextPassword;
    private EditText Textnombre; // No la he ocupado
    private EditText Textfecha; // NO la he ocupado
    private Button btnRegistrar;
    private Button bntloging;
    private ProgressDialog progressDialog;

    //variables de los datos a registar
    private String name= "";
    private String email= "";
    private String password= "";
    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Referenciamos los views
        TextEmail = (EditText) findViewById(R.id.TxtEmail);
        TextPassword = (EditText) findViewById(R.id.TxtPassword);
        Textnombre =(EditText) findViewById(R.id.Nombre);
        btnRegistrar = (Button) findViewById(R.id.botonRegistrar);
        bntloging = (Button) findViewById(R.id.loging);

        progressDialog = new ProgressDialog(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //attaching listener to button
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = Textnombre.getText().toString();
                email =TextEmail.getText().toString();
                password = TextPassword.getText().toString();


                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){

                    if(password.length() >= 6){
                        registerUser();
                    }else{
                        Toast.makeText(registro.this, "La contraseña debe tener al menos 6 caracteres.",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(registro.this, "Debe ingresar completo los campos.",Toast.LENGTH_LONG).show();
                }
            }
        });


        // Ya tengo cuenta
        bntloging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registro.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void registerUser() {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String,Object> map = new HashMap<>();
                    map.put("name",name);
                    map.put("email",email);
                    map.put("password",password);
                    String id = firebaseAuth.getCurrentUser().getUid();

                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()){
                                startActivity(new Intent(registro.this,Perfil_Usuario.class));
                                finish();
                            }else{
                                Toast.makeText(registro.this, "No se pudo enviar los datos correctamente",Toast.LENGTH_SHORT);
                            }
                        }
                    });
                }else{
                    Toast.makeText(registro.this, "No se pudo registrar usuario.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // metodo para regresar a poratda
    public void poratda (View v){
        Intent portada = new Intent(this, MainActivity.class);
        startActivity(portada);
    }

    private void registrarUsuario(){

        //Obtenemos el email y la contraseña desde las cajas de texto
        String email = TextEmail.getText().toString().trim();
        String password  = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //Creamos un nuevo usuario ********
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){

                            Toast.makeText(registro.this,"Se ha registrado el usuario con el email: "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                        }else{
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                            Toast.makeText(registro.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(registro.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                        }
                    }
                    progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view) {

        registrarUsuario();
    }
}