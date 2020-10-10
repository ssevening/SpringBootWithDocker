package hello.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * TOP API: aliexpress.affiliate.link.generate response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliexpressAffiliateLinkGenerateResponse extends TaobaoResponse {

	private static final long serialVersionUID = 7151833574835486652L;

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
 * 推广链接列表
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class PromotionLink extends TaobaoObject {

	private static final long serialVersionUID = 4192537621316818195L;

	/**
		 * 推广链接
		 */
		@JsonProperty("promotion_link")
		private String promotionLink;
		/**
		 * 原始链接或者值
		 */
		@JsonProperty("source_value")
		private String sourceValue;
	

	public String getPromotionLink() {
			return this.promotionLink;
		}
		public void setPromotionLink(String promotionLink) {
			this.promotionLink = promotionLink;
		}
		public String getSourceValue() {
			return this.sourceValue;
		}
		public void setSourceValue(String sourceValue) {
			this.sourceValue = sourceValue;
		}

}

	/**
 * 返回结果明细信息
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class PromotionLinkResultDto extends TaobaoObject {

	private static final long serialVersionUID = 7493397597371319485L;

	/**
		 * 推广链接列表
		 */
		// @ApiListField("promotion_links")
		@JsonProperty("promotion_link")
		private List<PromotionLink> promotionLinks;
		/**
		 * 返回总量
		 */
		@JsonProperty("total_result_count")
		private Long totalResultCount;
		/**
		 * 推广者TrackingId
		 */
		@JsonProperty("tracking_id")
		private String trackingId;
	

	public List<PromotionLink> getPromotionLinks() {
			return this.promotionLinks;
		}
		public void setPromotionLinks(List<PromotionLink> promotionLinks) {
			this.promotionLinks = promotionLinks;
		}
		public Long getTotalResultCount() {
			return this.totalResultCount;
		}
		public void setTotalResultCount(Long totalResultCount) {
			this.totalResultCount = totalResultCount;
		}
		public String getTrackingId() {
			return this.trackingId;
		}
		public void setTrackingId(String trackingId) {
			this.trackingId = trackingId;
		}

}

	/**
 * 返回结果
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class ResponseDto extends TaobaoObject {

	private static final long serialVersionUID = 6655345873333886194L;

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
		 * 返回结果明细信息
		 */
		@JsonProperty("result")
		private PromotionLinkResultDto result;
	

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
		public PromotionLinkResultDto getResult() {
			return this.result;
		}
		public void setResult(PromotionLinkResultDto result) {
			this.result = result;
		}

}



}
