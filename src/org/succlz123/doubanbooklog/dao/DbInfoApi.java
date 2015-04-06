package org.succlz123.doubanbooklog.dao;

import org.json.JSONException;
import org.json.JSONObject;
import org.succlz123.doubanbooklog.bean.DbInfo;
import org.succlz123.doubanbooklog.support.http.JavaHttpClient;

import java.util.HashMap;

/**
 * Created by fashi on 2015/3/31.
 */
public class DbInfoApi {

    public static DbInfo getDbInfo(String token) {
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("Authorization", "Bearer " + token);

        String json = JavaHttpClient.getInstance().doGet(ApiUrlHelper.USER_INFO, header, token);
        try {
            return DbInfo.parseJson(new JSONObject(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}


