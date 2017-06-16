package www.weimeng.com.meili.bean;

/**
 * Created by Administrator on 2017/6/7.
 */

public class Text_Bean {

    String name;
    String head_url;
    String shjian;
    String nairong;

    public Text_Bean(String name,String head_url,String shjian,String nairong) {
        this.name=name;
        this.head_url=head_url;
        this.shjian=shjian;
        this.nairong=nairong;
    }

    public String getShjian() {
        return shjian;
    }

    public void setShjian(String shjian) {
        this.shjian = shjian;
    }

    public String getHead_url() {

        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNairong() {

        return nairong;
    }

    public void setNairong(String nairong) {
        this.nairong = nairong;
    }
}
