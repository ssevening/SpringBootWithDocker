package hello;


import hello.api.*;
import hello.dao.pojo.MonitorInfo;
import hello.pojo.AliexpressAffiliateFeaturedpromoProductsGetResponse;
import hello.pojo.AliexpressAffiliateProductQueryResponse;
import hello.pojo.AliexpressAffiliateProductSmartmatchResponse;
import hello.pojo.AliexpressAffiliateProductdetailGetResponse;
import hello.service.MonitorService;
import hello.utils.MainUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;


/**
 * // 每天早八点到晚八点，间隔2分钟执行任务
 *
 * @Scheduled(cron="0 0/2 8-20 * * ?")
 * // 每天早八点到晚八点，间隔3分钟执行任务
 * @Scheduled(cron="0 0/3 8-20 * * ?")
 * // 每天早八点到晚八点，间隔1分钟执行任务
 * @Scheduled(cron="0 0/1 8-20 * * ?")
 */
@Component
public class ScheduledTest {

    @Autowired
    private MonitorService monitorService;
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTest.class);

    // 每天早八点到晚八点，间隔10分钟执行任务
    @Scheduled(cron = "0 0/10 8-20 * * ?")
    public void executeUploadTask() {
        List<MonitorInfo> monitorInfoList = monitorService.findAll();
        if (monitorInfoList != null && !monitorInfoList.isEmpty()) {
            for (int i = 0; i < monitorInfoList.size(); i++) {
                MonitorInfo monitorInfo = monitorInfoList.get(i);
                String result = getDataFromInternet(monitorInfo.getUrl());
                if (result != null && result.contains(monitorInfo.getMonitorField())) {
                    // 正常
                    logger.debug(monitorInfo.getTitle() + monitorInfo.getUrl() + "正常!");
                } else {
                    MainUtils.sendmail(monitorInfo.getMailTo(), monitorInfo.getTitle() + "异常", monitorInfo.getUrl() + "访问异常！\r\n返回结果：" + result);
                }
            }
        }
    }


    // 每天早八点到晚八点，间隔2分钟执行任务
    @Scheduled(cron = "0 0/10 8-20 * * ?")
    public void executeCheckAPIStatusTask() {
        Thread current = Thread.currentThread();
        checkFeaturedProductTask("New Arrival");
        checkProductDetailTask("4001266081726");
        checkSmartMatchTask();
        checkProductQuery();
    }

    /**
     * 检查产品 Detail API 的健壮性
     *
     * @param productId
     */
    private void checkProductDetailTask(String productId) {
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
            if (response.getRespResult().getRespCode() != 200) {
                MainUtils.sendmail("chenxing.pancx@alibaba-inc.com", paramMap.get("method"), affProductDetailGetAPI.getFinalRequestUrl() + "接口调用失败！");
            } else {
                logger.info(paramMap.get("method") + "task is ok!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查产品列表 API 的稳定性
     *
     * @param promotionName
     */
    private void checkFeaturedProductTask(String promotionName) {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("target_language", "en");
        paramMap.put("target_currency", "usd");
        paramMap.put("page_no", "0");
        paramMap.put("promotion_name", promotionName);

        FeaturedPromoProductGetAPI featuredPromoProductGetAPI = new FeaturedPromoProductGetAPI();
        featuredPromoProductGetAPI.setIsPostRequest(true);
        featuredPromoProductGetAPI.setNeedAopSignature();
        paramMap.put("method", "aliexpress.affiliate.featuredpromo.products.get");
        featuredPromoProductGetAPI.setParamMap(paramMap);
        try {
            String response = featuredPromoProductGetAPI.request();
            AliexpressAffiliateFeaturedpromoProductsGetResponse aliexpressAffiliateFeaturedpromoProductsGetResponse = FeaturedPromoProductGetAPI.getResult(response);
            if (aliexpressAffiliateFeaturedpromoProductsGetResponse.getRespResult().getRespCode() != 200) {
                MainUtils.sendmail("chenxing.pancx@alibaba-inc.com", paramMap.get("method"), featuredPromoProductGetAPI.getFinalRequestUrl() + "接口调用失败！\r\n返回结果:" + response);
            } else {
                logger.info(paramMap.get("method") + "task is ok!");
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    private void checkSmartMatchTask() {
        SmartMatchAPI smartMatchAPI = new SmartMatchAPI();
        smartMatchAPI.setNeedAopSignature();
        smartMatchAPI.setIsPostRequest(true);

        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("method", "aliexpress.affiliate.product.smartmatch");
        // Must have filed
        paramMap.put("device_id", "null");
        paramMap.put("tracking_id", AFFBaseAPI.TRACKING_ID);
        // can be empty
        paramMap.put("product_id", "4001266081726");
        paramMap.put("device", "{}");
        paramMap.put("site", "{}");
        paramMap.put("app", "{}");
        paramMap.put("user", "{}");
        paramMap.put("target_currency", "USD");
        paramMap.put("target_language", "en");
        smartMatchAPI.setParamMap(paramMap);
        try {
            String response = smartMatchAPI.request();
            AliexpressAffiliateProductSmartmatchResponse aliexpressAffiliateProductSmartmatchResponse = SmartMatchAPI.getResult(response);
            if (aliexpressAffiliateProductSmartmatchResponse.getRespResult().getRespCode() != 200) {
                MainUtils.sendmail("chenxing.pancx@alibaba-inc.com", paramMap.get("method"), smartMatchAPI.getFinalRequestUrl() + "接口调用失败！\r\n返回结果:" + response);
            } else {
                logger.info(paramMap.get("method") + "task is ok!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void checkProductQuery() {
        AFFProductQueryAPI affProductQueryAPI = new AFFProductQueryAPI();
        affProductQueryAPI.setIsPostRequest(true);
        affProductQueryAPI.setNeedAopSignature();
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("fields",
                "totalResults,lotNum,packageType,imageUrl,volume,productId,discount,validTime,originalPrice,productTitle,productUrl,salePrice,commission");
        paramMap.put("keywords", "dress");
        // 类目
        paramMap.put("category_ids", "");
        paramMap.put("min_sale_price", "");
        paramMap.put("max_sale_price", "");
        paramMap.put("page_no", "1");
        // orignalPriceUp, orignalPriceDown, sellerRateDown, commissionRateUp, commissionRateDown, volumeDown,
        // validTimeUp, validTimeDown
        paramMap.put("sort", "");
        paramMap.put("method", "aliexpress.affiliate.product.query");
        affProductQueryAPI.setParamMap(paramMap);
        try {
            String response = affProductQueryAPI.request();
            AliexpressAffiliateProductQueryResponse aliexpressAffiliateProductQueryResponse = AFFProductQueryAPI.getResult(response);
            if (aliexpressAffiliateProductQueryResponse.getRespResult().getRespCode() != 200) {
                MainUtils.sendmail("chenxing.pancx@alibaba-inc.com", paramMap.get("method"), affProductQueryAPI.getFinalRequestUrl() + "接口调用失败！\r\n返回结果:" + response);
            } else {
                logger.info(paramMap.get("method") + "task is ok!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成SEO文件
     */
    public void genSiteMap() {

        //文件目录
        Path rootLocation = Paths.get("seo");
        if (Files.notExists(rootLocation)) {
            try {
                Files.createDirectories(rootLocation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String getDataFromInternet(String path) {
        try {
            URL url = new URL(path.trim());
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if (200 == urlConnection.getResponseCode()) {
                //得到输入流
                InputStream is = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while (-1 != (len = is.read(buffer))) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                return baos.toString("utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
