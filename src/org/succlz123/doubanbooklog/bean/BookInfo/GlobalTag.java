package org.succlz123.doubanbooklog.bean.BookInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fashi on 2015/4/1.
 */
public class GlobalTag {

    private int count;//标签人数
    private String name;//标签名字
    private String title;//标签标题

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static GlobalTag parseJson(JSONObject object) {
        try {
            GlobalTag globalTag=new GlobalTag();

            Integer count=object.getInt("count");
            String name=object.getString("name");
            String title=object.getString("title");

            globalTag.setCount(count);
            globalTag.setName(name);
            globalTag.setTitle(title);

            return globalTag;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
