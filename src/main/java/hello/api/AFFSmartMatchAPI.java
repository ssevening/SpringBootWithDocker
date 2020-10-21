package hello.api;


import com.fasterxml.jackson.databind.JsonNode;
import hello.pojo.AliexpressAffiliateProductSmartmatchResponse;
import hello.utils.JsonMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AFFSmartMatchAPI extends AFFBaseAPI {

    public static String responseString = "aliexpress_affiliate_product_smartmatch_response";


    public static AliexpressAffiliateProductSmartmatchResponse getResult(String response) throws IOException {
        JsonNode jsonNode = JsonMapper.json2node(response).get(responseString);
        AliexpressAffiliateProductSmartmatchResponse result = JsonMapper.node2pojo(jsonNode, AliexpressAffiliateProductSmartmatchResponse.class);

        return result;
    }

    public static Map<String, Object> getFromNet(String productId, int pageNo) {
        Map<String, Object> resultMap = new HashMap<>();
        AFFSmartMatchAPI smartMatchAPI = new AFFSmartMatchAPI();
        smartMatchAPI.setIsPostRequest(true);
        smartMatchAPI.setNeedAopSignature();
        HashMap<String, String> sparamMap = new HashMap<String, String>();
        sparamMap.put("method", "aliexpress.affiliate.product.smartmatch");
        // Must have filed
        sparamMap.put("device_id", "null");
        sparamMap.put("tracking_id", AFFBaseAPI.TRACKING_ID);
        // can be empty
        sparamMap.put("product_id", productId);
        sparamMap.put("device", "{}");
        sparamMap.put("site", "{}");
        sparamMap.put("app", "{}");
        sparamMap.put("user", "{}");
        sparamMap.put("target_currency", "USD");
        sparamMap.put("target_language", "en");
        sparamMap.put("page_no", pageNo + "");
        smartMatchAPI.setParamMap(sparamMap);
        String response = null;
        try {
            response = smartMatchAPI.request();
            AliexpressAffiliateProductSmartmatchResponse aliexpressAffiliateProductSmartmatchResponse = AFFSmartMatchAPI.getResult(response);
            resultMap.put("result", aliexpressAffiliateProductSmartmatchResponse);
            resultMap.put("api", smartMatchAPI);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;

    }
}
