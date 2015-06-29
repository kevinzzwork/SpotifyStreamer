package com.kzhou.spotifystreamer.model.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User: Kevin
 * Date: 14/06/15
 * Time: 10:47 PM
 */
public class ListItem implements Parcelable {
    private String imageUrl;

    private String name;

    private String subName;

    private String id;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUrl);
        dest.writeString(this.name);
        dest.writeString(this.subName);
        dest.writeString(this.id);
    }

    public ListItem() {
    }

    protected ListItem(Parcel in) {
        this.imageUrl = in.readString();
        this.name = in.readString();
        this.subName = in.readString();
        this.id = in.readString();
    }

    public static final Parcelable.Creator<ListItem> CREATOR = new Parcelable.Creator<ListItem>() {
        public ListItem createFromParcel(Parcel source) {
            return new ListItem(source);
        }

        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };
}
