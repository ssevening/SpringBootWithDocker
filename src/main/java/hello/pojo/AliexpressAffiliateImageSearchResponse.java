package hello.pojo;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * TOP API: aliexpress.affiliate.image.search response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliexpressAffiliateImageSearchResponse extends TaobaoResponse {

	private static final long serialVersionUID = 1221953814757884856L;

	/** 
	 * 默认描述
	 */
	@JsonProperty("result")
	private Response result;
	@JsonProperty("request_id")
	public String request_id;


	public void setResult(Response result) {
		this.result = result;
	}
	public Response getResult( ) {
		return this.result;
	}
	
	/**
 * code
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class PromoCodeDto extends TaobaoObject {

	private static final long serialVersionUID = 8898379236774818982L;

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
		 * 优惠方式 0 满减，1 满折
		 */
		@JsonProperty("code_campaigntype")
		private String codeCampaigntype;
		/**
		 * code最低使用门槛，0 为不限制
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
		 * code面额（满减或满折），若为满减则值为入参时选择的币种+绝对值，若为满折则值为绝对值百分比
		 */
		@JsonProperty("code_value")
		private String codeValue;
		/**
		 * 商品最优code码
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
 * 图搜结果
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class Product extends TaobaoObject {

	private static final long serialVersionUID = 7751825392997184135L;

	/**
		 * app专享价
		 */
		@JsonProperty("app_sale_price")
		private String appSalePrice;
		/**
		 * app专享价币种
		 */
		@JsonProperty("app_sale_price_currency")
		private String appSalePriceCurrency;
		/**
		 * 佣金率
		 */
		@JsonProperty("commision_rate")
		private String commisionRate;
		/**
		 * 折扣比例
		 */
		@JsonProperty("discount")
		private String discount;
		/**
		 * 商品好评率
		 */
		@JsonProperty("evaluate_rate")
		private String evaluateRate;
		/**
		 * 一级类目 ID
		 */
		@JsonProperty("first_level_category_id")
		private String firstLevelCategoryId;
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
		private String lastestVolume;
		/**
		 * 商品原价
		 */
		@JsonProperty("original_price")
		private String originalPrice;
		/**
		 * 商品原价币种
		 */
		@JsonProperty("original_price_currency")
		private String originalPriceCurrency;
		/**
		 * 平台商家类型
		 */
		@JsonProperty("platform_product_type")
		private String platformProductType;
		/**
		 * 商品访问地址
		 */
		@JsonProperty("product_detail_url")
		private String productDetailUrl;
		/**
		 * 商品 ID
		 */
		@JsonProperty("product_id")
		private String productId;
		/**
		 * 商品主图地址
		 */
		@JsonProperty("product_main_image_url")
		private String productMainImageUrl;
		/**
		 * 商品小图地址列表
		 */
		// @ApiListField("product_small_image_urls")
		@JsonProperty("string")
		private List<String> productSmallImageUrls;
		/**
		 * 商品标题
		 */
		@JsonProperty("product_title")
		private String productTitle;
		/**
		 * 商品介绍视频
		 */
		@JsonProperty("product_video_url")
		private String productVideoUrl;
		/**
		 * code
		 */
		@JsonProperty("promo_code_info")
		private PromoCodeDto promoCodeInfo;
		/**
		 * 推广链接地址
		 */
		@JsonProperty("promotion_link")
		private String promotionLink;
		/**
		 * JV 佣金率
		 */
		@JsonProperty("relevant_market_commission_rate")
		private String relevantMarketCommissionRate;
		/**
		 * 商品售价
		 */
		@JsonProperty("sale_price")
		private String salePrice;
		/**
		 * 商品售价币种
		 */
		@JsonProperty("sale_price_currency")
		private String salePriceCurrency;
		/**
		 * 二级类目 ID
		 */
		@JsonProperty("second_level_category_id")
		private String secondLevelCategoryId;
		/**
		 * 二级类目名称
		 */
		@JsonProperty("second_level_category_name")
		private String secondLevelCategoryName;
		/**
		 * 店铺 ID
		 */
		@JsonProperty("shop_id")
		private Long shopId;
		/**
		 * 店铺地址
		 */
		@JsonProperty("shop_url")
		private String shopUrl;
		/**
		 * 按目标币种转换后的app专享价
		 */
		@JsonProperty("target_app_sale_price")
		private String targetAppSalePrice;
		/**
		 * 按目标币种转换后的app专享价币种
		 */
		@JsonProperty("target_app_sale_price_currency")
		private String targetAppSalePriceCurrency;
		/**
		 * 按目标币种转换后的商品原价
		 */
		@JsonProperty("target_original_price")
		private String targetOriginalPrice;
		/**
		 * 按目标币种转换后的商品原价币种
		 */
		@JsonProperty("target_original_price_currency")
		private String targetOriginalPriceCurrency;
		/**
		 * 按目标币种转换后的商品售价
		 */
		@JsonProperty("target_sale_price")
		private String targetSalePrice;
		/**
		 * 按目标币种转换后的商品售价币种
		 */
		@JsonProperty("target_sale_price_currency")
		private String targetSalePriceCurrency;
	

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
		public String getCommisionRate() {
			return this.commisionRate;
		}
		public void setCommisionRate(String commisionRate) {
			this.commisionRate = commisionRate;
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
		public void setEvaluateRate(String evaluateRate) {
			this.evaluateRate = evaluateRate;
		}
		public String getFirstLevelCategoryId() {
			return this.firstLevelCategoryId;
		}
		public void setFirstLevelCategoryId(String firstLevelCategoryId) {
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
		public String getLastestVolume() {
			return this.lastestVolume;
		}
		public void setLastestVolume(String lastestVolume) {
			this.lastestVolume = lastestVolume;
		}
		public String getOriginalPrice() {
			return this.originalPrice;
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
		public String getProductDetailUrl() {
			return this.productDetailUrl;
		}
		public void setProductDetailUrl(String productDetailUrl) {
			this.productDetailUrl = productDetailUrl;
		}
		public String getProductId() {
			return this.productId;
		}
		public void setProductId(String productId) {
			this.productId = productId;
		}
		public String getProductMainImageUrl() {
			return this.productMainImageUrl;
		}
		public void setProductMainImageUrl(String productMainImageUrl) {
			this.productMainImageUrl = productMainImageUrl;
		}
		public List<String> getProductSmallImageUrls() {
			return this.productSmallImageUrls;
		}
		public void setProductSmallImageUrls(List<String> productSmallImageUrls) {
			this.productSmallImageUrls = productSmallImageUrls;
		}
		public String getProductTitle() {
			return this.productTitle;
		}
		public void setProductTitle(String productTitle) {
			this.productTitle = productTitle;
		}
		public String getProductVideoUrl() {
			return this.productVideoUrl;
		}
		public void setProductVideoUrl(String productVideoUrl) {
			this.productVideoUrl = productVideoUrl;
		}
		public PromoCodeDto getPromoCodeInfo() {
			return this.promoCodeInfo;
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
		public String getSecondLevelCategoryId() {
			return this.secondLevelCategoryId;
		}
		public void setSecondLevelCategoryId(String secondLevelCategoryId) {
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

}

	/**
 * 图片识别的坐标
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class ProductImgRegionDto extends TaobaoObject {

	private static final long serialVersionUID = 2875843429168756238L;

	/**
		 * 图片识别的坐标 pos_bottom_right_x
		 */
		@JsonProperty("pos_bottom_right_x")
		private String posBottomRightX;
		/**
		 * 图片识别的坐标 pos_bottom_right_y
		 */
		@JsonProperty("pos_bottom_right_y")
		private String posBottomRightY;
		/**
		 * 图片识别的坐标 pos_top_left_x
		 */
		@JsonProperty("pos_top_left_x")
		private String posTopLeftX;
		/**
		 * 图片识别的坐标 pos_top_left_y
		 */
		@JsonProperty("pos_top_left_y")
		private String posTopLeftY;
	

	public String getPosBottomRightX() {
			return this.posBottomRightX;
		}
		public void setPosBottomRightX(String posBottomRightX) {
			this.posBottomRightX = posBottomRightX;
		}
		public String getPosBottomRightY() {
			return this.posBottomRightY;
		}
		public void setPosBottomRightY(String posBottomRightY) {
			this.posBottomRightY = posBottomRightY;
		}
		public String getPosTopLeftX() {
			return this.posTopLeftX;
		}
		public void setPosTopLeftX(String posTopLeftX) {
			this.posTopLeftX = posTopLeftX;
		}
		public String getPosTopLeftY() {
			return this.posTopLeftY;
		}
		public void setPosTopLeftY(String posTopLeftY) {
			this.posTopLeftY = posTopLeftY;
		}

}

	/**
 * 图搜结果
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class TrafficImageSearchResultDto extends TaobaoObject {

	private static final long serialVersionUID = 6878685235613481458L;

	/**
		 * 图搜结果
		 */
		// @ApiListField("products")
		@JsonProperty("product")
		private List<Product> products;
		/**
		 * 图片识别的坐标
		 */
		@JsonProperty("region")
		private ProductImgRegionDto region;
		/**
		 * 总数
		 */
		@JsonProperty("total_record_count")
		private Long totalRecordCount;
	

	public List<Product> getProducts() {
			return this.products;
		}
		public void setProducts(List<Product> products) {
			this.products = products;
		}
		public ProductImgRegionDto getRegion() {
			return this.region;
		}
		public void setRegion(ProductImgRegionDto region) {
			this.region = region;
		}
		public Long getTotalRecordCount() {
			return this.totalRecordCount;
		}
		public void setTotalRecordCount(Long totalRecordCount) {
			this.totalRecordCount = totalRecordCount;
		}

}

	/**
 * 默认描述
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class Response extends TaobaoObject {

	private static final long serialVersionUID = 5823397673976443983L;

	/**
		 * 返回结果状态码
		 */
		@JsonProperty("code")
		private String code;
		/**
		 * 图搜结果
		 */
		@JsonProperty("data")
		private TrafficImageSearchResultDto data;
		/**
		 * 默认描述
		 */
		@JsonProperty("message")
		private String message;
		/**
		 * 是否成功
		 */
		@JsonProperty("success")
		private Boolean success;
	

	public String getCode() {
			return this.code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public TrafficImageSearchResultDto getData() {
			return this.data;
		}
		public void setData(TrafficImageSearchResultDto data) {
			this.data = data;
		}
		public String getMessage() {
			return this.message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Boolean getSuccess() {
			return this.success;
		}
		public void setSuccess(Boolean success) {
			this.success = success;
		}

}



}
