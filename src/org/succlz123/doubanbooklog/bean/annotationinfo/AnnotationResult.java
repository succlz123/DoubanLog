package org.succlz123.doubanbooklog.bean.annotationinfo;

import org.json.JSONObject;

/**
 * Created by fashi on 2015/4/4.
 */
public class AnnotationResult {

    private AuthorUser author_user;
    private String summary;
    private Integer book_id;
    private String time;
    private Integer id;

    public AuthorUser getAuthor_user() {
        return author_user;
    }

    public void setAuthor_user(AuthorUser author_user) {
        this.author_user = author_user;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static AnnotationResult parseJson(JSONObject jsonObject) {

        AnnotationResult annotationResult = new AnnotationResult();

        String summary = jsonObject.optString("summary");
        Integer book_id = jsonObject.optInt("book_id");
        String time = jsonObject.optString("time");
        Integer id = jsonObject.optInt("id");
        {
            JSONObject jsonObject1 = jsonObject.optJSONObject("author_user");
            AuthorUser authorUser = AuthorUser.parseJson(jsonObject1);
            annotationResult.setAuthor_user(authorUser);

        }
        annotationResult.setSummary(summary);
        annotationResult.setBook_id(book_id);
        annotationResult.setTime(time);
        annotationResult.setId(id);
        return annotationResult;
    }
}
