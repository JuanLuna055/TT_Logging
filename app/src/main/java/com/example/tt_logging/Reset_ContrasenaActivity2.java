package com.example.tt_logging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset_ContrasenaActivity2 extends AppCompatActivity {

    private EditText mEdit_email;
    private Button mButton_contra;

    private String email;
    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset__contrasena2);

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        mEdit_email = (EditText) findViewById(R.id.EditText_reset_correo);
        mButton_contra = (Button) findViewById(R.id.resetear_contra);

        mButton_contra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEdit_email.getText().toString();

                if(!email.isEmpty()){
                    mDialog.setMessage("Espere un momento...");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    resetPassword();
                }else{
                    Toast.makeText(getApplicationContext(), "Debe de ingresar el email", Toast.LENGTH_SHORT).show();
                }
                mDialog.dismiss();
            }
        });
    }

    private void resetPassword(){
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Se ha enviado un correo para restablecer su contraseña.",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"No se pudo encontrar la direccion de correo ingresada.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}