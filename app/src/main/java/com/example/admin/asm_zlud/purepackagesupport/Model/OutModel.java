package com.example.admin.asm_zlud.purepackagesupport.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.admin.asm_zlud.purepackagesupport.Model.enums.InReasonType;
import com.example.admin.asm_zlud.purepackagesupport.Model.enums.OutReasonType;


public class OutModel implements Parcelable{
    String id;
    String note, date;
    Double amount;

    protected OutModel(Parcel in) {
        id = in.readString();
        note = in.readString();
        date = in.readString();
        if (in.readByte() == 0) {
            amount = null;
        } else {
            amount = in.readDouble();
        }
    }

    public static final Creator<OutModel> CREATOR = new Creator<OutModel>() {
        @Override
        public OutModel createFromParcel(Parcel in) {
            return new OutModel(in);
        }

        @Override
        public OutModel[] newArray(int size) {
            return new OutModel[size];
        }
    };

    public OutReasonType getType() {
        return type;
    }

    public void setType(OutReasonType type) {
        this.type = type;
    }

    OutReasonType type;

    public OutModel() {
    }

    public OutModel(String id, String note, Double amount, String date, OutReasonType type) {
        this.id = id;
        this.note = note;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }





    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        parcel.writeString(id);
        parcel.writeString(note);
        parcel.writeString(date);
        if (amount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(amount);
        }
    }
}
