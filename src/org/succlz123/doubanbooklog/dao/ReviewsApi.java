package org.succlz123.doubanbooklog.dao;

import org.json.JSONException;
import org.json.JSONObject;
import org.succlz123.doubanbooklog.bean.reviewsinfo.ReviewsObject;
import org.succlz123.doubanbooklog.support.http.JavaHttpClient;

/**
 * Created by fashi on 2015/4/6.
 */
public class ReviewsApi {

    public static ReviewsObject GetReveiws(int id,int start) {
        String url = ApiUrlHelper.USER_BOOK_REVIEWS.replace(":id", Integer.toString(id));
        if(start!=0){
            url=url+"?start="+start;
        }
        String json = JavaHttpClient.getInstance().doGet(url, null, null);

        try {
            ReviewsObject reviewsObject = ReviewsObject.parseJson(new JSONObject(json));
            return reviewsObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
