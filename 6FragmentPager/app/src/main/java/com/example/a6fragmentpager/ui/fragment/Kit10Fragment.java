package com.example.a6fragmentpager.ui.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a6fragmentpager.R;

public class Kit10Fragment extends Fragment {
    private String name;

    public Kit10Fragment(String name) {
            this.name = name;
    }

    public String getName() {
        return name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag = inflater.inflate(R.layout.fragment_kit10, container, false);
        Switch swLaser = frag.findViewById(R.id.swLaser);

        swLaser.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                swLaser.setText("--------*");
            }else{
                swLaser.setText(".........");
            }
        });
        return frag;
    }

}
