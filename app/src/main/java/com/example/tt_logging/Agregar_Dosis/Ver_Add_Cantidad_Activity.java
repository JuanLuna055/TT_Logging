package com.example.tt_logging.Agregar_Dosis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tt_logging.Menu_principla_Activity;
import com.example.tt_logging.R;
import com.example.tt_logging.Receta.ListElement;

import java.util.Calendar;

public class Ver_Add_Cantidad_Activity extends AppCompatActivity {

    private TextView medicamento, Status, fecha, cantidad;
    private EditText new_Cantidad;
    private Button btn_agregar_Cantidad, cancelar;
    private String id_shared;
    private int nueva_Cant;
    private ListElement new_med;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__add__cantidad);
        new_med = (ListElement) getIntent().getSerializableExtra("medicina");
        System.out.println("Llego el medicamenot:id "+new_med.getId_medicamento());
        medicamento = findViewById(R.id.TxtView_Medic_Dosis);
        Status = findViewById(R.id.txtView_status_Dosis);
        fecha = findViewById(R.id.TxtView_Termina_Dosis);
        cantidad= findViewById(R.id.TextView_Cantidad_Dosisi);
        new_Cantidad = findViewById(R.id.EditText_CantidadDosis);
        btn_agregar_Cantidad = (Button) findViewById(R.id.button_add_CantidadDosis);
        cancelar = findViewById(R.id.btn_Cancelar);
        nueva_Cant = 1;

        //Enviar Informacion
        medicamento.setText(""+new_med.getMedicamento());
        Status.setText("Status: "+new_med.getStatus());
        cantidad.setText("Dosis Actual: "+new_med.getCantidad());
        fecha.setText("Fin del Tratamiento: "+new_med.getTermina().get(Calendar.DAY_OF_MONTH)+
                "/"+new_med.getTermina().get(Calendar.MONTH)+"/"+new_med.getTermina().get(Calendar.YEAR));

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_agregar_Cantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nueva_Cant = Integer.parseInt(new_Cantidad.getText().toString());
                System.out.println("El id sin cambios es "+new_med.getId_medicamento());
                analizar_Id_medi(new_med);
                agregar_cantidad(nueva_Cant);
                System.out.println("El nuevo id:"+new_med.getId_medicamento());
                guardar_shared_nuevo_IdMed(new_med.getId_medicamento());
                startActivity(new Intent(Ver_Add_Cantidad_Activity.this, Menu_principla_Activity.class));
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

    public void agregar_cantidad(int cantidad){
        if (cantidad > 0){
            new_med.setCantidad(new_med.getCantidad()+cantidad);
            Toast.makeText(this,"Se guardo la cantidad correctamente.",Toast.LENGTH_SHORT);
        }else{
            Toast.makeText(this, "No se puede agregar cantidades negativas", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardar_shared_nuevo_IdMed(String id_med){
        System.out.println("Guardaremos el medicamento");
        SharedPreferences datos = getSharedPreferences("notificaciones",MODE_PRIVATE);
        SharedPreferences.Editor miEditor = datos.edit();
        miEditor.remove(id_shared);
        miEditor.apply();
        miEditor.putString(id_shared,id_med);
        miEditor.apply();
    }


}