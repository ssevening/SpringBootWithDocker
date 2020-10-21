package hello.api;


import com.fasterxml.jackson.databind.JsonNode;
import hello.pojo.AliexpressAffiliateHotproductDownloadResponse;
import hello.pojo.AliexpressAffiliateHotproductQueryResponse;
import hello.pojo.Product;
import hello.utils.JsonMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AFFHotProductDownloadAPI extends AFFBaseAPI {

    public AFFHotProductDownloadAPI() {

    }


    public static String responseString = "aliexpress_affiliate_hotproduct_download_response";

    public static AliexpressAffiliateHotproductDownloadResponse getResult(String response) throws IOException {
        JsonNode jsonNode = JsonMapper.json2node(response).get(responseString);
        AliexpressAffiliateHotproductDownloadResponse result = JsonMapper.node2pojo(jsonNode, AliexpressAffiliateHotproductDownloadResponse.class);

        return result;
    }


    public static Map<String, Object> getFromNet(Long categoryId, String pageNo) {
        Map<String, Object> resultMap = new HashMap<>();
        AliexpressAffiliateHotproductDownloadResponse aliexpressAffiliateHotproductDownloadResponse = null;
        AFFHotProductDownloadAPI affHotProductDownloadAPI = new AFFHotProductDownloadAPI();
        affHotProductDownloadAPI.setIsPostRequest(true);
        affHotProductDownloadAPI.setNeedAopSignature();
        HashMap<String, String> paramMap = new HashMap<String, String>();

        paramMap.put("method", "aliexpress.affiliate.hotproduct.download");
        paramMap.put("fields",
                "totalResults,lotNum,packageType,imageUrl,volume,productId,discount,validTime,originalPrice,productTitle,productUrl,salePrice,commission");
        // 类目
        paramMap.put("category_id", categoryId + "");
        affHotProductDownloadAPI.setParamMap(paramMap);
        paramMap.put("page_no", pageNo);
        try {
            String response = affHotProductDownloadAPI.request();
            aliexpressAffiliateHotproductDownloadResponse = getResult(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("api", affHotProductDownloadAPI);
        resultMap.put("result", aliexpressAffiliateHotproductDownloadResponse);
        return resultMap;
    }

    public static void main(String[] args) throws IOException {
        try {
            Map<String, Object> downloadResultMap = getFromNet(new Long(200000212), "5000");
            AliexpressAffiliateHotproductDownloadResponse aliexpressAffiliateHotproductDownloadResponse = (AliexpressAffiliateHotproductDownloadResponse) downloadResultMap.get("result");
            if (aliexpressAffiliateHotproductDownloadResponse != null && aliexpressAffiliateHotproductDownloadResponse.getRespResult() != null && aliexpressAffiliateHotproductDownloadResponse.getRespResult().getRespCode() == 200) {
                List<Product> productList = aliexpressAffiliateHotproductDownloadResponse.getRespResult().getResult().getProducts();
                System.out.println(JsonMapper.pojo2json(productList));
            } else {
                System.out.println("product empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
