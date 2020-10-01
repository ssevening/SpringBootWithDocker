package hello.api;


import com.fasterxml.jackson.databind.JsonNode;
import hello.pojo.AliexpressAffiliateProductdetailGetResponse;
import hello.utils.JsonMapper;

import java.io.IOException;

public class AFFProductDetailGetAPI extends AFFBaseAPI {


    public static String responseString = "aliexpress_affiliate_productdetail_get_response";

    public static AliexpressAffiliateProductdetailGetResponse getResult(String response) throws IOException {
        JsonNode jsonNode = JsonMapper.json2node(response).get(responseString);
        AliexpressAffiliateProductdetailGetResponse result = JsonMapper.node2pojo(jsonNode, AliexpressAffiliateProductdetailGetResponse.class);
        return result;
    }
}
