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
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import www.weimeng.com.meili.R;
import www.weimeng.com.meili.bean.Exp_Data;
import www.weimeng.com.meili.bean.Exp_list;
import www.weimeng.com.meili.bean.Exp_root;
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
    private List<Exp_Data> dada_list;
    private MyAdapter myAdapter;

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

        OkHttpClient client = new OkHttpClient();

        OkHttpUtils
                //.get()
                .post()
                .url("https://wmapi.bilekang.com/index.php?m=home&v=TagList&a=showTagList")
                //.addParams("token", stt)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onResponse(String response, int id) {

                        analysisJson(response);

                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }


                });



        list = new ArrayList<>();
        for (int i = 0; i <9; i++) {

            list.add("das"+i);

        }

        list_base = new ArrayList<>();

        for (int i = 0; i < 8; i++) {

            list_base.add(new Expert_Bean("http://g.hiphotos.baidu.com/image/pic/item/4b90f603738da977c76ab6fab451f8198718e39e.jpg","名字"+i, list));

        }

        myAdapter = new MyAdapter();


    }

    void  analysisJson(String json){

        Gson gson=new Gson();

        Log.i("dengpao", json);

        Exp_root exp_root=gson.fromJson(json, Exp_root.class);

        dada_list = exp_root.getData();

        for (int i = 0; i < dada_list.size(); i++) {

            Log.i("dengpoa", dada_list.get(i).getTag_name());
            
        }

        lv.setAdapter(myAdapter);


    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return dada_list.size();
        }

        @Override
        public Object getItem(int position) {
            return dada_list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holer=null;
            View view;

            if (convertView == null){

                view=View.inflate(ctx,R.layout.expert_item_yout,null);
                holer=new ViewHolder(view);
                view.setTag(holer);
            }else {
                view=convertView;
                holer=(ViewHolder)convertView.getTag();
            }

            if (dada_list.size()>position){

                Exp_Data info =dada_list.get(position);

                holer.binData(info,position);
            }

            return view;

            //////
        }



    }

    class ViewHolder{

        public   ImageView imageView;
        public  TextView textView;
        public  TagFlowLayout tagFlowLayout;
        private List<Exp_list> exp_lists;

        ViewHolder(View view){

            imageView = (ImageView)view.findViewById(R.id.image_heaaaaaaaa);

            textView = (TextView)view.findViewById(R.id.dfdfinpehnxlmhnj);

            tagFlowLayout = (TagFlowLayout)view.findViewById(R.id.expert_fl);

        }

        void binData(Exp_Data obj,int position){

            textView.setText(obj.getTag_name());

            Glide.with(ctx).load(list_base.get(position).getImg_head()).placeholder(R.mipmap.j).error(R.mipmap.j).into(imageView);

            exp_lists = obj.getList();

            tagFlowLayout.setAdapter(new TagAdapter<Exp_list>(exp_lists) {

                @Override
                public View getView(FlowLayout_ parent, int position, Exp_list o) {

                    //mInflater.inflate(ctx,tagFlowLayout,null);

                    View view=View.inflate(ctx,R.layout.tv,null);

                    //View view=mInflater.inflate(R.layout.expert_item_yout, tagFlowLayout, false);

                    TextView textView=(TextView)view.findViewById(R.id.dzfhjdsdfg);

                    textView.setText(o.getTag_name());

                    return view;
                }

                @Override
                public boolean setSelected(int position, Exp_list o) {

                    return o.equals("Android");
                }

            });


            tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout_ parent) {

                    Log.i("dengpao", exp_lists.get(position).getTag_name()+"----"+view.getId());

                    return true;
                }
            });

            tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                @Override
                public void onSelected(Set<Integer> selectPosSet) {

                    Toast.makeText(ctx, ""+selectPosSet.toString(), Toast.LENGTH_SHORT).show();

                }
            });

        }


    }




    ///////
}
