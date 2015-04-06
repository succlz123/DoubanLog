package org.succlz123.doubanbooklog.ui.contetnfragment;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.bean.reviewsinfo.ReviewsResult;
import org.succlz123.doubanbooklog.dao.GetBmApi;

/**
 * Created by fashi on 2015/4/6.
 */
public class ReviewsDialogFragment extends Fragment {

    public static ReviewsDialogFragment newInstance(ReviewsResult reviewsResult) {
        ReviewsDialogFragment reviewsDialogFragment = new ReviewsDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("reviewsResult", reviewsResult);
        reviewsDialogFragment.setArguments(bundle);
        return reviewsDialogFragment;
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reviews_dialogfragment, container, false);

//        AlertDialog.Builder builer = new AlertDialog.Builder(getActivity());
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View view = inflater.inflate(R.layout.reviews_dialogfragment, null);
//        builer.setView(view).setNegativeButton("关闭", null);

        ImageView avater_img = (ImageView) view.findViewById(R.id.reviews_dialog_img);
        TextView time = (TextView) view.findViewById(R.id.reviews_dialog_time);
        TextView name = (TextView) view.findViewById(R.id.reviews_dialog_name);
        ImageView share_img = (ImageView) view.findViewById(R.id.reviews_dialog_share);
        TextView titel = (TextView) view.findViewById(R.id.reviews_dialog_title);
        TextView content = (TextView) view.findViewById(R.id.reviews_dialog_content);

        ReviewsResult reviewsResult=(ReviewsResult)getArguments().getSerializable("reviewsResult");

        new ImageViewAsyncTask(avater_img,reviewsResult).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        time.setText(reviewsResult.getCreate_time());
        name.setText(reviewsResult.getReviewsUser().getName());
        share_img.setImageResource(R.drawable.share);
        titel.setText(reviewsResult.getTitle());
        content.setText(reviewsResult.getText());

//        return builer.create();
        return view;
    }

    private class ImageViewAsyncTask extends AsyncTask<Void, Void, Bitmap> {

        private ImageView avater;
        private ReviewsResult reviewsResult;

        public ImageViewAsyncTask(ImageView avater, ReviewsResult reviewsResult) {
            this.avater = avater;
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
            avater.setImageBitmap(aVoid);
        }
    }

}
