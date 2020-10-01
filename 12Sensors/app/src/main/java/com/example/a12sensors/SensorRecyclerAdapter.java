package com.example.a12sensors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SensorRecyclerAdapter extends RecyclerView.Adapter<RowHolder> {
    private List<AccelSet> list = new ArrayList<>();
    private Context context;

    public SensorRecyclerAdapter(Context context){
        this.context = context;
    }

    public void addAccelSet(AccelSet set) {
            list.add(set);
            notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.recycler_row_accel_set, parent, false);
        RowHolder row = new RowHolder(rowView);
        return row;
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        AccelSet set = list.get(position);
        holder.bindModel(set);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
