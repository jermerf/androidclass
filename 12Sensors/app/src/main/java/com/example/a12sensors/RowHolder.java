package com.example.a12sensors;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RowHolder extends RecyclerView.ViewHolder {
    private TextView x;
    private TextView y;
    private TextView z;

    public RowHolder(@NonNull View itemView) {
        super(itemView);
        x = itemView.findViewById(R.id.txtRX);
        y = itemView.findViewById(R.id.txtRY);
        z = itemView.findViewById(R.id.txtRZ);
    }

    public void bindModel(AccelSet set){
        x.setText("X:" + set.getX());
        y.setText("Y:" + set.getX());
        z.setText("Z:" + set.getX());
    }
}
