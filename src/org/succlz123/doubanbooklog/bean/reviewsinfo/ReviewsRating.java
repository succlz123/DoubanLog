package org.succlz123.doubanbooklog.bean.reviewsinfo;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by fashi on 2015/4/6.
 */
public class ReviewsRating implements Serializable {

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
}
