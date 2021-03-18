package hello.api;


import com.fasterxml.jackson.databind.JsonNode;
import hello.pojo.AliexpressAffiliateHotproductQueryResponse;
import hello.pojo.AliexpressAffiliateProductQueryResponse;
import hello.utils.JsonMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AFFHotProductQueryAPI extends AFFBaseAPI {

    public AFFHotProductQueryAPI() {

    }

    public static String responseString = "aliexpress_affiliate_hotproduct_query_response";

    public static AliexpressAffiliateHotproductQueryResponse getResult(String response) throws IOException {
        JsonNode jsonNode = JsonMapper.json2node(response).get(responseString);
        AliexpressAffiliateHotproductQueryResponse result = JsonMapper.node2pojo(jsonNode, AliexpressAffiliateHotproductQueryResponse.class);

        return result;
    }


    public static Map<String, Object> getFromNet(String keywords, String pageNo, String sort) {
        Map<String, Object> resultMap = new HashMap<>();
        AliexpressAffiliateHotproductQueryResponse aliexpressAffiliateHotproductQueryResponse = null;
        AFFHotProductQueryAPI affHotProductQueryAPI = new AFFHotProductQueryAPI();
        affHotProductQueryAPI.setIsPostRequest(true);
        affHotProductQueryAPI.setNeedAopSignature();
        HashMap<String, String> paramMap = new HashMap<String, String>();

        paramMap.put("method", "aliexpress.affiliate.hotproduct.query");
        paramMap.put("fields",
                "totalResults,lotNum,packageType,imageUrl,volume,productId,discount,validTime,originalPrice,productTitle,productUrl,salePrice,commission");
        paramMap.put("keywords", keywords);
        // 类目
        paramMap.put("category_ids", "");
        paramMap.put("min_sale_price", "");
        paramMap.put("max_sale_price", "");
        affHotProductQueryAPI.setParamMap(paramMap);
        paramMap.put("sort", sort);
        paramMap.put("page_no", pageNo);
        try {
            String response = affHotProductQueryAPI.request();
            aliexpressAffiliateHotproductQueryResponse = getResult(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        resultMap.put("api", affHotProductQueryAPI);
        resultMap.put("result", aliexpressAffiliateHotproductQueryResponse);
        return resultMap;
    }

    public static void main(String[] args) {
        getFromNet("mp3", "1", "");
    }

}
