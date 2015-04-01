package org.succlz123.doubanbooklog.bean.BookInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fashi on 2015/4/1.
 */
public class GlobalRating {

    private int max;//最大评分 默认10
    private int numRaters;//评分人数
    private int average;//综合评分
    private int min;//最小评分 默认0

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
        try {
            GlobalRating globalRating=new GlobalRating();

            Integer max=object.getInt("max");
            Integer numRaters=object.getInt("numRaters");
            Integer average=object.getInt("average");
            Integer min=object.getInt("min");

            globalRating.setMax(max);
            globalRating.setNumRaters(numRaters);
            globalRating.setAverage(average);
            globalRating.setMin(min);

            return globalRating;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
