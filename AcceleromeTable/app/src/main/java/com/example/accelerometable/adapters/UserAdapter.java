package com.example.accelerometable.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.accelerometable.R;
import com.example.accelerometable.model.SQLiteManager;
import com.example.accelerometable.model.User;
import com.example.accelerometable.model.UserTable;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.zip.Inflater;

public class UserAdapter extends RecyclerView.Adapter<UserHolder> {
    private volatile List<User> users;
    private Context context;

    private SQLiteManager sql;

    private Executor offMain;

    public UserAdapter(Context context) {
        this.context = context;

        offMain = Executors.newSingleThreadExecutor();

        offMain.execute(() -> {
            sql = new SQLiteManager(context);
            SQLiteDatabase db = sql.getReadableDatabase();
            users = UserTable.getUsers(db);
        });

    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_user, parent, false);
        UserHolder row = new UserHolder(v, context);
        return row;
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.bindModel(users.get(position));
    }

    @Override
    public int getItemCount() {
        if (users == null) return 0;
        return users.size();
    }

    public User addNewUser(String username) {
        User user = new User(username);
        offMain.execute(() -> {
            SQLiteDatabase db = sql.getWritableDatabase();
            UserTable.insert(db, user);
            users.add(user);
            runOnMainThread(() -> {
                notifyDataSetChanged();
            });
        });

        return user;
    }

    private void runOnMainThread(Runnable run) {
        Handler handle = new Handler(Looper.getMainLooper());
        handle.post(run);
    }
}
