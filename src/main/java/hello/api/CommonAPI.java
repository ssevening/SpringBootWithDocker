package hello.api;


import java.util.HashMap;

public class CommonAPI {

    public static String[] getPromotionProductBySmartMatch = new String[]{"param2", "1", "portals.open",
            "api.getPromotionProductBySmartMatch"};

    public static String[] listFeaturedList = new String[]{"param2", "1", "portals.open",
            "api.listFeaturedList"};

    public static String[] listFeaturedProducts = new String[]{"param2", "1", "portals.open",
            "api.listProductbyFeaturedList"};




    public static String[] getPromotionProductDetail = new String[]{"param2", "2", "portals.open",
            "api.getPromotionProductDetail"};


    public static HashMap<String, String> apiMap = new HashMap<String, String>();

    public static String APP_KEY = "63414";
    public static String APP_SK = "m6i2F6e";

    static {

        apiMap.put("listPromotionProductApi", "http://gw.api.alibaba.com/openapi/param2/2/portals.open/api.listPromotionProduct/" + APP_KEY);
        apiMap.put("productDetailApi", "http://gw.api.alibaba.com/openapi/param2/2/portals.open/api.getPromotionProductDetail/" + APP_KEY);
        apiMap.put("promotionLinksApi", "http://gw.api.alibaba.com/openapi/param2/2/portals.open/api.getPromotionLinks/" + APP_KEY);
        apiMap.put("listHotProducts", "http://gw.api.alibaba.com/openapi/param2/2/portals.open/api.listHotProducts/" + APP_KEY);
        apiMap.put("listProductbyFeaturedList", "http://gw.api.alibaba.com/openapi/param2/1/portals.open/api.listProductbyFeaturedList/" + APP_KEY);


        apiMap.put("homeApi", "http://csuperdeals.oss-us-west-1.aliyuncs.com/Index_92.txt");
        apiMap.put("featuredApi", "http://csuperdeals.oss-us-west-1.aliyuncs.com/featured_92.txt");
        apiMap.put("updateApi", "http://csuperdeals.oss-us-west-1.aliyuncs.com/update.txt");
        apiMap.put("hotWishUrl", "http://activity.dealfuns.com/hotwish/1/Index.asp");
        apiMap.put("trackingId", "mogoair");

        apiMap.put("bestSellingUrl", "");
        apiMap.put("couponUrl", "");
        apiMap.put("superDealsUrl", "");
        apiMap.put("promotionType", "Under_$5");


    }


}
