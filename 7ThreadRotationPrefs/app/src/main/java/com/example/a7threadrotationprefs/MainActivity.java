package com.example.a7threadrotationprefs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.a7threadrotationprefs.config.AppSettings;

public class MainActivity extends AppCompatActivity {
    private TextView txtFluffiness;
    private TextView txtRole;
    private TextView txtStatus;

    private AppSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtFluffiness = findViewById(R.id.txtFluffiness);
        txtRole = findViewById(R.id.txtRole);
        txtStatus = findViewById(R.id.txtStatus);
        settings = new AppSettings(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setUi();
    }

    @Override
    protected void onResume() {
        setUi();
        super.onResume();
    }

    private void setUi(){
        txtFluffiness.setText(settings.getFluffiness()+"");
        txtRole.setText(settings.getRole());
        txtStatus.setText(settings.getStatus().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menuSettings:
                Intent menuIntent = new Intent(this, SettingsActivity.class);
                startActivity(menuIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}