package org.succlz123.doubanbooklog.dao;

/**
 * Created by fashi on 2015/3/31.
 */
public class ApiUrlHelper {

    public static final String USER_INFO = "https://api.douban.com/v2/user/~me";//api登入地址
    public static final String BASE = "https://api.douban.com";//api调用地址
    public static final String BOOK_INFO = BASE + "/v2/book/:id";//获取图书信息
    public static final String BOOK_INFO_ISBN = BASE + "/v2/book/isbn/:name";//根据isbn获取图书信息
    public static final String USER_SEARCH = BASE + "/v2/book/search";//搜索图书
    public static final String USER_MAX_TAG = BASE + "/v2/book/:id/tags";//获取某个图书中标记最多的标签
    public static final String USER_ALL_TAG = BASE + "/v2/book/user/:name/tags";//获取用户对图书的所有标签
    public static final String USER_BOOK_ALL_INFO = BASE + "/v2/book/user/:name/collections";//获取某个用户的所有图书收藏信息
    public static final String USER_ONEBOOK_INFO = BASE + "/v2/book/:id/collection";//获取用户对某本图书的收藏信息
    public static final String USER_ALL_NOTE = BASE + "/v2/book/user/:name/annotations";// 获取某个用户的所有笔记
    public static final String USER_ONEBOOK_NOTE = BASE + "/v2/book/:id/annotations";//获取某本图书的所有笔记
    public static final String USER_ONENOTE_INFO = BASE + "/v2/book/annotation/:id";//获取某篇笔记的信息
    public static final String USER_BOOK_CATALOG = BASE + "/v2/book/series/:id/books";//获取丛书书目信息
}
