package com.example.tt_logging.Alarmas;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_logging.R;
import com.example.tt_logging.Receta.Add_Alarma;
import com.example.tt_logging.Receta.ListAdapter;
import com.example.tt_logging.Receta.ListElement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class FirstFragment extends Fragment {

    public List<ListElement> elements = new ArrayList<>();
    private List<ListElement> alarmas = new ArrayList<>();
    public ListElement notif;
    public ArrayList<String> notificaciones;
    public RecyclerView recyclerView_alarma;
    private ImageButton btn_agregar;
    private TextView titular;
    private View vista;
    private Calendar actual = Calendar.getInstance();
    private ArrayList<String> tags= new ArrayList<>();

    private int i;

    public FirstFragment (){

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
        //escoger_Alarma();
        mostrarAlarma();
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
        alarmas.clear();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView_alarma =vista.findViewById(R.id.listRecycleViewAlarma);
        titular= vista.findViewById(R.id.textView_titular);



        if (savedInstanceState != null){
            titular.setText("Agregamos"+ getArguments().getString("medicina","hola"));

        }else {
            System.out.println("*+*+*+*+*+* Es nuelo");

        }


        // cargarLista();
        selec_Alarm();
        mostrarAlarma();
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
    private void mostrarAlarma(){
        recyclerView_alarma.setLayoutManager(new LinearLayoutManager(getContext()));
        ListAdapterAlarma listAdapter = new ListAdapterAlarma(alarmas, getContext());
        recyclerView_alarma.setHasFixedSize(true);
        recyclerView_alarma.setAdapter(listAdapter);
        listAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String medicamento = alarmas.get(recyclerView_alarma.getChildAdapterPosition(view)).getMedicamento();
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

    private Date sumarRestarDiasFecha(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_MONTH, dias);  // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
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

    private void escoger_Alarma(){
        int i=0;
        int bandera =1;
        Calendar aux = Calendar.getInstance();
        boolean acept = false;
        if (elements!=null){
            try{
                do{
                    if(elements.get(i).getInicio().get(Calendar.MONTH) == actual.get(Calendar.MONTH)
                            && actual.get(Calendar.MONTH) == elements.get(i).getTermina().get(Calendar.MONTH) ){
                        System.out.println("Analizar: "+elements.get(i).getMedicamento()+" Inicia:"+ elements.get(i).getInicio().getTime().toString()
                                +" Termina: "+elements.get(i).getTermina().getTime().toString());
                        //Es el mismo mes de inicio de la alarma
                        if(actual.get(Calendar.DAY_OF_MONTH) >= elements.get(i).getInicio().get(Calendar.DAY_OF_MONTH) &&
                                actual.get(Calendar.DAY_OF_MONTH) <= elements.get(i).getTermina().get(Calendar.DAY_OF_MONTH)){
                            int x = elements.get(i).getInicio().get(Calendar.DAY_OF_MONTH);
                            int y = elements.get(i).getTermina().get(Calendar.DAY_OF_MONTH);
                                bandera=1;
                            do{
                                if(x == actual.get(Calendar.DAY_OF_MONTH)){
                                    System.out.println("Se acepto: "+elements.get(i).getMedicamento()+" Inicia:"+ elements.get(i).getInicio().getTime().toString()
                                            +" Termina: "+elements.get(i).getTermina().getTime().toString());
                                    alarmas.add(elements.get(i));
                                    i++;
                                    bandera = 0;
                                }else{
                                    x = x + elements.get(i).getFrecuencia()+1;
                                }
                            }while(x <= y && bandera==1);
                            //Es el mismo mes pero esta en Mes Termina
                        }
                    }else if(actual.get(Calendar.MONTH) >= elements.get(i).getInicio().get(Calendar.MONTH)
                            && actual.get(Calendar.MONTH) <= elements.get(i).getTermina().get(Calendar.MONTH)){
                        System.out.println("Analizar SEGUNDO IF: "+elements.get(i).getMedicamento()+" Inicia:"+ elements.get(i).getInicio().getTime().toString()
                                +" Termina: "+elements.get(i).getTermina().getTime().toString());
                        aux = elements.get(i).getInicio();
                        int x = elements.get(i).getTermina().get(Calendar.DAY_OF_MONTH);
                        int y = elements.get(i).getTermina().get(Calendar.MONTH);
                        do{
                            if(aux.get(Calendar.DAY_OF_MONTH) == actual.get(Calendar.DAY_OF_MONTH) && aux.get(Calendar.MONTH) == actual.get(Calendar.MONTH)){
                                alarmas.add(elements.get(i));
                                i++;
                                break;
                            }else {
                                aux.setTime(sumarRestarDiasFecha(aux.getTime(),elements.get(i).getFrecuencia()+1));
                            }

                        }while(aux.get(Calendar.DAY_OF_MONTH) <= x && aux.get(Calendar.MONTH) <= y );
                    }else{
                        i++;
                    }
                }while(i <= elements.size());
            }catch (Exception e){
                System.out.println(e);
            }

        }



    }

    private void selec_Alarm(){
        int i;
        for(i=0; i< elements.size(); i++){
            generar_alarma(elements.get(i));
        }
    }

    public void generar_alarma (ListElement medi){
        Calendar fecha = medi.getInicio();
        Calendar actual = Calendar.getInstance();
        System.out.println("horas recuperadas: "+medi.getHoras_rec());
        String tag_comp;
        String medicamento =medi.getMedicamento();
        int termina = medi.getTermina().get(Calendar.DAY_OF_MONTH) - medi.getInicio().get(Calendar.DAY_OF_MONTH);
        if(termina < 0){
            termina = -termina;
        }
        int j=0;
        String tag;
        Long AlerTime;
        int frecu = medi.getFrecuencia();
        j=0;
        do{
            tag = medicamento+"a"+fecha.get(Calendar.YEAR)+"m"+fecha.get(Calendar.MONTH)+"d"+fecha.get(Calendar.DAY_OF_MONTH);
            tags.add(tag);
            System.out.println("ID: "+tag);
            if( medi.getFrecuencia() == 7){
                fecha.setTime(sumarRestarMes(fecha.getTime(),1));
            }else{
                fecha.setTime(sumarRestarDiasFecha(fecha.getTime(),medi.getFrecuencia()+1));
            }
            if (medi.getFrecuencia() == 0){
                j++;
            }else{
                j=j+frecu+1;
            }

        }while(j <= termina);

        tag_comp = medi.getMedicamento()+"a"+actual.get(Calendar.YEAR)+"m"+actual.get(Calendar.MONTH)+"d"+actual.get(Calendar.DAY_OF_MONTH);
        System.out.println("tag a comparar "+tag_comp);
        for (int i=0; i<tags.size(); i++){
            if (tag_comp.equals(tags.get(i))){
                System.out.println("Se escogio el medicamento con tag "+tag_comp);
                alarmas.add(medi);
            }
        }
        tags.clear();
    }

    public Date sumarRestarMes(Date fecha, int mes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MONTH, mes);  // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }
}