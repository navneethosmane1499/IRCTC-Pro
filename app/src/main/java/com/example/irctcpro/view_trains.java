package com.example.irctcpro;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class view_trains extends AppCompatActivity {

    DatabaseHelperT DB = new DatabaseHelperT(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trains);

        Cursor res = DB.get_train();

        String[] trains_names = new String[res.getCount()];
        String[] trains_no = new String[res.getCount()];
        String[] trains_type  = new String[res.getCount()];

        int i = 0;
        while (res.moveToNext()) {
            trains_no[i] = res.getString(0);
            trains_names[i] = res.getString(1);
            trains_type[i++] = res.getString(2);
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter3_VT(trains_names, trains_no, trains_type));
    }

    public void view_train(View view){
        TextView textView = view.findViewById(R.id.train_no);
        String train_no = textView.getText().toString();
        String[] res = DB.getTrainNameType(train_no);
        StringBuilder buffer = new StringBuilder();
        buffer.append("Train Number : ").append(train_no).append("\nTrain Name : ").append(res[0]).append("\nTrain Type : ").append(res[1]);
        Cursor res1 = DB.getSource(train_no);
        if(res1.moveToNext()){
            buffer.append("\nSource : ").append(res1.getString(0)).append(" - ").append(res1.getString(1));
            res1 = DB.getDestination(train_no);
            res1.moveToNext();
            buffer.append("\nDestination : ").append(res1.getString(0)).append(" - ").append(res1.getString(1));
        } else
            buffer.append("\nTrain currently not Servicing!");
        show_msg("Train Information", buffer.toString());
    }

    public void show_msg(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }

}
