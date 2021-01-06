package com.example.tt_logging.Receta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;


import com.example.tt_logging.Agregar_Dosis.Add_Dosis_Activity;
import com.example.tt_logging.Notas.Notas_Activity;
import com.example.tt_logging.Persona_Cuidado.Add_PersonasHelp_Activity;
import com.example.tt_logging.R;

public class Add_Alarma extends AppCompatActivity {


    private CardView add_medicina,add_nota,add_personaCuidad,addCantidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__alarma);


        add_nota = (CardView)findViewById(R.id.add_nota);
        add_medicina = (CardView)findViewById(R.id.add_medic);
        add_personaCuidad = findViewById(R.id.add_persona);
        addCantidad = findViewById(R.id.add_dosis);
        add_medicina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"agregar medicina",Toast.LENGTH_SHORT).show();
                startActivity(new Intent (Add_Alarma.this,Add_Medicina.class));
                finish();
            }
        });

        add_nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (Add_Alarma.this, Notas_Activity.class));
                finish();
            }
        });

        add_personaCuidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (Add_Alarma.this, Add_PersonasHelp_Activity.class));
                finish();
            }
        });

        addCantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Add_Alarma.this, Add_Dosis_Activity.class));
            }
        });
    }
}
