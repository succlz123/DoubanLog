package org.succlz123.doubanbooklog.support.lib;

import org.json.JSONException;
import org.json.JSONObject;
import org.succlz123.doubanbooklog.bean.DoubanAccount;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by fashi on 2015/3/29.
 */
public class JavaHttpClient {

    private static JavaHttpClient instance;

    public synchronized static JavaHttpClient getInstance() {
        if (instance == null) {
            instance = new JavaHttpClient();

        }
        return instance;
    }

    public String doGet(String token) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL("https://api.douban.com/shuo/v2/statuses/");
            HttpURLConnection connection = (HttpsURLConnection) url.openConnection();

//            String encode= Base64.encodeToString(token.getBytes("UTF-8"),Base64.NO_WRAP);

            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Bearer"+token);
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() == 200) {
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


    public String doPost() {

        try {
            DoubanAccount account = new DoubanAccount();
            account.getAccess_token();
//            String adress=
//                    url= new URL(adress);

            URL url = new URL("");
            HttpURLConnection  connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            InputStream in = connection.getInputStream();

//        InputStreamReader inx=new InputStreamReader(in);
//        可以拆出来
//        BufferedReader reader=new BufferedReader(inx);

            if (connection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {

                    final String json = inputStream2String(in);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public String inputStream2String(InputStream in) throws IOException {

        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];

        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    private DoubanAccount getDoubanInfoFromJson(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            String accessToken = jsonObject.optString("access_token", "");
            String userName = jsonObject.optString("douban_user_name", "");
            Integer userId = jsonObject.optInt("douban_user_id", 0);
            Integer expiresIn = jsonObject.optInt("expires_in", 0);
            String refreshToken = jsonObject.optString("refresh_token", "");

            DoubanAccount dba = new DoubanAccount();
            dba.setAccess_token(accessToken);
            dba.setDouban_user_name(userName);
            dba.setDouban_user_id(userId);
            dba.setExpires_in(expiresIn);
            dba.setRefresh_token(refreshToken);

            return dba;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
