package com.example.accelerometable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.accelerometable.adapters.UserAdapter;
import com.example.accelerometable.model.User;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listUsers;
    private EditText txtNewUsername;

    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listUsers = findViewById(R.id.listUsers);
        txtNewUsername = findViewById(R.id.txtNewUsername);

        userAdapter = new UserAdapter(this);
        listUsers.setAdapter(userAdapter);
        listUsers.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public void addUser(View v) {
        User user = userAdapter.addNewUser(txtNewUsername.getText().toString());
        toastShort("Created user " + user.getUsername());
    }


    private void toastShort(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void toastLong(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}