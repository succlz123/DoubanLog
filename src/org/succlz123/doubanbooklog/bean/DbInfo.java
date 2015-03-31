package org.succlz123.doubanbooklog.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fashi on 2015/3/31.
 */
public class DbInfo {

    private String name; //豆瓣用户名
    private String created;//注册时间
    private String avatar;//头像小图
    private String signature;//签名
    private String uid; //豆瓣uid
    private String alt;//用户主页
    private String desc;//个人简介
    private String type;//用户组or权限? type:user
    private String id; //豆瓣id
    private String large_avatar;//头像大图

    public String getLarge_avatar() {
        return large_avatar;
    }

    public void setLarge_avatar(String large_avatar) {
        this.large_avatar = large_avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static DbInfo parseJson(JSONObject object) {
        try {
            String name = object.getString("name");
            String created = object.getString("created");
            String avatar = object.getString("avatar");
            String signature = object.getString("signature");
            String uid = object.getString("uid");
            String alt = object.getString("alt");
            String desc = object.getString("desc");
            String id = object.getString("id");
            String large_avatar = object.getString("large_avatar");
            DbInfo dbInfo = new DbInfo();
            dbInfo.setName(name);
            dbInfo.setCreated(created);
            dbInfo.setAvatar(avatar);
            dbInfo.setSignature(signature);
            dbInfo.setUid(uid);
            dbInfo.setAvatar(alt);
            dbInfo.setDesc(desc);
            dbInfo.setId(id);
            dbInfo.setLarge_avatar(large_avatar);
            return dbInfo;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }
}
