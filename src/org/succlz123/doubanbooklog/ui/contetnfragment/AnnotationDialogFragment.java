package org.succlz123.doubanbooklog.ui.contetnfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.bean.annotationinfo.AnnotationResult;
import org.succlz123.doubanbooklog.dao.GetBmApi;

/**
 * Created by fashi on 2015/4/5.
 */
public class AnnotationDialogFragment extends DialogFragment {

    public static AnnotationDialogFragment newInstance(AnnotationResult annotationResult) {
        AnnotationDialogFragment annotationDialogFragment = new AnnotationDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("annotationResult", annotationResult);
        annotationDialogFragment.setArguments(bundle);
        return annotationDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builer = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.annotation_dialogfragment, null);
        builer.setView(view).setNegativeButton("关闭", null);

        AnnotationResult annotationResult = (AnnotationResult) getArguments().getSerializable("annotationResult");

        ImageView avater_img = (ImageView) view.findViewById(R.id.annotation_dialog_img);
        TextView time = (TextView) view.findViewById(R.id.annotation_dialog_time);
        TextView name = (TextView) view.findViewById(R.id.annotation_dialog_name);
        ImageView share_img = (ImageView) view.findViewById(R.id.annotation_dialog_share);
        TextView chapter = (TextView) view.findViewById(R.id.annotation_dialog_chapter);
        TextView content = (TextView) view.findViewById(R.id.annotation_dialog_content);

        new ImageViewAsyncTask(avater_img, annotationResult).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        time.setText(annotationResult.getTime().toString());
        name.setText(annotationResult.getAuthor_user().getName().toString());
        share_img.setImageResource(R.drawable.share);
        chapter.setText("章节: "+annotationResult.getChapter().toString());
        content.setText(annotationResult.getContent().toString());
        return builer.create();
    }

    private class ImageViewAsyncTask extends AsyncTask<Void, Void, Bitmap> {

        private ImageView avater;
        private AnnotationResult annotationResult;

        public ImageViewAsyncTask(ImageView avater, AnnotationResult annotationResult) {
            this.avater = avater;
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
            avater.setImageBitmap(aVoid);
        }
    }
}


