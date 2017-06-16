package www.weimeng.com.meili.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import www.weimeng.com.meili.R;
import www.weimeng.com.meili.bean.Text_Bean;
import www.weimeng.com.meili.view.NineGridTestLayout;
import www.weimeng.com.meili.view.RoundImageView;
import www.weimeng.com.meili.view.XListView;

/**
 * Created by Administrator on 2017/6/9.
 */

public class Item_Uaer_Activity extends AppCompatActivity implements XListView.IXListViewListener {


    private XListView xlist;
    private ArrayList<Text_Bean > text_been;
    private MyAdapter adapter;


    private final int ADD_IMAG=105;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case ADD_IMAG:

                    for (int i = 0; i < 7; i++) {

                        ImageView point = new ImageView(Item_Uaer_Activity.this);

                       // RoundImageView ratioImageView=new RoundImageView(Item_Uaer_Activity.this);

                        // point.setBackgroundResource(R.mipmap.dot_focus);

                        // point.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));  //设置图片宽高.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));  //设置图片宽高

                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,100);

                        params.gravity= Gravity.CENTER;

                        params.leftMargin = 5; //设置点的左边距

                        params.bottomMargin=5;

                        params.topMargin=5;

                        Glide.with(Item_Uaer_Activity.this).load("http://pic31.nipic.com/20130624/8821914_104949466000_2.jpg").placeholder(R.mipmap.j).error(R.mipmap.j).into(point);

                        ll.addView(point,params); //将指示点，添加至布局

                    }

                    xlist.addHeaderView(head_view);


                    break;
            }
        }
    };
    private LinearLayout ll;
    private View head_view;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item_user_layout);

        xlist =(XListView) findViewById(R.id.item_user_xlist);

        initeData();

        /**
         * 上拉刷新，下拉加载都能用
         */
        xlist.setPullLoadEnable(true);

        xlist.setXListViewListener(this);

        text_been = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            text_been.add(new Text_Bean("小新"+i,"http://c.hiphotos.baidu.com/image/pic/item/7dd98d1001e939011b9c86d07fec54e737d19645.jpg","2017-6-"+i, "我是一只小花貓"));

        }

        adapter = new MyAdapter();

        xlist.setAdapter(adapter);

    }

   void initeData(){

       head_view = View.inflate(this, R.layout.head_layout,null);

       ll = (LinearLayout) head_view.findViewById(R.id.hean_image_ll);


       /*for (int i = 0; i < 7; i++) {

           //ImageView point = new ImageView(this);

           RoundImageView ratioImageView=new RoundImageView(Item_Uaer_Activity.this);

          // point.setBackgroundResource(R.mipmap.dot_focus);

          // point.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));  //设置图片宽高.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));  //设置图片宽高

           LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,100);

           params.gravity= Gravity.CENTER;

           Glide.with(Item_Uaer_Activity.this).load("http://pic31.nipic.com/20130624/8821914_104949466000_2.jpg").placeholder(R.mipmap.j).error(R.mipmap.j).into(ratioImageView);

           params.leftMargin = 5; //设置点的左边距

           params.bottomMargin=5;

           params.topMargin=5;


           ll.addView(ratioImageView,params); //将指示点，添加至布局

       }
       
       xlist.addHeaderView(head_view);*/

       handler.sendEmptyMessage(ADD_IMAG);

    }

    @Override
    public void onRefresh() {


    }

    @Override
    public void onLoadMore() {


    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return text_been.size();
        }

        @Override
        public Object getItem(int position) {
            return text_been.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view=View.inflate(Item_Uaer_Activity.this , R.layout.gengduo_item_layout,null);

            RoundImageView name_img=(RoundImageView)view.findViewById(R.id.genduo_item_head_img);

            TextView name_tv=(TextView)view.findViewById(R.id.genduo_iten_name);

            TextView shijian_tv=(TextView)view.findViewById(R.id.genduo_item_shijian);

            TextView context_tv=(TextView)view.findViewById(R.id.gengduo_iten_context);

            NineGridTestLayout imgfenge=(NineGridTestLayout)view.findViewById(R.id.genduo_item_imgfenge);

            Text_Bean info= info=text_been.get(position);

            Glide.with(Item_Uaer_Activity.this).load(info.getHead_url()).placeholder(R.mipmap.j).error(R.mipmap.j).into(name_img);

            name_tv.setText(info.getName());

            shijian_tv.setText(info.getShjian());

            context_tv.setText(info.getNairong());

            List<String> list = new ArrayList<>();

            list.add("http://pic4.nipic.com/20091203/1295091_123813163959_2.jpg");
            list.add("http://pic4.nipic.com/20091203/1295091_123813163959_2.jpg");
            list.add("http://pic4.nipic.com/20091203/1295091_123813163959_2.jpg");

            imgfenge.setUrlList(list);

            return view;
        }
    }



}
