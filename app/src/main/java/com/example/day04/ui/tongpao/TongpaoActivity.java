package com.example.day04.ui.tongpao;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.day04.R;
import com.example.day04.ui.tongpao.fragment.ArticleFragment;
import com.example.day04.ui.tongpao.fragment.PhotographyFragment;
import com.example.day04.ui.tongpao.fragment.RecommendFragment;
import com.example.day04.ui.tongpao.fragment.SquareFragment;
import com.example.day04.ui.tongpao.fragment.VideoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TongpaoActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tongpao);
        initView();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        list = new ArrayList<>();
        list.add(new RecommendFragment());//推荐
        list.add(new SquareFragment());//广场
        list.add(new VideoFragment());//视频
        list.add(new PhotographyFragment());//摄影
        list.add(new ArticleFragment());//文章

        //tablayout横向滚动
        tabLayout.setTabMode( TabLayout.MODE_SCROLLABLE );
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    class MyAdapter extends FragmentPagerAdapter{
        String [] tabTitle = {"推荐", "广场", "视频", "摄影", "文章"};
        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle[position];
        }
    }

}