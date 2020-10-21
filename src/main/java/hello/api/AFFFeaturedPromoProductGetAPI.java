package hello.api;


import com.fasterxml.jackson.databind.JsonNode;
import hello.pojo.AliexpressAffiliateFeaturedpromoProductsGetResponse;
import hello.utils.JsonMapper;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.HashMap;


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

}
