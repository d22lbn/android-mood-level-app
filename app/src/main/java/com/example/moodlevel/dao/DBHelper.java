package com.example.moodlevel.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "mood", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(new StringBuilder()
                .append("create table if not exists mood (")
                .append("id integer primary key autoincrement, ")
                .append("date text, ")
                .append("time text, ")
                .append("moodLevel integer);").toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists mood ");
        onCreate(db);
    }
}
