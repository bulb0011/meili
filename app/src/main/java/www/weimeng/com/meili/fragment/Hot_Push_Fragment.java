package www.weimeng.com.meili.fragment;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import www.weimeng.com.meili.R;
import www.weimeng.com.meili.activity.Item_Uaer_Activity;
import www.weimeng.com.meili.bean.Text_Bean;
import www.weimeng.com.meili.view.NineGridTestLayout;
import www.weimeng.com.meili.view.RoundImageView;
import www.weimeng.com.meili.view.XListView;

import static android.view.View.inflate;

/**
 * Created by Administrator on 2017/6/7.
 */

public class Hot_Push_Fragment extends BaseFragment implements XListView.IXListViewListener{

    private XListView xlist;
    private List<Text_Bean> text_been;

    private int i;
    private boolean isFirst;

    private final int LoadMore=106;

    private final int Refresh=105;


    Handler handler=new Handler() {

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {

                case LoadMore:
                    /*initData();
                    //一定的条目
                    xlist.setSelection(adapter.getCount()-1);
                    //跳转到最后的方法
//				list_view.setSelectionFromTop(pas, top);
                    adapter.notifyDataSetChanged();
                    onLoad();*/

                    for (int i = 0; i < 10; i++) {

                        text_been.add(new Text_Bean("小新"+i,"http://a.hiphotos.baidu.com/image/pic/item/a6efce1b9d16fdfa0fbc1ebfb68f8c5495ee7b8b.jpg","2017-6-"+i, "我是一只小花貓"));

                    }

                    adapter.notifyDataSetChanged();

                    onLoad();
                    break;

                case Refresh:
                    /*text_been.clear();
                    isFirst =true;
                    i=1;
                    initData();
                    if (adapter==null) {
                        adapter=new MyAdapter();
                    }
//				weight_list_view.setAdapter(adapter);
                    adapter.notifyDataSetInvalidated();
                    onLoad();*/

                    for (int i = 0; i < 10; i++) {

                        text_been.add(new Text_Bean("小新"+i,"http://b.hiphotos.baidu.com/image/pic/item/a71ea8d3fd1f4134e61e0f90211f95cad1c85e36.jpg","时间", "我是一只小花貓"));

                    }

                    adapter = new MyAdapter();

                    xlist.setAdapter(adapter);

                    onLoad();
                    break;
            }
        };

    };
    private MyAdapter adapter;

    private boolean isadd;

    private LinearLayout ll;

    @Override
    public View initView() {

        View view= inflate(getContext(), R.layout.genduo_fragment_layout,null);

        xlist =(XListView)view.findViewById(R .id.genduo_fragment_list_view);

        /**
         * 上拉刷新，下拉加载都能用
         */
        xlist.setPullLoadEnable(true);

        xlist.setXListViewListener(this);

        return view;
    }

    @Override
    public void initData() {

        text_been = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            text_been.add(new Text_Bean("小新"+i,"http://b.hiphotos.baidu.com/image/pic/item/a71ea8d3fd1f4134e61e0f90211f95cad1c85e36.jpg","2017-6-"+i, "我是一只小花貓"));

        }

        adapter = new MyAdapter();

        xlist.setAdapter(adapter);

    }

    /**
     * 刷新
     */
    @Override
    public void onRefresh() {

        /*for (int i = 0; i < 10; i++) {

            text_been.add(new Text_Bean("小新"+i,"354564","时间", "内容"));

        }

        adapter = new MyAdapter();

        xlist.setAdapter(adapter);

        onLoad();*/

        //再次添加数据
//		initData();
        //取消还没有结束的消息
        handler.removeMessages(Refresh);
        handler.sendEmptyMessageDelayed(Refresh, 2000);

    }

    /**
     * 加载
     */
    @Override
    public void onLoadMore() {

        /*for (int i = 0; i < 10; i++) {

            text_been.add(new Text_Bean("小新"+i,"354564","2017-6-"+i, "我是一只小花貓"));

        }

        adapter.notifyDataSetChanged();

        onLoad();*/

        isadd =true;
        //取消还没有结束的消息
        handler.removeMessages(LoadMore);
        handler.sendEmptyMessageDelayed(LoadMore, 2000);

    }

    /**
     * listview 的notifyDataSetChanged
     */
    private void onLoad() {
        /**
         * 停止刷新重置标题
         */
        xlist.stopRefresh();
        /**
         * 停止刷新重置页脚视图
         */
        xlist.stopLoadMore();
        xlist.setRefreshTime("刚刚");
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

            View view=View.inflate(getContext(), R.layout.gengduo_item_layout,null);

            RoundImageView name_img=(RoundImageView)view.findViewById(R.id.genduo_item_head_img);

            TextView name_tv=(TextView)view.findViewById(R.id.genduo_iten_name);

            TextView shijian_tv=(TextView)view.findViewById(R.id.genduo_item_shijian);

            TextView context_tv=(TextView)view.findViewById(R.id.gengduo_iten_context);

            NineGridTestLayout imgfenge=(NineGridTestLayout)view.findViewById(R.id.genduo_item_imgfenge);

            Text_Bean info=text_been.get(position);

            Glide.with(getContext()).load(info.getHead_url()).placeholder(R.mipmap.j).error(R.mipmap.j).into(name_img);

            name_tv.setText(info.getName());

            shijian_tv.setText(info.getShjian());

            context_tv.setText(info.getNairong());

            List<String> list = new ArrayList<>();

            list.add("http://pic31.nipic.com/20130624/8821914_104949466000_2.jpg");
            list.add("http://pic31.nipic.com/20130624/8821914_104949466000_2.jpg");
            list.add("http://pic31.nipic.com/20130624/8821914_104949466000_2.jpg");

            imgfenge.setUrlList(list);

            //ll =(LinearLayout)

            /**
             * 点加到每一个的item
             *
             */
            view.findViewById(R .id.genduo_dainji_ll).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(getContext(), Item_Uaer_Activity.class);

                    //Intent intent=new Intent(getContext(), Super_RecyclerView_Activity.class);

                    startActivity(intent);

                }
            });

            return view;

        }

    }

    class ViewHolder{

        public ViewHolder(View view) {

        }

    }




}
