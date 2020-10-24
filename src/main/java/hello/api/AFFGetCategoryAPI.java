package hello.api;


import com.fasterxml.jackson.databind.JsonNode;
import hello.pojo.AliexpressAffiliateCategoryGetResponse;
import hello.utils.JsonMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AFFGetCategoryAPI extends AFFBaseAPI {


    public AFFGetCategoryAPI() {

    }

    public static String responseString = "aliexpress_affiliate_category_get_response";

    public static AliexpressAffiliateCategoryGetResponse getResult(String response) throws IOException {
        JsonNode jsonNode = JsonMapper.json2node(response).get(responseString);
        AliexpressAffiliateCategoryGetResponse result = JsonMapper.node2pojo(jsonNode, AliexpressAffiliateCategoryGetResponse.class);
        return result;
    }

    public static void main(String[] args) {
        AFFGetCategoryAPI affGetCategoryAPI = new AFFGetCategoryAPI();
        affGetCategoryAPI.setIsPostRequest(true);
        affGetCategoryAPI.setNeedAopSignature();
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("method", "aliexpress.affiliate.category.get");
        affGetCategoryAPI.setParamMap(paramMap);
        System.out.println(affGetCategoryAPI.getFinalRequestUrl());
    }

    public static Map<String, Object> getFromNet() {
        Map<String, Object> resultMap = new HashMap<>();
        AFFGetCategoryAPI affGetCategoryAPI = new AFFGetCategoryAPI();
        affGetCategoryAPI.setIsPostRequest(true);
        affGetCategoryAPI.setNeedAopSignature();
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("method", "aliexpress.affiliate.category.get");
        affGetCategoryAPI.setParamMap(paramMap);
        resultMap.put("api", affGetCategoryAPI);

        try {
            String response = affGetCategoryAPI.request();
            AliexpressAffiliateCategoryGetResponse aliexpressAffiliateCategoryGetResponse = getResult(response);
            resultMap.put("result", aliexpressAffiliateCategoryGetResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;

    }

}
