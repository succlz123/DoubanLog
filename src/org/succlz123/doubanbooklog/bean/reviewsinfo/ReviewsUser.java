package org.succlz123.doubanbooklog.bean.reviewsinfo;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by fashi on 2015/4/6.
 */
public class ReviewsUser implements Serializable{

    private String name;
    private String url;
    private String avatar;
    private Integer uid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public static ReviewsUser parseJson(JSONObject jsonObject) {

        ReviewsUser reviewsUser = new ReviewsUser();

        String name = jsonObject.optString("name");
        String url = jsonObject.optString("url");
        String avatar = jsonObject.optString("avatar");
        Integer uid = jsonObject.optInt("uid");

        reviewsUser.setName(name);
        reviewsUser.setUrl(url);
        reviewsUser.setAvatar(avatar);
        reviewsUser.setUid(uid);

        return reviewsUser;
    }
}
