package com.example.a4listsandmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtItem;
    ListView listItems;

    List<String> items = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtItem = findViewById(R.id.txtItemTitle);
        listItems = findViewById(R.id.listItems);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        listItems.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menuAdvanced:
                Intent customListIntent = new Intent(this, CustomListViewActivity.class);
                startActivity(customListIntent);
                break;
            case R.id.menuQuit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addClicked(View v){
        items.add(txtItem.getText().toString());
        txtItem.setText("");
        adapter.notifyDataSetChanged();
    }
}