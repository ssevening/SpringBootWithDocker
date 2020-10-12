package hello.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import hello.utils.JsonMapper;

import javax.persistence.*;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product extends TaobaoObject {

    public void webToDB() {
        try {
            this.promoCodeInfoJson = JsonMapper.pojo2json(promoCodeInfo);
            this.productSmallImageUrlsJson = JsonMapper.pojo2json(productSmallImageUrls);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dbToWeb() {
        try {
            this.promoCodeInfo = JsonMapper.json2pojo(promoCodeInfoJson, PromoCodeDto.class);
            this.productSmallImageUrls = JsonMapper.json2pojo(productSmallImageUrlsJson, ImageInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String id;

    private static final long serialVersionUID = 6897781539788667454L;

    /**
     * app专享售价
     */
    @JsonProperty("app_sale_price")
    private String appSalePrice;
    /**
     * app专享售价币种
     */
    @JsonProperty("app_sale_price_currency")
    private String appSalePriceCurrency;
    /**
     * 佣金率
     */
    @JsonProperty("commission_rate")
    private String commissionRate;
    /**
     * 折扣比例
     */
    @JsonProperty("discount")
    public String discount;
    /**
     * 好评率
     */
    @JsonProperty("evaluate_rate")
    private String evaluateRate;
    /**
     * 一级类目ID
     */
    @JsonProperty("first_level_category_id")
    private Long firstLevelCategoryId;
    /**
     * 一级类目名称
     */
    @JsonProperty("first_level_category_name")
    private String firstLevelCategoryName;
    /**
     * 爆品佣金率
     */
    @JsonProperty("hot_product_commission_rate")
    private String hotProductCommissionRate;
    /**
     * 近期销量
     */
    @JsonProperty("lastest_volume")
    private Long lastestVolume;
    /**
     * 商品原始报价
     */
    @JsonProperty("original_price")
    private String originalPrice;
    /**
     * 原始售价币种
     */
    @JsonProperty("original_price_currency")
    private String originalPriceCurrency;
    /**
     * 平台商品类型：ALL,PLAZA,TMALL
     */
    @JsonProperty("platform_product_type")
    private String platformProductType;
    /**
     * 商品访问地址
     */
    @JsonProperty("product_detail_url")
    private String productDetailUrl;
    /**
     * 商品ID
     */
    @JsonProperty("product_id")
    private Long productId;
    /**
     * 商品主图地址
     */
    @JsonProperty("product_main_image_url")
    private String productMainImageUrl;
    /**
     * 商品小图地址列表
     */
    // @ApiListField("product_small_image_urls")
    @Transient
    // 忽略掉这个字段
    @JsonProperty("product_small_image_urls")
    public ImageInfo productSmallImageUrls;

    // 数据化持久字段
    private String productSmallImageUrlsJson;
    /**
     * 商品标题
     */
    @JsonProperty("product_title")
    private String productTitle;
    /**
     * 商品视频地址
     */
    @JsonProperty("product_video_url")
    private String productVideoUrl;
    /**
     * code信息
     */
    @JsonProperty("promo_code_info")
    @Transient
    private PromoCodeDto promoCodeInfo;

    /**
     * code jsonInfo
     */
    private String promoCodeInfoJson;
    /**
     * 推广链接
     */
    @JsonProperty("promotion_link")
    private String promotionLink;
    /**
     * JV佣金率
     */
    @JsonProperty("relevant_market_commission_rate")
    private String relevantMarketCommissionRate;
    /**
     * 商品售价
     */
    @JsonProperty("sale_price")
    public String salePrice;
    /**
     * 商品售价币种
     */
    @JsonProperty("sale_price_currency")
    private String salePriceCurrency;
    /**
     * 商品二级类目ID
     */
    @JsonProperty("second_level_category_id")
    private Long secondLevelCategoryId;
    /**
     * 商品二级类目名称
     */
    @JsonProperty("second_level_category_name")
    private String secondLevelCategoryName;
    /**
     * 店铺ID
     */
    @JsonProperty("shop_id")
    private Long shopId;
    /**
     * 店铺地址
     */
    @JsonProperty("shop_url")
    private String shopUrl;
    /**
     * 按目标币种转换后的APP专享价
     */
    @JsonProperty("target_app_sale_price")
    private String targetAppSalePrice;
    /**
     * 按目标币种转换后的APP专享价币种
     */
    @JsonProperty("target_app_sale_price_currency")
    private String targetAppSalePriceCurrency;
    /**
     * 按目标币种转换后的原始价格
     */
    @JsonProperty("target_original_price")
    private String targetOriginalPrice;
    /**
     * 按目标币种转换后的原始价格币种
     */
    @JsonProperty("target_original_price_currency")
    private String targetOriginalPriceCurrency;
    /**
     * 按目标币种转换后的售价
     */
    @JsonProperty("target_sale_price")
    private String targetSalePrice;
    /**
     * 按目标币种转换后的售价币种
     */
    @JsonProperty("target_sale_price_currency")
    private String targetSalePriceCurrency;


    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false, columnDefinition = "varchar(3072)")
    public String keywords;
    @Column(nullable = false, columnDefinition = "varchar(3072)")
    public String description;

    public String getDisplayPrice() {
        if (salePrice != null) {
            return getSalePriceWithCurrency();
        } else if (originalPrice != null) {
            return getOriginalPriceWithCurrency();
        } else {
            return getOriginalPriceWithCurrency();
        }


    }

    public String getHttpDetailUrl() {
        return productDetailUrl;
    }


    public String getAppSalePrice() {
        return this.appSalePrice;
    }

    public void setAppSalePrice(String appSalePrice) {
        this.appSalePrice = appSalePrice;
    }

    public String getAppSalePriceCurrency() {
        return this.appSalePriceCurrency;
    }

    public void setAppSalePriceCurrency(String appSalePriceCurrency) {
        this.appSalePriceCurrency = appSalePriceCurrency;
    }

    public String getCommissionRate() {
        return this.commissionRate;
    }

    public void setCommissionRate(String commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getDiscount() {
        return this.discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getEvaluateRate() {
        return this.evaluateRate;
    }

    public int getEvaluateRateIntValue() {
        if (getEvaluateRate() != null) {
            return (int) Float.parseFloat(getEvaluateRate().replace("%", ""));
        } else {
            return 0;
        }
    }

    public int getEvaluateRate5IntValue() {
        return getEvaluateRateIntValue() / 20;
    }

    public void setEvaluateRate(String evaluateRate) {
        this.evaluateRate = evaluateRate;
    }

    public Long getFirstLevelCategoryId() {
        return this.firstLevelCategoryId;
    }

    public void setFirstLevelCategoryId(Long firstLevelCategoryId) {
        this.firstLevelCategoryId = firstLevelCategoryId;
    }

    public String getFirstLevelCategoryName() {
        return this.firstLevelCategoryName;
    }

    public void setFirstLevelCategoryName(String firstLevelCategoryName) {
        this.firstLevelCategoryName = firstLevelCategoryName;
    }

    public String getHotProductCommissionRate() {
        return this.hotProductCommissionRate;
    }

    public void setHotProductCommissionRate(String hotProductCommissionRate) {
        this.hotProductCommissionRate = hotProductCommissionRate;
    }

    public Long getLastestVolume() {
        return this.lastestVolume;
    }

    public void setLastestVolume(Long lastestVolume) {
        this.lastestVolume = lastestVolume;
    }

    public String getOriginalPrice() {
        return this.originalPrice;
    }

    public String getOriginalPriceWithCurrency() {
        return originalPriceCurrency + " " + originalPrice;
    }

    public String getSalePriceWithCurrency() {
        return salePriceCurrency + " " + salePrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getOriginalPriceCurrency() {
        return this.originalPriceCurrency;
    }

    public void setOriginalPriceCurrency(String originalPriceCurrency) {
        this.originalPriceCurrency = originalPriceCurrency;
    }

    public String getPlatformProductType() {
        return this.platformProductType;
    }

    public void setPlatformProductType(String platformProductType) {
        this.platformProductType = platformProductType;
    }


    public void setProductDetailUrl(String productDetailUrl) {
        this.productDetailUrl = productDetailUrl;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductMainImageUrl() {
        if (productMainImageUrl != null) {
            productMainImageUrl = productMainImageUrl.replace("http://", "https://");
        }
        return this.productMainImageUrl;
    }

    public String getProductImageByIndex(int index) {
        String imageUrl = "";
        if (getProductSmallImageUrls() != null && !getProductSmallImageUrls().isEmpty() && getProductSmallImageUrls().size() > index) {
            imageUrl = getProductSmallImageUrls().get(index).replace("http://", "https://");
        } else {
            return "";
        }
        return imageUrl;
    }

    public void setProductMainImageUrl(String productMainImageUrl) {
        this.productMainImageUrl = productMainImageUrl;
    }

    public List<String> getProductSmallImageUrls() {
        return this.productSmallImageUrls.string;
    }


    public String getProductTitle() {
        return this.productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductVideoUrl() {
        if (productVideoUrl != null) {
            productVideoUrl = productVideoUrl.replace("http://", "https://");
        }
        return this.productVideoUrl;
    }

    public void setProductVideoUrl(String productVideoUrl) {
        this.productVideoUrl = productVideoUrl;
    }

    public PromoCodeDto getPromoCodeInfo() {
        return this.promoCodeInfo;
    }

    public String getPromotionCodeStrInDetail() {
        if (promoCodeInfo != null) {
            return promoCodeInfo.getPromotionCodeStrInDetail();
        }
        return null;
    }

    public void setPromoCodeInfo(PromoCodeDto promoCodeInfo) {
        this.promoCodeInfo = promoCodeInfo;
    }

    public String getPromotionLink() {
        return this.promotionLink;
    }

    public void setPromotionLink(String promotionLink) {
        this.promotionLink = promotionLink;
    }

    public String getRelevantMarketCommissionRate() {
        return this.relevantMarketCommissionRate;
    }

    public void setRelevantMarketCommissionRate(String relevantMarketCommissionRate) {
        this.relevantMarketCommissionRate = relevantMarketCommissionRate;
    }

    public String getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSalePriceCurrency() {
        return this.salePriceCurrency;
    }

    public void setSalePriceCurrency(String salePriceCurrency) {
        this.salePriceCurrency = salePriceCurrency;
    }

    public Long getSecondLevelCategoryId() {
        return this.secondLevelCategoryId;
    }

    public void setSecondLevelCategoryId(Long secondLevelCategoryId) {
        this.secondLevelCategoryId = secondLevelCategoryId;
    }

    public String getSecondLevelCategoryName() {
        return this.secondLevelCategoryName;
    }

    public void setSecondLevelCategoryName(String secondLevelCategoryName) {
        this.secondLevelCategoryName = secondLevelCategoryName;
    }

    public Long getShopId() {
        return this.shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopUrl() {
        return this.shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getTargetAppSalePrice() {
        return this.targetAppSalePrice;
    }

    public void setTargetAppSalePrice(String targetAppSalePrice) {
        this.targetAppSalePrice = targetAppSalePrice;
    }

    public String getTargetAppSalePriceCurrency() {
        return this.targetAppSalePriceCurrency;
    }

    public void setTargetAppSalePriceCurrency(String targetAppSalePriceCurrency) {
        this.targetAppSalePriceCurrency = targetAppSalePriceCurrency;
    }

    public String getTargetOriginalPrice() {
        return this.targetOriginalPrice;
    }

    public void setTargetOriginalPrice(String targetOriginalPrice) {
        this.targetOriginalPrice = targetOriginalPrice;
    }

    public String getTargetOriginalPriceCurrency() {
        return this.targetOriginalPriceCurrency;
    }

    public void setTargetOriginalPriceCurrency(String targetOriginalPriceCurrency) {
        this.targetOriginalPriceCurrency = targetOriginalPriceCurrency;
    }

    public String getTargetSalePrice() {
        return this.targetSalePrice;
    }

    public void setTargetSalePrice(String targetSalePrice) {
        this.targetSalePrice = targetSalePrice;
    }

    public String getTargetSalePriceCurrency() {
        return this.targetSalePriceCurrency;
    }

    public void setTargetSalePriceCurrency(String targetSalePriceCurrency) {
        this.targetSalePriceCurrency = targetSalePriceCurrency;
    }

    public String getProductSmallImageUrlsJson() {
        return productSmallImageUrlsJson;
    }

    public void setProductSmallImageUrlsJson(String productSmallImageUrlsJson) {
        this.productSmallImageUrlsJson = productSmallImageUrlsJson;
    }

    public String getPromoCodeInfoJson() {
        return promoCodeInfoJson;
    }

    public void setPromoCodeInfoJson(String promoCodeInfoJson) {
        this.promoCodeInfoJson = promoCodeInfoJson;
    }

    public class ImageInfo {
        public List<String> string;
    }

}
