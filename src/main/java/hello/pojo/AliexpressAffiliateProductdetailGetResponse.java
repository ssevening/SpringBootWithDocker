package hello.pojo;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * TOP API: aliexpress.affiliate.productdetail.get response.
 *
 * @author top auto create
 * @since 1.0, null
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AliexpressAffiliateProductdetailGetResponse extends TaobaoResponse {

    private static final long serialVersionUID = 3376864415336689958L;

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
     * code信息
     *
     * @author top auto create
     * @since 1.0, null
     */
    public static class PromoCodeDto extends TaobaoObject {

        private static final long serialVersionUID = 5359472183472323736L;

        /**
         * code使用有效期的结束时间
         */
        @JsonProperty("code_availabletime_end")
        private String codeAvailabletimeEnd;
        /**
         * code使用有效期的开始时间
         */
        @JsonProperty("code_availabletime_start")
        private String codeAvailabletimeStart;
        /**
         * 优惠方式 1 满减，2 满折
         */
        @JsonProperty("code_campaigntype")
        private String codeCampaigntype;
        /**
         * 最低使用门槛
         */
        @JsonProperty("code_mini_spend")
        private String codeMiniSpend;
        /**
         * 品code合一url
         */
        @JsonProperty("code_promotionurl")
        private String codePromotionurl;
        /**
         * code剩余可使用的数量
         */
        @JsonProperty("code_quantity")
        private String codeQuantity;
        /**
         * 面额
         */
        @JsonProperty("code_value")
        private String codeValue;
        /**
         * 专属绑定PID的code码
         */
        @JsonProperty("promo_code")
        private String promoCode;


        public String getCodeAvailabletimeEnd() {
            return this.codeAvailabletimeEnd;
        }

        public void setCodeAvailabletimeEnd(String codeAvailabletimeEnd) {
            this.codeAvailabletimeEnd = codeAvailabletimeEnd;
        }

        public String getCodeAvailabletimeStart() {
            return this.codeAvailabletimeStart;
        }

        public void setCodeAvailabletimeStart(String codeAvailabletimeStart) {
            this.codeAvailabletimeStart = codeAvailabletimeStart;
        }

        public String getCodeCampaigntype() {
            return this.codeCampaigntype;
        }

        public void setCodeCampaigntype(String codeCampaigntype) {
            this.codeCampaigntype = codeCampaigntype;
        }

        public String getCodeMiniSpend() {
            return this.codeMiniSpend;
        }

        public void setCodeMiniSpend(String codeMiniSpend) {
            this.codeMiniSpend = codeMiniSpend;
        }

        public String getCodePromotionurl() {
            return this.codePromotionurl;
        }

        public void setCodePromotionurl(String codePromotionurl) {
            this.codePromotionurl = codePromotionurl;
        }

        public String getCodeQuantity() {
            return this.codeQuantity;
        }

        public void setCodeQuantity(String codeQuantity) {
            this.codeQuantity = codeQuantity;
        }

        public String getCodeValue() {
            return this.codeValue;
        }

        public void setCodeValue(String codeValue) {
            this.codeValue = codeValue;
        }

        public String getPromoCode() {
            return this.promoCode;
        }

        public void setPromoCode(String promoCode) {
            this.promoCode = promoCode;
        }

    }


    /**
     * 返回结果明细
     *
     * @author top auto create
     * @since 1.0, null
     */
    public static class TrafficProductResultDto extends TaobaoObject {

        private static final long serialVersionUID = 7734199177324516988L;

        /**
         * 当前页记录数
         */
        @JsonProperty("current_record_count")
        private Long currentRecordCount;
        /**
         * 商品列表
         */
        @JsonProperty("products")
        public ProductInfo products;


        public Long getCurrentRecordCount() {
            return this.currentRecordCount;
        }

        public void setCurrentRecordCount(Long currentRecordCount) {
            this.currentRecordCount = currentRecordCount;
        }

        public List<Product> getProducts() {
            return products.product;
        }


    }

    /**
     * 返回结果
     *
     * @author top auto create
     * @since 1.0, null
     */
    public static class ResponseDto extends TaobaoObject {

        private static final long serialVersionUID = 4269869491636235662L;

        /**
         * 返回结果状态码
         */
        @JsonProperty("resp_code")
        private Long respCode;
        /**
         * 返回结果状态码描述
         */
        @JsonProperty("resp_msg")
        private String respMsg;
        /**
         * 返回结果明细
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
