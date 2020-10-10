package hello.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * TOP API: aliexpress.affiliate.order.list response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliexpressAffiliateOrderListResponse extends TaobaoResponse {

	private static final long serialVersionUID = 6166944488151589358L;

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
 * 订单列表
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class Order extends TaobaoObject {

	private static final long serialVersionUID = 1486882844622265567L;

	/**
		 * 类目ID
		 */
		@JsonProperty("category_id")
		private Long categoryId;
		/**
		 * 佣金率
		 */
		@JsonProperty("commission_rate")
		private String commissionRate;
		/**
		 * 订单创建时间
		 */
		@JsonProperty("created_time")
		private String createdTime;
		/**
		 * 自定义参数(JSON格式）
		 */
		@JsonProperty("custom_parameters")
		private String customParameters;
		/**
		 * 订单完成时的预计佣金
		 */
		@JsonProperty("estimated_finished_commission")
		private Long estimatedFinishedCommission;
		/**
		 * 订单支付时的预计佣金
		 */
		@JsonProperty("estimated_paid_commission")
		private Long estimatedPaidCommission;
		/**
		 * 订单完成金额
		 */
		@JsonProperty("finished_amount")
		private Long finishedAmount;
		/**
		 * 订单完成时间
		 */
		@JsonProperty("finished_time")
		private String finishedTime;
		/**
		 * 是否爆品:Y或者N
		 */
		@JsonProperty("is_hot_product")
		private String isHotProduct;
		/**
		 * 是否新买家
		 */
		@JsonProperty("is_new_buyer")
		private String isNewBuyer;
		/**
		 * 新买家奖励佣金
		 */
		@JsonProperty("new_buyer_bonus_commission")
		private Long newBuyerBonusCommission;
		/**
		 * 订单ID
		 */
		@JsonProperty("order_id")
		private Long orderId;
		/**
		 * 子订单ID：已废弃，请使用sub_order_id
		 */
		@JsonProperty("order_number")
		private Long orderNumber;
		/**
		 * 订单状态:Payment Completed,Buyer Confirmed Receipt
		 */
		@JsonProperty("order_status")
		private String orderStatus;
		/**
		 * 订单支付完成金额
		 */
		@JsonProperty("paid_amount")
		private Long paidAmount;
		/**
		 * 订单支付完成时间
		 */
		@JsonProperty("paid_time")
		private String paidTime;
		/**
		 * 父订单ID:已废弃，请使用order_id
		 */
		@JsonProperty("parent_order_number")
		private Long parentOrderNumber;
		/**
		 * 下单商品数量
		 */
		@JsonProperty("product_count")
		private Long productCount;
		/**
		 * 商品DetailUrl
		 */
		@JsonProperty("product_detail_url")
		private String productDetailUrl;
		/**
		 * 商品ID
		 */
		@JsonProperty("product_id")
		private Long productId;
		/**
		 * 商品主图Url
		 */
		@JsonProperty("product_main_image_url")
		private String productMainImageUrl;
		/**
		 * 商品标题
		 */
		@JsonProperty("product_title")
		private String productTitle;
		/**
		 * 推广者结算币种
		 */
		@JsonProperty("settled_currency")
		private String settledCurrency;
		/**
		 * 物流国家
		 */
		@JsonProperty("ship_to_country")
		private String shipToCountry;
		/**
		 * 子订单ID
		 */
		@JsonProperty("sub_order_id")
		private Long subOrderId;
		/**
		 * trackingId
		 */
		@JsonProperty("tracking_id")
		private String trackingId;
	

	public Long getCategoryId() {
			return this.categoryId;
		}
		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}
		public String getCommissionRate() {
			return this.commissionRate;
		}
		public void setCommissionRate(String commissionRate) {
			this.commissionRate = commissionRate;
		}
		public String getCreatedTime() {
			return this.createdTime;
		}
		public void setCreatedTime(String createdTime) {
			this.createdTime = createdTime;
		}
		public String getCustomParameters() {
			return this.customParameters;
		}
		public void setCustomParameters(String customParameters) {
			this.customParameters = customParameters;
		}
		public Long getEstimatedFinishedCommission() {
			return this.estimatedFinishedCommission;
		}
		public void setEstimatedFinishedCommission(Long estimatedFinishedCommission) {
			this.estimatedFinishedCommission = estimatedFinishedCommission;
		}
		public Long getEstimatedPaidCommission() {
			return this.estimatedPaidCommission;
		}
		public void setEstimatedPaidCommission(Long estimatedPaidCommission) {
			this.estimatedPaidCommission = estimatedPaidCommission;
		}
		public Long getFinishedAmount() {
			return this.finishedAmount;
		}
		public void setFinishedAmount(Long finishedAmount) {
			this.finishedAmount = finishedAmount;
		}
		public String getFinishedTime() {
			return this.finishedTime;
		}
		public void setFinishedTime(String finishedTime) {
			this.finishedTime = finishedTime;
		}
		public String getIsHotProduct() {
			return this.isHotProduct;
		}
		public void setIsHotProduct(String isHotProduct) {
			this.isHotProduct = isHotProduct;
		}
		public String getIsNewBuyer() {
			return this.isNewBuyer;
		}
		public void setIsNewBuyer(String isNewBuyer) {
			this.isNewBuyer = isNewBuyer;
		}
		public Long getNewBuyerBonusCommission() {
			return this.newBuyerBonusCommission;
		}
		public void setNewBuyerBonusCommission(Long newBuyerBonusCommission) {
			this.newBuyerBonusCommission = newBuyerBonusCommission;
		}
		public Long getOrderId() {
			return this.orderId;
		}
		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}
		public Long getOrderNumber() {
			return this.orderNumber;
		}
		public void setOrderNumber(Long orderNumber) {
			this.orderNumber = orderNumber;
		}
		public String getOrderStatus() {
			return this.orderStatus;
		}
		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}
		public Long getPaidAmount() {
			return this.paidAmount;
		}
		public void setPaidAmount(Long paidAmount) {
			this.paidAmount = paidAmount;
		}
		public String getPaidTime() {
			return this.paidTime;
		}
		public void setPaidTime(String paidTime) {
			this.paidTime = paidTime;
		}
		public Long getParentOrderNumber() {
			return this.parentOrderNumber;
		}
		public void setParentOrderNumber(Long parentOrderNumber) {
			this.parentOrderNumber = parentOrderNumber;
		}
		public Long getProductCount() {
			return this.productCount;
		}
		public void setProductCount(Long productCount) {
			this.productCount = productCount;
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
		public String getProductTitle() {
			return this.productTitle;
		}
		public void setProductTitle(String productTitle) {
			this.productTitle = productTitle;
		}
		public String getSettledCurrency() {
			return this.settledCurrency;
		}
		public void setSettledCurrency(String settledCurrency) {
			this.settledCurrency = settledCurrency;
		}
		public String getShipToCountry() {
			return this.shipToCountry;
		}
		public void setShipToCountry(String shipToCountry) {
			this.shipToCountry = shipToCountry;
		}
		public Long getSubOrderId() {
			return this.subOrderId;
		}
		public void setSubOrderId(Long subOrderId) {
			this.subOrderId = subOrderId;
		}
		public String getTrackingId() {
			return this.trackingId;
		}
		public void setTrackingId(String trackingId) {
			this.trackingId = trackingId;
		}

}

	/**
 * 返回结果明细
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class TrafficOrderResultDto extends TaobaoObject {

	private static final long serialVersionUID = 2279764981413765794L;

	/**
		 * 当前页码
		 */
		@JsonProperty("current_page_no")
		private Long currentPageNo;
		/**
		 * 当前页记录条数
		 */
		@JsonProperty("current_record_count")
		private Long currentRecordCount;
		/**
		 * 订单列表
		 */
		// @ApiListField("orders")
		@JsonProperty("order")
		private List<Order> orders;
		/**
		 * 总页数
		 */
		@JsonProperty("total_page_no")
		private Long totalPageNo;
		/**
		 * 总记录数
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
		public List<Order> getOrders() {
			return this.orders;
		}
		public void setOrders(List<Order> orders) {
			this.orders = orders;
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

	private static final long serialVersionUID = 3353353928969492734L;

	/**
		 * 返回结果状态码
		 */
		@JsonProperty("resp_code")
		private Long respCode;
		/**
		 * 返回状态描述信息
		 */
		@JsonProperty("resp_msg")
		private String respMsg;
		/**
		 * 返回结果明细
		 */
		@JsonProperty("result")
		private TrafficOrderResultDto result;
	

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
		public TrafficOrderResultDto getResult() {
			return this.result;
		}
		public void setResult(TrafficOrderResultDto result) {
			this.result = result;
		}

}



}
