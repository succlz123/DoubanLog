package org.succlz123.doubanbooklog.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.squareup.okhttp.*;
import org.succlz123.doubanbooklog.DoubanApplication;
import org.succlz123.doubanbooklog.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by fashi on 2015/4/4.
 */
public class SetAnnotationActivity extends Activity {

    private Toolbar mToolbar;
    private Button toolbarbtn;
    private EditText paginationEdit;
    private EditText chapter_titleEdit;
    private EditText annotation_textEdit;
    private String content;
    private String page;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setannotation_activity);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("写书评");
        mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        setActionBar(mToolbar);
        toolbarbtn = (Button) findViewById(R.id.toolbar_btn);
        toolbarbtn.setBackgroundResource(R.drawable.back);
        toolbarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button ok = new Button(this);
        ok.setBackgroundResource(R.drawable.back);
        mToolbar.addView(ok, new ViewGroup.LayoutParams(40 * 3, ViewGroup.LayoutParams.MATCH_PARENT));

        paginationEdit = (EditText) findViewById(R.id.pagination);
        chapter_titleEdit = (EditText) findViewById(R.id.chapter_title);
        annotation_textEdit = (EditText) findViewById(R.id.annotation_edit_text);


        annotation_textEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final Button bt = (Button) findViewById(R.id.yuanwen);
        ok.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      new Thread(new Runnable() {
                                          @Override
                                          public void run() {
                                              String pagination = paginationEdit.getText().toString();
                                              String chapter_title = chapter_titleEdit.getText().toString();
                                              content = annotation_textEdit.getText().toString();

                                              if (chapter_title != null || !chapter_title.equals("")) {
                                                  page = chapter_title;
                                              } else if (pagination != null || !pagination.equals("")) {
                                                  page = pagination;
                                              } else {
                                                  Toast.makeText(SetAnnotationActivity.this, "请输入页码或者标题", Toast.LENGTH_LONG).show();
                                              }
                                              HashMap<String, String> params = new HashMap<String, String>();
                                              params.put("content", content);
                                              params.put("page", page);
                                              SetAnnotationActivity.SetAnnotation("https://api.douban.com/v2/book/1855231/annotations", params);
                                          }
                                      }

                                      ).

                                              start();
                                  }
                              }

        );

    }

    private static void SetAnnotation(String adress, HashMap<String, String> params) {

        HashMap<String, String> header = new HashMap<String, String>();
        header.put("Authorization", "Bearer " + DoubanApplication.getInstance().getAccount().getAccess_token());

        OkHttpClient okHttpClient = new OkHttpClient();
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            formEncodingBuilder.add(key, params.get(key));
        }
        Request.Builder builder = new Request.Builder().url(adress).post(formEncodingBuilder.build());

        builder.addHeader("Authorization", header.get("Authorization"));

        Request request = builder.build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
