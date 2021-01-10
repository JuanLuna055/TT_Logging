package com.example.tt_logging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

public class SeguimientoDato extends AppCompatActivity {
    private Spinner stipo;
    private TextView tvDatoTipo;
    private TextView tvDatoVariable;
    private TextView tvDatoFecha;

    private  String id_dm;
    private String tipo;
    private float var;
    private String metrica;

    private Calendar fecha = getInstance();

    private float dia = (float) fecha.get(DAY_OF_MONTH);
    private float mes = (float) fecha.get(MONTH);
    private float año = (float) fecha.get(YEAR);

    ArrayList<String> ids_datosmedicos;
    ArrayList<String> ids_datosPeso;
    ArrayList<String> ids_datosGlucosa;
    ArrayList<String> ids_datosTrigliceridos;
    List<DatoMedico> listaDatos;


    BarChart barChart;
    ArrayList<BarEntry> datosGrafico;
    BarDataSet barDataSet;
    BarData barData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento_dato);
        final String[] mediciones = {"Peso", "Glucosa", "Trgliceridos"};
        final String[] metricas = {"Kg", "mmol/L", "mg/dL"};

        stipo = (Spinner) findViewById(R.id.sTipoGrafica);
        tvDatoTipo = (TextView) findViewById(R.id.tvDatoTipo);
        tvDatoVariable = (TextView) findViewById(R.id.tvDatoVariable);
        tvDatoFecha = (TextView)findViewById(R.id.tvDatoFecha);
        barChart = findViewById(R.id.bcGrafico);

        listaDatos = new ArrayList<>();
        ids_datosmedicos = new ArrayList<>();
        ids_datosPeso = new ArrayList<>();
        ids_datosGlucosa = new ArrayList<>();
        ids_datosTrigliceridos = new ArrayList<>();
        datosGrafico = new ArrayList<>();

        leer_shared_datoMedico();
        cargarArrayDatoMedico();
        mostrarDatosMedicos();

        if (ids_datosmedicos.isEmpty()){
            System.out.println("No hay datos++++++++++++++++++++");
        }
        else {
            for (int i=0;i<ids_datosmedicos.size();i++) {
                System.out.println(ids_datosmedicos.get(i));
                String aux = ids_datosmedicos.get(i);
                String[] parts = aux.split("-");
                System.out.println(mediciones[0]);
                System.out.println(parts[0]);
                if (parts[0].equals(mediciones[0])){
                    ids_datosPeso.add(aux);
                }
                else {
                    if (parts[0].equals(mediciones[1])){
                        ids_datosGlucosa.add(aux);
                    }
                    else {
                        ids_datosTrigliceridos.add(aux);
                    }
                }
            }
        }
        if (ids_datosPeso.isEmpty()){
            System.out.println("No hay datos Peso++++++++++++++++++++");
        }
        else {
            System.out.println("Tenemos datos Peso++++++++++++++++++++");
            for (int i=0;i<ids_datosPeso.size();i++) {
                System.out.println(ids_datosPeso.get(i));
            }
        }

        if (ids_datosGlucosa.isEmpty()){
            System.out.println("No hay datos Glucosa++++++++++++++++++++");
        }
        else {
            System.out.println("Tenemos datos Glucosa++++++++++++++++++++");
            for (int i=0;i<ids_datosGlucosa.size();i++) {
                System.out.println(ids_datosGlucosa.get(i));
            }
        }

        if (ids_datosTrigliceridos.isEmpty()){
            System.out.println("No hay datos Trgliceridos++++++++++++++++++++");
        }
        else {
            System.out.println("Tenemos datos Trgliceridos++++++++++++++++++++");
            for (int i=0;i<ids_datosTrigliceridos.size();i++) {
                System.out.println(ids_datosTrigliceridos.get(i));
            }
        }
        tvDatoVariable.setText("--");
        tvDatoFecha.setText("--/--/----");


        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mediciones, android.R.layout.simple_spinner_dropdown_item);
        stipo.setAdapter(adapter);
        stipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Selecciono: "+parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT);

                tipo = mediciones[position];
                metrica = metricas[position];

                if (position == 0){
                    datosGrafico.clear();
                    tvDatoTipo.setText(metricas[position]);
                    if (ids_datosPeso.isEmpty()){
                        System.out.println("No hay datos Peso++++++++++++++++++++");
                            datosGrafico.add(new BarEntry(1, 0));
                            datosGrafico.add(new BarEntry(31, 0));
                            tvDatoVariable.setText("--");
                            tvDatoFecha.setText("--/--/----");
                    }
                    else {
                        System.out.println("Tenemos datos Peso++++++++++++++++++++");
                        int i;
                        for (i=0; i<(ids_datosPeso.size()-2) ;i++) {
                            System.out.println(ids_datosPeso.get(i));
                            String aux = ids_datosPeso.get(i);
                            String[] parts = aux.split("-");
                            String f = parts[3];
                            String[] fe = f.split("/");
                            System.out.println(parts[1]);
                            System.out.println(fe[0]);
                            System.out.println(fe[1]);
                            System.out.println(mes);
                            if (Float.valueOf(fe[1]) == mes+1){
                                datosGrafico.add(new BarEntry(Float.valueOf(fe[0]), Float.valueOf(parts[1]) ));
                            }
                        }
                        System.out.println("Salimos del four");
                        System.out.println(ids_datosPeso.get(i));
                        String aux = ids_datosPeso.get(i);
                        String[] parts = aux.split("-");
                        String f = parts[3];
                        String[] fe = f.split("/");
                        System.out.println(parts[1]);
                        System.out.println(fe[0]);
                        System.out.println(fe[1]);
                        System.out.println(mes);
                        tvDatoVariable.setText(parts[1]);
                        tvDatoFecha.setText(parts[3]);
                        if (Float.valueOf(fe[1]) == mes+1){
                            datosGrafico.add(new BarEntry(Float.valueOf(fe[0]), Float.valueOf(parts[1]) ));
                        }
                    }

                    barDataSet = new BarDataSet(datosGrafico, "Datos Medicos");
                    barDataSet.setColors(rgb("#25B9D0"));
                    barDataSet.setValueTextColor(Color.BLACK);
                    barDataSet.setValueTextSize(16F);

                    barData = new BarData(barDataSet);

                    barChart.setFitBars(true);
                    barChart.setData(barData);
                    switch ((int)mes){
                        case 0:
                            barChart.getDescription().setText("Enero");
                            break;
                        case 1:
                            barChart.getDescription().setText("Febrero");
                            break;
                        case 2:
                            barChart.getDescription().setText("Marzo");
                            break;
                        case 3:
                            barChart.getDescription().setText("Abril");
                            break;
                        case 4:
                            barChart.getDescription().setText("Mayo");
                            break;
                        case 5:
                            barChart.getDescription().setText("Junio");
                            break;
                        case 6:
                            barChart.getDescription().setText("Julio");
                            break;
                        case 7:
                            barChart.getDescription().setText("Agosto");
                            break;
                        case 8:
                            barChart.getDescription().setText("Septiembre");
                            break;
                        case 9:
                            barChart.getDescription().setText("Octubre");
                            break;
                        case 10:
                            barChart.getDescription().setText("Noviembre");
                            break;
                        case 11:
                            barChart.getDescription().setText("Diciembre");
                            break;
                    }
                    barChart.setBackgroundColor(rgb("#f9f9ed"));
                    barChart.animateX(1000);

                }
                else {
                    if (position == 1){
                        datosGrafico.clear();
                        tvDatoTipo.setText(metricas[position]);
                        if (ids_datosGlucosa.isEmpty()){
                            System.out.println("No hay datos Glucosa++++++++++++++++++++");
                            datosGrafico.add(new BarEntry(1, 0));
                            datosGrafico.add(new BarEntry(31, 0));
                            tvDatoVariable.setText("--");
                            tvDatoFecha.setText("--/--/----");
                        }
                        else {
                            System.out.println("Tenemos datos Glucosa++++++++++++++++++++");
                            int i;
                            for (i=0; i<(ids_datosGlucosa.size()-2) ;i++) {
                                System.out.println(ids_datosGlucosa.get(i));
                                String aux = ids_datosGlucosa.get(i);
                                String[] parts = aux.split("-");
                                String f = parts[3];
                                String[] fe = f.split("/");
                                System.out.println(parts[1]);
                                System.out.println(fe[0]);
                                if (Float.valueOf(fe[1]) == mes+1){
                                    datosGrafico.add(new BarEntry(Float.valueOf(fe[0]), Float.valueOf(parts[1]) ));
                                }
                            }
                            System.out.println("Salimos del four");
                            System.out.println(ids_datosGlucosa.get(i));
                            String aux = ids_datosGlucosa.get(i);
                            String[] parts = aux.split("-");
                            String f = parts[3];
                            String[] fe = f.split("/");
                            System.out.println(parts[1]);
                            System.out.println(fe[0]);
                            tvDatoVariable.setText(parts[1]);
                            tvDatoFecha.setText(parts[3]);
                            if (Float.valueOf(fe[1]) == mes+1){
                                datosGrafico.add(new BarEntry(Float.valueOf(fe[0]), Float.valueOf(parts[1]) ));
                            }
                        }

                        barDataSet = new BarDataSet(datosGrafico, "Datos Medicos");
                        barDataSet.setColors(rgb("#25B9D0"));
                        barDataSet.setValueTextColor(Color.BLACK);
                        barDataSet.setValueTextSize(16F);

                        barData = new BarData(barDataSet);

                        barChart.setFitBars(true);
                        barChart.setData(barData);
                        switch ((int)mes){
                            case 0:
                                barChart.getDescription().setText("Enero");
                                break;
                            case 1:
                                barChart.getDescription().setText("Febrero");
                                break;
                            case 2:
                                barChart.getDescription().setText("Marzo");
                                break;
                            case 3:
                                barChart.getDescription().setText("Abril");
                                break;
                            case 4:
                                barChart.getDescription().setText("Mayo");
                                break;
                            case 5:
                                barChart.getDescription().setText("Junio");
                                break;
                            case 6:
                                barChart.getDescription().setText("Julio");
                                break;
                            case 7:
                                barChart.getDescription().setText("Agosto");
                                break;
                            case 8:
                                barChart.getDescription().setText("Septiembre");
                                break;
                            case 9:
                                barChart.getDescription().setText("Octubre");
                                break;
                            case 10:
                                barChart.getDescription().setText("Noviembre");
                                break;
                            case 11:
                                barChart.getDescription().setText("Diciembre");
                                break;
                        }
                        barChart.setBackgroundColor(rgb("#f9f9ed"));
                        barChart.animateX(1000);
                    }
                    else {
                        datosGrafico.clear();
                        tvDatoTipo.setText(metricas[position]);
                        if (ids_datosTrigliceridos.isEmpty()){
                            System.out.println("No hay datos trigliceridos++++++++++++++++++++");
                            datosGrafico.add(new BarEntry(1, 0));
                            datosGrafico.add(new BarEntry(31, 0));
                            tvDatoVariable.setText("--");
                            tvDatoFecha.setText("--/--/----");
                        }
                        else {
                            System.out.println("Tenemos datos Trigliceridos++++++++++++++++++++");
                            int i;
                            for (i=0; i<(ids_datosTrigliceridos.size()-2) ;i++) {
                                System.out.println(ids_datosTrigliceridos.get(i));
                                String aux = ids_datosTrigliceridos.get(i);
                                String[] parts = aux.split("-");
                                String f = parts[3];
                                String[] fe = f.split("/");
                                System.out.println(parts[1]);
                                System.out.println(fe[0]);
                                if (Float.valueOf(fe[1]) == mes+1){
                                    datosGrafico.add(new BarEntry(Float.valueOf(fe[0]), Float.valueOf(parts[1]) ));
                                }
                            }
                            System.out.println("Salimos del four");
                            System.out.println(ids_datosTrigliceridos.get(i));
                            String aux = ids_datosTrigliceridos.get(i);
                            String[] parts = aux.split("-");
                            String f = parts[3];
                            String[] fe = f.split("/");
                            System.out.println(parts[1]);
                            System.out.println(fe[0]);
                            tvDatoVariable.setText(parts[1]);
                            tvDatoFecha.setText(parts[3]);
                            if (Float.valueOf(fe[1]) == mes+1){
                                datosGrafico.add(new BarEntry(Float.valueOf(fe[0]), Float.valueOf(parts[1]) ));
                            }
                        }

                        barDataSet = new BarDataSet(datosGrafico, "Datos Medicos");
                        barDataSet.setColors(rgb("#25B9D0"));
                        barDataSet.setValueTextColor(Color.BLACK);
                        barDataSet.setValueTextSize(16F);

                        barData = new BarData(barDataSet);

                        barChart.setFitBars(true);
                        barChart.setData(barData);
                        switch ((int)mes){
                            case 0:
                                barChart.getDescription().setText("Enero");
                                break;
                            case 1:
                                barChart.getDescription().setText("Febrero");
                                break;
                            case 2:
                                barChart.getDescription().setText("Marzo");
                                break;
                            case 3:
                                barChart.getDescription().setText("Abril");
                                break;
                            case 4:
                                barChart.getDescription().setText("Mayo");
                                break;
                            case 5:
                                barChart.getDescription().setText("Junio");
                                break;
                            case 6:
                                barChart.getDescription().setText("Julio");
                                break;
                            case 7:
                                barChart.getDescription().setText("Agosto");
                                break;
                            case 8:
                                barChart.getDescription().setText("Septiembre");
                                break;
                            case 9:
                                barChart.getDescription().setText("Octubre");
                                break;
                            case 10:
                                barChart.getDescription().setText("Noviembre");
                                break;
                            case 11:
                                barChart.getDescription().setText("Diciembre");
                                break;
                        }
                        barChart.setBackgroundColor(rgb("#f9f9ed"));
                        barChart.animateX(1000);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void leer_shared_datoMedico(){
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

    private void cargarArrayDatoMedico(){
        SharedPreferences datos = getSharedPreferences("datos_medicos",MODE_PRIVATE);
        int i=0;
        do{
            if(datos.getString("id_datomedico"+i,"error") != "error"){
                System.out.println("Se pudo leer el id_datomedico"+i);
                /*dos elemntos
                iddotosmedicos0 = tipo+"-"+var+"-"+metrica+"-"+fecha;
                iddotosmedicos1 = tipo+"-"+var+"-"+metrica+"-"+fecha;*/
                ids_datosmedicos.add(datos.getString("id_datomedico"+i,"Sin datos"));
            }
            //System.out.println("Valor de i: "+i);
            i++;
        }while (i<10);
    }

    private void mostrarDatosMedicos(){
        int i=0;
        if (ids_datosmedicos.isEmpty()){
            System.out.println("No hay datos :(");
        }else{
            for (i=0; i<ids_datosmedicos.size();i++){
                System.out.println(i+"Elemento Dato Medico: "+ids_datosmedicos.get(i));
                listaDatos.add(new DatoMedico(ids_datosmedicos.get(i)));
            }
        }
    }
}