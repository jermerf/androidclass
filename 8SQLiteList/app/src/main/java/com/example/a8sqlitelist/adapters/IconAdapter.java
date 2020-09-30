package com.example.a8sqlitelist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.a8sqlitelist.R;
import com.example.a8sqlitelist.model.IconResource;

import java.util.Arrays;
import java.util.List;

public class IconAdapter extends ArrayAdapter<IconResource> {

    public IconAdapter(Context ctx){
        super(ctx, android.R.layout.simple_list_item_1, IconResource.values());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_icon, parent, false);
        }
        IconResource icon = getItem(position);
        ImageView imgIcon = convertView.findViewById(R.id.imgIcon);
        TextView lblIcon = convertView.findViewById(R.id.lblIcon);

        imgIcon.setImageResource(icon.getResource());
        lblIcon.setText(icon.toString());
        return convertView;
    }



}
