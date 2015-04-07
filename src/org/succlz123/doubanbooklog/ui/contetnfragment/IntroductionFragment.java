package org.succlz123.doubanbooklog.ui.contetnfragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.bean.bookinfo.DbCollection;
import org.succlz123.doubanbooklog.bean.bookinfo.GlobalTag;
import org.succlz123.doubanbooklog.dao.GetBmApi;

import java.util.List;

/**
 * Created by fashi on 2015/4/3.
 */
public class IntroductionFragment extends Fragment {
    private DbCollection dbCollection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.introduction_fragment, container, false);
        Intent intent = getActivity().getIntent();
        dbCollection = (DbCollection) intent.getParcelableExtra("book_info");

        ImageView imageView = (ImageView) view.findViewById(R.id.introduction_book_imageview);
        TextView author = (TextView) view.findViewById(R.id.introduction_text_author);
        TextView publisher = (TextView) view.findViewById(R.id.introduction_text_publisher);
        TextView alt_title = (TextView) view.findViewById(R.id.introduction_text_alt_title);
        TextView pages = (TextView) view.findViewById(R.id.introduction_text_pages);
        TextView price = (TextView) view.findViewById(R.id.introduction_text_price);
        TextView isbn = (TextView) view.findViewById(R.id.introduction_text_isbn);
        RatingBar ratingbar = (RatingBar) view.findViewById(R.id.introduction_text_globalrating_ratingbar);
        TextView numraters = (TextView) view.findViewById(R.id.introduction_text_globalrating_numraters);

        TextView review_status = (TextView) view.findViewById(R.id.introduction_text_review_status);
        TextView review_time = (TextView) view.findViewById(R.id.introduction_text_review_time);
        RatingBar review_ratingbar = (RatingBar) view.findViewById(R.id.introduction_text_review_ratingbar);

        TextView tags = (TextView) view.findViewById(R.id.introduction_text_tags);
        TextView summarys = (TextView) view.findViewById(R.id.introduction_text_summarys);
        TextView catalogs = (TextView) view.findViewById(R.id.introduction_text_catalogs);
        TextView autho_intros = (TextView) view.findViewById(R.id.introduction_text_autho_intros);


        new ImageViewAsyncTask(imageView).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        author.setText("作者: " + dbCollection.getBook().getAuthor().toString());
        publisher.setText("出版社: " + dbCollection.getBook().getPublisher().toString());
        alt_title.setText("副标题: " + "" + dbCollection.getBook().getAlt_title());//空字符串加int 自动转换string
        pages.setText("页数: " + "" + dbCollection.getBook().getPages());
        price.setText("定价: " + dbCollection.getBook().getPrice().toString());
        isbn.setText("ISBN: " + dbCollection.getBook().getIsbn13().toString());

        ratingbar.setMax(dbCollection.getBook().getRating().getMax());
        ratingbar.setProgress(dbCollection.getBook().getRating().getAverage());
        numraters.setText("综合评分: " + "" + dbCollection.getBook().getRating().getAverage() + " (" + "" + dbCollection.getBook().getRating().getNumRaters() + "人评价)");

        if(dbCollection.getStatus().toString().equals("reading")){
            review_status.setText("我的阅读状态: 在读");
        }else if(dbCollection.getStatus().toString().equals("read")){
            review_status.setText("我的阅读状态: 已读");
        }else if(dbCollection.getStatus().toString().equals("wish")){
            review_status.setText("我的阅读状态: 想读");
        }

        review_time.setText("更新时间: " + dbCollection.getUpdated().toString());
//        review_ratingbar.setMax();

        StringBuilder tagBuilder = new StringBuilder();
        List<GlobalTag> tagsList = dbCollection.getBook().getTags();
        for (int i = 0; i < tagsList.size(); i++) {
            GlobalTag singTag = tagsList.get(i);
            String title = singTag.getTitle();
            Integer count = singTag.getCount();
            tagBuilder.append(title + " (" + count + ") ");
        }
        tags.setText(tagBuilder);

        summarys.setText(dbCollection.getBook().getSummary().toString());
        catalogs.setText(dbCollection.getBook().getCatalog().toString());
        autho_intros.setText(dbCollection.getBook().getAuthor_intro().toString());
        return view;
    }

    private class ImageViewAsyncTask extends AsyncTask<Void, Void, Bitmap> {

        private ImageView imageView;
        private Bitmap bitmap;

        public ImageViewAsyncTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            bitmap = GetBmApi.getBitMap(dbCollection.getBook().getImage());
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap aVoid) {
            super.onPostExecute(aVoid);
            if (aVoid != null) {
                imageView.setImageBitmap(aVoid);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return;
            }
        }
    }
}
