package org.succlz123.doubanbooklog.ui.contetnfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.bean.annotationinfo.AnnotationObject;
import org.succlz123.doubanbooklog.bean.annotationinfo.AnnotationResult;
import org.succlz123.doubanbooklog.bean.bookinfo.DbCollection;
import org.succlz123.doubanbooklog.dao.AnnotationApi;
import org.succlz123.doubanbooklog.dao.GetBmApi;
import org.succlz123.doubanbooklog.support.com.shamanland.fab.FloatingActionButton;
import org.succlz123.doubanbooklog.support.com.shamanland.fab.ShowHideOnScroll;
import org.succlz123.doubanbooklog.support.xlistview.me.maxwin.view.XListView;
import org.succlz123.doubanbooklog.ui.activity.SetAnnotationActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fashi on 2015/4/3.
 */
public class AnnotationFragment extends Fragment {
    private AnnotationObject annotationObject;
    private DbCollection dbCollection;
    private List<AnnotationResult> annotationResult;
    private Boolean canLoadMore;
    private TextView none;
    private int xx;
    private Boolean booleanLoadMore = false;
    private org.succlz123.doubanbooklog.support.xlistview.me.maxwin.view.XListView xListView;
    private org.succlz123.doubanbooklog.support.com.shamanland.fab.FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.annotation_fragment, container, false);

        Intent intent = getActivity().getIntent();
        dbCollection = (DbCollection) intent.getParcelableExtra("book_info");//
        //上一个fragment传过来的数据 在这里进行接受 需要强制转换

        xListView = (XListView) view.findViewById(R.id.annotation_xlistview);
        none = (TextView) view.findViewById(R.id.annotation_none);//根据返回的笔记total来提示用户是否有笔记 没有显示貌似没有
        fab = (FloatingActionButton) view.findViewById(R.id.annotation_fab);//浮动的imagebutton
        fab.setShadow(true);
        fab.setColor(Color.parseColor("#0097a7"));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SetAnnotationActivity.class);
                startActivityForResult(intent, 2);
            }
        });
        xListView.setOnTouchListener(new ShowHideOnScroll(fab));//把xlistview和浮动imagebutton组合
        xListView.setPullLoadEnable(false);//默认不显示xlistview
        xListView.setPullRefreshEnable(true);//默认xlistview使用上下拉刷新

        //解析上一个fragment返回回来的数据intent里包含的书籍信息 包括对是否有笔记的显示提示
        new AnnotationAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        xListView.setAdapter(baseAdapter);//xlistview添加处理后的数据
        //xlistview的item监听
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AnnotationResult annotationResult = AnnotationFragment.this.annotationObject.getAnnotationResult().get(position - xListView.getHeaderViewsCount());
                AnnotationDialogFragment annotationDialogFragment = AnnotationDialogFragment.newInstance(annotationResult);
                annotationDialogFragment.show(getFragmentManager(), "");
            }
        });
        //xlistview的上下拉刷新
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                Refresh();
            }

            @Override
            public void onLoadMore() {
                LoadMore();
            }
        });
        return view;
    }

    BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            if (annotationObject != null) {
                return annotationObject.getItemCount();
                //匿名内部类一进来就被调用 所以先判断是否为空  getannotation  的返回值得 size 用total会数组越界
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.annotation_listview_item, parent, false);
            } else {
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.annotation_item_imageView);
            TextView name = (TextView) convertView.findViewById(R.id.annotation_item_name);
            TextView time = (TextView) convertView.findViewById(R.id.annotation_item_time);
            TextView text = (TextView) convertView.findViewById(R.id.annotation_item_text);

            annotationResult = AnnotationFragment.this.annotationObject.getAnnotationResult();
            AnnotationResult annotationResults = annotationResult.get(position);

            new ImageViewAsyncTask(imageView, annotationResults).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            name.setText((position + 1) + ". " + annotationResults.getAuthor_user().getName());
            time.setText(annotationResults.getTime());
            text.setText(annotationResults.getContent());
            return convertView;
        }
    };

    private class AnnotationAsyncTask extends AsyncTask<Void, Void, AnnotationObject> {
        private Integer id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            id = dbCollection.getBook_id();
        }

        @Override
        protected AnnotationObject doInBackground(Void... params) {
            return AnnotationApi.getAnnotation(id, 0);
        }

        @Override
        protected void onPostExecute(AnnotationObject result) {
            super.onPostExecute(result);
            AnnotationFragment.this.annotationObject = result;
            baseAdapter.notifyDataSetChanged();
            reset();
            xx = 0;
            if (result != null) {
                canLoadMore = result.getAnnotationResult().size() < result.getTotal().intValue();
                xListView.setPullLoadEnable(canLoadMore);
            }
            if (result.getTotal() == 0) {
                none.setVisibility(View.VISIBLE);
            }
        }
    }

    private class ImageViewAsyncTask extends AsyncTask<Void, Void, Bitmap> {
        private ImageView imageView;
        private AnnotationResult annotationResult;

        public ImageViewAsyncTask(ImageView imageView, AnnotationResult annotationResult) {
            this.imageView = imageView;
            this.annotationResult = annotationResult;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            return GetBmApi.getBitMap(annotationResult.getAuthor_user().getAvatar().toString());
        }

        @Override
        protected void onPostExecute(Bitmap aVoid) {
            super.onPostExecute(aVoid);
            imageView.setImageBitmap(aVoid);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }

    private class LoadMoreAsyncTask extends AsyncTask<Void, Void, AnnotationObject> {
        private int start;
        private int id;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            start = xx + 10;
            id = dbCollection.getBook_id();
        }

        @Override
        protected AnnotationObject doInBackground(Void... params) {
            return AnnotationApi.getAnnotation(id, start);
        }

        @Override
        protected void onPostExecute(AnnotationObject aVoid) {
            super.onPostExecute(aVoid);
            AnnotationFragment.this.annotationObject.getAnnotationResult().addAll(aVoid.getAnnotationResult());
            //把新刷新出来的数据 加入到上面去 然后刷新listview
            baseAdapter.notifyDataSetChanged();
            reset();
            if (annotationResult.size() < 20 || annotationObject.getStart() >= annotationObject.getTotal()) {
                xListView.setPullLoadEnable(false);
                booleanLoadMore = false;
            } else {
                xx = start;
            }
        }
    }

    private void reset() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);

        xListView.stopRefresh();
        xListView.stopLoadMore();
        xListView.setRefreshTime(str);
    }

    private void Refresh() {
        new AnnotationAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void LoadMore() {
        if (!booleanLoadMore) {
            new LoadMoreAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }
    }
}
