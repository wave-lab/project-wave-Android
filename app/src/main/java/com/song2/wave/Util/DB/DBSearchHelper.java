package com.song2.wave.Util.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBSearchHelper extends SQLiteOpenHelper {

    public DBSearchHelper(@Nullable Context context) {
        super(context, "SEARCH", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //
        db.execSQL("CREATE TABLE SEARCH(_id INTEGER PRIMARY KEY AUTOINCREMENT,keyword TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //

    }

    public void insert(String keyword){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("INSERT INTO SEARCH VALUE(null,"+keyword+");");
        db.close();
    }

    public void delete(String keyword){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM SEARCH WHERE keyword = " + keyword + ");");
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM SEARCH");
        db.close();
    }
}

