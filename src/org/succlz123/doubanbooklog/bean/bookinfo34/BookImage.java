package org.succlz123.doubanbooklog.bean.bookinfo34;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fashi on 2015/4/1.
 */
public class BookImage implements Parcelable {

    private String small;//����Сͼ
    private String large;//�����ͼ
    private String medium;//������ͼ

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public static BookImage parseJson(JSONObject object) {
        try {
            BookImage bookImage = new BookImage();

            String small = object.getString("small");
            String large = object.getString("large");
            String medium = object.getString("medium");

            bookImage.setSmall(small);
            bookImage.setLarge(large);
            bookImage.setMedium(medium);

            return bookImage;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.small);
        dest.writeString(this.large);
        dest.writeString(this.medium);
    }

    public BookImage() {
    }

    private BookImage(Parcel in) {
        this.small = in.readString();
        this.large = in.readString();
        this.medium = in.readString();
    }

    public static final Parcelable.Creator<BookImage> CREATOR = new Parcelable.Creator<BookImage>() {
        public BookImage createFromParcel(Parcel source) {
            return new BookImage(source);
        }

        public BookImage[] newArray(int size) {
            return new BookImage[size];
        }
    };
}
