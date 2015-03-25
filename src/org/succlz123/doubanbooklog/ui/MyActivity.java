package org.succlz123.doubanbooklog.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import org.json.JSONException;
import org.json.JSONObject;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.bean.DoubanAccount;
import org.succlz123.doubanbooklog.ui.login.LoginActivity;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MyActivity.this, LoginActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        getJson("");
    }

    public String getJson(String json) {

        String result = "{\"access_token\":\"aafe693f84661e8a00fb3bbe8d325b9e\",\n" +
                "\"douban_user_name\":\"sunjacker\",\n" +
                "\"douban_user_id\":\"4500341\"\n" +
                ",\"expires_in\":604800,\n" +
                "\"refresh_token\":\"80e45ac3734eeec382ad104a59eccc49\"}";

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
            dba.setAccess_token(refreshToken);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;

    }

}
