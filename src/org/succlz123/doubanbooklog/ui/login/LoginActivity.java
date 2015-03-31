package org.succlz123.doubanbooklog.ui.login;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toolbar;
import org.json.JSONException;
import org.json.JSONObject;
import org.succlz123.doubanbooklog.DoubanApplication;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.bean.DbAccount;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by fashi on 2015/3/25.
 */
public class LoginActivity extends Activity {

    private static final String doubanUrl = "https://www.douban.com/service/auth2/auth";
    private static final String tokenUrl = "https://www.douban.com/service/auth2/token";
    private static final String apiKey = "0e24b05dfc621fc02a6e1119f5265f40";
    private static final String secret = "e8a9edd1c47e067b";
    private static final String redirectUrl = "doubanbooklog://ok";

    private WebView webView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_webview);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("登录");
        mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        final Button toolbarBtn = (Button) findViewById(R.id.toolbar_btn);

        toolbarBtn.setBackgroundResource(R.drawable.back);

        toolbarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        webView = (WebView) findViewById(R.id.login_webview);
        String url = doubanUrl + "?" + "client_id=" + apiKey + "&redirect_uri=" + redirectUrl
                + "&response_type=code&scope=book_basic_r,book_basic_w,douban_basic_common";//scope是权限
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(redirectUrl)) {
                    String code = Uri.parse(url).getQueryParameter("code");
                    Log.v("LoginActivity", "code=" + code);
                    getToken(code);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    public void getToken(String code) {

        final String adress = tokenUrl + "?" + "client_id=" + apiKey + "&client_secret=" + secret + "&redirect_uri=" + redirectUrl + "&grant_type=" + "authorization_code" + "&code=" + code;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(adress);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setUseCaches(false);
                    conn.setRequestMethod("POST");
                    InputStream a = conn.getInputStream();
                    final String json = inputStream2String(a);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            DbAccount account = getAccoutFromJson(json);
                            DoubanApplication.getInstance().addAccount(account);
                            finish();
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public String inputStream2String(InputStream in) throws IOException {

        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];

        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    private DbAccount getAccoutFromJson(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            String accessToken = jsonObject.optString("access_token", "");
            String userName = jsonObject.optString("douban_user_name", "");
            Integer userId = jsonObject.optInt("douban_user_id", 0);
            Integer expiresIn = jsonObject.optInt("expires_in", 0);
            String refreshToken = jsonObject.optString("refresh_token", "");

            DbAccount dba = new DbAccount();
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
