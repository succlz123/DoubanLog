package org.succlz123.doubanbooklog.bean.BookInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fashi on 2015/4/1.
 */
public class DbObject {

    private int count;//一次返回的书籍信息数量 默认20
    private int start;//
    private int total;//
    private List<DbCollection> collections;//返回回来的书籍(默认20本)的信息列表

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

    public List<DbCollection> getCollections() {
        return collections;
    }

    public void setCollections(List<DbCollection> collections) {
        this.collections = collections;
    }

    public static DbObject parseJson(JSONObject object) {

        try {
            DbObject dbObject = new DbObject();

            Integer count = object.getInt("count");
            Integer start = object.getInt("start");
            Integer total = object.getInt("total");

            JSONArray collectionsJSOArray = object.optJSONArray("collections");
            List<DbCollection> collections = new ArrayList<DbCollection>();
            for (int i = 0; i < collectionsJSOArray.length(); i++) {
                JSONObject jsonObject = collectionsJSOArray.getJSONObject(i);
                DbCollection collection = DbCollection.parseJson(jsonObject);
                if (collection != null) {
                    collections.add(collection);
                }
            }

            dbObject.setCount(count);
            dbObject.setStart(start);
            dbObject.setTotal(total);
            dbObject.setCollections(collections);

            return dbObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
