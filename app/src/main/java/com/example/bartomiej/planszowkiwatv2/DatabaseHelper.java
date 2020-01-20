package com.example.bartomiej.planszowkiwatv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG="DatabaseHelper";
    private static final String TABLE_NAME="people_table";
    private static final String COL1="ID";
    private static final String COL2="login";
    private static final String COL3="password";

    public DatabaseHelper(Context context)//, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT, " + COL3 + " TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
    }

    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result==-1) {return false;}
        else
        {
            return true;}
    }

    public boolean deleteData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        Log.d(TAG, "addData: Deleting " + item + " from " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        String whereArgs="name LIKE '%"+item+"%";

        db.delete(TABLE_NAME, COL2, new String[] {item});

        if(result==-1) {return false;}
        else
        {
            return true;}
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}
