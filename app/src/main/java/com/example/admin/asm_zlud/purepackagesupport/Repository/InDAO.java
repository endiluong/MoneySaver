package com.example.admin.asm_zlud.purepackagesupport.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.asm_zlud.purepackagesupport.Model.InModel;
import com.example.admin.asm_zlud.purepackagesupport.Model.enums.InReasonType;

import java.util.ArrayList;

public class InDAO {
    private SQLiteDatabase db;
    private static InDAO instance;

    public InDAO(Context context) {
        SQLiteHelper sqlHelper = new SQLiteHelper(context);
        db = sqlHelper.getWritableDatabase();
    }


    //Get One ITEM
    public ArrayList<InModel> getDataModels(String sql, String... selectionArgs) {

        ArrayList<InModel> result = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        InModel temp;

        while (c.moveToNext()) {
            temp = new InModel();
            temp.setId(c.getInt(c.getColumnIndex(SQLiteHelper.DATA_MODEL_ID)));
            temp.setAmount(c.getDouble(c.getColumnIndex(SQLiteHelper.DATA_MODEL_AMOUNT)));
            temp.setDate(c.getString(c.getColumnIndex(SQLiteHelper.DATA_MODEL_DATE)));
            temp.setNote(c.getString(c.getColumnIndex(SQLiteHelper.DATA_MODEL_NOTE)));
            temp.setType(InReasonType.values()[c.getInt(c.getColumnIndex(SQLiteHelper.DATA_MODEL_REASON))]);
            result.add(temp);
        }
        return result;
    }

    //Get All List
    public ArrayList<InModel> getAllItem() {
        String sql = "SELECT * FROM " + SQLiteHelper.DATA_TABLE_NAME_IN;
        return getDataModels(sql);
    }

    //get By Id
    public  InModel getById(String Id) {
        String sql = "SELECT * FROM " + SQLiteHelper.DATA_TABLE_NAME_IN + " WHERE ID=? ";
        ArrayList<InModel> list = getDataModels(sql, Id);
        return list.get(0);
    }

    //get By Title
    public ArrayList<InModel> getByName(String title) {
        String sql = "SELECT * FROM " + SQLiteHelper.DATA_TABLE_NAME_IN + " WHERE TITLE LIKE ? ";
        ArrayList<InModel> list = getDataModels(sql, title);
        return list;
    }

    /////////////////////////////////////////////////
    //////////
    /// ADD ITEM TO DATABASE
    ///////////////////////////////////////////////
    public long insertIncome(InModel datamodel) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.DATA_MODEL_ID , datamodel.getId());
        values.put(SQLiteHelper.DATA_MODEL_AMOUNT, datamodel.getAmount());
        values.put(SQLiteHelper.DATA_MODEL_DATE, datamodel.getDate());
        values.put(SQLiteHelper.DATA_MODEL_NOTE, datamodel.getNote());
        values.put(SQLiteHelper.DATA_MODEL_REASON,datamodel.getType().ordinal());

        return db.insert(SQLiteHelper.DATA_TABLE_NAME_IN, null, values);
    }
    /////////////////////////////////////////////////
    /////////
    /// UPDATE
    ////////////////////////////////////////////////
    //Update
    public int updateIncome(InModel datamodel) {
        ContentValues values = new ContentValues();

        values.put(SQLiteHelper.DATA_MODEL_AMOUNT, datamodel.getAmount());
        values.put(SQLiteHelper.DATA_MODEL_DATE, datamodel.getDate());
        values.put(SQLiteHelper.DATA_MODEL_NOTE, datamodel.getNote());
        values.put(SQLiteHelper.DATA_MODEL_REASON,datamodel.getType().ordinal());

        return db.update(SQLiteHelper.DATA_TABLE_NAME_IN, values, "id=?", new String[]{String.valueOf(datamodel.getId())});
    }

    ///////////////////////////////////////////////
    ////////
    /// DELETE
    //////////////////////////////////////////////
    public int deleteIncome(InModel datamodel) {
        return db.delete(SQLiteHelper.DATA_TABLE_NAME_IN, "id=?", new String[]{String.valueOf(datamodel.getId())});
    }

    //////////////////////////////////////////////
    /////////////
    /// CREATE INSTANCE for other class recall
    ///////////////////////////////////////////

    public static InDAO getInstance(Context context) {
        if (instance == null) {
            instance = new InDAO(context);
        }
        return instance;
    }
}
