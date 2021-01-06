package com.example.tt_logging.Agregar_Dosis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tt_logging.R;
import com.example.tt_logging.Receta.ListAdapter;
import com.example.tt_logging.Receta.ListElement;
import com.example.tt_logging.Receta.repeticiones.Descripcion_Medicina;

import java.util.ArrayList;
import java.util.List;

public class Add_Dosis_Activity extends AppCompatActivity {

    private ArrayList<String> ids_medicamentos = new ArrayList<>();
    public List<ListElement> elements = new ArrayList<>();
    public RecyclerView recyclerView_dosis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__dosis);

        recyclerView_dosis = findViewById(R.id.listRecycleView_Adddosis);
        guardarArrayNotificaciones();
        mostrarArraylist(ids_medicamentos);
        mostrarTratamiento();

    }


    private void mostrarTratamiento(){
        recyclerView_dosis.setLayoutManager(new LinearLayoutManager(this));
        ListAdapterDosis listAdapterDosis = new ListAdapterDosis(elements, this, new ListAdapterDosis.OnItemClickListener() {
            @Override
            public void OnItemClick(ListElement item) {
                moveToDosis(item);
            }
        });
        recyclerView_dosis.setHasFixedSize(true);
        recyclerView_dosis.setAdapter(listAdapterDosis);
    }

    public void moveToDosis(ListElement item){
        Intent intent = new Intent(this, Ver_Add_Cantidad_Activity.class);
        intent.putExtra("medicina",item);
        startActivity(intent);
    }

    public void guardarArrayNotificaciones(){
        SharedPreferences datos = getSharedPreferences("notificaciones",MODE_PRIVATE);
        System.out.println("Leemos de Shared...");
        int i=0;
        do{
            if(datos.getString("id_med"+i,"error") != "error"){
                System.out.println("Se pudo leer el id_med"+i);
                ids_medicamentos.add(datos.getString("id_med"+i,"Sin medicamento"));
            }
            System.out.println("Valor de i: "+i);
            i++;
        }while (i<10);
    }

    private void mostrarArraylist(ArrayList<String> medicinas){
        int i=0;
        if (medicinas.isEmpty()){
            System.out.println("No hay ninguna notificacion :(");
        }else{
            for (i=0; i<medicinas.size();i++){
                System.out.println(i+"Elemento: "+medicinas.get(i));
                elements.add(new ListElement(medicinas.get(i)));
            }
        }
    }
}