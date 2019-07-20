package com.song2.wave.Util.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    public long insertColumn(String songId, String songUrl, String originArtist, String coverArtist, String songTitle){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.SONGID, songId);
        values.put(DataBases.CreateDB.SONGURL, songUrl);
        values.put(DataBases.CreateDB.ORIGINARTIST, originArtist);
        values.put(DataBases.CreateDB.COVERARTIST, coverArtist);
        values.put(DataBases.CreateDB.SONGTITLE, songTitle);

        return mDB.insert(DataBases.CreateDB._TABLENAME0, null, values);
    }

    public Cursor selectColumns(){
        return mDB.query(DataBases.CreateDB._TABLENAME0, null, null, null, null, null, null);
    }


    private class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
        private static final int DATABASE_VERSION = 1;
        public SQLiteDatabase mDB;
        private DatabaseHelper mDBHelper;
        private Context mCtx;


        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(DataBases.CreateDB._CREATE0);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME0);
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context){
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    public void close(){
        mDB.close();
    }
}
