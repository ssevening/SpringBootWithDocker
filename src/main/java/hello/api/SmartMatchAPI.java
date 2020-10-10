package hello.api;



import com.fasterxml.jackson.databind.JsonNode;
import hello.pojo.AliexpressAffiliateProductSmartmatchResponse;
import hello.utils.JsonMapper;

import java.io.IOException;

public class SmartMatchAPI extends AFFBaseAPI {

    public static String responseString = "aliexpress_affiliate_product_smartmatch_response";


    public static AliexpressAffiliateProductSmartmatchResponse getResult(String response) throws IOException {
        JsonNode jsonNode = JsonMapper.json2node(response).get(responseString);
        AliexpressAffiliateProductSmartmatchResponse result = JsonMapper.node2pojo(jsonNode, AliexpressAffiliateProductSmartmatchResponse.class);

        return result;
    }
}
