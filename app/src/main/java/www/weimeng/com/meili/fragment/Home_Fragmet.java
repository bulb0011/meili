package www.weimeng.com.meili.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.weimeng.com.meili.R;
import www.weimeng.com.meili.activity.GenDuo_Activity;
import www.weimeng.com.meili.view.FlowLayout;
import www.weimeng.com.meili.view.NineGridTestLayout;
import www.weimeng.com.meili.view.RollViewPager;


/**
 * Created by Administrator on 2017/6/1.
 */

public class Home_Fragmet extends BaseFragment implements View.OnClickListener {

    private LinearLayout top_news_viewpager,dots_ll;

    private TextView top_news_title;
    private RollViewPager rollViewpager;
    private ArrayList<String> imageUrlList;
    private NineGridTestLayout ngl1,ngl2,ngl3;
    private TextView genduo;


    @Override
    public View initView() {

        View view=View.inflate(getContext(), R.layout.home_layout,null);

        ngl1 = (NineGridTestLayout)view.findViewById(R.id.home_one);
        ngl2 = (NineGridTestLayout)view.findViewById(R.id.home_one2);
        ngl3 = (NineGridTestLayout)view.findViewById(R.id.home_one3);

        FlowLayout  linearLayout =(FlowLayout ) view.findViewById(R.id.home_ll_1);

        TextView textView=new TextView(getContext());

        textView.setText("测试");

        textView.setTextColor(Color.WHITE);

        //textView.setScrollBarStyle(R.style.text_flag_01);

        textView.setBackgroundResource(R.drawable.flag_01);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,-2);

        params.gravity=Gravity.LEFT;

        params.bottomMargin=5;

        params.leftMargin =20; //设置点的左边距

        params.topMargin=5;


        linearLayout.addView(textView,params);


        genduo = (TextView)view.findViewById(R.id.home_guoqugenduo_tv);

        genduo.setOnClickListener(this);



        /*ImageView img=(ImageView)view.findViewById(R.id.home_zjhu);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.printsign);

        Bitmap smallBitmap = Bitmap.createBitmap(bitmap,20,20, bitmap.getWidth() - 20, bitmap.getHeight() - 20);

        img.setImageBitmap(smallBitmap);*/

        //初始化轮播图
        initLunBo(view);



        return view;
    }


    @Override
    public void initData() {

    }

    /**
     * 轮播图的初始化
     * @param view
     */
    private void initLunBo(View view) {

        top_news_viewpager =(LinearLayout) view.findViewById(R.id.top_news_viewpager);

        top_news_title = (TextView) view.findViewById(R.id.top_news_title);

        dots_ll=(LinearLayout) view.findViewById(R.id.dots_ll);

        rollViewpager = new RollViewPager(getContext());

        imageUrlList = new ArrayList<String>();

        imageUrlList.add("http://g.hiphotos.baidu.com/image/pic/item/4b90f603738da977c76ab6fab451f8198718e39e.jpg");
        imageUrlList.add("http://e.hiphotos.baidu.com/image/pic/item/902397dda144ad343de8b756d4a20cf430ad858f.jpg");
        imageUrlList.add("http://a.hiphotos.baidu.com/image/pic/item/a6efce1b9d16fdfa0fbc1ebfb68f8c5495ee7b8b.jpg");

        ngl1.setUrlList(imageUrlList);//////////////////////////////////////////////////////////////
        ngl2.setUrlList(imageUrlList);//////////////////////////////////////////////////////////////
        ngl3.setUrlList(imageUrlList);//////////////////////////////////////////////////////////////

        List<String> titleList=new ArrayList<String>();
        titleList.add("图片1");
        titleList.add("图片2");

        //第一个轮播图
        rollViewpager.setTitles(titleList, top_news_title);
        rollViewpager.setImageUrlList(imageUrlList);

        top_news_viewpager.removeAllViews();

        top_news_viewpager.addView(rollViewpager);

        addPoint();

        rollViewpager.startRoll();
    }



    /**
     * 动态添加广告图指示点
     */
    private void addPoint() {

        // 清空之前的所有的点
        dots_ll.removeAllViews();

        List<ImageView> pointList = new ArrayList<ImageView>();

        for (int i=0;i<imageUrlList.size();i++) {
            // 添加点
            ImageView point = new ImageView(getContext());
            if(i==0){
                point.setBackgroundResource(R.mipmap.dot_focus);
            }else{
                point.setBackgroundResource(R.mipmap.dot_normal);
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);

            params.gravity= Gravity.CENTER;

            params.leftMargin = 15; //设置点的左边距

            dots_ll.addView(point,params); //将指示点，添加至布局

            pointList.add(point); //将指示点添加指集合

        }

        rollViewpager.setPointList(pointList);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            /**
             * 更多
             */
            case R.id.home_guoqugenduo_tv:

               // Toast.makeText(getContext(),"dianji",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getContext(), GenDuo_Activity.class);

               /* Intent intent=new Intent(Knowledge_Activity.this, Konwledge_WedActivity.class);

                Knowledge_Activity.this.startActivity(intent);*/

                getContext().startActivity(intent);

                break;


        }

    }
}
