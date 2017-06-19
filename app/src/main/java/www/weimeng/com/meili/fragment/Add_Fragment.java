package www.weimeng.com.meili.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;

import www.weimeng.com.meili.R;
import www.weimeng.com.meili.utils.GlideImageLoader;

/**
 * Created by Administrator on 2017/6/1.
 */

public class Add_Fragment extends BaseFragment{

    private ArrayList<ImageItem> imageItems;
    private TextView tv;
    private View view;

    @Override
    public View initView() {

        view = View.inflate(getContext(), R.layout.add_fragment_layout,null);

        return view;
    }

    @Override
    public void initData() {

        view.findViewById(R.id.huoqutupoain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker imagePicker = ImagePicker.getInstance();
                imagePicker.setImageLoader(new GlideImageLoader());
                imagePicker.setMultiMode(true);   //多选
                imagePicker.setShowCamera(true);  //显示拍照按钮
                imagePicker.setSelectLimit(9);    //最多选择9张
                imagePicker.setCrop(false);       //不进行裁剪
                Intent intent = new Intent(getContext(), ImageGridActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        tv =(TextView)view.findViewById(R.id.shangchaun);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (imageItems != null && imageItems.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < imageItems.size(); i++) {
                        if (i == imageItems.size() - 1) sb.append("图片").append(i + 1).append(" ： ").append(imageItems.get(i).path);
                        else sb.append("图片").append(i + 1).append(" ： ").append(imageItems.get(i).path).append("\n");
                    }
                } else {
                    tv.setText("--");
                }
            } else {
                Toast.makeText(getContext(), "没有选择图片", Toast.LENGTH_SHORT).show();
                tv.setText("--");
            }
        }

    }
}
