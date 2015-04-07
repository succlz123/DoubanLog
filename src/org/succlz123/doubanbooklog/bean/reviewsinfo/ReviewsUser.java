package org.succlz123.doubanbooklog.bean.reviewsinfo;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

/**
 * Created by fashi on 2015/4/6.
 */
public class ReviewsUser implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeString(this.avatar);
        dest.writeValue(this.uid);
    }

    public ReviewsUser() {
    }

    private ReviewsUser(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
        this.avatar = in.readString();
        this.uid = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<ReviewsUser> CREATOR = new Parcelable.Creator<ReviewsUser>() {
        public ReviewsUser createFromParcel(Parcel source) {
            return new ReviewsUser(source);
        }

        public ReviewsUser[] newArray(int size) {
            return new ReviewsUser[size];
        }
    };
}
