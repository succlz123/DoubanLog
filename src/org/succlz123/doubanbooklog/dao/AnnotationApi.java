package org.succlz123.doubanbooklog.dao;

import org.json.JSONException;
import org.json.JSONObject;
import org.succlz123.doubanbooklog.bean.annotationinfo.AnnotationObject;
import org.succlz123.doubanbooklog.support.http.JavaHttpClient;

/**
 * Created by fashi on 2015/4/4.
 */
public class AnnotationApi {

    public static AnnotationObject getAnnotation(int id) {

        String url = ApiUrlHelper.USER_ONEBOOK_NOTE.replace(":id", Integer.toString(id));
        String json = JavaHttpClient.getInstance().doGet(url, null, null);
        try {
            AnnotationObject annotationObject = AnnotationObject.parseJson(new JSONObject(json));
            return annotationObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
