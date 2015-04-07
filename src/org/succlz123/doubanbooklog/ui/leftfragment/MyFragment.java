package org.succlz123.doubanbooklog.ui.leftfragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.support.slidingtab.SlidingTabLayout;
import org.succlz123.doubanbooklog.ui.mainfragment.ReadFragment;
import org.succlz123.doubanbooklog.ui.mainfragment.ReadingFragment;
import org.succlz123.doubanbooklog.ui.mainfragment.WishFragment;

/**
 * Created by fashi on 2015/3/27.
 */
public class MyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.my_fragment, container, false);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        viewPager.setAdapter(new MyFragmentAdapte(getChildFragmentManager(), getActivity()));
        viewPager.setOffscreenPageLimit(3);//默认viewPager是2个缓存 为了不让每次切换都要刷新 http 所以设置3个
        SlidingTabLayout tabLayout = (SlidingTabLayout) view.findViewById(R.id.tab);

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
        return view;
    }

    public class MyFragmentAdapte extends FragmentPagerAdapter {

        private Context context;

        public MyFragmentAdapte(android.support.v4.app.FragmentManager fm, Context context) {
            super(fm);
            this.context = context;

        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new ReadingFragment();
                case 1:
                    return new ReadFragment();
                case 2:
                    return new WishFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "在读";
                case 1:
                    return "已读";
                case 2:
                    return "想读";
            }
            return super.getPageTitle(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


}
