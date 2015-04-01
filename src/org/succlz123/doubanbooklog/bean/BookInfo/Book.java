package org.succlz123.doubanbooklog.bean.BookInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fashi on 2015/4/1.
 */
public class Book {

    private GlobalRating rating;//大家的评价
    private String subtitle;//书籍副标题
    private List<String> author;//书籍作者列表
    private String pubdate;//出版日期
    private List<GlobalTag> tags;//大家的标签列表
    private String origin_title;//不晓得什么东西 默认空
    private String image;//书籍封面 默认返回的是封面中图
    private String binding;//装帧
    private List<String> translator;//译者列表
    private String catalog;//目录
    private int pages;//页数
    private BookImage images;//书籍封面
    private String alt;//书籍主页
    private int publisher;//书籍在豆瓣的id
    private int isbn10;//书籍在isbn10编号
    private int isbn13;//书籍在isbn13编号 默认好像显示isbn13
    private String title;//书籍标题
    private String url;//书籍在豆瓣api的地址
    private String alt_title;//不晓得什么东西 默认空
    private String author_intro;//作者简介
    private String summary;//书籍简介
    private double price;//书籍定价

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

    public int getPublisher() {
        return publisher;
    }

    public void setPublisher(int publisher) {
        this.publisher = publisher;
    }

    public int getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(int isbn10) {
        this.isbn10 = isbn10;
    }

    public int getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(int isbn13) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static Book parseJson(JSONObject object) {
        try {
            Book book = new Book();

            {
                JSONObject jsonObject = new JSONObject("rating");
                GlobalRating globalRating = GlobalRating.parseJson(jsonObject);
                book.setRating(globalRating);
            }

            String subtitle = object.getString("subtitle");

            {
                JSONArray authorJSONArray = object.getJSONArray("author");
                List<String> collections = new ArrayList<String>();
                for (int i = 0; i < authorJSONArray.length(); i++) {
                    String result = authorJSONArray.getString(i);
                    collections.add(result);
                }
                book.setAuthor(collections);
            }

            String pubdate = object.getString("pudate");

            {
                JSONArray tagsJSONArray = object.getJSONArray("tags");
                List<GlobalTag> collections = new ArrayList<GlobalTag>();
                for (int i = 0; i < tagsJSONArray.length(); i++) {
                    JSONObject jsonObject = tagsJSONArray.getJSONObject(i);
                    GlobalTag globalTag = GlobalTag.parseJson(jsonObject);
                    if (globalTag != null) {
                        collections.add(globalTag);
                    }
                }
                book.setTags(collections);
            }

            String origin_title = object.getString("origin_title");
            String image = object.getString("image");
            String binding = object.getString("binding");

            {
                JSONArray translatorJSONArray = object.getJSONArray("translator");
                List<String> collections = new ArrayList<String>();
                for (int i = 0; i < translatorJSONArray.length(); i++) {
                    String result = translatorJSONArray.getString(i);
                    collections.add(result);
                }
                book.setTranslator(collections);
            }

            String catalog = object.getString("catalog");
            Integer pages = object.getInt("pages");

            {
                JSONObject jsonObject = new JSONObject("images");
                BookImage bookImage = BookImage.parseJson(jsonObject);
                book.setImages(bookImage);
            }

            String alt = object.getString("alt");
            Integer publisher = object.getInt("publisher");
            Integer isbn10 = object.getInt("isbn10");
            Integer isbn13 = object.getInt("isbn13");
            String title = object.getString("title");
            String url = object.getString("url");
            String alt_title = object.getString("alt_title");
            String author_intro = object.getString("author_intro");
            String summary = object.getString("summary");
            Double price = object.getDouble("price");

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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
