package com.example.rajpatel.ljietcloud.DatabaseFile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HimangiPatel on 01/02/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // TABLE INFORMATTION
    public static final String TABLE_MONDAY = "monday";
    public static final String TABLE_TUESDAY = "tuesday";
    public static final String TABLE_WEDNESDAY = "wednesday";
    public static final String TABLE_THURSDAY = "thursday";
    public static final String TABLE_FRIDAY = "friday";
    public static final String TABLE_SATURDAY = "saturday";

    public static final String TABLE_CLASSRECORD = "class_record";



    public static final String TT_ID = "_id";
    public static final String SUB_NAME = "name";
    public static final String SUB_TIME = "time";

    public static final String SUBJECT = "sub";
    public static final String SUB_NOTES = "sub_notes";
    public static final String SUB_RECORDPATH = "path";



    // DATABASE INFORMATION
    static final String DB_NAME = "TIMETABLE.DB";
    static final int DB_VERSION = 1;

    // TABLE CREATION STATEMENT
    private static final String CREATE_TABLE_MONDAY = "create table "
            + TABLE_MONDAY + "(" + TT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SUB_NAME + " text not null, "
            + SUB_TIME + " text not null);";

    private static final String CREATE_TABLE_TUESDAY = "create table "
            + TABLE_TUESDAY + "(" + TT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SUB_NAME + " text not null, "
            + SUB_TIME + " text not null);";

    private static final String CREATE_TABLE_WEDNESDAY ="create table "
            + TABLE_WEDNESDAY + "(" + TT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SUB_NAME + " text not null, "
            + SUB_TIME + " text not null);";

    private static final String CREATE_TABLE_THURSDAY = "create table "
            + TABLE_THURSDAY + "(" + TT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SUB_NAME + " text not null, "
            + SUB_TIME + " text not null);";

    private static final String CREATE_TABLE_FRIDAY = "create table "
            + TABLE_FRIDAY + "(" + TT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SUB_NAME + " text not null, "
            + SUB_TIME + " text not null);";

    private static final String CREATE_TABLE_SATURDAY = "create table "
            + TABLE_SATURDAY + "(" + TT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SUB_NAME + " text not null, "
            + SUB_TIME + " text not null);";



    private static final String CREATE_TABLE_CLASSRECORD = "create table "
            + TABLE_CLASSRECORD + "(" + TT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SUBJECT + " text not null, "
            + SUB_NOTES + " text not null, "
    + SUB_RECORDPATH +" text not null);";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MONDAY);
        db.execSQL(CREATE_TABLE_TUESDAY);
        db.execSQL(CREATE_TABLE_WEDNESDAY);
        db.execSQL(CREATE_TABLE_THURSDAY);
        db.execSQL(CREATE_TABLE_FRIDAY);
        db.execSQL(CREATE_TABLE_SATURDAY);
        db.execSQL(CREATE_TABLE_CLASSRECORD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MONDAY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TUESDAY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEDNESDAY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THURSDAY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIDAY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SATURDAY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSRECORD);


        onCreate(db);
    }

}
