package org.succlz123.doubanbooklog.bean.reviewsinfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fashi on 2015/4/6.
 */
public class ReviewsObject {

    private int count;
    private int start;
    private int total;
    private List<ReviewsResult> reviewsResult;

    public int getItemcount() {
        return reviewsResult.size();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ReviewsResult> getReviewsResult() {
        return reviewsResult;
    }

    public void setReviewsResult(List<ReviewsResult> reviewsResult) {
        this.reviewsResult = reviewsResult;
    }

    public static ReviewsObject parseJson(JSONObject jsonObject) {

        ReviewsObject reviewsObject = new ReviewsObject();

        Integer count = jsonObject.optInt("count");
        Integer start = jsonObject.optInt("start");
        Integer total = jsonObject.optInt("total");

        JSONArray jsonArray = jsonObject.optJSONArray("reviews");
        List<ReviewsResult> reviewsResultList = new ArrayList<ReviewsResult>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
            ReviewsResult reviewsResult = ReviewsResult.parseJson(jsonObject1);
            reviewsResultList.add(reviewsResult);
        }

        reviewsObject.setReviewsResult(reviewsResultList);

        reviewsObject.setCount(count);
        reviewsObject.setStart(start);
        reviewsObject.setTotal(total);

        return reviewsObject;
    }
}
