package www.weimeng.com.meili.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/6/1.
 */

public class Knack_Fragment extends BaseFragment {

    @Override
    public View initView() {
        TextView v=new TextView(getContext());

        v.setText("宝典");

        return v;
    }

    @Override
    public void initData() {

    }


}
