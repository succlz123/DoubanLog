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
import android.widget.*;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.bean.bookinfo.DbCollection;
import org.succlz123.doubanbooklog.bean.reviewsinfo.ReviewsObject;
import org.succlz123.doubanbooklog.bean.reviewsinfo.ReviewsResult;
import org.succlz123.doubanbooklog.dao.GetBmApi;
import org.succlz123.doubanbooklog.dao.ReviewsApi;
import org.succlz123.doubanbooklog.support.com.shamanland.fab.FloatingActionButton;
import org.succlz123.doubanbooklog.support.com.shamanland.fab.ShowHideOnScroll;
import org.succlz123.doubanbooklog.support.xlistview.me.maxwin.view.XListView;
import org.succlz123.doubanbooklog.ui.activity.SetAnnotationActivity;

import java.util.List;

/**
 * Created by fashi on 2015/4/3.
 */
public class ReviewsFragment extends Fragment {
    private XListView xListView;
    private FloatingActionButton fab;
    private DbCollection dbCollection;
    private List<ReviewsResult> reviewsResult;
    private ReviewsObject reviewsObject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reviews_fragment, container, false);
        Intent intent = getActivity().getIntent();
        dbCollection = (DbCollection) intent.getSerializableExtra("book_info");

        xListView = (XListView) view.findViewById(R.id.reviews_xlistview);

        fab = (FloatingActionButton) view.findViewById(R.id.reviews_fab);//浮动的imagebutton
        fab.setShadow(true);
        fab.setColor(Color.parseColor("#0097a7"));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SetAnnotationActivity.class);
                startActivityForResult(intent, 2);
            }
        });

        new ReviewsAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        xListView.setOnTouchListener(new ShowHideOnScroll(fab));//把xlistview和浮动imagebutton组合
        xListView.setPullLoadEnable(false);//默认不显示xlistview
        xListView.setPullRefreshEnable(true);//默认xlistview使用上下拉刷新
        xListView.setAdapter(baseAdapter);
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReviewsResult reviewsResult = reviewsObject.getReviewsResult().get(position - xListView.getHeaderViewsCount());
                ReviewsDialogFragment reviewsDialogFragment = ReviewsDialogFragment.newInstance(reviewsResult);
//                reviewsDialogFragment.show(getFragmentManager(), "");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.reviews_fragment, reviewsDialogFragment)
                        .addToBackStack("")//自动处理fragment的返回键
                        .commitAllowingStateLoss();
            }
        });
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });

        return view;
    }

    BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            if (reviewsObject != null) {
                return reviewsObject.getItemcount();
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
                convertView = getActivity().getLayoutInflater().inflate(R.layout.reviews_listview_item, parent, false);
            } else {
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.reviews_item_imageView);
            TextView title = (TextView) convertView.findViewById(R.id.reviews_item_title);
            TextView name = (TextView) convertView.findViewById(R.id.reviews_item_name);
            RatingBar ratingbar = (RatingBar) convertView.findViewById(R.id.reviews_item_ratingbar);
            TextView time = (TextView) convertView.findViewById(R.id.reviews_item_time);
            TextView useful = (TextView) convertView.findViewById(R.id.reviews_item_useful);
            TextView text = (TextView) convertView.findViewById(R.id.reviews_item_text);

            reviewsResult = reviewsObject.getReviewsResult();
            ReviewsResult reviewsResults = reviewsResult.get(position);

            new ImageViewAsyncTask(imageView, reviewsResults).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            title.setText((position + 1) + ". " + reviewsResults.getTitle());
            name.setText(reviewsResults.getReviewsUser().getName());
            ratingbar.setMax(reviewsResults.getReviewsRating().getMax());
            ratingbar.setProgress(reviewsResults.getReviewsRating().getValue());
            time.setText(reviewsResults.getCreate_time().substring(0, 10));
            useful.setText(reviewsResults.getUseful_count() + "人认为有用");
            text.setText(reviewsResults.getText());

            return convertView;
        }
    };

    private class ReviewsAsyncTask extends AsyncTask<Void, Void, ReviewsObject> {

        private int id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            id = dbCollection.getBook_id();
        }

        @Override
        protected ReviewsObject doInBackground(Void... params) {
            return ReviewsApi.GetReveiws(id);
        }

        @Override
        protected void onPostExecute(ReviewsObject aVoid) {
            super.onPostExecute(aVoid);
            reviewsObject = aVoid;
            baseAdapter.notifyDataSetChanged();
        }
    }

    private class ImageViewAsyncTask extends AsyncTask<Void, Void, Bitmap> {
        private ImageView imageView;
        private ReviewsResult reviewsResult;

        public ImageViewAsyncTask(ImageView imageView, ReviewsResult reviewsResult) {
            this.imageView = imageView;
            this.reviewsResult = reviewsResult;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            return GetBmApi.getBitMap(reviewsResult.getReviewsUser().getAvatar().toString());
        }

        @Override
        protected void onPostExecute(Bitmap aVoid) {
            super.onPostExecute(aVoid);
            imageView.setImageBitmap(aVoid);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }

}
