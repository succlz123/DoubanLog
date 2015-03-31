package org.succlz123.doubanbooklog.ui.main_fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import org.succlz123.doubanbooklog.DoubanApplication;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.bean.BookInfo;
import org.succlz123.doubanbooklog.bean.DoubanAccount;
import org.succlz123.doubanbooklog.dao.ReadApi;

import java.util.List;

/**
 * Created by fashi on 2015/3/27.
 */
public class ReadFragment extends Fragment {

    private DoubanAccount account;

    private List<BookInfo> list;

    private MediaPlayer mediaPlayer=new MediaPlayer();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.read_fragment, container, false);


       final Button login_btn = (Button) view.findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener()

                                     {
                                         @Override
                                         public void onClick(View view) {

//                                             Intent intent = new Intent(getActivity(), LoginActivity.class);
//                                             startActivityForResult(intent, 0);
                                             ReadApi.getBooks();
//                                             List<BookInfo> bookList = ReadApi.getBooks();
//                                             Log.d("1123", ReadApi.getBooks());

                                         }
                                     }

        );

        ListView listView = (ListView) view.findViewById(R.id.read_list_view);

        listView.setAdapter(adapter);

//        this.account = DoubanApplication.getInstance().getAccount();
//        if (this.account == null) {
//            login_btn.setVisibility(View.VISIBLE);
//        } else {
//            login_btn.setVisibility(View.INVISIBLE);
//            refresh();
//        }

        DoubanApplication.getInstance().addAccountListener(new DoubanApplication.AccountChangeListener() {
            @Override
            public void onAccount(DoubanAccount account) {
                ReadFragment.this.account = account;
                login_btn.setVisibility(View.INVISIBLE);
                refresh();
            }
        });

        return view;
    }

    private void refresh() {
//        String token = this.account.getAccess_token();
//        List<BookInfo> bookList = ReadApi.getBooks();
//        this.list = bookList;
//        adapter.notifyDataSetChanged();//刷新 adapter

    }

    BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
//                    holder = new ViewHolder();
                view = getActivity().getLayoutInflater().inflate(R.layout.reading_list_view_item, null, false);
            } else {

                TextView id = (TextView) view.findViewById(R.id.read_list_view_item_bookname);

//                BookInfo bookInfo = ReadFragment.this.list.get(i);


//                id.setText(ReadApi.getBooks());
            }
            return view;
        }
    };


}
