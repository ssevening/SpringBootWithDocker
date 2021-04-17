package hello;


import hello.api.*;
import hello.dao.pojo.MonitorInfo;
import hello.pojo.*;
import hello.service.CategoryService;
import hello.service.MonitorService;
import hello.service.ProductService;
import hello.sitemap.SiteMapUtils;
import hello.utils.JsonMapper;
import hello.utils.MailUtils;

import hello.utils.NetworkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    // 每天早八点到晚八点，间隔10分钟执行任务
    // @Scheduled(cron = "0 0/10 8-20 * * ?")
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
                    MailUtils.sendmail(new String[]{monitorInfo.getMailTo()}, monitorInfo.getTitle() + "异常", monitorInfo.getUrl() + "访问异常！\r\n返回结果：" + result);
                }
            }
        }
    }

    @Scheduled(cron = "0 15 10 ? * FRI")

    // 每天10:15运行 0 15 10 ? * *
    // @Scheduled(cron = "0 55 15 ? * *")
    public void buildSiteMap() {
        logger.debug("start buildSiteMap.....");
        try {
            for (int i = 328; i < 500; i++) {
                System.out.println("buildSiteMap index:" + i);
                String res = NetworkUtils.doGet("https://www.dealfuns.com/product/" + i + "/sitemap.xml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("finished buildSiteMap.....");
        MailUtils.sendmail(new String[]{"282867908@qq.com"}, "finished build sitemap.", "buildSiteMap Success!!!");
    }

    // 每天晚上 10:00触发
    // @Scheduled(cron = "0 0 22 * * ?")
    // 每周一上午10点15分执行任务
    @Scheduled(cron = "0 15 10 ? * MON")

    // 每天10:15运行 0 15 10 ? * *
    // @Scheduled(cron = "0 55 15 ? * *")
    public void executeGetProductData() {
        logger.debug("start executeGetProductData.....");
        try {
            Map<String, Object> resultMap = AFFGetCategoryAPI.getFromNet();
            AliexpressAffiliateCategoryGetResponse response = (AliexpressAffiliateCategoryGetResponse) resultMap.get("result");
            List<Category> categoryList = response.getRespResult().getResult().getCategories();
            int totalCount = 0;
            for (int i = 0; i < categoryList.size(); i++) {
                Category category = categoryList.get(i);
                logger.debug(category.getCategoryName() + " data is getting.....");
                for (int j = 0; j <= 500; j++) {
                    Map<String, Object> downloadResultMap = AFFHotProductDownloadAPI.getFromNet(category.getCategoryId(), j + "");
                    AliexpressAffiliateHotproductDownloadResponse downloadResponse = (AliexpressAffiliateHotproductDownloadResponse) downloadResultMap.get("result");
                    if (downloadResponse.getRespResult() != null && downloadResponse.getRespResult().getResult() != null && downloadResponse.getRespResult().getRespCode() == 200) {
                        List<Product> productList = downloadResponse.getRespResult().getResult().getProducts();
                        if (productList != null && productList.size() > 0) {
                            for (Product product : productList) {
                                productService.save(product);
                                totalCount++;
                            }
                            logger.debug("now the total count is " + totalCount);
                        }
                    } else {
                        logger.debug(category.getCategoryName() + " get product list page:" + j + " is null");
                        break;
                    }
                }
                logger.debug("finished" + category.getCategoryName() + "product data.");
            }
            logger.debug("Total saved product count:" + totalCount);
            MailUtils.sendmail(new String[]{"282867908@qq.com"}, "getProductData detail log info", "executeGetProductData Success!!!" + totalCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 每天早八点到晚八点，间隔2分钟执行任务
    // @Scheduled(cron = "0 0/10 8-20 * * ?")
    public void executeFlushCategoryData() {
        try {
            Map<String, Object> resultMap = AFFGetCategoryAPI.getFromNet();
            AliexpressAffiliateCategoryGetResponse response = (AliexpressAffiliateCategoryGetResponse) resultMap.get("result");
            List<Category> categoryList = response.getRespResult().getResult().getCategories();
            for (int i = 0; i < categoryList.size(); i++) {
                Category category = categoryList.get(i);
                Map<String, Object> downloadResultMap = AFFHotProductDownloadAPI.getFromNet(category.getCategoryId(), "1");
                AliexpressAffiliateHotproductDownloadResponse downloadResponse = (AliexpressAffiliateHotproductDownloadResponse) downloadResultMap.get("result");
                if (downloadResponse.getRespResult() != null && downloadResponse.getRespResult().getResult() != null && downloadResponse.getRespResult().getRespCode() == 200) {
                    List<Product> productList = downloadResponse.getRespResult().getResult().getProducts();
                    if (productList != null && productList.size() >= 2) {
                        category.setFirstImageUrl(productList.get(0).getProductMainImageUrl());
                        category.setSecImageUrl(productList.get(1).getProductMainImageUrl());
                        categoryService.save(category);
                    } else {
                        categoryService.save(category);
                    }
                }
            }
            logger.debug("Category data flush finished!");
        } catch (Exception e) {
            logger.debug("Category data flush error!");
            e.printStackTrace();
        }
    }

    // 每天早1到晚23点，间隔5分钟执行任务
    @Scheduled(cron = "0 0/10 1-23 * * ?")
    public void executeCheckAPIStatusTask() {
        checkFeaturedProductTask("New Arrival");
        checkProductDetailTask("4001266081726");
        checkSmartMatchTask();
        checkProductQuery();
        // checkHotProductQuery();
        // checkHotProductDownload();
    }

    //每天上午08:15触发
    @Scheduled(cron = "0 15 08 ? * *")
    public void executeCheckServerStatusTask() {
        checkServerStatus();
    }

    private void checkServerStatus() {
        MailUtils.sendmail(new String[]{"chenxing.pancx@alibaba-inc.com"}, "dealfuns.com 服务器处于启动中...", "https://www.dealfuns.com \r\n 网站可以正常访问!");
    }

    private void checkHotProductQuery() {
        Map<String, Object> resultMap = AFFHotProductQueryAPI.getFromNet("mp3", "1", "");
        AFFHotProductQueryAPI api = (AFFHotProductQueryAPI) resultMap.get("api");
        AliexpressAffiliateHotproductQueryResponse result = (AliexpressAffiliateHotproductQueryResponse) resultMap.get("result");
        if (result.getRespResult() != null && result.getRespResult().getRespCode() != 200) {
            try {
                MailUtils.sendmail(aliMailToArray, api.getParamMap().get("method"), api.getFinalRequestUrl() + "\r\n接口调用失败！\r\n 返回结果:\r\n" + JsonMapper.pojo2json(result));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.info(api.getParamMap().get("method") + " task is Ok!");
        }
    }

    private void checkHotProductDownload() {
        try {
            Map<String, Object> resultMap = AFFHotProductDownloadAPI.getFromNet(new Long(100005529), "1");
            AFFHotProductDownloadAPI api = (AFFHotProductDownloadAPI) resultMap.get("api");
            AliexpressAffiliateHotproductDownloadResponse result = (AliexpressAffiliateHotproductDownloadResponse) resultMap.get("result");
            if (result.getRespResult().getRespCode() != 200) {
                try {
                    MailUtils.sendmail(aliMailToArray, api.getParamMap().get("method"), api.getFinalRequestUrl() + "\r\n接口调用失败！\r\n 返回结果:\r\n" + JsonMapper.pojo2json(result));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                logger.info(api.getParamMap().get("method") + " task is Ok!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                MailUtils.sendmail(aliMailToArray, paramMap.get("method"), affProductDetailGetAPI.getFinalRequestUrl() + "\r\n接口调用失败！\r\n 返回结果:\r\n" + response);
            } else {
                logger.info(paramMap.get("method") + " task is Ok!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] aliMailToArray = new String[]{"chenxing.pancx@alibaba-inc.com", "ruixi.ls@alibaba-inc.com"};

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

        AFFFeaturedPromoProductGetAPI featuredPromoProductGetAPI = new AFFFeaturedPromoProductGetAPI();
        featuredPromoProductGetAPI.setIsPostRequest(true);
        featuredPromoProductGetAPI.setNeedAopSignature();
        paramMap.put("method", "aliexpress.affiliate.featuredpromo.products.get");
        featuredPromoProductGetAPI.setParamMap(paramMap);
        try {
            String response = featuredPromoProductGetAPI.request();
            AliexpressAffiliateFeaturedpromoProductsGetResponse aliexpressAffiliateFeaturedpromoProductsGetResponse = AFFFeaturedPromoProductGetAPI.getResult(response);
            if (aliexpressAffiliateFeaturedpromoProductsGetResponse.getRespResult().getRespCode() != 200) {
                MailUtils.sendmail(aliMailToArray, paramMap.get("method"), featuredPromoProductGetAPI.getFinalRequestUrl() + "\r\n接口调用失败！\r\n 返回结果:\r\n" + response);
            } else {
                logger.info(paramMap.get("method") + " task is Ok!");
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    private void checkSmartMatchTask() {
        AFFSmartMatchAPI smartMatchAPI = new AFFSmartMatchAPI();
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
            AliexpressAffiliateProductSmartmatchResponse aliexpressAffiliateProductSmartmatchResponse = AFFSmartMatchAPI.getResult(response);
            if (aliexpressAffiliateProductSmartmatchResponse.getRespResult().getRespCode() != 200) {
                MailUtils.sendmail(aliMailToArray, paramMap.get("method"), smartMatchAPI.getFinalRequestUrl() + "\r\n接口调用失败！\r\n 返回结果:\r\n" + response);
            } else {
                logger.info(paramMap.get("method") + " task is Ok!");
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
                MailUtils.sendmail(aliMailToArray, paramMap.get("method"), affProductQueryAPI.getFinalRequestUrl() + "\r\n接口调用失败！\r\n 返回结果:\r\n" + response);
            } else {
                logger.info(paramMap.get("method") + " task is Ok!");
            }

        } catch (Exception e) {
            e.printStackTrace();
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
