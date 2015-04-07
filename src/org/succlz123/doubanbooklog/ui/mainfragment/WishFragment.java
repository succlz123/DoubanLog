package org.succlz123.doubanbooklog.ui.mainfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.succlz123.doubanbooklog.DoubanApplication;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.bean.bookinfo34.DbCollection;
import org.succlz123.doubanbooklog.bean.bookinfo34.DbObject;
import org.succlz123.doubanbooklog.bean.DbAccount;
import org.succlz123.doubanbooklog.dao.BookInfoApi;
import org.succlz123.doubanbooklog.dao.GetBmApi;
import org.succlz123.doubanbooklog.support.xlistview.me.maxwin.view.XListView;
import org.succlz123.doubanbooklog.ui.activity.ContentActivity;
import org.succlz123.doubanbooklog.ui.login.LoginActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fashi on 2015/3/27.
 */
public class WishFragment extends Fragment {

    private org.succlz123.doubanbooklog.support.xlistview.me.maxwin.view.XListView listView;
    private DbAccount account;
    private DbObject dbObject;
    private LruCache<DbCollection, Bitmap> lruCache;
    private int page;
    private String status = "wish";
    private boolean isLoading = false;
    private boolean canLoadMore = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wish_fragment, container, false);
        final Button login_btn = (Button) view.findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent intent = new Intent(getActivity(), LoginActivity.class);
                                             startActivityForResult(intent, 1);
                                         }
                                     }
        );

        listView = (org.succlz123.doubanbooklog.support.xlistview.me.maxwin.view.XListView) view.findViewById(R.id.wish_list_view);
        listView.setAdapter(adapter);
        listView.setPullLoadEnable(false);
        listView.setPullRefreshEnable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DbCollection dbCollection = WishFragment.this.dbObject.getCollections().get(position - listView.getHeaderViewsCount());//因为listview里有个头尾 所以用它来获取list里的第一个
                Intent intent = new Intent(getActivity(), ContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("book_info", dbCollection);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        listView.setXListViewListener(new XListView.IXListViewListener() {

            @Override
            public void onRefresh() {
                refresh();
            }

            @Override
            public void onLoadMore() {
                loadMore();
            }
        });

        this.account = DoubanApplication.getInstance().getAccount();
        if (this.account == null) {
            login_btn.setVisibility(View.VISIBLE);
        } else {
            login_btn.setVisibility(View.INVISIBLE);
            refresh();
        }

        DoubanApplication.getInstance().addAccountListener(new DoubanApplication.AccountChangeListener() {
            @Override
            public void onAccount(DbAccount account) {
                WishFragment.this.account = account;
                login_btn.setVisibility(View.INVISIBLE);
                refresh();
            }
        });
        return view;
    }

    BaseAdapter adapter = new BaseAdapter() {
        final int TYPE_DEFAULT = 0;

        @Override
        public int getCount() {
            if (WishFragment.this.dbObject != null) {
                return WishFragment.this.dbObject.getItemCount();
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
                convertView = getActivity().getLayoutInflater().inflate(R.layout.wish_list_view_item, null, false);
            } else {
            }
            final ImageView imageView = (ImageView) convertView.findViewById(R.id.wish_list_view_item_img);
            TextView title = (TextView) convertView.findViewById(R.id.wish_list_view_item_bookname);
            TextView author = (TextView) convertView.findViewById(R.id.wish_list_view_item_writer);
            TextView info = (TextView) convertView.findViewById(R.id.wish_list_view_item_info);
            TextView updated = (TextView) convertView.findViewById(R.id.wish_list_view_item_time);
            TextView tag = (TextView) convertView.findViewById(R.id.wish_list_view_item_tag);

            final DbCollection dbCollection = WishFragment.this.dbObject.getCollections().get(i);
//            Bitmap memoryCachedBitmap = WishFragment.this.lruCache.get(dbCollection);
//            if (memoryCachedBitmap != null) {
//                imageView.setImageBitmap(memoryCachedBitmap);
//            } else {
            if (dbCollection.getBook().getImage() != null && !dbCollection.getBook().getImage().trim().equals("") && dbCollection.getBook().getImage().startsWith("http://")) {
                imageView.setTag(dbCollection);
                imageView.setImageBitmap(null);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = GetBmApi.getBitMap(dbCollection.getBook().getImage());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Object tagObject = imageView.getTag();
                                if (tagObject == dbCollection) {
                                    imageView.setImageBitmap(bitmap);
                                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                                        WishFragment.this.lruCache.put(dbCollection, bitmap);
                                }
                            }
                        });
                    }
                }).start();
            } else {
                imageView.setImageBitmap(null);
            }
//            }

            title.setText((i + 1) + "." + dbCollection.getBook().getTitle());

            StringBuilder authorBuilder = new StringBuilder();
            List<String> authors = dbCollection.getBook().getAuthor();
//            for (String singleAuthor : authors) {
//                authorBuilder.append(" ");
//                authorBuilder.append(singleAuthor);
//            }

            for (int who = 0; who < authors.size(); who++) {
                if (who > 0) {
                    authorBuilder.append(" ");
                }
                String singleAuthor = authors.get(who);
                authorBuilder.append(singleAuthor);
            }//输出获得的列表

            author.setText(authorBuilder.toString());
            info.setText(dbCollection.getBook().getPublisher() + " " + dbCollection.getBook().getPubdate() + " " + dbCollection.getBook().getPrice());
            updated.setText(dbCollection.getUpdated() + " 想读");

//            if (dbCollection.getBook().get() != null && book.getTag() != "") {
//                tag.setText("标签 " + book.getTag());
//            } else {
//                tag.setVisibility(View.GONE);
//            }
            return convertView;
        }
    };

    private void reset() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);

        listView.stopRefresh();
        listView.stopLoadMore();
        listView.setRefreshTime(str);
    }

    private void refresh() {
        if (!isLoading) {
            isLoading = true;
            new RefreshAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private void loadMore() {
        if (!isLoading && canLoadMore) {
            isLoading = true;
            new LoadMoreAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    private class RefreshAsyncTask extends AsyncTask<Void, Void, DbObject> {
        private String token;
        private Integer id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            token = WishFragment.this.account.getAccess_token();
            id = WishFragment.this.account.getDouban_user_id();
        }

        @Override
        protected DbObject doInBackground(Void... params) {
            DbObject dbObject = BookInfoApi.getBooks(token, id, status, 0);
            return dbObject;
        }

        @Override
        protected void onPostExecute(DbObject result) {
            super.onPostExecute(result);
            isLoading = false;
            reset();
            if (result == null || result.getItemCount() == 0) {
                return;
            }
            page = 0;
            WishFragment.this.dbObject = result;
            adapter.notifyDataSetChanged();

            if (dbObject != null) {
                canLoadMore = dbObject.getItemCount() < dbObject.getTotal();
            }

            listView.setPullLoadEnable(canLoadMore);
        }
    }

    private class LoadMoreAsyncTask extends AsyncTask<Void, Void, DbObject> {

        private String token;
        private Integer id;
        private int requestPage;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            token = WishFragment.this.account.getAccess_token();
            id = WishFragment.this.account.getDouban_user_id();
            requestPage = page + 20;
        }

        @Override
        protected DbObject doInBackground(Void... params) {
            DbObject dbObject = BookInfoApi.getBooks(token, id, status, requestPage);
            return dbObject;
        }

        @Override
        protected void onPostExecute(DbObject result) {
            super.onPostExecute(result);
            isLoading = false;
            reset();
            if (result == null || result.getItemCount() == 0) {
                return;
            }
            page = requestPage;
            if (WishFragment.this.dbObject == null || WishFragment.this.dbObject.getItemCount() == 0) {
                WishFragment.this.dbObject = result;
                adapter.notifyDataSetChanged();
                return;
            }
            WishFragment.this.dbObject.addOld(result);
            adapter.notifyDataSetChanged();
            if (dbObject != null) {
                canLoadMore = dbObject.getItemCount() < dbObject.getTotal();
            }
            listView.setPullLoadEnable(canLoadMore);
        }
    }
}
