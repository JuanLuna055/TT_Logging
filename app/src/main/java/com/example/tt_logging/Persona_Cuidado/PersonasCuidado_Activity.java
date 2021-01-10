package com.example.tt_logging.Persona_Cuidado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tt_logging.Persona_Cuidado.ListAdapterPersona;
import com.example.tt_logging.Persona_Cuidado.ListPersonaCuidado;
import com.example.tt_logging.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PersonasCuidado_Activity extends AppCompatActivity {

    ListAdapterPersona adapterPersona;
    RecyclerView recyclerViewPersonas;
    ArrayList<String> ids_personas;
    List <ListPersonaCuidado> listaPersonas;
    private Calendar inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas_cuidado);

        recyclerViewPersonas = findViewById(R.id.listRecycleViewPersona);
        listaPersonas = new ArrayList<>();
        inicio = Calendar.getInstance();
        ids_personas = new ArrayList<>();

        leer_shared_personaHelp();

        cargarArrayListPersona();

        mostrarPerosnasHelp();

        mostrarDataPersonas();

        //cargarListaPersonas();
       // mostrarDataPersonas();
    }

    private void cargarListaPersonas() {
       listaPersonas.add(new ListPersonaCuidado("#000000","Juan Luna","Hermano",""+inicio.getTime().toString(),"5514246059"));
        listaPersonas.add(new ListPersonaCuidado("#000000","Sandra Alvarez","Persona de cuidado",""+inicio.getTime().toString(),"5514246059"));
        listaPersonas.add(new ListPersonaCuidado("#000000","Mario Casasola","Familiar",""+inicio.getTime().toString(),"5514246059"));
        listaPersonas.add(new ListPersonaCuidado("#000000","Carmen Marales","Esposa",""+inicio.getTime().toString(),"5514246059"));

    }

    private void mostrarDataPersonas(){
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPersonas.setHasFixedSize(true);
        adapterPersona = new ListAdapterPersona(listaPersonas,this);
        recyclerViewPersonas.setAdapter(adapterPersona);

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

    private void leer_shared_personaHelp(){
        SharedPreferences datos = getSharedPreferences("persona_help",MODE_PRIVATE);
        System.out.println("Leemos de Shared...");
        int i=0;
        do{
            if(datos.getString("id_personaHelp"+i,"error") != "error"){
                System.out.println("Se pudo leer el id_persona"+i);
            }
            i++;
        }while (i<10);
    }

    private void cargarArrayListPersona(){
        //Aqui cargamos los String de ids de las personas
        SharedPreferences datos = getSharedPreferences("persona_help",MODE_PRIVATE);
        int i=0;
        do{
            if(datos.getString("id_personaHelp"+i,"error") != "error"){
                System.out.println("Se pudo leer el id_personaHelp"+i);
                ids_personas.add(datos.getString("id_personaHelp"+i,"Sin persona"));
            }
            //System.out.println("Valor de i: "+i);
            i++;
        }while (i<10);
    }

    private void mostrarPerosnasHelp(){
        int i=0;
        if (ids_personas.isEmpty()){
            System.out.println("No hay ninguna medicina :(");
        }else{
            for (i=0; i<ids_personas.size();i++){
                System.out.println(i+"Elemento Persona: "+ids_personas.get(i));
                listaPersonas.add(new ListPersonaCuidado(ids_personas.get(i)));
            }
        }
    }

}