package hello.api;



import com.fasterxml.jackson.databind.JsonNode;
import hello.pojo.AliexpressAffiliateCategoryGetResponse;
import hello.utils.JsonMapper;

import java.io.IOException;
import java.util.Map;


public class GetCategoryAPI extends AFFBaseAPI {



    public GetCategoryAPI() {

    }

    public static String responseString = "aliexpress_affiliate_category_get_response";
    public static AliexpressAffiliateCategoryGetResponse getResult(String response) throws IOException {
        JsonNode jsonNode = JsonMapper.json2node(response).get(responseString);
        AliexpressAffiliateCategoryGetResponse result = JsonMapper.node2pojo(jsonNode, AliexpressAffiliateCategoryGetResponse.class);

        return result;
    }

}
