package com.example.accelerometable.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accelerometable.GameActivity;
import com.example.accelerometable.R;
import com.example.accelerometable.model.User;

public class UserHolder extends RecyclerView.ViewHolder {
    private TextView txtUsername;
    private TextView txtHighscore;
    private User user;

    private Context context;

    public UserHolder(@NonNull View itemView, @NonNull Context context) {
        super(itemView);
        this.context = context;
        txtUsername = itemView.findViewById(R.id.txtUsername);
        txtHighscore = itemView.findViewById(R.id.txtHighscore);
        itemView.setOnClickListener(v -> {
            Intent gameIntent = new Intent(context, GameActivity.class);
            gameIntent.putExtra(GameActivity.EXTRA_USER, user);
            context.startActivity(gameIntent);
        });
    }

    public void bindModel(User user) {
        this.user = user;
        String prefix = context.getString(R.string.highscore_prefix);
        txtUsername.setText(user.getUsername());
        txtHighscore.setText(prefix + user.getHighscore());
    }

}
