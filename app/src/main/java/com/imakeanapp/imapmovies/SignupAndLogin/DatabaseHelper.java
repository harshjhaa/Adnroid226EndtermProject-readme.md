package com.imakeanapp.imapmovies.SignupAndLogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MoviesForMeDatabase1.db";
    private static final String TABLE_NAME = "my_data";
    public static final String ID = "ID";
    private static final String name = "NAME";
    private static final String email = "EMAIL";
    private static final String password = "PASSWORD";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL text UNIQUE, PASSWORD TEXT ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(EditText mName, EditText mEmail, EditText mPassword) {

        String nm = mName.getText().toString().trim();
        String em = mEmail.getText().toString().trim();
        String ps = mPassword.getText().toString().trim();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(name, nm);
        cv.put(email, em);
        cv.put(password, ps);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    //to view data
    public Cursor fetchData() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(" SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    //get the data
    public Cursor getData(String user) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + TABLE_NAME + " where " + email + " = ?", new String[]{user});
        return cursor;
    }

    //to check if email is present or not in the database
    public Cursor checkForData(String mEmail) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from " + TABLE_NAME + " where " + email + " = ?", new String[]{mEmail});
        return cursor;
    }
}
