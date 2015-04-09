package org.succlz123.doubanbooklog.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import com.squareup.okhttp.OkHttpClient;
import org.succlz123.doubanbooklog.DoubanApplication;
import org.succlz123.doubanbooklog.R;

import java.util.HashMap;

/**
 * Created by fashi on 2015/4/9.
 */
public class SetReviewsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setreviews_activity);


        EditText titleEdit = (EditText) findViewById(R.id.title);
        EditText contentEdit = (EditText) findViewById(R.id.content);



    }

    private static void SetReviews (){
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("Authorization", "Bearer " + DoubanApplication.getInstance().getAccount().getAccess_token());
        OkHttpClient okHttpClient=new OkHttpClient();
    }
}
