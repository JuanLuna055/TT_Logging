package com.example.tt_logging.Receta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.Data;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tt_logging.Menu_principla_Activity;
import com.example.tt_logging.R;
import com.example.tt_logging.Receta.ModificarHorario.Mod_unavez;
import com.example.tt_logging.Receta.repeticiones.Cinco_Veces_Fragment;
import com.example.tt_logging.Receta.repeticiones.Diez_Veces_Fragment;
import com.example.tt_logging.Receta.repeticiones.Doce_Veces_Fragment;
import com.example.tt_logging.Receta.repeticiones.Dos_Veces_Fragment;
import com.example.tt_logging.Receta.repeticiones.Nueve_Veces_Fragment;
import com.example.tt_logging.Receta.repeticiones.Ocho_Veces_Fragment;
import com.example.tt_logging.Receta.repeticiones.Once_Veces_Fragment;
import com.example.tt_logging.Receta.repeticiones.Seis_Veces_Fragment;
import com.example.tt_logging.Receta.repeticiones.Siete_Veces_Fragment;
import com.example.tt_logging.Receta.repeticiones.Tres_Veces_Fragment;
import com.example.tt_logging.Receta.repeticiones.VeintiCuatro_Veces_Fragment;
import com.example.tt_logging.Receta.repeticiones.cuatro_Veces_Fragment;
import com.example.tt_logging.Receta.repeticiones.unavez_Fragment;
import com.example.tt_logging.SecondFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Add_Medicina extends AppCompatActivity {

    //Obtener la info del XML
    private EditText nom_medicamento, recordato;
    private int frecuncia, repeticion;
    private TextView fecha_inicio, fecha_final;
    private VeintiCuatro_Veces_Fragment v24;
    private ListElement medicamento;
    private unavez_Fragment una;
    private Button btn_medicina, btn_modHorario;
    private Bundle bundle;
    private int i, anio, mes, dia, termina;
    private Button selefecha_inicio , selefecha_final;
    private Spinner spinner_frecuncia;
    private Spinner spinner_veces;
    private Calendar inicio, termino;private Calendar actual;

    private ArrayList<String> horas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__medicina);

        una = new unavez_Fragment();
        loadFragment(una);
        nom_medicamento = (EditText) findViewById(R.id.Txt_medic);
        recordato = (EditText) findViewById(R.id.edt_recordatorio);
        btn_modHorario = (Button) findViewById(R.id.btn_horario);
        bundle = new Bundle();
        selefecha_final = (Button) findViewById(R.id.btn_selefecha_final);
        selefecha_inicio = (Button) findViewById(R.id.btn_selefecha_inicio);
        fecha_final = (TextView) findViewById(R.id.tv_fecha_final);
        fecha_inicio = (TextView) findViewById(R.id.tv_fecha_inicio);
        actual = Calendar.getInstance();
        inicio = Calendar.getInstance();
        termino = Calendar.getInstance();
        horas = new ArrayList<>();
        i=0;

        /// Frecuncia
        spinner_frecuncia = (Spinner) findViewById(R.id.id_Spinner_frecuencia);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.frecuncia_dias,
        android.R.layout.simple_spinner_item);
        spinner_frecuncia.setAdapter(adapter);

        spinner_frecuncia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Selecciono: "+adapterView.getItemAtPosition(position).toString()+"posision: *** "+position,Toast.LENGTH_SHORT).show();
                frecuncia = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //Repeticiones
        spinner_veces = (Spinner) findViewById(R.id.id_Spinner_repeticion);
        final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.Repeticiones,
                android.R.layout.simple_spinner_dropdown_item);
        spinner_veces.setAdapter(adapter1);
        spinner_veces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long id) {
                Toast.makeText(getApplicationContext(),"Seleccino: "+adapterView.getItemAtPosition(posicion).toString(),Toast.LENGTH_SHORT);
                repeticion = posicion;
                cargar_horario(repeticion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        selefecha_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anio = actual.get(Calendar.YEAR);
                mes = actual.get(Calendar.MONTH);
                dia = actual.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {

                        inicio.set(Calendar.DAY_OF_MONTH,d);
                        inicio.set(Calendar.MONTH,m);
                        inicio.set(Calendar.YEAR,y);
                        inicio.set(Calendar.HOUR_OF_DAY,8);
                        inicio.set(Calendar.MINUTE,0);
                        System.out.println(inicio.getTime().toString());
                        SimpleDateFormat formant = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate = formant.format(inicio.getTime());
                        fecha_inicio.setText(strDate);
                    }
                },anio,mes,dia);
                datePickerDialog.show();
            }
        });

        selefecha_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                anio = actual.get(Calendar.YEAR);
                mes = actual.get(Calendar.MONTH);
                dia = (actual.get(Calendar.DAY_OF_MONTH))+1;

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        termino.set(Calendar.DAY_OF_MONTH,d);
                        termino.set(Calendar.MONTH,m);
                        termino.set(Calendar.YEAR,y);
                        System.out.println(termino.getTime().toString());
                        SimpleDateFormat formant = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate = formant.format(termino.getTime());
                        fecha_final.setText(strDate);

                    }
                },anio,mes,dia+1);
                datePickerDialog.show();
            }
        });
            /// FINALIZAR
                btn_medicina = (Button)findViewById(R.id.button_add_medi);
                btn_medicina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // horas: (16-30)
                cargar_horas(repeticion);
                termina = termino.get(Calendar.DAY_OF_MONTH) - inicio.get(Calendar.DAY_OF_MONTH);
                if(termina < 0){
                    termina = -termina;
                }
                System.out.println("Termina en dias:"+termina);
                Generar_Alarmas(repeticion,inicio,nom_medicamento.getText().toString(),recordato.getText().toString(),termina);
                Toast.makeText(Add_Medicina.this, "Estamos por ebvari medicina", Toast.LENGTH_SHORT).show();
                medicamento = new ListElement("#FF0000",nom_medicamento.getText().toString(),recordato.getText().toString(),"Cantidad:",i++,inicio,termino,horas,repeticion);
                Intent intent = new Intent(Add_Medicina.this, Menu_principla_Activity.class);
                bundle = new Bundle();
                bundle.putSerializable("medicina",medicamento);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //// Modificar Horario
        btn_modHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nom_medicamento.getText().length() > 0){
                    System.out.println("----> Se ingreso el mediccamento: "+nom_medicamento.getText().toString()+" Con recordatorio: "+recordato.getText().toString());
                    medicamento = new ListElement("#F48888",""+nom_medicamento.getText().toString(),""+recordato.getText().toString(),"Cantidad",i++,inicio,termino,horas,repeticion);
                    modifica_horario(medicamento,frecuncia,repeticion);
                }else{
                    Toast.makeText(getApplicationContext(),"Llenar todos los datos.",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framecontainer_horario,fragment);
        transaction.commit();
    }

    public void cargar_horario(int veces){
        switch (veces){
            case 0:{
                //Caso una al dia
                unavez_Fragment unavez_fragment = new unavez_Fragment();
                loadFragment(unavez_fragment);
                break;
            }
            case 1:{
                //Caso una al dos
                Dos_Veces_Fragment dos_pastilla = new Dos_Veces_Fragment();
                loadFragment(dos_pastilla);
                break;
            }case 2:{
                //Caso una al tres
                    Tres_Veces_Fragment tres_pastilla = new Tres_Veces_Fragment();
                loadFragment(tres_pastilla);
                break;
            }case 3:{
                //Caso una al cuatro
                cuatro_Veces_Fragment cuatro_pastilla = new cuatro_Veces_Fragment();
                loadFragment(cuatro_pastilla);
                break;
            }case 4:{
                //Caso una al dia
                Cinco_Veces_Fragment cinco_pastilla = new Cinco_Veces_Fragment();
                loadFragment(cinco_pastilla);
                break;
            }case 5:{
                //Caso una al dia
                Seis_Veces_Fragment seis_pastilla = new Seis_Veces_Fragment();
                loadFragment(seis_pastilla);
                break;
            }case 6:{
                //Caso una al dia
                Siete_Veces_Fragment siete_pastilla = new Siete_Veces_Fragment();
                loadFragment(siete_pastilla);
                break;
            }case 7:{
                //Caso una al dia
                Ocho_Veces_Fragment ocho_pastilla = new Ocho_Veces_Fragment();
                loadFragment(ocho_pastilla);
                break;
            }case 8:{
                //Caso una al dia
                Nueve_Veces_Fragment nueve_pastilla = new Nueve_Veces_Fragment();
                loadFragment(nueve_pastilla);
                break;
            }case 9:{
                //Caso una al dia
                Diez_Veces_Fragment diez_pastilla = new Diez_Veces_Fragment();
                loadFragment(diez_pastilla);
                break;
            }case 10:{
                //Caso una al dia
                Once_Veces_Fragment once_pastilla = new Once_Veces_Fragment();
                loadFragment(once_pastilla);
                break;
            }case 11:{
                //Caso una al dia
                Doce_Veces_Fragment doce_pastilla = new Doce_Veces_Fragment();
                loadFragment(doce_pastilla);
                break;
            }case 12:{
                //Caso una al dia
                VeintiCuatro_Veces_Fragment veinti_pastilla = new VeintiCuatro_Veces_Fragment();
                loadFragment(veinti_pastilla);
                break;
            } default: break;
        }
    }

    public void modifica_horario(ListElement medicamentoenv, int veces,int periodo){
        switch (veces){
            case 0:{
                //Caso una al dia
                Intent intent = new Intent(Add_Medicina.this, Mod_unavez.class);
                bundle = new Bundle();
                bundle.putSerializable("medicina",medicamentoenv);
                bundle.putInt("periodo",periodo);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }
            case 1:{
                //Caso una al dos
                Dos_Veces_Fragment dos_pastilla = new Dos_Veces_Fragment();
                loadFragment(dos_pastilla);
                break;
            }case 2:{
                //Caso una al tres
                Tres_Veces_Fragment tres_pastilla = new Tres_Veces_Fragment();
                loadFragment(tres_pastilla);
                break;
            }case 3:{
                //Caso una al cuatro
                cuatro_Veces_Fragment cuatro_pastilla = new cuatro_Veces_Fragment();
                loadFragment(cuatro_pastilla);
                break;
            }case 4:{
                //Caso una al dia
                Cinco_Veces_Fragment cinco_pastilla = new Cinco_Veces_Fragment();
                loadFragment(cinco_pastilla);
                break;
            }case 5:{
                //Caso una al dia
                Seis_Veces_Fragment seis_pastilla = new Seis_Veces_Fragment();
                loadFragment(seis_pastilla);
                break;
            }case 6:{
                //Caso una al dia
                Siete_Veces_Fragment siete_pastilla = new Siete_Veces_Fragment();
                loadFragment(siete_pastilla);
                break;
            }case 7:{
                //Caso una al dia
                Ocho_Veces_Fragment ocho_pastilla = new Ocho_Veces_Fragment();
                loadFragment(ocho_pastilla);
                break;
            }case 8:{
                //Caso una al dia
                Nueve_Veces_Fragment nueve_pastilla = new Nueve_Veces_Fragment();
                loadFragment(nueve_pastilla);
                break;
            }case 9:{
                //Caso una al dia
                Diez_Veces_Fragment diez_pastilla = new Diez_Veces_Fragment();
                loadFragment(diez_pastilla);
                break;
            }case 10:{
                //Caso una al dia
                Once_Veces_Fragment once_pastilla = new Once_Veces_Fragment();
                loadFragment(once_pastilla);
                break;
            }case 11:{
                //Caso una al dia
                Doce_Veces_Fragment doce_pastilla = new Doce_Veces_Fragment();
                loadFragment(doce_pastilla);
                break;
            }case 12:{
                //Caso una al dia
                VeintiCuatro_Veces_Fragment veinti_pastilla = new VeintiCuatro_Veces_Fragment();
                loadFragment(veinti_pastilla);
                break;
            } default: break;
        }
    }

    public void cargar_horas (int veces){
        switch (veces){
            case 0:{
                horas.add("(8-0)]");
                break;
            }
            case 1:{
                horas.add("(8-0)");
                horas.add("(20-0)]");
                break;
            }case 2:{
                horas.add("(8-0)");
                horas.add("(14-0)");
                horas.add("(20-0)]");

                break;
            }case 3:{
                horas.add("(8-0)");
                horas.add("(13-0)");
                horas.add("(18-0)");
                horas.add("(23-0)]");
                break;
            }case 4:{
                horas.add("(7-0)");
                horas.add("(10-45)");
                horas.add("(15-30)");
                horas.add("(19-45)");
                horas.add("(20-0)]");
                break;
            }case 5:{
                horas.add("(5-0)");
                horas.add("(9-0)");
                horas.add("(13-0)");
                horas.add("(17-0)");
                horas.add("(21-0)");
                horas.add("(23-0)]");
                break;
            }case 6:{
                //Caso una al dia
                horas.add("(2-0)");
                horas.add("(5-25)");
                horas.add("(8-50)");
                horas.add("(12-15)");
                horas.add("(15-40)");
                horas.add("(19-5)");
                horas.add("(22-30)]");
                break;
            }case 7:{
                //Caso una al dia
                horas.add("(1-0)");
                horas.add("(4-0)");
                horas.add("(7-0)");
                horas.add("(10-0)");
                horas.add("(13-0)");
                horas.add("(16-0)");
                horas.add("(18-0)");
                horas.add("(22-0)]");
                break;
            }case 8:{
                //Caso una al dia
                horas.add("(1-0)");
                horas.add("(3-40)");
                horas.add("(6-20)");
                horas.add("(9-0)");
                horas.add("(11-40)");
                horas.add("(14-20)");
                horas.add("(17-0)");
                horas.add("(19-40)");
                horas.add("(22-20)]");
                break;
            }case 9:{
                //Caso una al dia
                horas.add("(1-0)");
                horas.add("(3-25)");
                horas.add("(5-50)");
                horas.add("(8-10)");
                horas.add("(10-35)");
                horas.add("(13-0)");
                horas.add("(15-25)");
                horas.add("(17-50)");
                horas.add("(20-10)");
                horas.add("(22-35)]");
                break;
            }case 10:{
                //Caso una al dia
                horas.add("(1-0)");
                horas.add("(3-10)");
                horas.add("(5-20)");
                horas.add("(7-30)");
                horas.add("(9-40)");
                horas.add("(11-50)");
                horas.add("(14-0)");
                horas.add("(16-10)");
                horas.add("18-20)");
                horas.add("20-30)");
                horas.add("22-40)]");
                break;
            }case 11:{
                //Caso una al dia
                horas.add("(1-0)");
                horas.add("(2-0)");
                horas.add("(4-0)");
                horas.add("(6-0)");
                horas.add("(10-0)");
                horas.add("(12-0)");
                horas.add("(14-0)");
                horas.add("(16-0)");
                horas.add("(18-0)");
                horas.add("(20-0)");
                horas.add("(22-0)");
                horas.add("(23-55)]");
                break;
            }case 12:{
                //Caso una al dia
                horas.add("(1-0)");
                horas.add("(2-0)");
                horas.add("(3-0)");
                horas.add("(4-0)");
                horas.add("(5-0)");
                horas.add("(6-0)");
                horas.add("(7-0)");
                horas.add("(8-0)");
                horas.add("(9-0)");
                horas.add("(10-0)");
                horas.add("(11-0)");
                horas.add("(12-0)");
                horas.add("(13-0)");
                horas.add("(14-0)");
                horas.add("(15-0)");
                horas.add("(16-0)");
                horas.add("(17-0)");
                horas.add("(18-0)");
                horas.add("(19-0)");
                horas.add("(20-0)");
                horas.add("(21-0)");
                horas.add("(22-0)");
                horas.add("(23-0)");
                horas.add("(23-55)]");
                break;
            } default: break;
        }
    }

    public void alarmas_establecidas (Calendar fecha, String medicamento, String Detalles,int fin){
        int j=0;
        String tag;
        Long AlerTime;
        if (frecuncia == 7){
            frecuncia =1;
        }else{
            frecuncia++;
        }
            j=0;
            do{
                tag = medicamento+"a"+fecha.get(Calendar.YEAR)+"m"+fecha.get(Calendar.MONTH)+"d"+fecha.get(Calendar.DAY_OF_MONTH)+"h"+fecha.get(Calendar.HOUR_OF_DAY);
                System.out.println("Alarma programada para: "+fecha.getTime().toString()+"ID: "+tag);
                AlerTime = fecha.getTimeInMillis() - System.currentTimeMillis();
                System.out.println("El tiempo para esta alarma es: "+AlerTime);
                Data data = GuardarData(medicamento,Detalles,10);
                //WorkManager_noti.Guardarnoti(AlerTime,data,tag);
                fecha.setTime(sumarRestarDiasFecha(fecha.getTime(),frecuncia));
                j=j+frecuncia;
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

    private void Generar_Alarmas(int veces,Calendar fecha, String medicamento, String detalles, int fin){
        switch (veces){
            case 0:{
                //(8-0)]");
                fecha.set(Calendar.HOUR_OF_DAY,8);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                break;
            }
            case 1:{
                //"(8-0)");
                //"(20-0)]");
                fecha.set(Calendar.HOUR_OF_DAY,8);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,20);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                break;
            }case 2:{
                //horas.add("(8-0)");horas.add("(14-0)");horas.add("(20-0)]");
                fecha.set(Calendar.HOUR_OF_DAY,8);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,14);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,20);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                break;
            }case 3:{
                //horas.add("(8-0)");horas.add("(13-0)");horas.add("(18-0)");horas.add("(23-0)]");
                fecha.set(Calendar.HOUR_OF_DAY,8);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,13);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,18);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,23);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                break;
            }case 4:{
                //horas.add("(7-0)");horas.add("(10-45)");horas.add("(15-30)");horas.add("(19-45)");horas.add("(20-0)]");
                fecha.set(Calendar.HOUR_OF_DAY,7);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,10);
                fecha.set(Calendar.MINUTE,45);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,15);
                fecha.set(Calendar.MINUTE,30);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,19);
                fecha.set(Calendar.MINUTE,45);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,20);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                break;
            }case 5:{
                //horas.add("(5-0)");horas.add("(9-0)");horas.add("(13-0)");horas.add("(17-0)");horas.add("(21-0)");horas.add("(23-0)]");
                fecha.set(Calendar.HOUR_OF_DAY,5);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,9);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,13);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,17);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,21);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,23);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                break;
            }case 6:{
                //Caso una al dia
                //horas.add("(2-0)");horas.add("(5-25)");horas.add("(8-50)");horas.add("(12-15)");horas.add("(15-40)");horas.add("(19-5)");horas.add("(22-30)]");
                fecha.set(Calendar.HOUR_OF_DAY,2);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,5);
                fecha.set(Calendar.MINUTE,25);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,8);
                fecha.set(Calendar.MINUTE,50);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,12);
                fecha.set(Calendar.MINUTE,15);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,15);
                fecha.set(Calendar.MINUTE,40);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,19);
                fecha.set(Calendar.MINUTE,5);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,22);
                fecha.set(Calendar.MINUTE,30);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                break;
            }case 7:{
                //Caso una al dia
                //horas.add("(1-0)");horas.add("(4-0)");horas.add("(7-0)");horas.add("(10-0)");horas.add("(13-0)");horas.add("(16-0)");horas.add("(18-0)");horas.add("(22-0)]");
                fecha.set(Calendar.HOUR_OF_DAY,1);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,4);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,7);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,10);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,13);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,16);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,18);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,22);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);

                break;
            }case 8:{
                //Caso una al dia
                //horas.add("(1-0)");horas.add("(3-40)");horas.add("(6-20)");horas.add("(9-0)");horas.add("(11-40)");horas.add("(14-20)");horas.add("(17-0)");horas.add("(19-40)");horas.add("(22-20)]");
                fecha.set(Calendar.HOUR_OF_DAY,1);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,3);
                fecha.set(Calendar.MINUTE,40);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,6);
                fecha.set(Calendar.MINUTE,20);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,9);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,11);
                fecha.set(Calendar.MINUTE,40);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,14);
                fecha.set(Calendar.MINUTE,20);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,17);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,19);
                fecha.set(Calendar.MINUTE,40);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                break;
            }case 9:{
                //Caso una al dia
                //horas.add("(1-0)");horas.add("(3-25)");horas.add("(5-50)");horas.add("(8-10)");
                // horas.add("(10-35)");horas.add("(13-0)");horas.add("(15-25)");horas.add("(17-50)");horas.add("(20-10)");horas.add("(22-35)]");
                fecha.set(Calendar.HOUR_OF_DAY,1);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,3);
                fecha.set(Calendar.MINUTE,25);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,5);
                fecha.set(Calendar.MINUTE,50);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,8);
                fecha.set(Calendar.MINUTE,10);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,10);
                fecha.set(Calendar.MINUTE,35);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,13);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,15);
                fecha.set(Calendar.MINUTE,25);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,17);
                fecha.set(Calendar.MINUTE,50);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,20);
                fecha.set(Calendar.MINUTE,10);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,25);
                fecha.set(Calendar.MINUTE,35);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                break;
            }case 10:{
                //Caso una al dia
                //horas.add("(1-0)");horas.add("(3-10)");horas.add("(5-20)");horas.add("(7-30)");horas.add("(9-40)");
                // horas.add("(11-50)");horas.add("(14-0)");horas.add("(16-10)");horas.add("18-20)");horas.add("20-30)");horas.add("22-40)]");
                fecha.set(Calendar.HOUR_OF_DAY,1);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,3);
                fecha.set(Calendar.MINUTE,10);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,5);
                fecha.set(Calendar.MINUTE,20);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,7);
                fecha.set(Calendar.MINUTE,30);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,9);
                fecha.set(Calendar.MINUTE,40);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,11);
                fecha.set(Calendar.MINUTE,50);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,14);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,16);
                fecha.set(Calendar.MINUTE,10);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,18);
                fecha.set(Calendar.MINUTE,20);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,20);
                fecha.set(Calendar.MINUTE,30);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,22);
                fecha.set(Calendar.MINUTE,40);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                break;
            }case 11:{
                //Caso una al dia
                //horas.add("(1-0)");horas.add("(2-0)");horas.add("(4-0)");horas.add("(6-0)");horas.add("(10-0)");horas.add("(12-0)");
                // horas.add("(14-0)");horas.add("(16-0)");horas.add("(18-0)");horas.add("(20-0)");horas.add("(22-0)");horas.add("(23-55)]");
                fecha.set(Calendar.HOUR_OF_DAY,1);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,2);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,4);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,6);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,10);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,12);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,14);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,16);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,18);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,20);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,22);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,23);
                fecha.set(Calendar.MINUTE,55);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                break;
            }case 12:{
                //Caso una al dia
                //horas.add("(1-0)");horas.add("(2-0)");horas.add("(3-0)");horas.add("(4-0)");horas.add("(5-0)");horas.add("(6-0)");
                // horas.add("(7-0)");horas.add("(8-0)");horas.add("(9-0)");horas.add("(10-0)");horas.add("(11-0)");horas.add("(12-0)");horas.add("(13-0)");horas.add("(14-0)");horas.add("(15-0)");horas.add("(16-0)");horas.add("(17-0)");horas.add("(18-0)");horas.add("(19-0)");horas.add("(20-0)");horas.add("(21-0)");horas.add("(22-0)");horas.add("(23-0)");horas.add("(23-55)]");
                fecha.set(Calendar.HOUR_OF_DAY,1);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,2);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,3);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,4);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,5);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,6);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,7);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,8);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,9);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,10);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,11);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,12);
                fecha.set(Calendar.MINUTE,55);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,13);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,14);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,15);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,16);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,17);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,18);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,19);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,20);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,21);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,22);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,23);
                fecha.set(Calendar.MINUTE,0);
                alarmas_establecidas(fecha,medicamento,detalles,fin);
                fecha.set(Calendar.HOUR_OF_DAY,23);
                fecha.set(Calendar.MINUTE,55);
                alarmas_establecidas(fecha,medicamento,detalles,fin);

                break;
            } default: break;
        }
    }

}