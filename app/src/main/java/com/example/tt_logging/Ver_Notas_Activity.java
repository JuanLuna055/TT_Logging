package com.example.tt_logging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tt_logging.Notas.Notas;
import com.example.tt_logging.Persona_Cuidado.ListAdapterPersona;
import com.example.tt_logging.Persona_Cuidado.ListPersonaCuidado;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Ver_Notas_Activity extends AppCompatActivity {

    RecyclerView recyclerViewNotas;
    List<Notas> listanotas;
    AdapterNotas adapterNotas;
    private Calendar inicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__notas);
        recyclerViewNotas = findViewById(R.id.listRecycleViewNotas);
        listanotas = new ArrayList<>();
        inicio = Calendar.getInstance();

        cargarNotas();
        mostrarDataPersonas();
    }

    private void cargarNotas(){
        listanotas.add(new Notas("Hoy me senti cansado.",inicio.getTime().toString()));
        listanotas.add(new Notas("Tuve pequeños mareos por la mañana, nada grave pero senti \n el malestar.",inicio.getTime().toString()));
        listanotas.add(new Notas("Me seinto mucho mejor he tenido mas ganas de hacer las cosas..",inicio.getTime().toString()));
        listanotas.add(new Notas("Todo va mejorando puedo tener mucha mas movilidad, noto que la \n medicina esta ayudando en diferentes puntos..",inicio.getTime().toString()));
        listanotas.add(new Notas("Hoy me senti cansado.",inicio.getTime().toString()));
    }

    private void mostrarDataPersonas(){
        recyclerViewNotas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotas.setHasFixedSize(true);
        adapterNotas = new AdapterNotas(listanotas,this);
        recyclerViewNotas.setAdapter(adapterNotas);

    }

}