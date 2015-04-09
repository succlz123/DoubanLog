package org.succlz123.doubanbooklog.dao;

import org.json.JSONException;
import org.json.JSONObject;
import org.succlz123.doubanbooklog.bean.reviewsinfo.ReviewsObject;
import org.succlz123.doubanbooklog.support.http.JavaHttpClient;
import org.succlz123.doubanbooklog.ui.login.LoginActivity;

/**
 * Created by fashi on 2015/4/6.
 */
public class ReviewsApi {

    public static ReviewsObject GetReveiws(int id,int start) {
//        String apiKey= LoginActivity.apiKey.toString();
//        String apiKey="0dad551ec0f84ed02907ff5c42e8ec70";
        String url = ApiUrlHelper.USER_BOOK_REVIEWS.replace(":id", Integer.toString(id));
        if(start!=0){
            url=url.replace("&count=30","&start="+start+"&count=30");
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
