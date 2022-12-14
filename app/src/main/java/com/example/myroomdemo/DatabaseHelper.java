package com.example.myroomdemo;

import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH; // полный путь к базе данных
    private static String DB_NAME = "room.db";
    private static final int SCHEMA = 1; // версия базы данных
    public static final String TABLE = "tunes"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CATEGORY_NAME = "category_name";
    public static final String COLUMN_PRICE = "price";
    private Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext = context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/" + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void create_db() {

        File file = new File(DB_PATH);
        file.delete();
        try (InputStream assetsInput = myContext.getAssets().open("raw/" + DB_NAME);
             OutputStream dbTargetOutput = new FileOutputStream(DB_PATH)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = assetsInput.read(buffer)) > 0) {
                dbTargetOutput.write(buffer, 0, length);
            }
            dbTargetOutput.flush();
        } catch (IOException ex) {
            Log.d("DatabaseHelper", ex.getMessage());
        }
    }

    public SQLiteDatabase open() throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}