package com.example.tt_logging.Receta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
    private int i, anio, mes, dia;
    private Button selefecha_inicio , selefecha_final;
    private Spinner spinner_frecuncia;
    private Spinner spinner_veces;
    private Calendar inicio, termino;
    private Calendar actual;
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
                        if(d == dia){
                            d++;
                        }
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
        horas.add("("+inicio.get(Calendar.HOUR_OF_DAY)+"-"+inicio.get(Calendar.MINUTE)+")");
            /// FINALIZAR
                btn_medicina = (Button)findViewById(R.id.button_add_medi);
                btn_medicina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // horas: (16-30)
                horas.add("("+inicio.get(Calendar.HOUR_OF_DAY)+"-"+inicio.get(Calendar.MINUTE));
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
}