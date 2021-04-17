package hello.utils;

import java.util.HashMap;

public class CouponUtils {
    // platform to icon
    public static HashMap<String, String> iconMap = new HashMap<>();

    static {
        iconMap.put("aliexpress.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/aliexpress.png");
        iconMap.put("amazon.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/amazon.png");
        iconMap.put("banggood.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/banggood.png");
        iconMap.put("boohoo.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/boohoo.png");
        iconMap.put("chegg.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/chegg.png");
        iconMap.put("ebay.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/ebay.png");
        iconMap.put("gearbest.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/gearbest.png");
        iconMap.put("groupon.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/groupon.png");
        iconMap.put("intuit.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/intuit.png");
        iconMap.put("nike.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/nike.png");
        iconMap.put("shein.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/shein.png");
        iconMap.put("sigmabeauty.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/sigma.png");
        iconMap.put("target.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/target.png");
        iconMap.put("torrid.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/torrid.png");
        iconMap.put("wish.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/wish.png");
        iconMap.put("zazzle.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/zazzle.png");
        iconMap.put("6pm.com", "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/6pm.png");
    }

    public static String getImageUrl(String platform) {
        return iconMap.get(platform);
    }


    public static HashMap<String, String> targetMap = new HashMap<>();

    static {
        targetMap.put("shein.com", "https://www.shein.com");
        targetMap.put("aliexpress.com", "https://www.aliexpress.com");
        targetMap.put("wish.com", "https://www.wish.com");
        targetMap.put("groupon.com", "https://www.groupon.com");
        targetMap.put("amazon.com", "https://www.amazon.com");
        targetMap.put("gearbest.com", "https://www.gearbest.com");
        targetMap.put("nike.com", "https://www.nike.com");
        targetMap.put("chegg.com", "https://www.chegg.com");
        targetMap.put("6pm.com", "https://www.6pm.com");
        targetMap.put("zazzle.com", "https://www.zazzle.com");
        targetMap.put("ebay.com", "https://www.ebay.com");
        targetMap.put("sigmabeauty.com", "https://www.sigmabeauty.com");
        targetMap.put("boohoo.com", "https://www.boohoo.com");
        targetMap.put("intuit.com", "https://www.intuit.com");
        targetMap.put("banggood.com", "https://www.banggood.com");
        targetMap.put("target.com", "https://www.target.com");
        targetMap.put("torrid.com", "https://www.torrid.com");
    }


    public static String getTargetUrlByPlatform(String platform) {
        String result = targetMap.get(platform);
        if (result != null) {
            return result;
        } else {
            return "https://" + platform;
        }
    }


    public static void main(String[] args) {
        for (String key : targetMap.keySet()) {
            try {
                System.out.println(key);
                String result = NetworkUtils.doGet("https://www.dealfuns.com/coupon/list?platform=" + key);
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
