package com.example.tt_logging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.tt_logging.Receta.ListElement;
import com.example.tt_logging.Receta.ThirdFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principla_);

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
            preferences = getSharedPreferences("medicamento", Context.MODE_PRIVATE);
            SharedPreferences.Editor obj = preferences.edit();
            //obj.putString( )

        }

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

    public void cargar_a_fichero(String id_notificacion){
        String archivos [] = fileList();

        if (ArchivoExiste(archivos, "receta.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("receta.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String recetaCompleta="";
                while(linea != null){
                    recetaCompleta = recetaCompleta + linea + "\n";
                    linea = br.readLine();
                    System.out.println("-R: "+linea);
                }
                br.close();
                archivo.close();
                System.out.println(recetaCompleta);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean ArchivoExiste(String[] archivos, String nombreArchivo) {
        for(int i=0; i<archivos.length;i++){
            if(nombreArchivo.equals(archivos[i])){
                return true;
            }
        }
        return false;
    }

    public void Guardar_en_Archivo(View view, ListElement medicamento) throws FileNotFoundException {
        OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("receta.txt", Activity.MODE_PRIVATE));
        try {
            archivo.write(medicamento.getId_medicamento());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}