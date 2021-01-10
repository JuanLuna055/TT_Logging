package com.example.tt_logging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tt_logging.Receta.ListElement;

import java.util.List;

public class  MedicamentoAdaptador extends RecyclerView.Adapter<MedicamentoAdaptador.MedicamentoViewHolder>{
    private List<ListElement> medicamentos;
    private LayoutInflater mInflate;
    private Context context;
    final  MedicamentoAdaptador.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ListElement item);
    }

    public MedicamentoAdaptador(List<ListElement> medicamentos, Context context, MedicamentoAdaptador.OnItemClickListener listener){
        this.mInflate = LayoutInflater.from(context);
        this.context = context;
        this.medicamentos = medicamentos;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {//Cantidad de elementos
        return medicamentos.size();
    }

    @NonNull
    @Override
    public MedicamentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_medicamentos, parent,false);

        return new MedicamentoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MedicamentoViewHolder medicamentoViewHolder, final int position) {
        medicamentoViewHolder.bindData(medicamentos.get(position));
    }

    public void setItems(List<ListElement> items){
        medicamentos = items;
    }

    public class MedicamentoViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMedicamentoNombre;
        private TextView tvMedicamentoEfecto;
        private CardView cv;

        public MedicamentoViewHolder( @NonNull View itemView) {
            super(itemView);
            tvMedicamentoNombre =(TextView) itemView.findViewById(R.id.tvMedicamentoNombrecv);
            tvMedicamentoEfecto =(TextView) itemView.findViewById(R.id.tvMedicamentoDescripcion);
            cv = (CardView) itemView.findViewById(R.id.cvMedicamentos);
        }

        void bindData(final ListElement item){
            tvMedicamentoNombre.setText(item.getMedicamento());
            tvMedicamentoEfecto.setText(item.getRecordatorio());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}