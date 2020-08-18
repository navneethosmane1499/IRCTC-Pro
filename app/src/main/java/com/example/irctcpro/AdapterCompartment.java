package com.example.irctcpro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterCompartment extends RecyclerView.Adapter<AdapterCompartment.ViewHolder> {

    private String[] data;
    AdapterCompartment(String[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_template_compartment, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setHint(data[position]);
        if(position < 2) {
            String message = "General " + String.valueOf(position + 1);
            holder.textView.setText(message);
        } else if(position < 5) {
            String message = "Semi Sleeper S" + String.valueOf(position - 1);
            holder.textView.setText(message);
        } else if(position < 8) {
            String message = "Air Conditioned A" + String.valueOf(position - 4);
            holder.textView.setText(message);
        } else {
            String message = "General " + String.valueOf(position - 5);
            holder.textView.setText(message);
        }
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.compartment_no);
        }
    }
}