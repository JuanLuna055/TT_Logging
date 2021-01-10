package com.example.tt_logging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddDatoMedico extends AppCompatActivity {
    private Spinner stipo;
    private EditText etvar;
    private TextView tvfecha;
    private TextView tvmetrica;
    private Button bAdd;
    private DatoMedico datito;

    private  String id_dm;
    private String tipo;
    private float var;
    private String metrica;
    private String fecha;

    Calendar aux = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dato_medico);
        final String[] mediciones = {"Peso", "Glucosa", "Trgliceridos"};
        final String[] metricas = {"Kg", "mmol/L", "mg/dL"};

        stipo = (Spinner) findViewById(R.id.sTipo);
        etvar = (EditText) findViewById(R.id.etValor);
        tvfecha = (TextView) findViewById(R.id.tvFecha);
        tvmetrica = (TextView) findViewById(R.id.tvMetrica);
        bAdd = (Button)findViewById(R.id.bAdd);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mediciones, android.R.layout.simple_spinner_dropdown_item);
        stipo.setAdapter(adapter);
        stipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Selecciono: "+parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT);

                tipo = mediciones[position];
                metrica = metricas[position];

                tvmetrica.setText(metrica);
                /*metrica(tipo);*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fecha = aux.get(Calendar.DAY_OF_MONTH) + "/" + aux.get(Calendar.MONTH)+1 + "/" + aux.get(Calendar.YEAR);
        tvfecha.setText(fecha);

        leer_shared_datosMedicos();
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var = Float.valueOf(etvar.getText().toString());
                datito = new DatoMedico(tipo, var, metrica, fecha);
                id_dm = datito.getId_dato();
                System.out.println("DATO---------MEDICO"+id_dm);
                guardar_shared_DatoMedico(datito.getId_dato());
                startActivity(new Intent(AddDatoMedico.this, Menu_principla_Activity.class));
                finish();
            }
        });

    }

    private void guardar_shared_DatoMedico(String dato){
        System.out.println("Guardaremos la nota");
        SharedPreferences datos = getSharedPreferences("datos_medicos",MODE_PRIVATE);
        SharedPreferences.Editor miEditor = datos.edit();
        boolean ans=true;
        int i=0;
        do {

            if(datos.getString("id_datomedico"+i,"error") == "error" ){
                System.out.println("Se guardo el no: id_datomedico"+i);
                miEditor.putString("id_datomedico"+i,dato);
                miEditor.apply();
                break;
            }
            System.out.println("--valor de i: "+i);
            i++;
        }while (i<10);
    }

    private void leer_shared_datosMedicos(){
        SharedPreferences datos = getSharedPreferences("datos_medicos",MODE_PRIVATE);
        System.out.println("Leemos de Shared...");
        int i=0;
        do{
            if(datos.getString("id_datomedico"+i,"error") != "error"){
                System.out.println("Se pudo leer el id_datomedico"+i);

            }
            i++;
        }while (i<10);
    }
}