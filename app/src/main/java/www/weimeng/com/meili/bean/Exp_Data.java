package www.weimeng.com.meili.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class Exp_Data {


    private String tid;

    private String tag_name;

    private String parent_id;

    private List<Exp_list> list ;

    public void setTid(String tid){
        this.tid = tid;
    }
    public String getTid(){
        return this.tid;
    }
    public void setTag_name(String tag_name){
        this.tag_name = tag_name;
    }
    public String getTag_name(){
        return this.tag_name;
    }
    public void setParent_id(String parent_id){
        this.parent_id = parent_id;
    }
    public String getParent_id(){
        return this.parent_id;
    }
    public void setList(List<Exp_list> list){
        this.list = list;
    }
    public List<Exp_list> getList(){
        return this.list;
    }
}
