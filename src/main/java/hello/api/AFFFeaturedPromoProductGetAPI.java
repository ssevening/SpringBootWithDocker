package hello.api;


import com.fasterxml.jackson.databind.JsonNode;
import hello.pojo.AliexpressAffiliateFeaturedpromoProductsGetResponse;
import hello.pojo.AliexpressAffiliateProductSmartmatchResponse;
import hello.utils.JsonMapper;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AFFFeaturedPromoProductGetAPI extends AFFBaseAPI {


    @Override
    public String getTopMethodName() {
        return "aliexpress.affiliate.featuredpromo.products.get";
    }

    public static String responseString = "aliexpress_affiliate_featuredpromo_products_get_response";

    public static AliexpressAffiliateFeaturedpromoProductsGetResponse getResult(String response) throws IOException {
        JsonNode jsonNode = JsonMapper.json2node(response).get(responseString);
        AliexpressAffiliateFeaturedpromoProductsGetResponse result = JsonMapper.node2pojo(jsonNode, AliexpressAffiliateFeaturedpromoProductsGetResponse.class);

        return result;
    }


    public static Map<String, Object> getFromNet(String promotionName, int pageNo) {
        Map<String, Object> resultMap = new HashMap<>();
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("target_language", "en");
        paramMap.put("target_currency", "usd");
        paramMap.put("page_no", "" + pageNo);
        paramMap.put("promotion_name", promotionName);

        AFFFeaturedPromoProductGetAPI featuredPromoProductGetAPI = new AFFFeaturedPromoProductGetAPI();
        featuredPromoProductGetAPI.setIsPostRequest(true);
        featuredPromoProductGetAPI.setNeedAopSignature();
        paramMap.put("method", "aliexpress.affiliate.featuredpromo.products.get");
        featuredPromoProductGetAPI.setParamMap(paramMap);
        String response = null;
        System.out.println(featuredPromoProductGetAPI.getFinalRequestUrl());
        try {
            response = featuredPromoProductGetAPI.request();
            AliexpressAffiliateFeaturedpromoProductsGetResponse aliexpressAffiliateFeaturedpromoProductsGetResponse = AFFFeaturedPromoProductGetAPI.getResult(response);
            resultMap.put("result", aliexpressAffiliateFeaturedpromoProductsGetResponse);
            resultMap.put("api", featuredPromoProductGetAPI);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    public static void main(String[] args) {
        getFromNet("New Arrival", 2);
    }

}
