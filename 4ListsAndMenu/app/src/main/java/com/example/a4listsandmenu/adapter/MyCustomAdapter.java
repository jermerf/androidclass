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

public class MyCustomAdapter extends ArrayAdapter<CatPost> {

    public MyCustomAdapter(Context ctx, List<CatPost> list) {
        super(ctx, R.layout.list_item_custom, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_custom, parent, false);
        }
        ImageView icon = convertView.findViewById(R.id.imgIcon);
        TextView title = convertView.findViewById(R.id.lblItemTitle);
        TextView content = convertView.findViewById(R.id.lblItemContent);
        icon.setImageResource(getItem(position).getIconResource());
        title.setText(getItem(position).getTitle());
        content.setText(getItem(position).getContent());
        return convertView;
    }
}
