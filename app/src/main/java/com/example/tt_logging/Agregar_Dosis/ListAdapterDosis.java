package com.example.tt_logging.Agregar_Dosis;

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

import java.util.List;

public class ListAdapterDosis extends RecyclerView.Adapter<ListAdapterDosis.ViewHolder> {

    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    final ListAdapterDosis.OnItemClickListener listener;
    //listener

    public interface OnItemClickListener{
        void OnItemClick(ListElement item);
    }

    // Commit 2
    //private View.OnClickListener listener;
    //private Context context;

    public ListAdapterDosis(List<ListElement> itemsList, Context context, ListAdapterDosis.OnItemClickListener listener){
        mInflater = LayoutInflater.from(context);
        this.context = context;
        mData = itemsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_dosis,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterDosis.ViewHolder holder, int position) {
        String medicina = mData.get(position).getMedicamento();
        String status = mData.get(position).getStatus();
        String cantidad =""+mData.get(position).getCantidad();
        holder.medicamento.setText(medicina);
        holder.cantidad.setText(cantidad);
        holder.status.setText(status);
        //holder.iconImagen.setColorFilter();
        holder.bindData(mData.get(position));
        System.out.println("Posicion escogida es: "+position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImagen;
        TextView medicamento, cantidad, status;

        public ViewHolder (View itemView){
            super(itemView);
            iconImagen =(ImageView) itemView.findViewById(R.id.iconImagenViewdosis);
            medicamento = (TextView)itemView.findViewById(R.id.name_medicamento_dosis);
            cantidad =(TextView) itemView.findViewById(R.id.Cantidad_View_dosis);
            status = (TextView) itemView.findViewById(R.id.Status_dosis);
        }

        void bindData(final ListElement item){
            iconImagen.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            medicamento.setText(item.getMedicamento());
            cantidad.setText("Cantidad: "+item.getCantidad());
            status.setText(item.getStatus());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClick(item);
                }
            });
        }
    }
}