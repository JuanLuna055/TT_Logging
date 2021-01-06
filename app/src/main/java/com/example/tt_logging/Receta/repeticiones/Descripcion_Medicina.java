package com.example.tt_logging.Receta.repeticiones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.WorkManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_logging.Menu_principla_Activity;
import com.example.tt_logging.R;
import com.example.tt_logging.Receta.ListElement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Descripcion_Medicina extends AppCompatActivity {

    //TextView
    TextView medicamento;
    TextView recordatorio;
    TextView status;
    TextView cantidad;
    TextView inicio;
    private  String id_shared;
    TextView termina;
    TextView frecuncia;
    TextView repeticion;
    TextView horario;
    private Button btn_eliminar, btn_regresar;
    private int cantidad_dosis;
    private ImageView icono_med;
    private ArrayList horas , minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion__medicina);

        final ListElement medicina = (ListElement) getIntent().getSerializableExtra("medicina");
        btn_eliminar = findViewById(R.id.button_eliminar_medicamento);
        icono_med = findViewById(R.id.Icono_midicina_perfil);
        medicamento = findViewById(R.id.txtView_medicina);
        recordatorio = findViewById(R.id.txtView_recomendacion);
        status = findViewById(R.id.txtView_status);
        cantidad = findViewById(R.id.txtView_cantidad);
        inicio= findViewById(R.id.txtView_Inicio);
        termina = findViewById(R.id.txtView_Termina);
        frecuncia = findViewById(R.id.txtView_Frecuencia);
        horario = findViewById(R.id.txtView_horario);
        repeticion = findViewById(R.id.txtView_repeticion);
        horas = new ArrayList();
        minutos = new ArrayList();

        medicamento.setText(medicina.getMedicamento());
        recordatorio.setText("Recormendacion de uso: "+medicina.getRecordatorio());
        status.setText("Status: "+medicina.getStatus());
        cantidad.setText("Cantidad: "+medicina.getCantidad());
        cantidad_dosis = medicina.getCantidad();
        color_Icono_Med();
        inicio.setText("Comienzo: "+medicina.getInicio().getTime().toString());
        termina.setText("Termina: "+medicina.getTermina().getTime().toString());
        frecuncia.setText("Tomar: "+medicina.getFrecuencia()+1+" Cada dia");
        repeticion.setText("Tomar: veces al dia: "+medicina.getFrecuencia());
        horario.setText("Horario alarmas: ["+medicina.getHoras_rec());

        analizar_Id_medi(medicina);

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Obtener_horas(medicina);
                generar_alarma(medicina);
                eliminarPreferencesMedicamentos();
                startActivity(new Intent(Descripcion_Medicina.this, Menu_principla_Activity.class));
                finish();
            }
        });

    }

    public void analizar_Id_medi(ListElement medicina){
        //Se busca la posicion dentro de shared reference del nuestro id a modificar.
        SharedPreferences datos = getSharedPreferences("notificaciones",MODE_PRIVATE);
        String id_obtenido;
        System.out.println("id de nuestra medicina: "+medicina.getId_medicamento());
        System.out.println("Leemos de Shared...");
        int i=0;
        do{
            if(datos.getString("id_med"+i,"error") != "error"){
                System.out.println("Se pudo leer el id_med"+i+"El id a comprar es: "+datos.getString("id_med"+i,"no hay id"));
                id_obtenido = datos.getString("id_med"+i,"no hay id");
                if(id_obtenido.equals(medicina.getId_medicamento())){
                    System.out.println("El id es id_med"+i);
                    id_shared = "id_med"+i;
                    break;
                }else{
                    id_obtenido="";
                }
            }
            System.out.println("Valor de i: "+i);
            i++;
        }while (i<10);
    }

    private void eliminarPreferencesMedicamentos(){
        SharedPreferences datos = getSharedPreferences("notificaciones",MODE_PRIVATE);
        SharedPreferences.Editor miEditor = datos.edit();
        miEditor.remove(id_shared);
        miEditor.apply();
        Toast.makeText(this, "Se elimino el medicamento correctamente", Toast.LENGTH_SHORT).show();
    }

    private void color_Icono_Med(){
        switch (cantidad_dosis){
            case 10:{
                icono_med.setColorFilter(Color.parseColor("#ffba08"),PorterDuff.Mode.SRC_IN);
                break;
            }
            case 9:{
                icono_med.setColorFilter(Color.parseColor("#ffba08"),PorterDuff.Mode.SRC_IN);
                break;
            }
            case 8:{
                icono_med.setColorFilter(Color.parseColor("#faa307"),PorterDuff.Mode.SRC_IN);
                break;
            }
            case 7:{
                icono_med.setColorFilter(Color.parseColor("#faa307"),PorterDuff.Mode.SRC_IN);
                break;
            }
            case 6:{
                icono_med.setColorFilter(Color.parseColor("#f48c06"),PorterDuff.Mode.SRC_IN);
                break;
            }
            case 5:{
                icono_med.setColorFilter(Color.parseColor("#f48c06"),PorterDuff.Mode.SRC_IN);
                break;
            }
            case 4:{
                icono_med.setColorFilter(Color.parseColor("#e85d04"),PorterDuff.Mode.SRC_IN);
                break;
            }
            case 3:{
                icono_med.setColorFilter(Color.parseColor("#e85d04"),PorterDuff.Mode.SRC_IN);
                break;
            }
            case 2:{
                icono_med.setColorFilter(Color.parseColor("#dc2f02"),PorterDuff.Mode.SRC_IN);
                break;
            }
            case 1:{
                icono_med.setColorFilter(Color.parseColor("#dc2f02"),PorterDuff.Mode.SRC_IN);
                break;
            }
            case 0:{
                icono_med.setColorFilter(Color.parseColor("#6a040f"),PorterDuff.Mode.SRC_IN);
                break;
            } default:{
                icono_med.setColorFilter(Color.parseColor("#64FFDA"),PorterDuff.Mode.SRC_IN);
            }

        }
    }

    public void generar_alarma (ListElement medi){
        Calendar fecha = medi.getInicio();
        Calendar actual = Calendar.getInstance();
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
            for (int i=0;i<horas.size();i++){
                System.out.println("tag enviado para eliminar: "+tag+"h"+horas.get(i));
                    EliminarNoti(tag+"h"+horas.get(i));
            }
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
    }

    private void Obtener_horas (ListElement medi){
        for(int i=0; i<medi.getHoras_rec().length();i++){
            if (medi.getHoras_rec().charAt(i) == '('){
                horas.add(""+medi.getHoras_rec().charAt(i+1));
            }
            if (medi.getHoras_rec().charAt(i) == '-'){
                minutos.add(""+medi.getHoras_rec().charAt(i+1));
            }
            if (medi.getHoras_rec().charAt(i) == ']'){
                break;
            }
        }

    }

    public Date sumarRestarMes(Date fecha, int mes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MONTH, mes);  // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }

    private void EliminarNoti(String tag){
        System.out.println("Seacciono: ");
        WorkManager.getInstance(getApplicationContext()).cancelAllWorkByTag(tag);
        Toast.makeText(Descripcion_Medicina.this,"Alarma Eliminada.", Toast.LENGTH_SHORT).show();

    }
    public Date sumarRestarDiasFecha(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_MONTH, dias);  // numero de horas a añadir, o restar en caso de horas<0
        return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
    }



}