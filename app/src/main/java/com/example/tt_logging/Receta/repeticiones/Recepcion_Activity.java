package com.example.tt_logging.Receta.repeticiones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tt_logging.R;

public class Recepcion_Activity extends AppCompatActivity {

    TextView medicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepcion);
        medicamento = findViewById(R.id.tag_recepcion);

        Bundle objeto = getIntent().getExtras();
        String name = (String) objeto.get("medicina");

        medicamento.setText("TAG: "+name);
    }
}