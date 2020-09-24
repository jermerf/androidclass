package com.example.a6fragmentpager.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a6fragmentpager.R;

public class Kit1Fragment extends Fragment {
    private String name;

    public Kit1Fragment(String name) {
            this.name = name;
    }

    public String getName() {
        return name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frag = inflater.inflate(R.layout.fragment_kit1, container, false);
        Button btnKit1 = frag.findViewById(R.id.btnKit1);
        btnKit1.setOnClickListener( v -> Toast.makeText(getActivity(), "Meowch", Toast.LENGTH_SHORT).show());

        return frag;
    }
}
