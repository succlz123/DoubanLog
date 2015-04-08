package org.succlz123.doubanbooklog.ui.leftfragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import org.succlz123.doubanbooklog.R;

/**
 * Created by fashi on 2015/3/26.
 */
public class AboutFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_fragment, container, false);

        Button weibo=(Button)view.findViewById(R.id.weibo);
        Button zhifubao=(Button)view.findViewById(R.id.zhifubao);

        weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://weibo.com/zzzllzzz/";
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://baidu.com/";
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });





        return view;
    }


}