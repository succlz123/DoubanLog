package org.succlz123.doubanbooklog.ui.login;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.succlz123.doubanbooklog.R;

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


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_webview);

        webView = (WebView) findViewById(R.id.login_webview);
        String url = doubanUrl + "?" + "client_id=" + apiKey + "&redirect_uri=" + redirectUrl
                + "&response_type=code&scope=book_basic_r,book_basic_w";
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
                    String b = inputStream2String(a);
                    Log.v("LoginActivity", " ’µΩ∑µªÿtoken" + b);

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


}
