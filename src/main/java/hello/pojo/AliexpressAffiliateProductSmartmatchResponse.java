package hello.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * TOP API: aliexpress.affiliate.product.smartmatch response.
 *
 * @author top auto create
 * @since 1.0, null
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AliexpressAffiliateProductSmartmatchResponse extends TaobaoResponse {

    private static final long serialVersionUID = 1312728578897761333L;

    /**
     * 返回结果
     */
    @JsonProperty("resp_result")
    private ResponseDto respResult;

    @JsonProperty("request_id")
    public String request_id;


    public void setRespResult(ResponseDto respResult) {
        this.respResult = respResult;
    }

    public ResponseDto getRespResult() {
        return this.respResult;
    }

    /**
     * 返回商品详情
     *
     * @author top auto create
     * @since 1.0, null
     */


    /**
     * 返回结果状态倾向
     *
     * @author top auto create
     * @since 1.0, null
     */
    public static class TrafficProductResultDto extends TaobaoObject {

        private static final long serialVersionUID = 4192448252912343899L;

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
         * 返回商品详情
         */
        // @ApiListField("products")
        @JsonProperty("products")
        public ProductInfo products;


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

        public List<Product> getProducts() {
            if (products != null) {
                return products.product;
            } else {
                return null;
            }

        }

    }

    /**
     * 返回结果
     *
     * @author top auto create
     * @since 1.0, null
     */
    public static class ResponseDto extends TaobaoObject {

        private static final long serialVersionUID = 2488352224513734644L;

        /**
         * 返回结果状态码
         */
        @JsonProperty("resp_code")
        private Long respCode;
        /**
         * 返回结果状态描述
         */
        @JsonProperty("resp_msg")
        private String respMsg;
        /**
         * 返回结果状态倾向
         */
        @JsonProperty("result")
        private TrafficProductResultDto result;


        public Long getRespCode() {
            return this.respCode;
        }

        public void setRespCode(Long respCode) {
            this.respCode = respCode;
        }

        public String getRespMsg() {
            return this.respMsg;
        }

        public void setRespMsg(String respMsg) {
            this.respMsg = respMsg;
        }

        public TrafficProductResultDto getResult() {
            return this.result;
        }

        public void setResult(TrafficProductResultDto result) {
            this.result = result;
        }

    }


}
