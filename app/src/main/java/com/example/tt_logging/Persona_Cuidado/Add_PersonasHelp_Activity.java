package com.example.tt_logging.Persona_Cuidado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tt_logging.Menu_principla_Activity;
import com.example.tt_logging.R;
import com.example.tt_logging.Receta.Add_Alarma;
import com.example.tt_logging.Receta.Add_Medicina;

import java.util.Calendar;

public class Add_PersonasHelp_Activity extends AppCompatActivity {

    private EditText name,parentezco,telefono;
    private Calendar inicio;
    private ListPersonaCuidado persona;
    private Button btnSave_persona;
    String id_personahelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__personas_help);

        name = (EditText) findViewById(R.id.TxtnombrePersonaHelp);
        parentezco = (EditText) findViewById(R.id.TxtParentezco);
        telefono = (EditText) findViewById(R.id.telefono);
        inicio = Calendar.getInstance();
        btnSave_persona = findViewById(R.id.btn_guardarPersona);



        leer_shared_personaHelp();
        btnSave_persona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persona = new ListPersonaCuidado("#000000",name.getText().toString(),parentezco.getText().toString()
                        ,inicio.getTime().toString(),telefono.getText().toString());
                id_personahelp = persona.getId_personahelp();
                System.out.println("id obtenido Persona: "+id_personahelp);
                guardar_shared_PersonaHelp(id_personahelp);
                startActivity(new Intent(Add_PersonasHelp_Activity.this, Menu_principla_Activity.class));
                finish();
            }
        });

    }

    private void guardar_shared_PersonaHelp(String persona){
        System.out.println("Guardaremos la Persona");
        SharedPreferences datos = getSharedPreferences("persona_help",MODE_PRIVATE);
        SharedPreferences.Editor miEditor = datos.edit();
        boolean ans=true;
        int i=0;
        do {

            if(datos.getString("id_personaHelp"+i,"error") == "error" ){
                System.out.println("Se guardo el no: id_personaHelp"+i);
                miEditor.putString("id_personaHelp"+i,persona);
                miEditor.apply();
                break;
            }
            System.out.println("--valor de i: "+i);
            i++;
        }while (i<10);
    }

    private void leer_shared_personaHelp(){
        SharedPreferences datos = getSharedPreferences("persona_help",MODE_PRIVATE);
        System.out.println("Leemos de Shared...");
        int i=0;
        do{
            if(datos.getString("id_personaHelp"+i,"error") != "error"){
                System.out.println("Se pudo leer el id_nota"+i);

            }
            i++;
        }while (i<10);
    }
}