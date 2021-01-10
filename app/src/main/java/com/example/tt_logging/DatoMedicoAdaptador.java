package com.example.tt_logging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DatoMedicoAdaptador extends RecyclerView.Adapter<DatoMedicoAdaptador.DatoMedicoViewHolder> {
    private List<DatoMedico> datos;
    private LayoutInflater mInflater;
    private Context context;
    final  DatoMedicoAdaptador.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(DatoMedico item);
    }

    public DatoMedicoAdaptador(List<DatoMedico> datos,  Context context, DatoMedicoAdaptador.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.datos = datos;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    @NonNull
    @Override
    public DatoMedicoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.from(parent.getContext()).inflate(R.layout.list_datos_medicos, parent, false);

        return new DatoMedicoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DatoMedicoViewHolder datoMedicoViewHolder, final int position) {
        datoMedicoViewHolder.bindData(datos.get(position));
    }

    public void setItems(List<DatoMedico> items){
        datos = items;
    }

    public class DatoMedicoViewHolder extends RecyclerView.ViewHolder{
        private TextView tvDatoNombre;
        private TextView tvDatoDescripcion;
        private CardView cv;

        public DatoMedicoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDatoNombre = (TextView)itemView.findViewById(R.id.tvDatoNombre);
            tvDatoDescripcion = (TextView)itemView.findViewById(R.id.tvDatoDescripcion);
            cv = (CardView) itemView.findViewById(R.id.cvDatosMedicosrv);
        }

        void bindData(final DatoMedico item){
            tvDatoNombre.setText(item.getTipo());
            tvDatoDescripcion.setText(String.valueOf(item.getVar()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
