package com.example.irctcpro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterSeats extends RecyclerView.Adapter<AdapterSeats.ViewHolder> {

    private int offset;
    private String data;
    AdapterSeats(String data, int offset) {
        this.data = data;
        this.offset = offset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_single_layout2, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.button.setText(String.valueOf(position*4 + offset));
        if (data.charAt(position) == 'b')
            holder.button.setBackgroundResource(R.drawable.grey_seat);
        else
            holder.button.setBackgroundResource(R.drawable.green_seat);
    }

    @Override
    public int getItemCount() {
        return data.length();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        Button button;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.seat);
        }
    }
}