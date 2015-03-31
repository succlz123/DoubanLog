//package org.succlz123.doubanbooklog.dao;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.succlz123.doubanbooklog.bean.Book;
//import org.succlz123.doubanbooklog.support.http.JavaHttpClient;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * Created by fashi on 2015/3/29.
// */
//public class ReadingApi {
//
//
//    public static List<Book> getBooks(String token) {
//
//
//        HashMap<String,String> header=new HashMap<String, String>();
//        header.put("Authorization","Bearer " + token);
//
//        String url=ApiUrlHelper.USER_INFO;
//
//        String json = JavaHttpClient.getInstance().doGet(ApiUrlHelper.USER_INFO,header,token);
//
//        try {
//            JSONObject jsonObject=new JSONObject(json);
//            JSONArray array = jsonObject.optJSONArray("info");
//            ArrayList<Book> books = new ArrayList<Book>();
//
//            for (int i = 0; i < array.length(); i++) {
//
//                JSONObject object = array.getJSONObject(i);
//                String imageView = object.optString("imageView");
//                String name = object.optString("name");
//                String writer = object.optString("writer");
//                String info = object.optString("info");
//                String time = object.optString("time");
//                String tag = object.optString("tag");
//                Book book = new Book();
//                book.setImageView(imageView);
//                book.setName(name);
//                book.setWriter(writer);
//                book.setInfo(info);
//                book.setTime(time);
//                book.setTag(tag);
//
//                books.add(book);
//
//            }
//            return books;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
//
