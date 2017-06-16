package www.weimeng.com.meili.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class Exp_root {

    private String code;

    private String msg;

    private int responsetime;

    private List<Exp_Data> data ;

    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setResponsetime(int responsetime){
        this.responsetime = responsetime;
    }
    public int getResponsetime(){
        return this.responsetime;
    }
    public void setData(List<Exp_Data> data){
        this.data = data;
    }
    public List<Exp_Data> getData(){
        return this.data;
    }

}
