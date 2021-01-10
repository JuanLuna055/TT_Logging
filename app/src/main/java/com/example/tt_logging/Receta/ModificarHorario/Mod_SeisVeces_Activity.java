package com.example.tt_logging.Receta.ModificarHorario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tt_logging.Menu_principla_Activity;
import com.example.tt_logging.R;
import com.example.tt_logging.Receta.ListElement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Mod_SeisVeces_Activity extends AppCompatActivity {

    // Cinco alarmas

    private Button selehora, selehora1, selehora2, selehora3, selehora4, selehora5;
    private TextView tvhora, tvhora1,tvhora2, tvhora3, tvhora4, tvhora5;

    private Calendar actual = Calendar.getInstance();
    private Calendar calendar = Calendar.getInstance();
    private Calendar calendar1 = Calendar.getInstance();
    private Calendar calendar2 = Calendar.getInstance();
    private Calendar calendar3 = Calendar.getInstance();
    private Calendar calendar4 = Calendar.getInstance();
    private Calendar calendar5 = Calendar.getInstance();
    private Button guardar;
    //Seleccionar Fechaprivate int minutos, hora;
    private int minutos, hora, minutos1, hora1, minutos2, hora2, hora3, minutos3, hora4, minutos4, hora5, minutos5;
    private List<ListElement> notifi_med = new ArrayList<>();;
    private  ListElement medicamento;
    int periodo;
    int termina;
    private ArrayList<String> horas;

    Calendar inicio = Calendar.getInstance();
    Calendar fin = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod__cuatro_veces);

        selehora = findViewById(R.id.btn_selehora);
        selehora1 = findViewById(R.id.btn1_selehora);
        selehora2 = findViewById(R.id.btn2_selehora);
        selehora3 = findViewById(R.id.btn3_selehora);
        selehora4 = findViewById(R.id.btn4_selehora);
        selehora5 = findViewById(R.id.btn5_selehora);

        tvhora = findViewById(R.id.tv_hora);
        tvhora1 = findViewById(R.id.tv1_hora);
        tvhora2 = findViewById(R.id.tv2_hora);
        tvhora3 = findViewById(R.id.tv3_hora);
        tvhora4 = findViewById(R.id.tv4_hora);
        tvhora5 = findViewById(R.id.tv5_hora);
        guardar = findViewById(R.id.btn_guardar);

        tvhora.setText(String.format("%02d:%02d",8,00));

        Bundle objeto = getIntent().getExtras();
        medicamento = null;
        if(objeto != null){

            // llegada de medicamento
            medicamento = (ListElement) objeto.getSerializable("medicina");
            notifi_med.add(medicamento);
            inicio = medicamento.getInicio();
            fin = medicamento.getTermina();
            termina = fin.get(Calendar.DAY_OF_MONTH) - inicio.get(Calendar.DAY_OF_MONTH);
            if(termina < 0){
                termina = -termina;
            }
            System.out.println("Termina en dias:"+termina);
            periodo = objeto.getInt("periodo",1);
            System.out.println(inicio.getTime().toString());
            System.out.println(fin.getTime().toString());
            System.out.println(" El dato resivido es el siguiente:");
            System.out.println("Medicamento: "+medicamento.getMedicamento()+"Status: "+medicamento.getStatus()+"Recordatorio: "+medicamento.getRecordatorio());

            //loadFragment(thirdFragment);
        }

        //Seleccionar Hora 1


        //Seleccionar Hora 1
        selehora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hora = actual.get(Calendar.HOUR_OF_DAY);
                minutos = actual.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        System.out.println("la hora seleccionada es: " + h);
                        System.out.println("Munitos seleccionados: " + m);
                        calendar.set(Calendar.HOUR_OF_DAY, h);
                        calendar.set(Calendar.MINUTE, m);
                        hora=h;
                        minutos=m;
                        tvhora.setText(String.format("%02d:%02d", h, m));
                        System.out.println(calendar.getTime().toString());
                    }
                }, hora, minutos, true);
                timePickerDialog.show();
            }
        });

        //Selecciona hora 2
        selehora1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hora1 = actual.get(Calendar.HOUR_OF_DAY);
                minutos1 = actual.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        System.out.println("la hora seleccionada es: " + h);
                        System.out.println("Munitos seleccionados: " + m);
                        calendar.set(Calendar.HOUR_OF_DAY, h);
                        calendar.set(Calendar.MINUTE, m);
                        hora1=h;
                        minutos1=m;
                        tvhora1.setText(String.format("%02d:%02d", h, m));
                        System.out.println(calendar.getTime().toString());
                    }
                }, hora1, minutos1, true);
                timePickerDialog.show();
            }
        });

        //Selecciona hora 3
        selehora2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hora2= actual.get(Calendar.HOUR_OF_DAY);
                minutos2 = actual.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        System.out.println("la hora seleccionada es: " + h);
                        System.out.println("Munitos seleccionados: " + m);
                        calendar.set(Calendar.HOUR_OF_DAY, h);
                        calendar.set(Calendar.MINUTE, m);
                        hora2=h;
                        minutos2=m;
                        tvhora2.setText(String.format("%02d:%02d", h, m));
                        System.out.println(calendar.getTime().toString());
                    }
                }, hora2, minutos2, true);
                timePickerDialog.show();
            }
        });

        //Selecciona hora 4
        selehora3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hora3= actual.get(Calendar.HOUR_OF_DAY);
                minutos3 = actual.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        System.out.println("la hora seleccionada es: " + h);
                        System.out.println("Munitos seleccionados: " + m);
                        calendar3.set(Calendar.HOUR_OF_DAY, h);
                        calendar3.set(Calendar.MINUTE, m);
                        hora3=h;
                        minutos3=m;
                        tvhora3.setText(String.format("%02d:%02d", h, m));
                        System.out.println(calendar.getTime().toString());
                    }
                }, hora3, minutos3, true);
                timePickerDialog.show();
            }
        });

        //Selecciona hora 5
        selehora4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hora4= actual.get(Calendar.HOUR_OF_DAY);
                minutos4 = actual.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        System.out.println("la hora seleccionada es: " + h);
                        System.out.println("Munitos seleccionados: " + m);
                        calendar4.set(Calendar.HOUR_OF_DAY, h);
                        calendar4.set(Calendar.MINUTE, m);
                        hora4=h;
                        minutos4=m;
                        tvhora4.setText(String.format("%02d:%02d", h, m));
                        System.out.println(calendar.getTime().toString());
                    }
                }, hora4, minutos4, true);
                timePickerDialog.show();
            }
        });

        //Selecciona hora 6
        selehora5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hora5= actual.get(Calendar.HOUR_OF_DAY);
                minutos5 = actual.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        System.out.println("la hora seleccionada es: " + h);
                        System.out.println("Munitos seleccionados: " + m);
                        calendar5.set(Calendar.HOUR_OF_DAY, h);
                        calendar5.set(Calendar.MINUTE, m);
                        hora5=h;
                        minutos5=m;
                        tvhora5.setText(String.format("%02d:%02d", h, m));
                        System.out.println(calendar.getTime().toString());
                    }
                }, hora5, minutos5, true);
                timePickerDialog.show();
            }
        });
        //Seleccionar guardar
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Primera alarma
                calendar.set(inicio.get(Calendar.YEAR),inicio.get(Calendar.MONTH),inicio.get(Calendar.DAY_OF_MONTH),actual.get(Calendar.HOUR_OF_DAY),actual.get(Calendar.MINUTE));
                System.out.println(calendar.getTime().toString());
                calendar.set(Calendar.HOUR_OF_DAY, hora);
                calendar.set(Calendar.MINUTE, minutos);
                System.out.println(calendar.getTime().toString());
                Generar_Alarma(calendar,medicamento.getMedicamento(),medicamento.getRecordatorio(),termina);
                //Segunda Alarma
                calendar1.set(inicio.get(Calendar.YEAR),inicio.get(Calendar.MONTH),inicio.get(Calendar.DAY_OF_MONTH),actual.get(Calendar.HOUR_OF_DAY),actual.get(Calendar.MINUTE));
                calendar1.set(Calendar.HOUR_OF_DAY, hora1);
                calendar1.set(Calendar.MINUTE, minutos1);
                System.out.println(calendar1.getTime().toString());
                Generar_Alarma(calendar1,medicamento.getMedicamento(),medicamento.getRecordatorio(),termina);
                //Tercer Alarma
                calendar2.set(inicio.get(Calendar.YEAR),inicio.get(Calendar.MONTH),inicio.get(Calendar.DAY_OF_MONTH),actual.get(Calendar.HOUR_OF_DAY),actual.get(Calendar.MINUTE));
                calendar2.set(Calendar.HOUR_OF_DAY, hora2);
                calendar2.set(Calendar.MINUTE, minutos2);
                System.out.println(calendar2.getTime().toString());
                Generar_Alarma(calendar2,medicamento.getMedicamento(),medicamento.getRecordatorio(),termina);
                //Cuatro Alarma
                calendar3.set(inicio.get(Calendar.YEAR),inicio.get(Calendar.MONTH),inicio.get(Calendar.DAY_OF_MONTH),actual.get(Calendar.HOUR_OF_DAY),actual.get(Calendar.MINUTE));
                calendar3.set(Calendar.HOUR_OF_DAY, hora3);
                calendar3.set(Calendar.MINUTE, minutos3);
                System.out.println(calendar3.getTime().toString());
                Generar_Alarma(calendar3,medicamento.getMedicamento(),medicamento.getRecordatorio(),termina);

                //Cinco Alarma
                calendar4.set(inicio.get(Calendar.YEAR),inicio.get(Calendar.MONTH),inicio.get(Calendar.DAY_OF_MONTH),actual.get(Calendar.HOUR_OF_DAY),actual.get(Calendar.MINUTE));
                calendar4.set(Calendar.HOUR_OF_DAY, hora4);
                calendar4.set(Calendar.MINUTE, minutos4);
                System.out.println(calendar4.getTime().toString());
                Generar_Alarma(calendar4,medicamento.getMedicamento(),medicamento.getRecordatorio(),termina);
                //Seis Alarma
                calendar5.set(inicio.get(Calendar.YEAR),inicio.get(Calendar.MONTH),inicio.get(Calendar.DAY_OF_MONTH),actual.get(Calendar.HOUR_OF_DAY),actual.get(Calendar.MINUTE));
                calendar5.set(Calendar.HOUR_OF_DAY, hora5);
                calendar5.set(Calendar.MINUTE, minutos5);
                System.out.println(calendar4.getTime().toString());
                Generar_Alarma(calendar4,medicamento.getMedicamento(),medicamento.getRecordatorio(),termina);

                //Este tag donde haremos gua de la notifcacion
                Toast.makeText(getApplicationContext(), "Alarma guardada.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Mod_SeisVeces_Activity.this, Menu_principla_Activity.class);
                Bundle bundle = new Bundle();
                horas.add("("+hora+"-"+minutos+")");
                horas.add("("+hora1+"-"+minutos1+")");
                horas.add("("+hora2+"-"+minutos2+")");
                horas.add("("+hora3+"-"+minutos3+")");
                horas.add("("+hora4+"-"+minutos4+")");
                horas.add("("+hora5+"-"+minutos5+")]");
                //medicamento = new ListElement("#FF0000",nom_medicamento.getText().toString(),recordato.getText().toString(),"Cantidad:",i++,inicio,termino,horas,repeticion);
                ListElement medicamento_mod = new ListElement(medicamento.getColor(), medicamento.getMedicamento(), medicamento.getRecordatorio(), "cantidad:", 10, medicamento.getInicio(), medicamento.getTermina(), horas, medicamento.getFrecuencia());
                bundle.putSerializable("medicina",medicamento_mod);
                intent.putExtras(bundle);
                startActivity(intent);
                //finish();

            }
        });
    }

    public void Generar_Alarma (Calendar fecha, String medicamento, String Detalles,int fin){
        int j=0;
        String tag;
        Long AlerTime;
        j=0;
        do{
            tag = medicamento+"-a-"+fecha.get(Calendar.YEAR)+"m-"+fecha.get(Calendar.MONTH)+"-d"+fecha.get(Calendar.DAY_OF_MONTH)+"-h"+fecha.get(Calendar.HOUR_OF_DAY);
            System.out.println("Alarma programada para: "+fecha.getTime().toString()+"ID: "+tag);
            AlerTime = fecha.getTimeInMillis() - System.currentTimeMillis();
            System.out.println("El tiempo para esta alarma es: "+AlerTime);
            Data data = GuardarData("Es hora de tomar tu: "+medicamento,"Recomendacion: "+Detalles,10);
            //WorkManager_noti.Guardarnoti(AlerTime,data,tag);
            if(this.medicamento.getFrecuencia() == 7){
                fecha.setTime(sumarRestarMes(fecha.getTime(),1));
            }else{
                fecha.setTime(sumarRestarDiasFecha(fecha.getTime(),this.medicamento.getFrecuencia()+1));
                j = j + this.medicamento.getFrecuencia()+1;
            }
            if (this.medicamento.getFrecuencia() == 0){
                j++;
            }else {
                j = j+this.medicamento.getFrecuencia() + 1;
            }
        }while(j <= fin);

    }

    private Data GuardarData(String titulo, String detalle, int id_noti){
        return new Data.Builder()
                .putString("titulo",titulo)
                .putString("detalle",detalle)
                .putInt("id_noti",id_noti).build();
    }

    public Date sumarRestarDiasFecha(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_MONTH, dias);  // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }

    public Date sumarRestarMes(Date fecha, int mes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MONTH, mes);  // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }

}