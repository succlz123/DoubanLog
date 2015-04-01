package org.succlz123.doubanbooklog.bean.BookInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fashi on 2015/4/1.
 */
public class BookImage {

    private String small;//封面小图
    private String large;//封面大图
    private String medium;//封面中图

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public static BookImage parseJson(JSONObject object) {
        try {
            BookImage bookImage = new BookImage();

            String small = object.getString("small");
            String large = object.getString("large");
            String medium = object.getString("medium");

            bookImage.setSmall(small);
            bookImage.setLarge(large);
            bookImage.setMedium(medium);

            return bookImage;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
