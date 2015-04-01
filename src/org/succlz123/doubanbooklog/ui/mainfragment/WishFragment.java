package org.succlz123.doubanbooklog.ui.mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.ui.login.LoginActivity;

/**
 * Created by fashi on 2015/3/27.
 */
public class WishFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.wish_fragment, container, false);

        Button login_btn = (Button) view.findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener()

                                     {
                                         @Override
                                         public void onClick(View view) {

                                             Intent intent = new Intent(getActivity(), LoginActivity.class);
                                             startActivityForResult(intent, 0);
                                         }
                                     }

        );

        

        return view;
    }

}
