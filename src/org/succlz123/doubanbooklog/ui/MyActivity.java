package org.succlz123.doubanbooklog.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.*;
import android.widget.*;
import org.succlz123.doubanbooklog.R;
import org.succlz123.doubanbooklog.ui.left_fragment.*;

import java.util.ArrayList;

public class MyActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerListview;
    private Button toolbarbtn;
    private int position;

    NotesFragment notesFragment = new NotesFragment();
    CommentFragment commentFragment = new CommentFragment();
    TagFragment tagFragment = new TagFragment();
    AboutFragment aboutFragment = new AboutFragment();
    MyFragment myFragment = new MyFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

//        if(this.getApplicationContext()== DoubanApplication.getInstance()){
//            Toast.makeText(this,"euqal",Toast.LENGTH_SHORT).show();
//        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("豆瓣BookLog");
        mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setActionBar(mToolbar);
         toolbarbtn = (Button) findViewById(R.id.toolbar_btn);
        toolbarbtn.setOnClickListener(toolbarBtnListener);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.darwer_layout);
        mDrawerLayout.setDrawerListener(mDrawerLayoutDrawerListener);//匿名内部类调用
//        mDrawerLayout.setDrawerListener(new DoubanDrawerListener(toolbarbtn));//普通内部类调用

//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.darwer_frame, notesFragment, "1")
//                .hide(notesFragment).commitAllowingStateLoss();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.darwer_frame, commentFragment, "2")
//                .hide(commentFragment).commitAllowingStateLoss();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.darwer_frame, tagFragment, "3")
//                .hide(tagFragment).commitAllowingStateLoss();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.darwer_frame, aboutFragment, "4")
//                .hide(aboutFragment).commitAllowingStateLoss();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.darwer_frame, myFragment, "0")
//                .show(myFragment).commitAllowingStateLoss();

        mDrawerListview = (ListView) findViewById(R.id.list_view);

        //实例在外面
        final ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(myFragment);
        fragmentList.add(notesFragment);
        fragmentList.add(commentFragment);
        fragmentList.add(tagFragment);
        fragmentList.add(aboutFragment);

//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        for(Fragment fragment:fragments){
//            transaction.add(R.id.darwer_frame,fragment,fragment.getClass().getSimpleName());
//            transaction.hide(fragment);
//        }
//        transaction.show(myFragment);
//        transaction.commitAllowingStateLoss();

        mDrawerListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                MyActivity.this.position = position;

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                for (int i = 0; i < fragmentList.size(); i++) {

                    Fragment fragment = fragmentList.get(i);

                    if (i == position) {

                        if (getSupportFragmentManager().findFragmentByTag(fragment.getClass().getSimpleName()) == null) {
                            transaction.replace(R.id.darwer_frame, fragment, fragment.getClass().getSimpleName());
                        }

                        transaction.show(fragment);

                    } else {
                        if (getSupportFragmentManager().findFragmentByTag(fragment.getClass().getSimpleName()) != null) {
                            transaction.hide(fragment);
                        }
                    }
                }

                transaction.commitAllowingStateLoss();
//
//                switch (i) {
//                    case 0:
//                        getSupportFragmentManager().beginTransaction()
//                                .hide(notesFragment)
//                                .hide(commentFragment)
//                                .hide(tagFragment)
//                                .hide(aboutFragment)
//                                .show(myFragment).commitAllowingStateLoss();
//                        break;
//                    case 1:
//                        getSupportFragmentManager().beginTransaction()
//                                .hide(myFragment)
//                                .hide(commentFragment)
//                                .hide(tagFragment)
//                                .hide(aboutFragment)
//                                .show(notesFragment).commitAllowingStateLoss();
//                        break;
//                    case 2:
//                        getSupportFragmentManager().beginTransaction()
//                                .hide(notesFragment)
//                                .hide(myFragment)
//                                .hide(tagFragment)
//                                .hide(aboutFragment)
//                                .show(commentFragment).commitAllowingStateLoss();
//                        break;
//                    case 3:
//                        getSupportFragmentManager().beginTransaction()
//                                .hide(notesFragment)
//                                .hide(commentFragment)
//                                .hide(myFragment)
//                                .hide(aboutFragment)
//                                .show(tagFragment).commitAllowingStateLoss();
//                        break;
//                    case 4:
//                        getSupportFragmentManager().beginTransaction()
//                                .hide(notesFragment)
//                                .hide(commentFragment)
//                                .hide(tagFragment)
//                                .hide(myFragment)
//                                .show(aboutFragment).commitAllowingStateLoss();
//                        break;
//                }
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });

        mDrawerListview.setAdapter(new BaseAdapter() {
            final int TYPE_INFO = 0;
            final int TYPE_COMMON = 1;

            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public int getItemViewType(int i) {
                if (i == 0) {
                    return TYPE_INFO;
                }
                return TYPE_COMMON;
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                switch (getItemViewType(i)) {
                    case TYPE_INFO:
                        view = getLayoutInflater().inflate(R.layout.left_darwer_layout_info, viewGroup, false);
                        break;
                    case TYPE_COMMON:
                        view = getLayoutInflater().inflate(R.layout.left_darwer_layout_option, viewGroup, false);
                        ImageView img = (ImageView) view.findViewById(R.id.list_view_img);
                        //这个 img是在 上一个定义的 view 里找  不然就去 acitity里找了
                        TextView textView = (TextView) view.findViewById(R.id.list_view_text);
                        if (i == 1) {
                            img.setImageResource(R.drawable.note);
                            textView.setText("我的笔记");
                        } else if (i == 2) {
                            img.setImageResource(R.drawable.comment);
                            textView.setText("我的书评");
                        } else if (i == 3) {
                            img.setImageResource(R.drawable.tag);
                            textView.setText("我的标签");
                        } else if (i == 4) {
                            img.setImageResource(R.drawable.about);
                            textView.setText("关于");
                        }
                        break;
                }
                return view;
            }
        });

        //代码模拟点击效果
        mDrawerListview.performItemClick(null, 0, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.oo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return super.onMenuItemSelected(featureId, item);
    }

    private View.OnClickListener toolbarBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                toolbarbtn.setBackgroundResource(R.drawable.toolbar);
            } else {
                mDrawerLayout.openDrawer(Gravity.LEFT);
                toolbarbtn.setBackgroundResource(R.drawable.back);
            }
        }
    };

    //匿名内部类
    private DrawerLayout.DrawerListener mDrawerLayoutDrawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(View view, float v) {
        }

        @Override
        public void onDrawerOpened(View view) {
            toolbarbtn.setBackgroundResource(R.drawable.back);
        }

        @Override
        public void onDrawerClosed(View view) {
            toolbarbtn.setBackgroundResource(R.drawable.toolbar);
        }

        @Override
        public void onDrawerStateChanged(int i) {

        }
    };

    //普通内部类
//    private class DoubanDrawerListener implements DrawerLayout.DrawerListener {
//        private Button toolbarBtn;
//
//        public DoubanDrawerListener(Button toolbarBtn) {
//            this.toolbarBtn = toolbarBtn;
//        }
//
//        @Override
//        public void onDrawerSlide(View drawerView, float slideOffset) {
//
//        }
//
//        @Override
//        public void onDrawerOpened(View view) {
//            this.toolbarBtn.setBackgroundResource(R.drawable.back);
//        }
//
//        @Override
//        public void onDrawerClosed(View view) {
//            this.toolbarBtn.setBackgroundResource(R.drawable.toolbar);
//        }
//
//        @Override
//        public void onDrawerStateChanged(int newState) {
//
//        }
//    }

    @Override
    public void onBackPressed() {
        if (this.position != 0) {
            mDrawerListview.performItemClick(null, 0, 0);
            return;
        }

        super.onBackPressed();
    }
}
