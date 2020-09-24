package com.example.a8sqlitelist.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a8sqlitelist.model.CatPost;
import com.example.a8sqlitelist.model.IconResource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CatPostTable {
    public static final String TABLE_NAME = "catpost";
    public static final String COL_id = "id";
    public static final String COL_title = "title";
    public static final String COL_content = "content";
    public static final String COL_icon = "icon";

    public static final String TABLE_CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_id + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            COL_title + " TEXT NOT NULL," +
            COL_content +" TEXT NOT NULL," +
            COL_icon + " TEXT NOT NULL );";

    public static void insert(SQLiteDatabase db, CatPost post) {
        ContentValues values = new ContentValues();
        values.put(COL_title, post.getTitle());
        values.put(COL_content, post.getContent());
        values.put(COL_icon, post.getIcon().toString());
        db.insert(TABLE_NAME, null, values);
    }

    public static List<CatPost> getCatPosts(SQLiteDatabase db){
        List<CatPost> results = new ArrayList<>();

        String[] columns = {COL_title, COL_content, COL_icon};

        Cursor cursor = db.query(TABLE_NAME, columns, null, null,null,null,null );

        while(cursor.moveToNext()){
            int iTitle = cursor.getColumnIndex(COL_title);
            int iContent = cursor.getColumnIndex(COL_content);
            int iIcon = cursor.getColumnIndex(COL_icon);
            CatPost post = new CatPost(
                    cursor.getString(iTitle),
                    cursor.getString(iContent),
                    cursor.getString(iIcon));
            results.add(post);
        }

        return results;
    }

}
