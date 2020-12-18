package com.example.tt_logging.Receta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_logging.Add_Tratamiento;
import com.example.tt_logging.R;

public class Add_Alarma extends AppCompatActivity {


    private CardView add_medicina;
    private CardView add_tratamiento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__alarma);



        add_medicina = (CardView)findViewById(R.id.add_medic);

        add_medicina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"agregar medicina",Toast.LENGTH_SHORT).show();
                startActivity(new Intent (Add_Alarma.this,Add_Medicina.class));
                finish();
            }
        });

        //Boton de agregar tratamiento
        add_tratamiento = (CardView)findViewById(R.id.add_tratamiento);

        add_tratamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Agregar Tratamiento",Toast.LENGTH_SHORT).show();
                startActivity(new Intent (Add_Alarma.this, Add_Tratamiento.class));
                finish();
            }
        });

    }
}
