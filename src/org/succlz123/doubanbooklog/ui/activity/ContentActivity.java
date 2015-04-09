package org.succlz123.doubanbooklog.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.bean.bookinfo.DbCollection;
import org.succlz123.doubanbooklog.support.slidingtab.SlidingTabLayout;
import org.succlz123.doubanbooklog.ui.contetnfragment.AnnotationFragment;
import org.succlz123.doubanbooklog.ui.contetnfragment.IntroductionFragment;
import org.succlz123.doubanbooklog.ui.contetnfragment.ReviewsFragment;

/**
 * Created by fashi on 2015/4/3.
 */
public class ContentActivity extends FragmentActivity {

    private Toolbar mToolbar;
    private Button toolbarbtn;
    private DbCollection dbCollection;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity);

        Intent intent = this.getIntent();
        dbCollection = (DbCollection) intent.getParcelableExtra("book_info");

        String title=dbCollection.getBook().getTitle();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(title);
        mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setActionBar(mToolbar);
//        toolbarbtn = (Button) findViewById(R.id.toolbar_btn);
//        toolbarbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//        toolbarbtn.setBackgroundResource(R.drawable.back);

        viewPager = (ViewPager) findViewById(R.id.content_viewpager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new ContentFragmentApadter(getSupportFragmentManager()));

        SlidingTabLayout tabLayout = (SlidingTabLayout) findViewById(R.id.content_tab);

        //标签分割颜色  标签下标颜色
        tabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            @Override
            public int getDividerColor(int position) {
                return 0;
            }

            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.white);
            }
        });

        tabLayout.setBackgroundResource(R.color.basecolor);
        tabLayout.setViewPager(viewPager);

    }

    private class ContentFragmentApadter extends FragmentPagerAdapter {

        public ContentFragmentApadter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new IntroductionFragment();
                case 1:
                    return new AnnotationFragment();
                case 2:
                    return new ReviewsFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "介绍";
                case 1:
                    return "笔记";
                case 2:
                    return "书评";
            }
            return super.getPageTitle(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
