package hello.api;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import hello.pojo.AliexpressAffiliateProductdetailGetResponse;
import hello.utils.JsonMapper;
import hello.utils.MailUtils;

import java.io.IOException;
import java.util.HashMap;

public class AFFProductDetailGetAPI extends AFFBaseAPI {


    public static String responseString = "aliexpress_affiliate_productdetail_get_response";

    public static AliexpressAffiliateProductdetailGetResponse getResult(String response) throws IOException {
        JsonNode jsonNode = JsonMapper.json2node(response).get(responseString);
        AliexpressAffiliateProductdetailGetResponse result = JsonMapper.node2pojo(jsonNode, AliexpressAffiliateProductdetailGetResponse.class);
        return result;
    }


    public static void main(String[] args) {
        String productId = "1005001794202792";
        AFFProductDetailGetAPI affProductDetailGetAPI = new AFFProductDetailGetAPI();
        affProductDetailGetAPI.setIsPostRequest(true);
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("method", "aliexpress.affiliate.productdetail.get");
        paramMap.put("product_ids", productId);
        paramMap.put("fields", "productId,productTitle,productUrl,imageUrl,originalPrice,salePrice,discount,evaluateScore,30daysCommission,volume,packageType,lotNum,validTime,storeName,storeUrl,allImageUrls");
        affProductDetailGetAPI.setParamMap(paramMap);
        try {
            String result = affProductDetailGetAPI.request();
            AliexpressAffiliateProductdetailGetResponse response = AFFProductDetailGetAPI.getResult(result);
            System.out.println(JSON.toJSON(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
