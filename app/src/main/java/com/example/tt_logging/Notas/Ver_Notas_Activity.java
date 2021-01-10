package com.example.tt_logging.Notas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tt_logging.Notas.AdapterNotas;
import com.example.tt_logging.Notas.Notas;
import com.example.tt_logging.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Ver_Notas_Activity extends AppCompatActivity {

    RecyclerView recyclerViewNotas;
    List<Notas> listanotas;
    ArrayList<String> listaids;
    AdapterNotas adapterNotas;
    private Calendar inicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__notas);
        recyclerViewNotas = findViewById(R.id.listRecycleViewNotas);
        listanotas = new ArrayList<>();
        inicio = Calendar.getInstance();
        listaids = new ArrayList<>();

        leer_shared_Nota();
        cargarArrayListNota();
        agregar_notas_list();
        mostrarDataNotas();
    }

    private void cargarNotas(){
        listanotas.add(new Notas("Hoy me senti cansado.",inicio.getTime().toString()));
        listanotas.add(new Notas("Tuve pequeños mareos por la mañana, nada grave pero senti \n el malestar.",inicio.getTime().toString()));
        listanotas.add(new Notas("Me seinto mucho mejor he tenido mas ganas de hacer las cosas..",inicio.getTime().toString()));
        listanotas.add(new Notas("Todo va mejorando puedo tener mucha mas movilidad, noto que la \n medicina esta ayudando en diferentes puntos..",inicio.getTime().toString()));
        listanotas.add(new Notas("Hoy me senti cansado.",inicio.getTime().toString()));
    }

    private void mostrarDataNotas(){
        recyclerViewNotas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotas.setHasFixedSize(true);
        adapterNotas = new AdapterNotas(listanotas,this);
        recyclerViewNotas.setAdapter(adapterNotas);

    }

    private void guardar_shared_PersonaHelp(String persona){
        System.out.println("Guardaremos la nota");
        SharedPreferences datos = getSharedPreferences("persona_help",MODE_PRIVATE);
        SharedPreferences.Editor miEditor = datos.edit();
        boolean ans=true;
        int i=0;
        do {

            if(datos.getString("id_personaHelp"+i,"error") == "error" ){
                System.out.println("Se guardo el no: id_personaHelp"+i);
                miEditor.putString("id_personaHelp"+i,persona);
                miEditor.apply();
                break;
            }
            System.out.println("--valor de i: "+i);
            i++;
        }while (i<10);
    }

    private void leer_shared_Nota(){
        SharedPreferences datos = getSharedPreferences("notas",MODE_PRIVATE);
        System.out.println("Leemos de Shared...");
        int i=0;
        do{
            if(datos.getString("id_nota"+i,"error") != "error"){
                System.out.println("Se pudo leer el id_nota"+i);

            }
            i++;
        }while (i<10);
    }

    private void cargarArrayListNota(){
        //Aqui cargamos los String de ids de las personas
        SharedPreferences datos = getSharedPreferences("notas",MODE_PRIVATE);
        int i=0;
        do{
            if(datos.getString("id_nota"+i,"error") != "error"){
                System.out.println("Se pudo leer el id_nota"+i);
                listaids.add(datos.getString("id_nota"+i,"Sin nota"));
            }
            //System.out.println("Valor de i: "+i);
            i++;
        }while (i<10);
    }

    private void agregar_notas_list(){
        int i=0;
        if (listaids.isEmpty()){
                System.out.println("No hay ninguna nota :(");
        }else{
            for (i=0; i<listaids.size();i++){
                System.out.println(i+"Elemento Persona: "+listaids.get(i));
                listanotas.add(new Notas(listaids.get(i)));
            }
        }
    }

    private void eliminarPreferencesNotas(){
        SharedPreferences datos = getSharedPreferences("notas",MODE_PRIVATE);
        SharedPreferences.Editor miEditor = datos.edit();
        miEditor.clear();
        miEditor.apply();

    }

}