package com.example.tt_logging.Persona_Cuidado;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tt_logging.R;
import com.example.tt_logging.Receta.ListElement;


import java.util.ArrayList;
import java.util.List;

public class ListAdapterPersona extends RecyclerView.Adapter<ListAdapterPersona.ViewHolder> implements View.OnClickListener {

    private List<ListPersonaCuidado> lista_personas;
    private LayoutInflater inflater;
    private Context context;
    //Listener
    private View.OnClickListener listener;

    public ListAdapterPersona(List<ListPersonaCuidado> lista_personas, Context context) {
        this.lista_personas = lista_personas;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapterPersona.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_persona,null);

        return new ListAdapterPersona.ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterPersona.ViewHolder holder, int position) {
        holder.bindData(lista_personas.get(position));
    }

    @Override
    public int getItemCount() {
        return lista_personas.size();
    }

    @Override
    public void onClick(View view) {
        if(lista_personas != null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre,fecha,telefono;
        ImageView iconoPersona;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconoPersona =(ImageView) itemView.findViewById(R.id.iconImagenViewP);
            nombre = (TextView)itemView.findViewById(R.id.name_persona);
            fecha =(TextView) itemView.findViewById(R.id.fecha_cuidado);
            telefono = (TextView) itemView.findViewById(R.id.telefono);
        }

        void bindData(final ListPersonaCuidado item){
            iconoPersona.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            nombre.setText(item.getName_persona());
            fecha.setText(item.getFecha_cuidado());
            telefono.setText(item.getTelefono());
        }
    }

}
