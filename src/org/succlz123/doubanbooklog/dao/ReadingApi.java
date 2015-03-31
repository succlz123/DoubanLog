package org.succlz123.doubanbooklog.dao;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.succlz123.doubanbooklog.DoubanApplication;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.bean.Book;
import org.succlz123.doubanbooklog.support.lib.JavaHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fashi on 2015/3/29.
 */
public class ReadingApi {


    public static List<Book> getBooks(String token) {


        String json = JavaHttpClient.getInstance().doGet(token);

//        String json1 = rawRead();

        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONArray array = jsonObject.optJSONArray("info");
            ArrayList<Book> books = new ArrayList<Book>();

            for (int i = 0; i < array.length(); i++) {

                JSONObject object = array.getJSONObject(i);
                String imageView = object.optString("imageView");
                String name = object.optString("name");
                String writer = object.optString("writer");
                String info = object.optString("info");
                String time = object.optString("time");
                String tag = object.optString("tag");
                Book book = new Book();
                book.setImageView(imageView);
                book.setName(name);
                book.setWriter(writer);
                book.setInfo(info);
                book.setTime(time);
                book.setTag(tag);

                books.add(book);
            }
            return books;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private  static String rawRead() {

        StringBuilder stringBuffer = new StringBuilder();
        try {
            InputStream inputStream = DoubanApplication.getInstance().getResources().openRawResource(R.raw.json);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String info = "";
            while ((info = bufferedReader.readLine()) != null) {
                Log.i("info", info);
                stringBuffer.append(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
