package www.weimeng.com.meili.bean;

/**
 * Created by Administrator on 2017/6/16.
 */

public class Exp_list {

    private String tid;

    private String tag_name;

    private String parent_id;

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
}
