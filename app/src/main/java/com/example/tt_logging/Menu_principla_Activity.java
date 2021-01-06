package com.example.tt_logging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tt_logging.Alarmas.FirstFragment;
import com.example.tt_logging.Receta.ListElement;
import com.example.tt_logging.Receta.ThirdFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Menu_principla_Activity extends AppCompatActivity {
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    CuartoFragment cuartofragment = new CuartoFragment();
    private boolean permiso;
    private String hora;
    private Bundle notificacion;
    private String nota_recibida;
    private SharedPreferences preferences;
    private ArrayList<String> ids_medicamentos = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("Menu principal: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Menu principal: onResumen");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Menu principal: Destroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principla_);
        System.out.println("Estamos en onCreate Activiti_principal");
        Bundle objeto = getIntent().getExtras();
        ListElement medicamento = null;
        ids_medicamentos.clear();
        //eliminarPreferencesMedicamentos();
        int bandera =0;
        // Obtenemos los Shareds con los datos Medicamento Notas persona de cuidado.
        if(objeto != null){
            medicamento = (ListElement) objeto.getSerializable("medicina");
            if (medicamento != null){
                // llegada de horario
                hora =  objeto.getString("horario");
                System.out.println("|||°°°°°°°°Horario: "+ hora);
                // Obtenemos medicina
                medicamento = (ListElement) objeto.getSerializable("medicina");
                System.out.println("|||||| El dato resivido es el siguiente:");
                System.out.println("El medicamento recibido es: "+medicamento.getId_medicamento());
                leer_shared();
                guardar_shared(medicamento.getId_medicamento());
                mostrarMedicamentos(ids_medicamentos);
                guardarArrayNotificaciones();
                bandera = 1;
            }

            nota_recibida = objeto.getString("nota","sin nota");
            if(nota_recibida != "sin nota"){
                leer_shared_notas();
                guardar_shared_Notas(nota_recibida);
            }
        }
        if(bandera == 0){
            guardarArrayNotificaciones();
            mostrarMedicamentos(ids_medicamentos);
        }




        // Enviamos los datos necesarios a los fraagmentos
        notificacion = new Bundle();
        notificacion.putSerializable("notificacion", ids_medicamentos);
        firstFragment.setArguments(notificacion);
        thirdFragment.setArguments(notificacion);
        loadFragment(firstFragment);
        BottomNavigationView navegation = findViewById(R.id.bottom_navegation);
         navegation.setOnNavigationItemSelectedListener(mOnNavegationItemSelectedListener);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavegationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    loadFragment(firstFragment);
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
            //System.out.println("--valor de i: "+i);
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
                System.out.println(datos.getString("id_med"+i,"sin med"));
            }
            i++;
        }while (i<10);
    }

    private void mostrarMedicamentos(ArrayList<String> medicinas){
        int i=0;
        if (medicinas.isEmpty()){
            System.out.println("No hay ninguna medicina :(");
        }else{
            for (i=0; i<medicinas.size();i++){
                System.out.println(i+"Elemento: "+medicinas.get(i));
            }
        }
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

    private void guardar_shared_Notas(String nota){
        System.out.println("Guardaremos la nota");
        SharedPreferences datos = getSharedPreferences("notas",MODE_PRIVATE);
        SharedPreferences.Editor miEditor = datos.edit();
        boolean ans=true;
        int i=0;
        do {

            if(datos.getString("id_med"+i,"error") == "error" ){
                System.out.println("Se guardo el no: id_nota"+i);
                miEditor.putString("id_nota"+i,nota);
                miEditor.apply();
                break;
            }
            System.out.println("--valor de i: "+i);
            i++;
        }while (i<10);
    }

    //VEMOS LOS DATOS QUE SE ENCUENTRA EN EL SHARED
    private void leer_shared_notas(){
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

    private void eliminarPreferencesMedicamentos(){
        SharedPreferences datos = getSharedPreferences("notificaciones",MODE_PRIVATE);
        SharedPreferences.Editor miEditor = datos.edit();
        miEditor.clear();
        miEditor.apply();
    }

    private void eliminarPreferencesNotas(){
        SharedPreferences datos = getSharedPreferences("notas",MODE_PRIVATE);
        SharedPreferences.Editor miEditor = datos.edit();
        miEditor.clear();
        miEditor.apply();
    }

}