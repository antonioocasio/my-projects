package com.example.recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class DNA implements Parcelable {
    private String title;
    private String  date;
    private String description;
    private String link;



    public DNA(String title, String date, String description, String link) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "DNA:" + '\n' +
                "title ='" + title + '\n' +
                ", date ='" + date + '\n' +
                ", description ='" + description;
    }

    protected DNA(Parcel in) {
        title = in.readString();
        date = in.readString();
        description = in.readString();
        link = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(description);
        dest.writeString(link);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DNA> CREATOR = new Parcelable.Creator<DNA>() {
        @Override
        public DNA createFromParcel(Parcel in) {
            return new DNA(in);
        }

        @Override
        public DNA[] newArray(int size) {
            return new DNA[size];
        }
    };
}
