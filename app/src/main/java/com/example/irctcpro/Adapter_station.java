package com.example.irctcpro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_station extends RecyclerView.Adapter<Adapter_station.ViewHolder>{

    private String[] name, num, type;
    Adapter_station(String[] name, String[] num, String[] type) {
        this.name = name;
        this.num = num;
        this.type = type;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_single_layout_station, parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.station_number.setText("Station No.: " +  num[position]);
        holder.station_name.setText("Station Name: "+name[position]);
        holder.station_type.setText("Station Type: " + type[position]);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView station_number, station_type, station_name;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            station_number = itemView.findViewById(R.id.station_no);
            station_type = itemView.findViewById(R.id.station_type);
            station_name = itemView.findViewById(R.id.station_name);
        }
    }
}
