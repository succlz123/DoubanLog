package org.succlz123.doubanbooklog.bean.bookinfo3;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fashi on 2015/4/1.
 */
public class Book implements Parcelable {

    private GlobalRating rating;//????????
    private String subtitle;//?鼮??????
    private List<String> author;//?鼮?????б?
    private String pubdate;//????????
    private List<GlobalTag> tags;//???????б?
    private String origin_title;//???????????? ????
    private String image;//?鼮???? ???????????????
    private String binding;//??
    private List<String> translator;//?????б?
    private String catalog;//??
    private int pages;//???
    private BookImage images;//?鼮????
    private String alt;//?鼮???
    private String publisher;//?鼮??????id
    private String isbn10;//?鼮??isbn10???
    private String isbn13;//?鼮??isbn13??? ?????????isbn13
    private String title;//?鼮????
    private String url;//?鼮?????api????
    private String alt_title;//???????????? ????
    private String author_intro;//??????
    private String summary;//?鼮???
    private String price;//?鼮????

    public GlobalRating getRating() {
        return rating;
    }

    public void setRating(GlobalRating rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public List<GlobalTag> getTags() {
        return tags;
    }

    public void setTags(List<GlobalTag> tags) {
        this.tags = tags;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public BookImage getImages() {
        return images;
    }

    public void setImages(BookImage images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public static Book parseJson(JSONObject object) {
        Book book = new Book();

        {
            JSONObject jsonObject = object.optJSONObject("rating");
            GlobalRating globalRating = GlobalRating.parseJson(jsonObject);
            book.setRating(globalRating);
        }

        String subtitle = object.optString("subtitle");

        {
            JSONArray authorJSONArray = object.optJSONArray("author");
            List<String> collections = new ArrayList<String>();
            for (int i = 0; i < authorJSONArray.length(); i++) {
                String result = authorJSONArray.optString(i);
                collections.add(result);
            }
            book.setAuthor(collections);
        }

        String pubdate = object.optString("pudate");

        {
            JSONArray tagsJSONArray = object.optJSONArray("tags");
            List<GlobalTag> collections = new ArrayList<GlobalTag>();
            for (int i = 0; i < tagsJSONArray.length(); i++) {
                JSONObject jsonObject = tagsJSONArray.optJSONObject(i);
                GlobalTag globalTag = GlobalTag.parseJson(jsonObject);
                if (globalTag != null) {
                    collections.add(globalTag);
                }
            }
            book.setTags(collections);
        }

        String origin_title = object.optString("origin_title");
        String image = object.optString("image");
        String binding = object.optString("binding");

        {
            JSONArray translatorJSONArray = object.optJSONArray("translator");
            List<String> collections = new ArrayList<String>();
            for (int i = 0; i < translatorJSONArray.length(); i++) {
                String result = translatorJSONArray.optString(i);
                collections.add(result);
            }
            book.setTranslator(collections);
        }

        String catalog = object.optString("catalog");
        Integer pages = object.optInt("pages");

        {
            JSONObject jsonObject = object.optJSONObject("images");
            BookImage bookImage = BookImage.parseJson(jsonObject);
            book.setImages(bookImage);
        }

        String alt = object.optString("alt");
        String publisher = object.optString("publisher");
        String isbn10 = object.optString("isbn10");
        String isbn13 = object.optString("isbn13");
        String title = object.optString("title");
        String url = object.optString("url");
        String alt_title = object.optString("alt_title");
        String author_intro = object.optString("author_intro");
        String summary = object.optString("summary");
        String price = object.optString("price");

        book.setSubtitle(subtitle);
        book.setPubdate(pubdate);
        book.setOrigin_title(origin_title);
        book.setImage(image);
        book.setBinding(binding);
        book.setCatalog(catalog);
        book.setPages(pages);
        book.setAlt(alt);
        book.setPublisher(publisher);
        book.setIsbn10(isbn10);
        book.setIsbn13(isbn13);
        book.setTitle(title);
        book.setUrl(url);
        book.setAlt_title(alt_title);
        book.setAuthor_intro(author_intro);
        book.setSummary(summary);
        book.setPrice(price);

        return book;

    }

    public Book() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.rating, 0);
        dest.writeString(this.subtitle);
        dest.writeList(this.author);
        dest.writeString(this.pubdate);
        dest.writeList(this.tags);
        dest.writeString(this.origin_title);
        dest.writeString(this.image);
        dest.writeString(this.binding);
        dest.writeList(this.translator);
        dest.writeString(this.catalog);
        dest.writeInt(this.pages);
        dest.writeParcelable(this.images, 0);
        dest.writeString(this.alt);
        dest.writeString(this.publisher);
        dest.writeString(this.isbn10);
        dest.writeString(this.isbn13);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.alt_title);
        dest.writeString(this.author_intro);
        dest.writeString(this.summary);
        dest.writeString(this.price);
    }

    private Book(Parcel in) {
        this.rating = in.readParcelable(GlobalRating.class.getClassLoader());
        this.subtitle = in.readString();
        this.author = new ArrayList<String>();
        in.readList(this.author, getClass().getClassLoader());
        this.pubdate = in.readString();
        this.tags = new ArrayList<GlobalTag>();
        in.readList(this.tags, getClass().getClassLoader());
        this.origin_title = in.readString();
        this.image = in.readString();
        this.binding = in.readString();
        this.translator = new ArrayList<String>();
        in.readList(this.translator,getClass().getClassLoader());
        this.catalog = in.readString();
        this.pages = in.readInt();
        this.images = in.readParcelable(BookImage.class.getClassLoader());
        this.alt = in.readString();
        this.publisher = in.readString();
        this.isbn10 = in.readString();
        this.isbn13 = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.alt_title = in.readString();
        this.author_intro = in.readString();
        this.summary = in.readString();
        this.price = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
