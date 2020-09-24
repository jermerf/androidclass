package com.example.a4listsandmenu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.a4listsandmenu.R;
import com.example.a4listsandmenu.model.CatPost;

import java.util.List;

public class IconAdapter extends ArrayAdapter<String> {

    public IconAdapter(Context ctx, List<String> list){
        super(ctx, android.R.layout.simple_list_item_1, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_icon, parent, false);
        }
        String name = getItem(position);
        ImageView imgIcon = convertView.findViewById(R.id.imgIcon);
        TextView lblIcon = convertView.findViewById(R.id.lblIcon);
        imgIcon.setImageResource(getIconResourceFromName(name));
        lblIcon.setText(name);
        return convertView;
    }

    public static int getIconResourceFromName(String name){
        switch(name){
            case "Camera": return android.R.drawable.ic_menu_camera;
            case "Phone":  return android.R.drawable.ic_menu_call;
            case "Wrench": return android.R.drawable.ic_menu_manage;
            default: return android.R.drawable.ic_menu_close_clear_cancel;
        }
    }
}
