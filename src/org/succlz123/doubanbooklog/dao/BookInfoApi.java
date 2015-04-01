package org.succlz123.doubanbooklog.dao;


import org.json.JSONException;
import org.json.JSONObject;
import org.succlz123.doubanbooklog.bean.BookInfo.DbObject;
import org.succlz123.doubanbooklog.support.http.JavaHttpClient;

import java.util.HashMap;

/**
 * Created by fashi on 2015/3/29.
 */
public class BookInfoApi {

    public static DbObject getBooks(String token,String name) {
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("Authorization", "Bearer " + token);
        String url = ApiUrlHelper.USER_BOOK_ALL_INFO.replace(":name",name);
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

