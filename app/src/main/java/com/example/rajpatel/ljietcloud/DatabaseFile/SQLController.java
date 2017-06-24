package com.example.rajpatel.ljietcloud.DatabaseFile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rajpatel.ljietcloud.ModelClass.ClassRecording;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HimangiPatel on 01/02/16.
 */
public class SQLController {
    private DatabaseHelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;
    private String[] allColumns = { DatabaseHelper.TT_ID,
            DatabaseHelper.SUBJECT,DatabaseHelper.SUB_NOTES,DatabaseHelper.SUB_RECORDPATH };


    public SQLController(Context c) {
        ourcontext = c;
    }

    public SQLController open() throws SQLException {
        dbhelper = new DatabaseHelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        database = dbhelper.getReadableDatabase();
        return this;

    }

    public void close() {
        dbhelper.close();
    }

    public void insertData(String time,String name,String TABLE_NAME) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.SUB_NAME, name);
        cv.put(DatabaseHelper.SUB_TIME,time);

        database.insert(TABLE_NAME, null, cv);
        Log.e("data inserted", "data inserted");
        Log.e("data inserted", String.valueOf(cv));


    }

    public void insertClassRecord(String Subject_name,String notes,String path,String TABLE_NAME){

        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.SUBJECT, Subject_name);
        cv.put(DatabaseHelper.SUB_NOTES,notes);
        cv.put(DatabaseHelper.SUB_RECORDPATH,path);

        database.insert(TABLE_NAME, null, cv);
        Log.e("data inserted", "data inserted");
        Log.e("data inserted", String.valueOf(cv));
    }



    public Cursor readData(String TABLE_NAME) {
        String[] allColumns = new String[]{DatabaseHelper.TT_ID, DatabaseHelper.SUB_NAME,DatabaseHelper.SUB_TIME};
        Cursor c = database.query(TABLE_NAME, allColumns, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


    public Cursor readRECORDING(String TABLE_NAME) {
        String[] allColumns = new String[]{DatabaseHelper.TT_ID, DatabaseHelper.SUBJECT,DatabaseHelper.SUB_NOTES};
        Cursor c = database.query(TABLE_NAME, allColumns, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }





    public int updateData(long memberID, String TABLE_NAME,String memberName) {
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(DatabaseHelper.SUB_NAME, memberName);
        int i = database.update(TABLE_NAME, cvUpdate,
                DatabaseHelper.TT_ID + " = " + memberID, null);
        return i;
    }

    public void deleteData(long memberID,String TABLE_NAME) {
        database.delete(TABLE_NAME, DatabaseHelper.TT_ID + "="
                + memberID, null);
        Log.e("", "DATA DELETE");
    }

    public List<ClassRecording> getAllBooks() {
        List<ClassRecording> books = new LinkedList<>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_CLASSRECORD,
                allColumns, null, null, null, null, null);



        ClassRecording book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new ClassRecording();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setSUBJECT(cursor.getString(1));
                book.setSUB_NOTES(cursor.getString(2));
                book.setSUB_RECORDPATH(cursor.getString(3));

                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }

        Log.d("getAllBooks()", books.toString());

        // return books
        return books;
    }
}
