package com.example.a8sqlitelist.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NoteTable {
    public static final String TABLE_NAME = "notes";
    public static final String COL_id = "id";
    public static final String COL_content = "content";

    public static final String TABLE_CREATE_QUERY =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COL_id + " NUMBER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    COL_content + " TEXT NOT NULL);";

    public static void insert(SQLiteDatabase db, String note){
        ContentValues values = new ContentValues();
        values.put(COL_content, note);

        db.insert(TABLE_NAME, null, values);
    }

    public static List<String> getList(SQLiteDatabase db){
        List<String> results = new ArrayList<>();

        String[] columns = {COL_content};

        Cursor cursor = db.query(TABLE_NAME, columns, null,null,null,null,null);

        while(cursor.moveToNext()){
            int iContent = cursor.getColumnIndex(COL_content);
            results.add(cursor.getString(iContent));
        }

        return results;
    }

}
