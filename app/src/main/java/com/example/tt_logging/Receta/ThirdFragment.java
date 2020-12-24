package com.example.tt_logging.Receta;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_logging.R;

import java.util.ArrayList;
import java.util.List;

public class ThirdFragment extends Fragment {

    public List<ListElement>  elements = new ArrayList<>();;
    public ListElement notif;
    public ArrayList<String> notificaciones;
    public RecyclerView recyclerView_receta;
    private ImageButton btn_agregar;
    private TextView titular;
    private View vista;

    private int i;

    public ThirdFragment (){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("Nos encontramos dentro de OnCreate: ");
        if(getArguments() != null){
            try {

                notificaciones = (ArrayList<String>) getArguments().getSerializable("notificacion");
                //System.out.println("/*/*/*/*/*/ Medicamento: "+notif.getMedicamento()+" Recomendacion: "+notif.getRecordatorio());
                //agregar_notificacion(notif);
                mostrarArraylist(notificaciones);

            }catch (Exception e){
                System.out.println("Error en: "+e.getMessage());
            }

        }

    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("-------Se encuentra en pausa");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Tercer Fragmento---- Essta en resume");

    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("Tercer Fragmento---- Esta en STOP");
        onDestroyView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("Tercer Fragmento---- Se destruyo el view");
    }

    @Override
    public void onStart() {
        super.onStart();

       // titular.setText("Estamos en pausa: "+i++);
        System.out.println("Tercer Fragmento---- Esta en START");
        mostrarTratamiento();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("Tercer Fragmento Nos encontramos en onDetach ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Tercer Fragmento Nos encontramos en onDestroy ");
        elements.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_third, container, false);
        recyclerView_receta =vista.findViewById(R.id.listRecycleView);
        titular= vista.findViewById(R.id.textView_titular);
        btn_agregar = vista.findViewById(R.id.agregar_receta);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agregar_items = new Intent(getContext(), Add_Alarma.class);
                startActivity(agregar_items);

            }
        });

        if (savedInstanceState != null){
            System.out.println("Tenemos la info");
                titular.setText("Agregamos"+ getArguments().getString("medicina","hola"));

        }else {
            System.out.println("*+*+*+*+*+* Es nuelo");

        }


       // cargarLista();
        mostrarTratamiento();
        return vista;
    }

    public void cargarLista(){
        //elements.add(new ListElement("#775447","Naproxeno","Tomar con dos vasos de agua","Cantidad:",10));
        //elements.add(new ListElement("#775447","Naproxeno","Tomar con dos vasos de agua","Cantidad:",20));
    }

    public void agregar_notificacion(ListElement item){
        elements.add(new ListElement(item));
    }
/*
    public void agregar_items(String color,String med, String recor, String status,int cant){
        elements.add(new ListElement(color,med,recor,status,cant));
    }
*/
    private void mostrarTratamiento(){
        recyclerView_receta.setLayoutManager(new LinearLayoutManager(getContext()));
        ListAdapter listAdapter = new ListAdapter(elements, getContext());
        recyclerView_receta.setHasFixedSize(true);
        recyclerView_receta.setAdapter(listAdapter);
        listAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String medicamento = elements.get(recyclerView_receta.getChildAdapterPosition(view)).getMedicamento();
                Toast.makeText(getContext(), "Medicamento: "+medicamento, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private List<ListElement> getList (){
        return elements;
    }

    private void setElements (ListElement medicamentos){
        if(medicamentos != null){
            Toast.makeText(getContext(), "Se agrego el medicamento :)", Toast.LENGTH_SHORT);
            elements.add(medicamentos);
        }

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