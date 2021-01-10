package com.example.tt_logging.Notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tt_logging.Menu_principla_Activity;
import com.example.tt_logging.R;
import com.example.tt_logging.Receta.Add_Medicina;

import java.util.Calendar;

public class Notas_Activity extends AppCompatActivity {

    private Button agregar_nota;
    private EditText notita;
    private Notas nota_enviar;
    private String mensaje;
    private Bundle nota;
    private Calendar fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

    agregar_nota = (Button) findViewById(R.id.guradar_add_nota);
    notita = (EditText) findViewById(R.id.txtMulti_Nota);
    leer_shared_Nota();
    agregar_nota.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Notas_Activity.this, Menu_principla_Activity.class);
            nota = new Bundle();
            fecha = Calendar.getInstance();
            nota_enviar = new Notas(""+notita.getText().toString(),""+fecha.getTime().toString());
            mensaje = nota_enviar.getId_nota();
            System.out.println("El id mensaje: "+mensaje);
            guardar_shared_Nota(mensaje);
            startActivity(intent);
        }
    });
    }

    private void guardar_shared_Nota(String mensaje){
        System.out.println("Guardaremos la nota");
        SharedPreferences datos = getSharedPreferences("notas",MODE_PRIVATE);
        SharedPreferences.Editor miEditor = datos.edit();
        int i=0;
        do {

            if(datos.getString("id_nota"+i,"error") == "error" ){
                System.out.println("Se guardo : id_nota"+i);
                miEditor.putString("id_nota"+i,mensaje);
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

    private void eliminarPreferencesNotas(){
        SharedPreferences datos = getSharedPreferences("notas",MODE_PRIVATE);
        SharedPreferences.Editor miEditor = datos.edit();
        miEditor.clear();
        miEditor.apply();

    }

}