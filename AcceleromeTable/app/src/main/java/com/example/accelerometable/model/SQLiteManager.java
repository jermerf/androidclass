package com.example.accelerometable.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteManager extends SQLiteOpenHelper {
    public static final String DATABASE = "catposts.db";
    public static final int DB_VERSION = 1;

    public SQLiteManager(@Nullable Context context) {
        super(context, DATABASE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTable.TABLE_CREATE_QUERY);
        UserTable.insert(db, new User("Guest"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}