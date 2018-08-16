package com.example.admin.asm_zlud.purepackagesupport.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.asm_zlud.purepackagesupport.Model.InModel;
import com.example.admin.asm_zlud.purepackagesupport.Model.OutModel;
import com.example.admin.asm_zlud.purepackagesupport.Model.enums.InReasonType;
import com.example.admin.asm_zlud.purepackagesupport.Model.enums.OutReasonType;

import java.util.ArrayList;

public class OutDAO {
    private SQLiteDatabase db;
    private static OutDAO instance;

    public OutDAO(Context context) {
        SQLiteHelper sqlHelper = new SQLiteHelper(context);
        db = sqlHelper.getWritableDatabase();
    }


    //Get One ITEM
    public ArrayList<OutModel> getDataModels(String sql, String... selectionArgs) {

        ArrayList<OutModel> result = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        OutModel temp;

        while (c.moveToNext()) {
            temp = new OutModel();
            temp.setId(c.getString(c.getColumnIndex(SQLiteHelper.DATA_MODEL_ID)));
            temp.setAmount(c.getDouble(c.getColumnIndex(SQLiteHelper.DATA_MODEL_AMOUNT)));
            temp.setDate(c.getString(c.getColumnIndex(SQLiteHelper.DATA_MODEL_DATE)));
            temp.setNote(c.getString(c.getColumnIndex(SQLiteHelper.DATA_MODEL_NOTE)));
            temp.setType(OutReasonType.values()[c.getInt(c.getColumnIndex(SQLiteHelper.DATA_MODEL_REASON))]);
            result.add(temp);
        }
        return result;
    }

    public Double getTotalMoney(String sql, String... selectionArgs) {

        ArrayList<OutModel> result = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        Double sumMoney=0d;
        while (c.moveToNext()) {
            sumMoney=c.getDouble(0);
        }
        return  sumMoney;
    }

    public ArrayList<OutModel> excuteGROUP(String sql, String... selectionArgs) {
        ArrayList<OutModel> result = new ArrayList<>();
        Cursor c = db.rawQuery(sql, null);
        OutModel temp;
        while (c.moveToNext()) {
            temp = new OutModel();
            temp.setType(OutReasonType.values()[c.getInt(c.getColumnIndex(SQLiteHelper.DATA_MODEL_REASON))]);
            temp.setAmount(c.getDouble(c.getColumnIndex(SQLiteHelper.DATA_MODEL_AMOUNT)));
            result.add(temp);
        }
        return result;
    }

    //Get All List
    public ArrayList<OutModel> getAllItem() {
        String sql = "SELECT * FROM " + SQLiteHelper.DATA_TABLE_NAME_OUT;
        return getDataModels(sql);
    }

    //get By Id
    public OutModel getById(String Id) {
        String sql = "SELECT * FROM " + SQLiteHelper.DATA_TABLE_NAME_OUT + " WHERE ID=? ";
        ArrayList<OutModel> list = getDataModels(sql, Id);
        return list.get(0);
    }

    //get By Name
    public ArrayList<OutModel> getByName() {
//        String sql = "SELECT AMOUNT FROM " + SQLiteHelper.DATA_TABLE_NAME_IN + "  WHERE Reason=" + type.ordinal();
//        String sql=  "SELECT "+ SQLiteHelper.DATA_MODEL_REASON+", SUM (i.Amount) FROM "+ SQLiteHelper.DATA_TABLE_NAME_IN+" i GROUP BY i.Reason= " + type.ordinal();
        String sql =
                "SELECT " + SQLiteHelper.DATA_MODEL_REASON +
                        ", SUM(" + SQLiteHelper.DATA_MODEL_AMOUNT +
                        ") AS " + SQLiteHelper.DATA_MODEL_AMOUNT + " FROM " +
                        SQLiteHelper.DATA_TABLE_NAME_OUT +
                        "  GROUP BY " + SQLiteHelper.DATA_MODEL_REASON;
        ArrayList<OutModel> list = excuteGROUP(sql);
        return list;
    }

    ///////////////////////////
    //TOTAL MONEY
    public Double getTotalMoney() {
        String sql = "SELECT SUM(" + SQLiteHelper.DATA_MODEL_AMOUNT + ") FROM " + SQLiteHelper.DATA_TABLE_NAME_OUT;
        Double total = getTotalMoney(sql);
        return total;
    }

    /////////////////////////////////////////////////
    //////////
    /// ADD ITEM TO DATABASE
    ///////////////////////////////////////////////
    public long insertOutcome(OutModel datamodel) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.DATA_MODEL_ID, datamodel.getId());
        values.put(SQLiteHelper.DATA_MODEL_AMOUNT, datamodel.getAmount());
        values.put(SQLiteHelper.DATA_MODEL_DATE, datamodel.getDate());
        values.put(SQLiteHelper.DATA_MODEL_NOTE, datamodel.getNote());
        values.put(SQLiteHelper.DATA_MODEL_REASON, datamodel.getType().ordinal());

        return db.insert(SQLiteHelper.DATA_TABLE_NAME_OUT, null, values);
    }

    /////////////////////////////////////////////////
    /////////
    /// UPDATE
    ////////////////////////////////////////////////
    //Update
    public int updateOutcome(OutModel datamodel) {
        ContentValues values = new ContentValues();

        values.put(SQLiteHelper.DATA_MODEL_AMOUNT, datamodel.getAmount());
        values.put(SQLiteHelper.DATA_MODEL_DATE, datamodel.getDate());
        values.put(SQLiteHelper.DATA_MODEL_NOTE, datamodel.getNote());
        values.put(SQLiteHelper.DATA_MODEL_REASON, datamodel.getType().ordinal());

        return db.update(SQLiteHelper.DATA_TABLE_NAME_OUT, values, "id=?", new String[]{String.valueOf(datamodel.getId())});
    }

    ///////////////////////////////////////////////
    ////////
    /// DELETE
    //////////////////////////////////////////////
    public int deleteIncome(OutModel datamodel) {
        return db.delete(SQLiteHelper.DATA_TABLE_NAME_OUT, "id=?", new String[]{String.valueOf(datamodel.getId())});
    }

    //////////////////////////////////////////////
    /////////////
    /// CREATE INSTANCE for other class recall
    ///////////////////////////////////////////

    public static OutDAO getInstance(Context context) {
        if (instance == null) {
            instance = new OutDAO(context);
        }
        return instance;
    }
}
