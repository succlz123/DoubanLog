package org.succlz123.doubanbooklog.bean.BookInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fashi on 2015/4/1.
 */
public class DbCollection {

    private String status;//标记你对此书的阅读状态 默认 在读 reading 读过 read 想读 wish
    private MyRating rating;//你的评分
    private String updated;//最后用户关于此书籍的信息更新时间
    private int user_id;//用户id
    private Book book;//返回回来的书籍信息
    private int book_id;//书籍在豆瓣的id
    private int id;//不知道有什么用的id

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MyRating getRating() {
        return rating;
    }

    public void setRating(MyRating rating) {
        this.rating = rating;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static DbCollection parseJson(JSONObject object) {
        try {
            DbCollection dbCollection = new DbCollection();
            String status = object.getString("status");
            String updated = object.getString("updated");
            Integer user_id = object.getInt("user_id");
            Integer book_id = object.getInt("book_id");
            Integer id = object.getInt("id");

            {
                JSONObject jsonObject = object.optJSONObject("rating");
                MyRating myRating = MyRating.parseJson(jsonObject);
                dbCollection.setRating(myRating);
            }

            {
                JSONObject jsonObject = object.optJSONObject("book");
                Book book = Book.parseJson(jsonObject);
                dbCollection.setBook(book);
            }

            dbCollection.setStatus(status);
            dbCollection.setUpdated(updated);
            dbCollection.setUser_id(user_id);
            dbCollection.setBook_id(book_id);
            dbCollection.setUser_id(id);

            return dbCollection;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}

