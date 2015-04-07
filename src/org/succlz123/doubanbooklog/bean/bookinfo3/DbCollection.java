package org.succlz123.doubanbooklog.bean.bookinfo3;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

/**
 * Created by fashi on 2015/4/1.
 */
public class DbCollection implements Parcelable {

    private String status;//�����Դ�����Ķ�״̬ Ĭ�� �ڶ� reading ���� read ��� wish
    private MyRating rating;//�������
    private String updated;//����û����ڴ��鼮����Ϣ����ʱ��
    private int user_id;//�û�id
    private Book book;//���ػ������鼮��Ϣ
    private int book_id;//�鼮�ڶ����id
    private int id;//��֪����ʲô�õ�id

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
        DbCollection dbCollection = new DbCollection();
        String status = object.optString("status");
        String updated = object.optString("updated");
        Integer user_id = object.optInt("user_id");
        Integer book_id = object.optInt("book_id");
        Integer id = object.optInt("id");

        {
            JSONObject jsonObject = object.optJSONObject("rating");
            if (jsonObject != null) {
                MyRating myRating = MyRating.parseJson(jsonObject);
                dbCollection.setRating(myRating);
            }
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

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeParcelable(this.rating, 0);
        dest.writeString(this.updated);
        dest.writeInt(this.user_id);
        dest.writeParcelable(this.book, 0);
        dest.writeInt(this.book_id);
        dest.writeInt(this.id);
    }

    public DbCollection() {
    }

    private DbCollection(Parcel in) {
        this.status = in.readString();
        this.rating = in.readParcelable(MyRating.class.getClassLoader());
        this.updated = in.readString();
        this.user_id = in.readInt();
        this.book = in.readParcelable(Book.class.getClassLoader());
        this.book_id = in.readInt();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<DbCollection> CREATOR = new Parcelable.Creator<DbCollection>() {
        public DbCollection createFromParcel(Parcel source) {
            return new DbCollection(source);
        }

        public DbCollection[] newArray(int size) {
            return new DbCollection[size];
        }
    };
}

