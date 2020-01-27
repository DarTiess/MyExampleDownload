package com.example.myexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseDownLoad extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "downloads_files.db";
    private static final int SCHEMA =4;
    public static final String TABLE = "downloads";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_PATH = "path";

    public DataBaseDownLoad(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE downloads ("+COLUMN_ID
        +" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_NAME
        +" TEXT, "+COLUMN_SIZE
        +" INTEGER, "+COLUMN_PATH
        +" TEXT) ;");

        db.execSQL("INSERT INTO "+TABLE+" ("+COLUMN_NAME
        +", "+COLUMN_SIZE+", "+COLUMN_PATH+") VALUES ('DarNaruto', 25,'sdfss');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS "+TABLE);
onCreate(db);
    }
}
