package org.succlz123.doubanbooklog.bean.reviewsinfo;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

/**
 * Created by fashi on 2015/4/6.
 */
public class ReviewsRating implements Parcelable {

    private int count;//
    private int max;//
    private int value;//

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static ReviewsRating parseJson(JSONObject jsonObject){

        ReviewsRating reviewsRating=new ReviewsRating();

        Integer count = jsonObject.optInt("count");
        Integer max = jsonObject.optInt("max");
        Integer value = jsonObject.optInt("value");

        reviewsRating.setCount(count);
        reviewsRating.setMax(max);
        reviewsRating.setValue(value);
        return  reviewsRating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeInt(this.max);
        dest.writeInt(this.value);
    }

    public ReviewsRating() {
    }

    private ReviewsRating(Parcel in) {
        this.count = in.readInt();
        this.max = in.readInt();
        this.value = in.readInt();
    }

    public static final Parcelable.Creator<ReviewsRating> CREATOR = new Parcelable.Creator<ReviewsRating>() {
        public ReviewsRating createFromParcel(Parcel source) {
            return new ReviewsRating(source);
        }

        public ReviewsRating[] newArray(int size) {
            return new ReviewsRating[size];
        }
    };
}
