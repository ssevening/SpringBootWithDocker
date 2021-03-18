package hello.pojo;

import java.util.HashMap;
import java.util.Map;

public class PriceHistory extends TaobaoObject {

    private String date;
    // 价格
    private String price;
    // 币种
    private String currency;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
