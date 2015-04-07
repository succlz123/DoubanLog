package org.succlz123.doubanbooklog.bean.bookinfo34;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fashi on 2015/4/1.
 */
public class DbObject {

    private int count;//返回图书个数 默认最多20
    private int start;//
    private int total;//
    private List<DbCollection> collections;//返回的所有图书信息

    public int getItemCount() {
        return collections.size();
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

    public void add(DbObject object) {
        this.collections.addAll(0, object.getCollections());
    }

    public void addOld(DbObject object) {
        this.collections.addAll(object.getCollections());
    }

    public static DbObject parseJson(JSONObject object) {
        DbObject dbObject = new DbObject();

        Integer count = object.optInt("count");
        Integer start = object.optInt("start");
        Integer total = object.optInt("total");

        JSONArray collectionsJSOArray = object.optJSONArray("collections");
        if (collectionsJSOArray != null) {
            List<DbCollection> collections = new ArrayList<DbCollection>();
            for (int i = 0; i < collectionsJSOArray.length(); i++) {
                JSONObject jsonObject = collectionsJSOArray.optJSONObject(i);
                DbCollection collection = DbCollection.parseJson(jsonObject);
                if (collection != null) {
                    collections.add(collection);
                }
            }
            dbObject.setCollections(collections);
        }

        dbObject.setCount(count);
        dbObject.setStart(start);
        dbObject.setTotal(total);
        return dbObject;
    }
}
