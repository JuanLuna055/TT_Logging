package com.example.tt_logging.Receta;

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

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements  View.OnClickListener{

    private List<ListElement> mData;
    private LayoutInflater mInflater;

    //listener
    private View.OnClickListener listener;
    //private Context context;

    public ListAdapter(List<ListElement> itemsList, Context context){
        mInflater = LayoutInflater.from(context);
        //  this.context = context;
        mData = itemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener Listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        String medicina = mData.get(position).getMedicamento();
        String recordatorio = mData.get(position).getRecordatorio();
        String status = mData.get(position).getStatus();
        holder.medicamento.setText(medicina);
        holder.recordatorio.setText(recordatorio);
        holder.status.setText(status);
        //holder.iconImagen.setColorFilter();
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImagen;
        TextView medicamento, recordatorio, status;

        public ViewHolder (View itemView){
            super(itemView);
            iconImagen =(ImageView) itemView.findViewById(R.id.iconImagenView);
            medicamento = (TextView)itemView.findViewById(R.id.name_medicamento);
            recordatorio =(TextView) itemView.findViewById(R.id.recordatorio);
            status = (TextView) itemView.findViewById(R.id.statusView);
        }

        void bindData(final ListElement item){
            iconImagen.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            medicamento.setText(item.getMedicamento());
            recordatorio.setText(item.getRecordatorio());
            status.setText(item.getStatus());
        }
    }
}
