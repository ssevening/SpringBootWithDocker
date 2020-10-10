package hello.api;


import com.fasterxml.jackson.databind.JsonNode;
import hello.pojo.AliexpressAffiliateProductQueryResponse;
import hello.utils.JsonMapper;

import java.io.IOException;


public class AFFProductQueryAPI extends AFFBaseAPI {

    public AFFProductQueryAPI() {

    }


    public static String responseString = "aliexpress_affiliate_product_query_response";

    public static AliexpressAffiliateProductQueryResponse getResult(String response) throws IOException {
        JsonNode jsonNode = JsonMapper.json2node(response).get(responseString);
        AliexpressAffiliateProductQueryResponse result = JsonMapper.node2pojo(jsonNode, AliexpressAffiliateProductQueryResponse.class);

        return result;
    }

}
