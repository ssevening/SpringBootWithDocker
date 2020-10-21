package hello.api;


import com.fasterxml.jackson.databind.JsonNode;
import hello.pojo.AliexpressAffiliateProductQueryResponse;
import hello.utils.JsonMapper;

import java.io.IOException;
import java.util.HashMap;


public class AFFProductQueryAPI extends AFFBaseAPI {

    public AFFProductQueryAPI() {

    }


    public static String responseString = "aliexpress_affiliate_product_query_response";

    public static AliexpressAffiliateProductQueryResponse getResult(String response) throws IOException {
        JsonNode jsonNode = JsonMapper.json2node(response).get(responseString);
        AliexpressAffiliateProductQueryResponse result = JsonMapper.node2pojo(jsonNode, AliexpressAffiliateProductQueryResponse.class);

        return result;
    }


    public static AliexpressAffiliateProductQueryResponse getFromNet(String keywords, String pageNo, String sort) {
        AliexpressAffiliateProductQueryResponse aliexpressAffiliateProductQueryResponse = null;
        AFFProductQueryAPI affProductQueryAPI = new AFFProductQueryAPI();
        affProductQueryAPI.setIsPostRequest(true);
        affProductQueryAPI.setNeedAopSignature();
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("fields",
                "totalResults,lotNum,packageType,imageUrl,volume,productId,discount,validTime,originalPrice,productTitle,productUrl,salePrice,commission");
        paramMap.put("keywords", keywords);
        // 类目
        paramMap.put("category_ids", "");
        paramMap.put("min_sale_price", "");
        paramMap.put("max_sale_price", "");
        paramMap.put("page_no", pageNo);
        //SALE_PRICE_ASC, SALE_PRICE_DESC, LAST_VOLUME_ASC, LAST_VOLUME_DESC
        paramMap.put("sort", sort);
        paramMap.put("method", "aliexpress.affiliate.product.query");
        affProductQueryAPI.setParamMap(paramMap);
        // System.out.println(affProductQueryAPI.getFinalRequestUrl());
        try {
            String response = affProductQueryAPI.request();
            aliexpressAffiliateProductQueryResponse = getResult(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aliexpressAffiliateProductQueryResponse;
    }

    public static void main(String[] args) {
        getFromNet("dress", "", "");

    }

}
