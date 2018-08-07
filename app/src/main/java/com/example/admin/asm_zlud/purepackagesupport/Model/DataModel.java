package com.example.admin.asm_zlud.purepackagesupport.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class DataModel implements Parcelable {
    String id,title,content;


    //////////////////////////////////
    //This is the Basic Data Model //
    ////////////////////////////////

    public DataModel() {
    }

    protected DataModel(Parcel in) {
        id= in.readString();
        title = in.readString();
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {

        this.content = content;
    }
}
