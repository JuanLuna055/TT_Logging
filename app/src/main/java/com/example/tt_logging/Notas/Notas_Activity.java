package com.example.tt_logging.Notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tt_logging.Menu_principla_Activity;
import com.example.tt_logging.R;
import com.example.tt_logging.Receta.Add_Medicina;

import java.util.Calendar;

public class Notas_Activity extends AppCompatActivity {

    private Button agregar_nota;
    private EditText notita;
    private String mensaje;
    private Bundle nota;
    private Calendar fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

    agregar_nota = (Button) findViewById(R.id.guradar_add_nota);
    notita = (EditText) findViewById(R.id.txtMulti_Nota);
    agregar_nota.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Notas_Activity.this, Menu_principla_Activity.class);
            nota = new Bundle();
            fecha = Calendar.getInstance();
            mensaje = notita.getText().toString() + fecha.getTime().toString();
            nota.putSerializable("nota",mensaje);
            intent.putExtras(nota);
            startActivity(intent);
        }
    });
    }
}