package com.example.tt_logging;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tt_logging.Receta.ListElement;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {
    private List<ListElement> medicamentos = new ArrayList<>();
    private RecyclerView listaMedicamentos;
    public ArrayList<String> notificaciones;
    private int i;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            try {

                notificaciones = (ArrayList<String>) getArguments().getSerializable("notificacion");
                //System.out.println("/*/*/*/*/*/ Medicamento: "+notif.getMedicamento()+" Recomendacion: "+notif.getRecordatorio());
                //agregar_notificacion(notif);
                mostrarArraylist(notificaciones);
                for (int i=0;i<medicamentos.size();i++){
                    System.out.println("Id med "+i+"*/ "+medicamentos.get(i).getId_medicamento());
                }

            }catch (Exception e){
                System.out.println("Error en: "+e.getMessage());
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        medicamentos.clear();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        listaMedicamentos = (RecyclerView) view.findViewById(R.id.rvMedicamentos);


        inicializarAdaptador();
        return view;
    }

    public void inicializarAdaptador() {
        listaMedicamentos.setLayoutManager(new LinearLayoutManager(getContext()));
        MedicamentoAdaptador adaptador = new MedicamentoAdaptador(medicamentos, getContext(), new MedicamentoAdaptador.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                moveToDescription(item);
            }
        });
        listaMedicamentos.setHasFixedSize(true);
        listaMedicamentos.setAdapter(adaptador);
    }

    private void moveToDescription(ListElement item) {
        Intent intent = new Intent(getContext(), DetalleMedicamento.class);
        intent.putExtra("Medicamento", item);
        startActivity(intent);
    }


    private void mostrarArraylist(ArrayList<String> medicinas){
        int i=0;
        if (medicinas.isEmpty()){
            System.out.println("No hay ninguna notificacion :(");
        }else{
            for (i=0; i<medicinas.size();i++){
                System.out.println(i+"Elemento: "+medicinas.get(i));
                medicamentos.add(new ListElement(medicinas.get(i)));
            }
        }
    }
}