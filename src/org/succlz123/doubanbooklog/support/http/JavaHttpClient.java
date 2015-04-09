package org.succlz123.doubanbooklog.support.http;

import com.squareup.okhttp.OkHttpClient;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by fashi on 2015/3/29.
 */
public class JavaHttpClient {

    private static JavaHttpClient instance;

    //单例模式  instance 实例  javaHttpClient的实例
    public synchronized static JavaHttpClient getInstance() {
        if (instance == null) {
            instance = new JavaHttpClient();
        }
        return instance;
    }

    public String doGet(String address, HashMap<String, String> headers, String token) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setConnectTimeout(6000);
            connection.setReadTimeout(6000);
            connection.setRequestMethod("GET");
            if (headers != null) {
                Set<String> keys = headers.keySet();//先是拿到所有的key 然后依次把key和对应的value放进去
                for (String key : keys) {
                    connection.setRequestProperty(key, headers.get(key));
                }
            }

            if (connection.getResponseCode() == 200) {
                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } else {
                InputStream in = connection.getErrorStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    public String doPost(String address) {

        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
