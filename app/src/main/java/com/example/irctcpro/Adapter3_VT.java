package com.example.irctcpro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter3_VT extends RecyclerView.Adapter<Adapter3_VT.ViewHolder> {

    private String[] name, num, type;
    Adapter3_VT(String[] name, String[] num, String[] type) {
        this.name = name;
        this.num = num;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_single_layout_3, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.train_type.setText(type[position]);
            holder.train_name.setText(name[position]);
            holder.train_number.setText(num[position]);
    }


    @Override
    public int getItemCount() {
        return name.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView train_number, train_type, train_name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            train_number = itemView.findViewById(R.id.train_no);
            train_type = itemView.findViewById(R.id.train_type);
            train_name = itemView.findViewById(R.id.train_name);
        }
    }
}