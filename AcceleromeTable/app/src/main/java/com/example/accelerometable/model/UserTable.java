package com.example.accelerometable.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserTable {
    public static final String TABLE_NAME = "catpost";
    public static final String COL_id = "id";
    public static final String COL_username = "username";
    public static final String COL_highscore = "highscore";

    public static final String TABLE_CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_id + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            COL_username + " TEXT NOT NULL," +
            COL_highscore + " INTEGER NOT NULL );";

    public static void insert(SQLiteDatabase db, User user) {
        ContentValues values = new ContentValues();
        values.put(COL_username, user.getUsername());
        values.put(COL_highscore, user.getHighscore());
        long id = db.insert(TABLE_NAME, null, values);
        user.setId(id);
    }

    public static List<User> getUsers(SQLiteDatabase db){
        List<User> results = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, null, null, null,null,null,null );

        while(cursor.moveToNext()){
            int iId = cursor.getColumnIndex(COL_id);
            int iUsername = cursor.getColumnIndex(COL_username);
            int iHighscore = cursor.getColumnIndex(COL_highscore);
            User user = new User(
                    cursor.getInt(iId),
                    cursor.getString(iUsername),
                    cursor.getInt(iHighscore));
            results.add(user);
        }

        return results;
    }

    public void updateHighscore(SQLiteDatabase db, User user, int newHighScore) {
        ContentValues values = new ContentValues();
        values.put(COL_highscore, newHighScore);

        String where = "id=" + user.getId();

        db.update(TABLE_NAME, values, where, null );

        user.setHighscore(newHighScore);
    }

}
