package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME="User.db";
    public static final String TABLE_NAME="User_Table";
    public static final String TABLE_USER_ID="ID";
    public static final String TABLE_USER_NAME="NAME";
    public static final String TABLE_USER_ADRESS="ADDRESS";
    public static final String TABLE_USER_NUMBER="NUMBER";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.delete(TABLE_NAME,"1",null);
        db.execSQL("create table " +TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,ADDRESS TEXT,NUMBER TEXT) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String address,String number)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TABLE_USER_NAME,name);
        contentValues.put(TABLE_USER_ADRESS,address);
        contentValues.put(TABLE_USER_NUMBER,number);
        long result=db.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public Cursor showAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from " + TABLE_NAME,null);

        return cursor;
    }


    public boolean updateData(String id,String name,String address,String number)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(TABLE_USER_ID,id);
        contentValues.put(TABLE_USER_NAME,name);
        contentValues.put(TABLE_USER_ADRESS,address);
        contentValues.put(TABLE_USER_NUMBER,number);

        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] {id});

        return true;
    }

    public Integer dataDelete(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});

    }


}
