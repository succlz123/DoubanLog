package org.succlz123.doubanbooklog.bean.bookinfo34;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

/**
 * Created by fashi on 2015/4/1.
 */
public class GlobalRating implements Parcelable {

    private int max;//
    private int numRaters;//
    private int average;//
    private int min;//

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getNumRaters() {
        return numRaters;
    }

    public void setNumRaters(int numRaters) {
        this.numRaters = numRaters;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public static GlobalRating parseJson(JSONObject object) {

            GlobalRating globalRating = new GlobalRating();

            Integer max = object.optInt("max");
            Integer numRaters = object.optInt("numRaters");
            Integer average = object.optInt("average");
            Integer min = object.optInt("min");

            globalRating.setMax(max);
            globalRating.setNumRaters(numRaters);
            globalRating.setAverage(average);
            globalRating.setMin(min);

            return globalRating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.max);
        dest.writeInt(this.numRaters);
        dest.writeInt(this.average);
        dest.writeInt(this.min);
    }

    public GlobalRating() {
    }

    private GlobalRating(Parcel in) {
        this.max = in.readInt();
        this.numRaters = in.readInt();
        this.average = in.readInt();
        this.min = in.readInt();
    }

    public static final Parcelable.Creator<GlobalRating> CREATOR = new Parcelable.Creator<GlobalRating>() {
        public GlobalRating createFromParcel(Parcel source) {
            return new GlobalRating(source);
        }

        public GlobalRating[] newArray(int size) {
            return new GlobalRating[size];
        }
    };
}
