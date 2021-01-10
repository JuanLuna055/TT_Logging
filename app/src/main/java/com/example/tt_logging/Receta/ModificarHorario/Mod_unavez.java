package com.example.tt_logging.Receta.ModificarHorario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.WorkManager;

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
import com.example.tt_logging.Receta.WorkManager_noti;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Mod_unavez extends AppCompatActivity {

    //// Solo una alarma;
    Button selehora;
    TextView tvhora;
    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();
    Button guardar, btn_eliminar;
    //Seleccionar Fechaprivate int minutos, hora;
    private int minutos, hora, dia, mes,anio;
    private  ListElement medicamento;
    private ListElement medicamento_mod;
    int periodo;
    int termina;
    private Calendar inicio = Calendar.getInstance();
    private Calendar fin = Calendar.getInstance();
    private ArrayList<String> horas;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_unavez);

        selehora = findViewById(R.id.btn_selehora);
        tvhora = findViewById(R.id.tv_hora);
        guardar = findViewById(R.id.btn_guardar);
        btn_eliminar = findViewById(R.id.btn_eliminar);
        horas = new ArrayList();
        tvhora.setText(String.format("%02d:%02d",8,00));

        Bundle objeto = getIntent().getExtras();
        medicamento = null;
        if(objeto != null){

            // llegada de medicamento
            medicamento = (ListElement) objeto.getSerializable("medicina");
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


        //Seleccionar Hora
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

        //Seleccionar guardar
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hora > -1 && minutos > -1){
                calendar.set(inicio.get(Calendar.YEAR),inicio.get(Calendar.MONTH),inicio.get(Calendar.DAY_OF_MONTH),actual.get(Calendar.HOUR_OF_DAY),actual.get(Calendar.MINUTE));
                System.out.println(calendar.getTime().toString());
                calendar.set(Calendar.HOUR_OF_DAY, hora);
                calendar.set(Calendar.MINUTE, minutos);
                System.out.println("Despues del cambio: "+calendar.getTime().toString());
                //calendar.setTime(sumarRestarminutosFecha(calendar.getTime(),1));
                System.out.println(calendar.getTime().toString());
                Generar_Alarma(calendar,medicamento.getMedicamento(),medicamento.getRecordatorio(),termina);

                //Este tag donde haremos gua de la notifcacion
                Toast.makeText(getApplicationContext(), "Alarma guardada.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Mod_unavez.this, Menu_principla_Activity.class);
                Bundle bundle = new Bundle();
                horas.add("("+hora+"-"+minutos+")]");
                //medicamento = new ListElement("#FF0000",nom_medicamento.getText().toString(),recordato.getText().toString(),"Cantidad:",i++,inicio,termino,horas,repeticion);
                medicamento_mod = new ListElement(medicamento.getColor(),medicamento.getMedicamento(),medicamento.getRecordatorio(),"Activo",medicamento.getCantidad(),medicamento.getInicio(),medicamento.getTermina(),horas,medicamento.getFrecuencia());
                bundle.putSerializable("medicina",medicamento_mod);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                }
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EliminarNoti("tag1");
            }
        });


    }


    private void EliminarNoti(String tag){
        System.out.println("Seacciono: ");
        WorkManager.getInstance(getApplicationContext()).cancelAllWorkByTag(tag);
        Toast.makeText(getApplicationContext(),"Alarma Eliminada.", Toast.LENGTH_SHORT).show();

    }

    private String generateKey(){
        return UUID.randomUUID().toString();
    }

    private Data GuardarData(String titulo, String detalle, String id_noti){
        return new Data.Builder()
                .putString("titulo",titulo)
                .putString("detalle",detalle)
                .putString("id_noti",id_noti).build();
    }

    public Date sumarRestarminutosFecha(Date fecha, int minutos){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MINUTE, minutos);  // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas

    }

    public Date sumarRestarhorasFecha(Date fecha, int horas){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.HOUR_OF_DAY, horas);  // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
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

    private void Generar_Alarma (Calendar fecha, String medicamento, String Detalles,int fin){
        int j=0;
        String tag;
        Long AlerTime;
        for (int i=0;i<1;i++){
            j=0;
            do{
                tag = medicamento+"a"+fecha.get(Calendar.YEAR)+"m"+fecha.get(Calendar.MONTH)+"d"+fecha.get(Calendar.DAY_OF_MONTH)+"h"+fecha.get(Calendar.HOUR_OF_DAY);
                System.out.println("Alarma programada para: "+fecha.getTime().toString()+"ID: "+tag);
                AlerTime = fecha.getTimeInMillis() - System.currentTimeMillis();
                System.out.println("El tiempo para esta alarma es: "+AlerTime);
                Data data = GuardarData("Es hora de tomar tu: "+medicamento,"Recomendacion: "+Detalles,""+tag);
                WorkManager_noti.Guardarnoti(AlerTime,data,tag);
                if(this.medicamento.getFrecuencia() == 7){
                    fecha.setTime(sumarRestarMes(fecha.getTime(),1));
                }else{
                    fecha.setTime(sumarRestarDiasFecha(fecha.getTime(),this.medicamento.getFrecuencia()+1));
                    j = j + this.medicamento.getFrecuencia()+1;
                }
            }while(j <= fin);
        }
    }

}