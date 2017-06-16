package www.weimeng.com.meili.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class Expert_Bean {

    public  String img_head;
    public  String tv_head;
    public List<String> tv_lt;

    public  Expert_Bean(String img_head,String tv_head,List<String> tv_lt){

        this.img_head=img_head;
        this.tv_head=tv_head;
        this.tv_lt=tv_lt;

    }



    public List<String> getTv_lt() {
        return tv_lt;
    }

    public void setTv_lt(List<String> tv_lt) {
        this.tv_lt = tv_lt;
    }

    public String getTv_head() {
        return tv_head;
    }

    public void setTv_head(String tv_head) {
        this.tv_head = tv_head;
    }

    public String getImg_head() {
        return img_head;
    }

    public void setImg_head(String img_head) {
        this.img_head = img_head;
    }
}
