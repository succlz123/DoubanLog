package org.succlz123.doubanbooklog.ui.main_fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.succlz123.doubanbooklog.DoubanApplication;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.bean.Book;
import org.succlz123.doubanbooklog.bean.DoubanAccount;
import org.succlz123.doubanbooklog.dao.ReadingApi;
import org.succlz123.doubanbooklog.ui.login.LoginActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by fashi on 2015/3/27.
 */
public class ReadingFragment extends Fragment {

    private DoubanAccount account;
    private List<Book> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.reading_fragment, container, false);

        final Button login_btn = (Button) view.findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener()

                                     {
                                         @Override
                                         public void onClick(View view) {

                                             Intent intent = new Intent(getActivity(), LoginActivity.class);
                                             startActivityForResult(intent, 0);
                                         }
                                     }

        );

        ListView listView = (ListView) view.findViewById(R.id.reading_list_view);

        listView.setAdapter(adapter);

        this.account = DoubanApplication.getInstance().getAccount();
        if (this.account == null) {
            login_btn.setVisibility(View.VISIBLE);
        } else {
            login_btn.setVisibility(View.INVISIBLE);
            refresh();
        }

        DoubanApplication.getInstance().addAccountListener(new DoubanApplication.AccountChangeListener() {
            @Override
            public void onAccount(DoubanAccount account) {
                ReadingFragment.this.account = account;
                login_btn.setVisibility(View.INVISIBLE);
                refresh();
            }
        });


        return view;
    }


    private void refresh() {
        final String token = this.account.getAccess_token();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Book> bookList = ReadingApi.getBooks(token);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ReadingFragment.this.list = bookList;
                        adapter.notifyDataSetChanged();//刷新 adapter
                    }
                });
            }
        }).start();
    }

    BaseAdapter adapter = new BaseAdapter() {

        final int TYPE_DEFAULT = 0;

        @Override
        public int getCount() {
            if (ReadingFragment.this.list != null) {
                return ReadingFragment.this.list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return TYPE_DEFAULT;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {

            if (convertView == null) {
//                    holder = new ViewHolder();
                convertView = getActivity().getLayoutInflater().inflate(R.layout.reading_list_view_item, null, false);
            } else {

            }
            final ImageView imageView = (ImageView) convertView.findViewById(R.id.reading_list_view_item_img);
            TextView name = (TextView) convertView.findViewById(R.id.reading_list_view_item_bookname);
            TextView writer = (TextView) convertView.findViewById(R.id.reading_list_view_item_writer);
            TextView info = (TextView) convertView.findViewById(R.id.reading_list_view_item_info);
            TextView time = (TextView) convertView.findViewById(R.id.reading_list_view_item_time);
            TextView tag = (TextView) convertView.findViewById(R.id.reading_list_view_item_tag);

            final Book book = ReadingFragment.this.list.get(i);
            if (book.getImageView() != null && !book.getImageView().trim().equals("") && book.getImageView().startsWith("http://")) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = getBitMap(book.getImageView());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                            }
                        });
                    }
                }).start();
            } else {
                imageView.setImageBitmap(null);
            }

            name.setText((i + 1) + "." + book.getName());
            writer.setText(book.getWriter());
            info.setText(book.getInfo());
            time.setText(book.getTime() + " 想读");
            if (book.getTag() != null && book.getTag() != "") {
                tag.setText("标签 " + book.getTag());
            } else {
                tag.setVisibility(View.GONE);
            }
            return convertView;
        }
    };

    public static Bitmap getBitMap(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;

        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection)
                    myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            int code = conn.getResponseCode();//获取网络返回参数 200 可以被外链显示
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
