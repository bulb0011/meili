package www.weimeng.com.meili;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;

import www.weimeng.com.meili.fragment.Add_Fragment;
import www.weimeng.com.meili.fragment.Expert_Fragment;
import www.weimeng.com.meili.fragment.Home_Fragmet;
import www.weimeng.com.meili.fragment.Knack_Fragment;
import www.weimeng.com.meili.fragment.This_Me_Fragment;
import www.weimeng.com.meili.utils.MessageEvent;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;

    private RadioButton home_frg,baodian_frg,zhuanjia_frg,add_frg,this_frg;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();

        EventBus.getDefault().register(this);
    }

    void initView(){

       // frameLayout = (FrameLayout) findViewById(R.id.home_fl);

        radioGroup = (RadioGroup) findViewById(R.id.home_rg);

        relativeLayout = (RelativeLayout) findViewById(R.id.home_tag_ll);

        home_frg = (RadioButton) findViewById(R.id. home_home_fragment);

        zhuanjia_frg = (RadioButton) findViewById(R.id. home_expert_fragment);

        add_frg = (RadioButton) findViewById(R.id. home_add_fragment);

        baodian_frg = (RadioButton) findViewById(R.id. home_knack_fragment);

        this_frg = (RadioButton) findViewById(R.id. home_this_my_fragment);


        findViewById(R.id. home_head_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().post(new MessageEvent("点击"));
            }
        });

        replaceContent(new Home_Fragmet(),"HOME");

        //设置默认选中
        home_frg.setChecked(true);

        initEvent();

    }


    /**
     *事件的方法
     */
    void  initEvent (){

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (group.getCheckedRadioButtonId()) {


                    case R.id.home_home_fragment:

                        replaceContent(new Home_Fragmet(),"HOME");

                        relativeLayout.setVisibility(View.VISIBLE);

                        break;

                    case R.id.home_expert_fragment:

                        replaceContent(new Expert_Fragment(),"EXPERT");

                        relativeLayout.setVisibility(View.GONE);

                        break;

                    case R.id.home_add_fragment:

                        replaceContent(new Add_Fragment(),"ADD");

                        relativeLayout.setVisibility(View.GONE);

                        break;


                    case R.id.home_knack_fragment:

                        replaceContent(new Knack_Fragment(),"KNACK");

                        relativeLayout.setVisibility(View.GONE);

                        break;


                    case R.id.home_this_my_fragment:

                        replaceContent(new This_Me_Fragment(),"THIS");

                        relativeLayout.setVisibility(View.GONE);

                        break;


                }

            }
        });

    }


    /**
     * 绑定Fragment
     * @param fragment
     */
    public void replaceContent(android.support.v4.app.Fragment fragment,String tag){

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_fl, fragment, tag)
                .commit();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
