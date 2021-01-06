package com.example.tt_logging.Notas;

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

import java.util.List;


public class AdapterNotas extends RecyclerView.Adapter<AdapterNotas.ViewHolder> implements View.OnClickListener {
    List<Notas> model;
    LayoutInflater inflarter;
    private Context context;
    private View.OnClickListener listener;
    public AdapterNotas(List<Notas> model, Context context){
        this.inflarter = LayoutInflater.from(context);
        this.model = model;
        this.context= context;
    }
        @NonNull
        @Override
    public AdapterNotas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflarter.inflate(R.layout.lista_notas,null);

        return new AdapterNotas.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotas.ViewHolder holder, int position) {
        holder.bindData(model.get(position));
    }

    @Override
    public int getItemCount() {
        return model.size();
    }
    public void setOnClickListener(View.OnClickListener Listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
            if(listener != null){
                listener.onClick(view);
            }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nota,fecha;
        ImageView iconoNota;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconoNota =(ImageView) itemView.findViewById(R.id.iconImagenViewNota);
            nota = itemView.findViewById(R.id.nota);
            fecha = itemView.findViewById(R.id.fecha_nota);
        }

        void bindData(final Notas item){
            iconoNota.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            nota.setText(item.getNota());
            fecha.setText(item.getFecha());
        }
    }

}
