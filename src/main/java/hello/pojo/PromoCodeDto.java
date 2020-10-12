package hello.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;

/**
 * code信息
 *
 * @author top auto create
 * @since 1.0, null
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PromoCodeDto extends TaobaoObject {

    private static final long serialVersionUID = 8671855297979169912L;

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


    public String getPromotionCodeStrInDetail() {
        String template = "{0}, use {1}";
        return MessageFormat.format(template, getCodeValue(), getPromoCode());
    }

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


