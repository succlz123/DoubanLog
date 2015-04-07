package org.succlz123.doubanbooklog.bean.annotationinfo;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

/**
 * Created by fashi on 2015/4/4.
 */
public class AuthorUser implements Parcelable {

    private String name;
    private String url;
    private String avatar;
    private int uid;
    private String alt;//读书主页
    private String large_avatar;

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

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getLarge_avatar() {
        return large_avatar;
    }

    public void setLarge_avatar(String large_avatar) {
        this.large_avatar = large_avatar;
    }

    public static AuthorUser parseJson(JSONObject jsonObject) {
        AuthorUser authorUser = new AuthorUser();

        String name = jsonObject.optString("name");
        String url = jsonObject.optString("url");
        String avatar = jsonObject.optString("avatar");
        Integer uid = jsonObject.optInt("uid");
        String alt = jsonObject.optString("alt");
        String large_avatar = jsonObject.optString("large_avatar");

        authorUser.setName(name);
        authorUser.setUrl(url);
        authorUser.setAvatar(avatar);
        authorUser.setUid(uid);
        authorUser.setAlt(alt);
        authorUser.setLarge_avatar(large_avatar);

        return authorUser;
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
        dest.writeInt(this.uid);
        dest.writeString(this.alt);
        dest.writeString(this.large_avatar);
    }

    public AuthorUser() {
    }

    private AuthorUser(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
        this.avatar = in.readString();
        this.uid = in.readInt();
        this.alt = in.readString();
        this.large_avatar = in.readString();
    }

    public static final Parcelable.Creator<AuthorUser> CREATOR = new Parcelable.Creator<AuthorUser>() {
        public AuthorUser createFromParcel(Parcel source) {
            return new AuthorUser(source);
        }

        public AuthorUser[] newArray(int size) {
            return new AuthorUser[size];
        }
    };
}
