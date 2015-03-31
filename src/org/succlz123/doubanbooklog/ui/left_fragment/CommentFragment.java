package org.succlz123.doubanbooklog.ui.left_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.succlz123.doubanbooklog.R;

/**
 * Created by fashi on 2015/3/26.
 */
public class CommentFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comment_fragment, container, false);
        return view;
    }
}
