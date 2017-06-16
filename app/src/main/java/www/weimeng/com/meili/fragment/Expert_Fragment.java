package www.weimeng.com.meili.fragment;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import www.weimeng.com.meili.R;
import www.weimeng.com.meili.bean.Expert_Bean;
import www.weimeng.com.meili.view.FlowLayout_;
import www.weimeng.com.meili.view.TagAdapter;
import www.weimeng.com.meili.view.TagFlowLayout;

import static android.view.View.inflate;

/**
 * Created by Administrator on 2017/6/1.
 */

public class Expert_Fragment extends BaseFragment{

    private Context ctx;
    private List<Expert_Bean> list_base;
    private List<String> list;
    private ListView lv;

    @Override
    public View initView() {

        this.ctx=getContext();

        View view= inflate(ctx, R.layout.expert_layout,null);

      //  tagFlowLayout = (TagFlowLayout)view.findViewById(R.id.expert_fl);

        lv = (ListView)view.findViewById(R.id.expert_listv);

        return view;
    }

    @Override
    public void initData() {



        list = new ArrayList<>();
        for (int i = 0; i <9; i++) {

            list.add("das"+i);

        }

        list_base = new ArrayList<>();

        for (int i = 0; i < 8; i++) {

            list_base.add(new Expert_Bean("http://g.hiphotos.baidu.com/image/pic/item/4b90f603738da977c76ab6fab451f8198718e39e.jpg","名字"+i, list));

        }

        MyAdapter myAdapter=new MyAdapter();

        lv.setAdapter(myAdapter);


    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list_base.size();
        }

        @Override
        public Object getItem(int position) {
            return list_base.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            View view=View.inflate(ctx,R.layout.expert_item_yout,null);

            ImageView imageView=(ImageView)view.findViewById(R.id.image_heaaaaaaaa);

            TextView textView=(TextView)view.findViewById(R.id.dfdfinpehnxlmhnj);

            TagFlowLayout tagFlowLayout = (TagFlowLayout)view.findViewById(R.id.expert_fl);


            textView.setText(list_base.get(position).getTv_head());


           Glide.with(ctx).load(list_base.get(position).getImg_head()).placeholder(R.mipmap.j).error(R.mipmap.j).into(imageView);

            List<String> list_info=list_base.get(position).getTv_lt();

            tagFlowLayout.setAdapter(new TagAdapter<String>(list_info) {

                @Override
                public View getView(FlowLayout_ parent, int position, String o) {

                    //mInflater.inflate(ctx,tagFlowLayout,null);

                    View view=View.inflate(ctx,R.layout.tv,null);

                    //View view=mInflater.inflate(R.layout.expert_item_yout, tagFlowLayout, false);

                    TextView textView=(TextView)view.findViewById(R.id.dzfhjdsdfg);

                    textView.setText(o);

                    return view;
                }

                @Override
                public boolean setSelected(int position, String o) {

                    return o.equals("Android");
                }

            });


            tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout_ parent) {

                    Log.i("dengpao", Expert_Fragment.this.list.get(position)+"----"+view.getId());

                    return true;
                }
            });

            tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                @Override
                public void onSelected(Set<Integer> selectPosSet) {

                    Toast.makeText(ctx, ""+selectPosSet.toString(), Toast.LENGTH_SHORT).show();

                }
            });


            return view;

            //////
        }


    }




    ///////
}
