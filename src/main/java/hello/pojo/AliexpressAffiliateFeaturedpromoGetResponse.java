package hello.pojo;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * TOP API: aliexpress.affiliate.featuredpromo.get response.
 * 
 * @author top auto create
 * @since 1.0, null
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliexpressAffiliateFeaturedpromoGetResponse extends TaobaoResponse {

	private static final long serialVersionUID = 1713341898871151213L;

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
 * 返回主题活动列表
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class Promo extends TaobaoObject {

	private static final long serialVersionUID = 8353431369833291822L;

	/**
		 * 主题活动的商品数量
		 */
		@JsonProperty("product_num")
		private Long productNum;
		/**
		 * 主题活动描述
		 */
		@JsonProperty("promo_desc")
		private String promoDesc;
		/**
		 * 主题活动名称
		 */
		@JsonProperty("promo_name")
		private String promoName;
	

	public Long getProductNum() {
			return this.productNum;
		}
		public void setProductNum(Long productNum) {
			this.productNum = productNum;
		}
		public String getPromoDesc() {
			return this.promoDesc;
		}
		public void setPromoDesc(String promoDesc) {
			this.promoDesc = promoDesc;
		}
		public String getPromoName() {
			return this.promoName;
		}
		public void setPromoName(String promoName) {
			this.promoName = promoName;
		}

}

	/**
 * 返回结果明细
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class TrafficFeaturedPromoResultDto extends TaobaoObject {

	private static final long serialVersionUID = 6369333723795117192L;

	/**
		 * 当前返回数量
		 */
		@JsonProperty("current_record_count")
		private Long currentRecordCount;
		/**
		 * 返回主题活动列表
		 */
		// @ApiListField("promos")
		@JsonProperty("promo")
		private List<Promo> promos;
	

	public Long getCurrentRecordCount() {
			return this.currentRecordCount;
		}
		public void setCurrentRecordCount(Long currentRecordCount) {
			this.currentRecordCount = currentRecordCount;
		}
		public List<Promo> getPromos() {
			return this.promos;
		}
		public void setPromos(List<Promo> promos) {
			this.promos = promos;
		}

}

	/**
 * 返回结果
 *
 * @author top auto create
 * @since 1.0, null
 */
public static class ResponseDto extends TaobaoObject {

	private static final long serialVersionUID = 6293145196928845862L;

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
		 * 返回结果明细
		 */
		@JsonProperty("result")
		private TrafficFeaturedPromoResultDto result;
	

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
		public TrafficFeaturedPromoResultDto getResult() {
			return this.result;
		}
		public void setResult(TrafficFeaturedPromoResultDto result) {
			this.result = result;
		}

}



}
