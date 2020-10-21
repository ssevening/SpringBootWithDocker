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

}
