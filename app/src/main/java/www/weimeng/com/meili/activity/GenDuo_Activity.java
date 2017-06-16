package www.weimeng.com.meili.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import www.weimeng.com.meili.R;
import www.weimeng.com.meili.fragment.BaseFragment;
import www.weimeng.com.meili.fragment.Hot_Push_Fragment;

/**
 * Created by Administrator on 2017/6/6.
 */

public class GenDuo_Activity extends AppCompatActivity {

    private TabLayout tab_findFragment_title;
    private ViewPager vp_findFragment_pager;

    private FragmentPagerAdapter fAdapter;                               //定义adapter

    private List<BaseFragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;
    private Hot_Push_Fragment hotRecommendFragment;
    private Hot_Push_Fragment hotCollectionFragment;
    private Hot_Push_Fragment hotMonthFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.genduo_latyout);

        tab_findFragment_title = (TabLayout)findViewById(R.id.tab_FindFragment_title);
        vp_findFragment_pager = (ViewPager)findViewById(R.id.vp_FindFragment_pager);

        ImageView imageView=(ImageView)findViewById(R.id.genduo_image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GenDuo_Activity.this.finish();

            }
        });

        //初始化各fragment
        hotRecommendFragment = new Hot_Push_Fragment();
        hotCollectionFragment = new Hot_Push_Fragment();
        hotMonthFragment = new Hot_Push_Fragment();

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(hotRecommendFragment);
        list_fragment.add(hotCollectionFragment);
        list_fragment.add(hotMonthFragment);

        //list_fragment.add(hotToday);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("专家热推");
        list_title.add("最新提问");
        list_title.add("最新回复");

        //设置TabLayout的模式
        tab_findFragment_title.setTabMode(TabLayout.MODE_FIXED);

        //为TabLayout添加tab名称
        tab_findFragment_title.addTab(tab_findFragment_title.newTab().setText(list_title.get(0)));
        tab_findFragment_title.addTab(tab_findFragment_title.newTab().setText(list_title.get(1)));
        tab_findFragment_title.addTab(tab_findFragment_title.newTab().setText(list_title.get(2)));

        fAdapter = new Find_tab_Adapter(GenDuo_Activity.this.getSupportFragmentManager(),list_fragment,list_title);

        //viewpager加载adapter
        vp_findFragment_pager.setAdapter(fAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        tab_findFragment_title.setupWithViewPager(vp_findFragment_pager);
        //tab_FindFragment_title.set
    }


    class Find_tab_Adapter extends FragmentPagerAdapter {

        private List<BaseFragment> list_fragment;                         //fragment列表
        private List<String> list_Title;                              //tab名的列表



        public Find_tab_Adapter(FragmentManager fm,List<BaseFragment> list_fragment,List<String> list_Title) {
            super(fm);
            this.list_fragment = list_fragment;
            this.list_Title = list_Title;
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            return list_Title.size();
        }

        //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {

            return list_Title.get(position % list_Title.size());
        }


    }


}
