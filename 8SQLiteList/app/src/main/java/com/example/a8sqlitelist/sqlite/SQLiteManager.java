package com.example.a8sqlitelist.sqlite;

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
        db.execSQL(CatPostTable.TABLE_CREATE_QUERY);
        db.execSQL(NoteTable.TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
