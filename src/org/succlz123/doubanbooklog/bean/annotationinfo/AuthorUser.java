package org.succlz123.doubanbooklog.bean.annotationinfo;

import org.json.JSONObject;

/**
 * Created by fashi on 2015/4/4.
 */
public class AuthorUser {

    private String name;
    private Boolean is_banned;
    private String url;
    private String avatar;
    private Integer uid;
    private String alt;//读书主页
    private String large_avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean is_banned() {
        return is_banned;
    }

    public void setIs_banned(Boolean is_banned) {
        this.is_banned = is_banned;
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
        Boolean is_banned = jsonObject.optBoolean("is_banned");
        String url = jsonObject.optString("url");
        String avatar = jsonObject.optString("avatar");
        Integer uid = jsonObject.optInt("uid");
        String alt = jsonObject.optString("alt");
        String large_avatar = jsonObject.optString("large_avatar");

        authorUser.setName(name);
        authorUser.setIs_banned(is_banned);
        authorUser.setUrl(url);
        authorUser.setAvatar(avatar);
        authorUser.setUid(uid);
        authorUser.setAlt(alt);
        authorUser.setLarge_avatar(large_avatar);

        return authorUser;
    }
}
