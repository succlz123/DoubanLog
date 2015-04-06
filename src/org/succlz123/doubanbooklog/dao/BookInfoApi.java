package org.succlz123.doubanbooklog.dao;


import org.json.JSONException;
import org.json.JSONObject;
import org.succlz123.doubanbooklog.bean.bookinfo.DbObject;
import org.succlz123.doubanbooklog.support.http.JavaHttpClient;

import java.util.HashMap;

/**
 * Created by fashi on 2015/3/29.
 */
public class BookInfoApi {

    public static DbObject getBooks(String token, int id, String status) {
        return getBooks(token, id, status, 0);//简化简化 方法的重载
    }

    public static DbObject getBooks(String token, int id, String status, int refresh) {
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("Authorization", "Bearer " + token);

        String url = ApiUrlHelper.USER_BOOK_ALL_INFO.replace(":name", Integer.toString(id));
        url = url + "?start=" + Integer.toString(refresh);

        if (status != null) {
            url = url + "&status=" + status;
        }

        String json = JavaHttpClient.getInstance().doGet(url, header, token);

        try {
            DbObject result = DbObject.parseJson(new JSONObject(json));
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

