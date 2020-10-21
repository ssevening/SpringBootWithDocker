package hello.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrafficProductResultDto extends TaobaoObject {

    private static final long serialVersionUID = 1374459664383733468L;

    /**
     * 当前返回页数
     */
    @JsonProperty("current_page_no")
    private Long currentPageNo;
    /**
     * 当前返回数量
     */
    @JsonProperty("current_record_count")
    private Long currentRecordCount;
    /**
     * 返回商品数量
     */
    @JsonProperty("products")
    private ProductInfo products;
    /**
     * 总计页数
     */
    @JsonProperty("total_page_no")
    private Long totalPageNo;
    /**
     * 总计数量
     */
    @JsonProperty("total_record_count")
    private Long totalRecordCount;


    public Long getCurrentPageNo() {
        return this.currentPageNo;
    }

    public void setCurrentPageNo(Long currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public Long getCurrentRecordCount() {
        return this.currentRecordCount;
    }

    public void setCurrentRecordCount(Long currentRecordCount) {
        this.currentRecordCount = currentRecordCount;
    }


    public Long getTotalPageNo() {
        return this.totalPageNo;
    }

    public void setTotalPageNo(Long totalPageNo) {
        this.totalPageNo = totalPageNo;
    }

    public Long getTotalRecordCount() {
        return this.totalRecordCount;
    }

    public void setTotalRecordCount(Long totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }


    public List<Product> getProducts() {
        return products.product;
    }
}
