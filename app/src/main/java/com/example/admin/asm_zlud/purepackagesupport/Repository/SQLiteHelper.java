package com.example.admin.asm_zlud.purepackagesupport.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    ///////////////////////////////
    //This is your SQLiteHeler ///
    //Handle all about SQLite ///
    //Using Variables to ReUse///
    ///////////////////////////

    //
    // This is Default Variables change it in use
    ////////////////////////////////////////////
    public static int DATABASE_VER = 1;
    public static String DATABASE_NAME = " My Note Database ";
    public static String DATA_TABLE_NAME_IN = "TABLE_IN";
    public static String DATA_TABLE_NAME_OUT = "TABLE_OUT";
    public static String DATA_MODEL_ID = "Id";
    public static String DATA_MODEL_AMOUNT = "Amount";
    public static String DATA_MODEL_NOTE = "Note";
    public static String DATA_MODEL_DATE = "Date";
    public static String DATA_MODEL_REASON = "Reason";
    ///////////////////////////////////////////
    //CREATE TABLE QUERY
    /////////////////////////////////////////
    //EDIT THIS QUERY IN USE     \/\/\/   //
    ///////////////////////////////////////
    public String CREATE_TABLE_IN = "CREATE TABLE " + DATA_TABLE_NAME_IN + "( " +
            DATA_MODEL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DATA_MODEL_AMOUNT + " DOUBLE," +
            DATA_MODEL_NOTE + " TEXT,"+
            DATA_MODEL_DATE + " STRING,"+
            DATA_MODEL_REASON + " INTEGER )";

    public String CREATE_TABLE_OUT = "CREATE TABLE " + DATA_TABLE_NAME_OUT + "( " +
            DATA_MODEL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DATA_MODEL_AMOUNT + " DOUBLE," +
            DATA_MODEL_NOTE + " TEXT,"+
            DATA_MODEL_DATE + " STRING,"+
            DATA_MODEL_REASON + " INTEGER )";

    ////////////////////////////////////////
    //Constructor
    ///////////////////////////////////////
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }
    /////////////////////////////////////

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_IN);
        db.execSQL(CREATE_TABLE_OUT);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATA_TABLE_NAME_IN);
        db.execSQL("DROP TABLE IF EXISTS " + DATA_TABLE_NAME_OUT);
        onCreate(db);
    }
}
