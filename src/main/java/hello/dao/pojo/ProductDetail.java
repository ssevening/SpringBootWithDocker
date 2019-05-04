package hello.dao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public int lotNum;
    // store name
    public String storeName;
    //piece
    public String packageType;
    public String imageUrl;
    // 逗号分隔的
    @Column(nullable = false, columnDefinition = "varchar(3072)")
    public String allImageUrls;
    // 平均评分4.8
    public String evaluationScore;
    // 权重
    public String volume;
    public String productId;
    // 折扣
    public String discount;
    // 有效时间 2014-04-30
    public String validTime;
    // 费率
    public String commissionRate;
    // public String 30daysCommission;
    // 原价
    public String originalPrice;
    // 产品标题
    @Column(nullable = true, columnDefinition = "varchar(1024)")
    public String productTitle;
    // 产品URL
    public String productUrl;
    // 活动价格
    public String salePrice;

    public String localPrice;
    // 佣金
    public String commission;
    public String storeUrl;

    @Column(nullable = false, columnDefinition = "varchar(3072)")
    public String keywords;
    @Column(nullable = false, columnDefinition = "varchar(3072)")
    public String description;

    // 点击URL
    public String clickUrl;
    public String getDisplayPrice() {
        if (localPrice != null) {
            return localPrice;
        } else {
            return salePrice;
        }
    }

}
