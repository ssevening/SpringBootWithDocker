package hello.pojo;

public class PriceHistory extends TaobaoObject {
    // 日期
    private String d;
    // 价格
    private String p;

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }
}
