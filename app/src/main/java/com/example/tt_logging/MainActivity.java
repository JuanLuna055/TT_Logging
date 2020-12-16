package com.example.tt_logging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Metodo Boton para regsitro
    public void registro(View v){
        Intent frame_registro = new Intent(this, registro.class);
        startActivity(frame_registro);
    }
    // Metodo Boton iniciar sesion
    public void iniciar_sesion(View view){
        //Toast.makeText(this,"EIniciar sesion",Toast.LENGTH_SHORT).show();
        Intent intent_session = new Intent(this, LoginActivity.class);
        startActivity(intent_session);
    }


}