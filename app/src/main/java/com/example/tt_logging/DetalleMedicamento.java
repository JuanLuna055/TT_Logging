package com.example.tt_logging;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tt_logging.Receta.ListElement;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Calendar;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

public class DetalleMedicamento extends AppCompatActivity {
    private TextView tvNombre;
    private TextView tvEfecto;
    private TextView tvInicio;
    private TextView tvFinal;
    private TextView tvCantidad;
    private TextView tvValidado;
    private TextView tvNoValidado;

    PieChart pieChart;
    ArrayList<PieEntry> datosGrafico;
    PieDataSet pieDataSet;
    PieData pieData;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_medicamento);

        ListElement medicamento = (ListElement) getIntent().getSerializableExtra("Medicamento");
        tvNombre = (TextView) findViewById(R.id.tvMedicamentoNombre);
        tvEfecto = (TextView) findViewById(R.id.tvMedicamentoEfecto);
        tvInicio = (TextView) findViewById(R.id.tvMedicamentoInicio);
        tvFinal = (TextView) findViewById(R.id.tvMedicamentoTermino);
        tvCantidad = (TextView) findViewById(R.id.tvMedicamentoCantidad);
        tvValidado = (TextView) findViewById(R.id.tvMedicamentoValidado);
        tvNoValidado = (TextView) findViewById(R.id.tvMedicamentoNoValidado);
        System.out.println();

        Calendar inicio = medicamento.getInicio();
        Calendar termina = medicamento.getTermina();
        int termi = termina.get(Calendar.DAY_OF_MONTH)-inicio.get(Calendar.DAY_OF_MONTH);
        String horas = medicamento.getHoras_rec();
        int aux = 0;
        int i;
        for ( i =0 ; i < horas.length(); i++) {
            if (horas.charAt(i) == '-'){
                aux++;
            }
        }

        float dosis = (termi/(medicamento.getFrecuencia()+1))*(aux+1);
        float novalidados = Float.valueOf(medicamento.getDosis_consumir());
        float validados = dosis-novalidados;
        float adherencia = (validados/dosis)*100;

        llenarGrafico(validados, novalidados);

        Calendar finicio = medicamento.getInicio();
        String fechainicio = finicio.get(Calendar.DAY_OF_MONTH)+"/"+finicio.get(Calendar.MONTH)+1+"/"+finicio.get(Calendar.YEAR);
        Calendar ffin = medicamento.getTermina();
        String fechafin = ffin.get(Calendar.DAY_OF_MONTH)+"/"+ffin.get(Calendar.MONTH)+1+"/"+ffin.get(Calendar.YEAR);


        tvNombre.setText(medicamento.getMedicamento());
        tvEfecto.setText(medicamento.getRecordatorio());
        tvInicio.setText(fechainicio);
        tvFinal.setText(fechafin);
        tvValidado.setText((int)validados+"");
        tvNoValidado.setText((int)novalidados+"");
        tvCantidad.setText(adherencia+"%");
    }

    public void llenarGrafico(float validados, float novalidados){
        pieChart = (PieChart)findViewById(R.id.pcGrafico);

        datosGrafico = new ArrayList<>();

        datosGrafico.add(new PieEntry(validados,"Tomados"));
        datosGrafico.add(new PieEntry(novalidados,"No tomados"));

        pieDataSet = new PieDataSet(datosGrafico, "Validacion");
        pieDataSet.setColors(rgb("#25B9D0"), rgb("#f1c40f"));
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Validaciones");
        pieChart.animate();
    }
}