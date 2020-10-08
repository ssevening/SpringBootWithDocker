package hello;

import hello.api.AFFProductDetailGetAPI;
import hello.api.FeaturedPromoProductGetAPI;
import hello.dao.ProductRepository;
import hello.dao.UserRepository;
import hello.dao.pojo.Notice;
import hello.pojo.AliexpressAffiliateFeaturedpromoProductsGetResponse;
import hello.pojo.AliexpressAffiliateProductdetailGetResponse;
import hello.pojo.Product;
import hello.service.BannerService;
import hello.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// 这个就是专门用来做webservice使用的，直接输入JSON结果，用来做服务端的接口交互使用。

@Controller
public class ProductController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;


    @Autowired
    private BannerService bannerService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


    @RequestMapping("/greetingall")
    public String greetingall(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "greetingall";
    }

    @RequestMapping("/Index.html")
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("bannerList", bannerService.findAll());
        buildFeaturedProducts(model, "New Arrival", "newArrivalProducts");
        buildFeaturedProducts(model, "Hot Product", "hotProducts");

        return "index";
    }


    private void buildFeaturedProducts(Model model, String promotionName, String result) {
        HashMap<String, String> newArrivalMap = new HashMap<>();
        newArrivalMap.put("target_language", "en");
        newArrivalMap.put("target_currency", "usd");
        newArrivalMap.put("page_no", "0");
        newArrivalMap.put("promotion_name", promotionName);

        FeaturedPromoProductGetAPI featuredPromoProductGetAPI = new FeaturedPromoProductGetAPI();
        featuredPromoProductGetAPI.setIsPostRequest(true);
        featuredPromoProductGetAPI.setNeedAopSignature();
        newArrivalMap.put("method", "aliexpress.affiliate.featuredpromo.products.get");
        featuredPromoProductGetAPI.setParamMap(newArrivalMap);
        try {
            String response = featuredPromoProductGetAPI.request();
            AliexpressAffiliateFeaturedpromoProductsGetResponse aliexpressAffiliateFeaturedpromoProductsGetResponse = FeaturedPromoProductGetAPI.getResult(response);
            model.addAttribute(result, aliexpressAffiliateFeaturedpromoProductsGetResponse.getRespResult().getResult().products.product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/queryById")
    public String queryById(@RequestParam Integer userId, Model model) {
        //model.addAttribute("uuu", userRepository.findOne(new Long(userId)));
        return "queryById";
    }


    @RequestMapping("/getProduct")
    public String getProduct(@RequestParam String productId, Model model) {
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
            Product product = response.getRespResult().getResult().products.product.get(0);
            model.addAttribute("product", product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "getProduct";
    }


    @RequestMapping("/queryByFirstName")
    public String queryByFirstName(@RequestParam String firstName, Model model) {
        model.addAttribute("users", userRepository.findByFirstNameEndsWith(firstName));
        return "queryByFirstName";
    }


    @RequestMapping("/findByEmail")
    public String findByEmail(@RequestParam String email, Model model) {
        model.addAttribute("user", userRepository.findByEmailAddress(email));
        return "findByEmail";
    }

    @RequestMapping("/product.html")
    public String findProductByProductId(@RequestParam String productId, Model model) {
        Map<String, String> productParamsMap = new HashMap<>();
        AFFProductDetailGetAPI affProductDetailGetAPI = new AFFProductDetailGetAPI();
        affProductDetailGetAPI.setNeedAopSignature();
        affProductDetailGetAPI.setIsPostRequest(true);
        productParamsMap.put("method", "aliexpress.affiliate.productdetail.get");
        productParamsMap.put("product_ids", productId);
        String localCurrency = "USD";
        String language = "en";
        productParamsMap.put("target_currency", localCurrency);
        productParamsMap.put("target_language", language);
        productParamsMap.put("fields", "productId,productTitle,productUrl,imageUrl,originalPrice,salePrice,discount,evaluateScore,30daysCommission,volume,packageType,lotNum,validTime,storeName,storeUrl,allImageUrls");

        affProductDetailGetAPI.setParamMap(productParamsMap);

        affProductDetailGetAPI.setParamMap(productParamsMap);
        try {
            String response = affProductDetailGetAPI.request();

            AliexpressAffiliateProductdetailGetResponse productdetailGetResponse = AFFProductDetailGetAPI.getResult(response);
            if (productdetailGetResponse != null && productdetailGetResponse.getRespResult() != null && productdetailGetResponse.getRespResult().getRespCode() == 200) {
                model.addAttribute("product", productdetailGetResponse.getRespResult().getResult().products.product.get(0));
                return "product";
            } else {
                Notice notice = new Notice();
                notice.message = "Product not found.";
                model.addAttribute("message", notice);
                return "notice";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    @RequestMapping("/search")
    public String queryProductDetailBySearch(@RequestParam String keywords, @RequestParam int page, @RequestParam int size, Model model) {
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        PageRequest pageRequest = new PageRequest(page, size, sort);
//        model.addAttribute("productList", productService.findByProductTitleContains(keywords, pageRequest));
        return "search";
    }


}