package org.succlz123.doubanbooklog.bean.reviewsinfo;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by fashi on 2015/4/6.
 */
public class ReviewsResult implements Serializable{

    private ReviewsRating reviewsRating;
    private int useful_count;
    private String sharing_url;
    private String title;
    private String url;
    private String text;
    private String create_time;
    private ReviewsUser reviewsUser;
    private int vote_status;
    private int useless_count;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ReviewsRating getReviewsRating() {
        return reviewsRating;
    }

    public void setReviewsRating(ReviewsRating reviewsRating) {
        this.reviewsRating = reviewsRating;
    }

    public int getUseful_count() {
        return useful_count;
    }

    public void setUseful_count(int useful_count) {
        this.useful_count = useful_count;
    }

    public String getSharing_url() {
        return sharing_url;
    }

    public void setSharing_url(String sharing_url) {
        this.sharing_url = sharing_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public ReviewsUser getReviewsUser() {
        return reviewsUser;
    }

    public void setReviewsUser(ReviewsUser reviewsUser) {
        this.reviewsUser = reviewsUser;
    }

    public int getVote_status() {
        return vote_status;
    }

    public void setVote_status(int vote_status) {
        this.vote_status = vote_status;
    }

    public int getUseless_count() {
        return useless_count;
    }

    public void setUseless_count(int useless_count) {
        this.useless_count = useless_count;
    }

    public static ReviewsResult parseJson(JSONObject jsonObject) {

        ReviewsResult reviewsResult = new ReviewsResult();

        {
            JSONObject jsonObject1 = jsonObject.optJSONObject("rating");
            ReviewsRating reviewsRating = ReviewsRating.parseJson(jsonObject1);
            reviewsResult.setReviewsRating(reviewsRating);

        }
        Integer useful_count = jsonObject.optInt("useful_count");
        String sharing_url = jsonObject.optString("sharing_url");
        String title=jsonObject.optString("title");
        String url = jsonObject.optString("url");
        String text = jsonObject.optString("text");
        String create_time = jsonObject.optString("create_time");
        {
            JSONObject jsonObject1 = jsonObject.optJSONObject("user");
            ReviewsUser reviewsUser = ReviewsUser.parseJson(jsonObject1);
            reviewsResult.setReviewsUser(reviewsUser);
        }
        Integer vote_status = jsonObject.optInt("vote_status");
        Integer useless_count = jsonObject.optInt("useless_count");

        reviewsResult.setUseful_count(useful_count);
        reviewsResult.setTitle(title);
        reviewsResult.setSharing_url(sharing_url);
        reviewsResult.setUrl(url);
        reviewsResult.setText(text);
        reviewsResult.setCreate_time(create_time);
        reviewsResult.setVote_status(vote_status);
        reviewsResult.setUseless_count(useless_count);

        return reviewsResult;
    }
}
