package com.example.yt.mysql;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yt on 13-Dec-17.
 */

public class DataBaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="student_table";
    public static final String col1="Id";
    public static final String col2="Name";
    public static final String col3="Surname";
    public static final String col4="Marks";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creates the table in database
        db.execSQL("create table "+TABLE_NAME+"("+col1+" integer PRIMARY KEY AUTOINCREMENT, "+col2+" text, "+col3+" text,"+col4+" integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop table if exists "+TABLE_NAME);
        onCreate(db);
    }

    public void yolo(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Drop table if exists "+TABLE_NAME);
        onCreate(db);
    }

    public boolean AddData(String name,String surname,String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,surname);
        contentValues.put(col4,marks);
        long isInserted = db.insert(TABLE_NAME,null,contentValues);
        if (isInserted==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res = db.rawQuery("select *from "+TABLE_NAME,null);
        return res;
    }

    public int updateData(String id, String name, String surname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,id);
        contentValues.put(col2,name);
        contentValues.put(col3,surname);
        contentValues.put(col4,marks);
        return db.update(TABLE_NAME,contentValues,"id = ?",new String[]{id});
    }

    public int delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"id = ?",new String[]{id});
    }

}
