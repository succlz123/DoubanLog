package org.succlz123.doubanbooklog.bean.annotationinfo;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

/**
 * Created by fashi on 2015/4/4.
 */
public class AnnotationResult implements Parcelable {

    private String chapter;
    private AuthorUser author_user;
    private String content;
    private int book_id;
    private String time;
    private int id;

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public AuthorUser getAuthor_user() {
        return author_user;
    }

    public void setAuthor_user(AuthorUser author_user) {
        this.author_user = author_user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static AnnotationResult parseJson(JSONObject jsonObject) {

        AnnotationResult annotationResult = new AnnotationResult();

        String chapter = jsonObject.optString("chapter");
        String content = jsonObject.optString("content");
        int book_id = jsonObject.optInt("book_id");
        String time = jsonObject.optString("time");
        int id = jsonObject.optInt("id");
        {
            JSONObject jsonObject1 = jsonObject.optJSONObject("author_user");
            AuthorUser authorUser = AuthorUser.parseJson(jsonObject1);
            annotationResult.setAuthor_user(authorUser);
        }
        annotationResult.setChapter(chapter);
        annotationResult.setContent(content);
        annotationResult.setBook_id(book_id);
        annotationResult.setTime(time);
        annotationResult.setId(id);
        return annotationResult;
    }

    public AnnotationResult() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.chapter);
        dest.writeParcelable(this.author_user, 0);
        dest.writeString(this.content);
        dest.writeInt(this.book_id);
        dest.writeString(this.time);
        dest.writeInt(this.id);
    }

    private AnnotationResult(Parcel in) {
        this.chapter = in.readString();
        this.author_user = in.readParcelable(getClass().getClassLoader());
        this.content = in.readString();
        this.book_id = in.readInt();
        this.time = in.readString();
        this.id = in.readInt();
    }

    public static final Creator<AnnotationResult> CREATOR = new Creator<AnnotationResult>() {
        public AnnotationResult createFromParcel(Parcel source) {
            return new AnnotationResult(source);
        }

        public AnnotationResult[] newArray(int size) {
            return new AnnotationResult[size];
        }
    };
}
