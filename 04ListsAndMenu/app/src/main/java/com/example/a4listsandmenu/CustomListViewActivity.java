package com.example.a4listsandmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.a4listsandmenu.adapter.IconAdapter;
import com.example.a4listsandmenu.adapter.MyCustomAdapter;
import com.example.a4listsandmenu.model.CatPost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomListViewActivity extends AppCompatActivity {
    EditText txtTitle;
    EditText txtContent;
    Spinner spinIcon;
    ListView listItems;

    List<CatPost> items = new ArrayList<>();
    MyCustomAdapter adapter;
    String[] icons = {"Camera", "Phone", "Wrench"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        txtTitle = findViewById(R.id.txtItemTitle);
        txtContent = findViewById(R.id.txtItemContent);
        spinIcon = findViewById(R.id.spinIcon);
        listItems = findViewById(R.id.listItems);

        spinIcon.setAdapter(new IconAdapter(this, Arrays.asList(icons)));
        adapter = new MyCustomAdapter(this, items);
        listItems.setAdapter(adapter);
    }

    public void addClicked(View v) {
        int iconResource = IconAdapter.getIconResourceFromName(spinIcon.getSelectedItem().toString());
        items.add(new CatPost(
                txtTitle.getText().toString(),
                txtContent.getText().toString(), iconResource));
        txtTitle.setText("");
        txtContent.setText("");
        adapter.notifyDataSetChanged();

    }

}