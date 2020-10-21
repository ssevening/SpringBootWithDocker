package hello.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDto extends TaobaoObject {

    private static final long serialVersionUID = 1325321965281421674L;

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
     * 返回结果状态详情
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