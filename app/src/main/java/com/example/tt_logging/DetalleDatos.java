package com.example.tt_logging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DetalleDatos extends AppCompatActivity {
    List<DatoMedico> datos;
    private RecyclerView listaDatos;

    ArrayList<String> ids_datosmedicos;
    List<DatoMedico> lstDatos;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_datos);
        inicializarAdaptador();
    }

    public void inicializarAdaptador() {
        datos = new ArrayList<>();
        /*
        datos.add(new DatoMedico("Peso", 80, "Kg", "6/1/2021"));
        datos.add(new DatoMedico("Gluscosa", 36, "unidades", "6/1/2021"));
        datos.add(new DatoMedico("Presion", 15, "psi", "6/1/2021"));*/

        DatoMedicoAdaptador adaptador = new DatoMedicoAdaptador(datos, this, new DatoMedicoAdaptador.OnItemClickListener() {
            @Override
            public void onItemClick(DatoMedico item) {
                moveToDescription(item);
            }
        });
        listaDatos = (RecyclerView)findViewById(R.id.rvDatosMedicos);
        listaDatos.setHasFixedSize(true);
        listaDatos.setLayoutManager(new LinearLayoutManager(this));
        listaDatos.setAdapter(adaptador);
    }

    private void moveToDescription(DatoMedico item) {
        Intent intent = new Intent(this, SeguimientoDato.class);
        intent.putExtra("Dato", item);
        startActivity(intent);
    }
}