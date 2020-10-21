package hello.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * TOP API: aliexpress.affiliate.category.get response.
 *
 * @author top auto create
 * @since 1.0, null
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliexpressAffiliateCategoryGetResponse extends TaobaoResponse {

    private static final long serialVersionUID = 7789886967727752617L;

    /**
     * 返回结果
     */
    @JsonProperty("resp_result")
    private ResponseResult respResult;
    @JsonProperty("request_id")
    public String request_id;


    public void setRespResult(ResponseResult respResult) {
        this.respResult = respResult;
    }

    public ResponseResult getRespResult() {
        return this.respResult;
    }


    /**
     * 返回记录结果列表
     *
     * @author top auto create
     * @since 1.0, null
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result extends TaobaoObject {

        private static final long serialVersionUID = 8236546376227249367L;

        /**
         * 类目信息
         */
        // @ApiListField("categories")
        @JsonProperty("categories")
        private CategoryInfo categories;
        /**
         * 返回结果数量
         */
        @JsonProperty("total_result_count")
        private Long totalResultCount;

        public Long getTotalResultCount() {
            return this.totalResultCount;
        }

        public void setTotalResultCount(Long totalResultCount) {
            this.totalResultCount = totalResultCount;
        }

        public List<Category> getCategories() {
            return categories.category;
        }

        public void setCategories(CategoryInfo categories) {
            this.categories = categories;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CategoryInfo {
        public List<Category> category;
    }

    /**
     * 返回结果
     *
     * @author top auto create
     * @since 1.0, null
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseResult extends TaobaoObject {

        private static final long serialVersionUID = 1684941384183356267L;

        /**
         * 返回状态码
         */
        @JsonProperty("resp_code")
        private Long respCode;
        /**
         * 返回状态描述
         */
        @JsonProperty("resp_msg")
        private String respMsg;
        /**
         * 返回记录结果列表
         */
        @JsonProperty("result")
        private Result result;


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

        public Result getResult() {
            return this.result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

    }


}
