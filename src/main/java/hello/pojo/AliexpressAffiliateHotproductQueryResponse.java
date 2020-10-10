package hello.pojo;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * TOP API: aliexpress.affiliate.hotproduct.query response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliexpressAffiliateHotproductQueryResponse extends TaobaoResponse {

	private static final long serialVersionUID = 4497385114299943252L;

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
	public ResponseDto getRespResult( ) {
		return this.respResult;
	}
	
	/**
 * code信息
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class PromoCodeDto extends TaobaoObject {

	private static final long serialVersionUID = 4484435967244923373L;

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
 * 返回商品数量
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class Product extends TaobaoObject {

	private static final long serialVersionUID = 7361325195311597532L;

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
		private String discount;
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
		 * 商品原始报价币种
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
		 * 商品ID
		 */
		@JsonProperty("product_id")
		private Long productId;
		/**
		 * 商品主题地址
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
		 * 商品视频地址
		 */
		@JsonProperty("product_video_url")
		private String productVideoUrl;
		/**
		 * code信息
		 */
		@JsonProperty("promo_code_info")
		private PromoCodeDto promoCodeInfo;
		/**
		 * 推广转链信息
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
		private String salePrice;
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
		 * 可达国家与到达时间
		 */
		@JsonProperty("ship_to_days")
		private String shipToDays;
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
		public Long getProductId() {
			return this.productId;
		}
		public void setProductId(Long productId) {
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
		public String getShipToDays() {
			return this.shipToDays;
		}
		public void setShipToDays(String shipToDays) {
			this.shipToDays = shipToDays;
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
 * 返回结果状态结果
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class TrafficProductResultDto extends TaobaoObject {

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
		// @ApiListField("products")
		@JsonProperty("product")
		private List<Product> products;
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
		public List<Product> getProducts() {
			return this.products;
		}
		public void setProducts(List<Product> products) {
			this.products = products;
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

}

	/**
 * 返回结果
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class ResponseDto extends TaobaoObject {

	private static final long serialVersionUID = 2758187435536395592L;

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
		 * 返回结果状态结果
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
