package com.example.irctcpro;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterTrains extends RecyclerView.Adapter<AdapterTrains.ViewHolder> {

    private String[] trainNos;
    private  String[] trainNames;
    AdapterTrains(String[] trainNos, String[] trainNames) {
        this.trainNos = trainNos;
        this.trainNames = trainNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_single_layout, parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setHint(trainNos[position]);
        holder.textView.setText(trainNos[position] + " - " + trainNames[position]);
    }

    @Override
    public int getItemCount() {
        return trainNos.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
