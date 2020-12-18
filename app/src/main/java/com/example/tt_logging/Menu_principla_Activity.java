package com.example.tt_logging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.impl.model.Preference;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tt_logging.Receta.ListElement;
import com.example.tt_logging.Receta.ThirdFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Menu_principla_Activity extends AppCompatActivity {
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    CuartoFragment cuartofragment = new CuartoFragment();
    public List<ListElement> notificaciones = new ArrayList<>();;
    private boolean permiso;
    private String hora;
    private Bundle notificacion;
    private SharedPreferences preferences;
    ArrayList<String> ids_medicamentos = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principla_);
        System.out.println("Estamos en onCreate Activiti_principal");
        Bundle objeto = getIntent().getExtras();
        ListElement medicamento = null;
        if(objeto != null){
            // llegada de horario
            hora =  objeto.getString("horario");
            System.out.println("|||°°°°°°°°Horario: "+ hora);
            medicamento = (ListElement) objeto.getSerializable("medicina");
            notificaciones.add(medicamento);
            System.out.println("|||||| El dato resivido es el siguiente:");
            System.out.println("Medicamento: "+medicamento.getMedicamento()+" Status: "+medicamento.getStatus()+" Recordatorio: "+medicamento.getRecordatorio());
            //loadFragment(thirdFragment);
           // preferences = getSharedPreferences("medicamento", Context.MODE_PRIVATE);
           // SharedPreferences.Editor obj = preferences.edit();

            //escribirFicheroMemoriaInterna(medicamento.getId_medicamento());
            //leerFicheroMemoriaInterna();

            guardar_shared(medicamento.getId_medicamento());
            leer_shared();
            mostrarMedicamentos(ids_medicamentos);
        }
        mostrarMedicamentos(ids_medicamentos);
        leer_shared();
        mostrarMedicamentos(ids_medicamentos);
        eliminarPreferences();
        leer_shared();
        notificacion = new Bundle();
        notificacion.putSerializable("notificacion",medicamento);
        thirdFragment.setArguments(notificacion);

        BottomNavigationView navegation = findViewById(R.id.bottom_navegation);
         navegation.setOnNavigationItemSelectedListener(mOnNavegationItemSelectedListener);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavegationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    startActivity(new Intent(getApplicationContext(),FirstFragment.class));
                    return true;
                case R.id.secondFragment:
                    loadFragment(secondFragment);
                    return true;
                case R.id.thirdFragment:
                    loadFragment(thirdFragment);
                    return true;
                case R.id.cuatroFragment:
                    loadFragment(cuartofragment);
                    return true;

            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_conteiner,fragment);
        transaction.commit();
    }

    private void escribirFicheroMemoriaInterna(String medicina){
        OutputStreamWriter escritor=null;
        try
        {
            escritor=new OutputStreamWriter(openFileOutput("receta.txt", Context.MODE_PRIVATE));
            escritor.write(medicina+"\n");
        }
        catch (Exception ex)
        {
            Log.e("ivan", "Error al escribir fichero a memoria interna");
        }
        finally
        {
            try {
                if(escritor!=null)
                    escritor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void leerFicheroMemoriaInterna() {
        InputStreamReader flujo=null;
        BufferedReader lector=null;
        try
        {
            flujo= new InputStreamReader(openFileInput("receta.txt"));
            lector= new BufferedReader(flujo);
            String texto = lector.readLine();
            while(texto!=null)
            {
                System.out.print("-R:"+texto);
                texto = lector.readLine();
            }
        }
        catch (Exception ex)
        {
            Log.e("ivan", "Error al leer fichero desde memoria interna");
        }
        finally
        {
            try {
                if(flujo!=null)
                    flujo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void guardar_shared(String id_med){
        System.out.println("Guardaremos el medicamento");
        SharedPreferences datos = getSharedPreferences("notificaciones",MODE_PRIVATE);
        SharedPreferences.Editor miEditor = datos.edit();
        boolean ans=true;
        int i=0;
        do {

            if(datos.getString("id_med"+i,"error") == "error" ){
                System.out.println("Se guardo el medicamento: id_med"+i);
                miEditor.putString("id_med"+i,id_med);
                miEditor.apply();
                break;
            }
            System.out.println("--valor de i: "+i);
            i++;
        }while (i<10);


    }

    private void leer_shared(){
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

    private void mostrarMedicamentos(ArrayList<String> medicinas){
        if (medicinas.isEmpty()){
            System.out.println("No hay ninguna medicina :(");
        }else{
            for (int i=0; i<medicinas.size();i++){
                System.out.println(i+"Elemento: "+medicinas.get(i));
            }
        }

    }

    private void eliminarPreferences(){
        SharedPreferences datos = getSharedPreferences("notificaciones",MODE_PRIVATE);
        SharedPreferences.Editor miEditor = datos.edit();
        miEditor.clear();
        miEditor.apply();
    }

}