package com.example.admin.asm_zlud.purepackagesupport.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.admin.asm_zlud.purepackagesupport.Model.enums.OutReasonType;

import java.util.Date;

public class OutModel implements Parcelable {
    int id;
    String note,date;
    Double amount;
    OutReasonType type;

    public OutModel() {
    }


    public OutModel(int id, String note, Double amount,String date,OutReasonType type) {
        this.id = id;
        this.note = note;
        this.amount = amount;
        this.date= date;
        this.type=type;
    }

    protected OutModel(Parcel in) {
        id = in.readInt();
        note = in.readString();
    }

    public OutReasonType getType() {
        return type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setType(OutReasonType type) {
        this.type = type;
    }

    public static final Creator<InModel> CREATOR = new Creator<InModel>() {
        @Override
        public InModel createFromParcel(Parcel in) {
            return new InModel(in);
        }

        @Override
        public InModel[] newArray(int size) {
            return new InModel[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return note;
    }

    public void setReason(String reason) {
        this.note = reason;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(note);
    }
}
