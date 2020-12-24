package com.example.tt_logging;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tt_logging.Receta.Add_Alarma;
import com.example.tt_logging.Receta.Add_Medicina;

import java.util.List;



/// Fragment de usuario

public class CuartoFragment extends Fragment {

    Button add_PersonaCuidado;
    Button add_VerNotas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cuarto, container, false);
        add_PersonaCuidado = view.findViewById(R.id.persona_acargo);
        add_VerNotas = view.findViewById(R.id.notas_paciente);
        add_PersonaCuidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"agregar medicina",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), PersonasCuidado_Activity.class));
            }
        });
        add_VerNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"agregar medicina",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), Ver_Notas_Activity.class));
            }
        });
        return view;
    }
}