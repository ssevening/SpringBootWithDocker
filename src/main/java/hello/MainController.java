package hello;

import hello.api.CommonAPI;
import hello.dao.ProductRepository;
import hello.dao.UserRepository;
import hello.dao.pojo.ProductDetail;
import hello.dao.pojo.User;
import hello.utils.JsonMapper;
import hello.utils.WebPageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * // 这个用来做界面的。
 */
@Controller    // This means that this class is a Controller
@RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewUser(@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path = "/allProducts")
    public @ResponseBody
    Iterable<ProductDetail> getAllProducts() {
        // This returns a JSON or XML with the users
        return productRepository.findAll();
    }


    @GetMapping(path = "/addProduct") // Map ONLY GET Requests
    public @ResponseBody
    String addProduct(@RequestParam String productId, @RequestParam String productCategory) {
        String resultStr = "200";
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

//        ProductDetail oldProductDetail = productRepository.queryProductDetailByProductId(productId);
//
//        if (oldProductDetail == null) {
//            GetProductDetailAPI productApi = new GetProductDetailAPI(CommonAPI.getPromotionProductDetail);
//            HashMap<String, String> paramMap = new HashMap();
//            paramMap.put("productId", productId);
//            paramMap.put("localCurrency", "USD");
//            paramMap.put("language", "en");
//            paramMap.put("fields", "productId,productTitle,productUrl,imageUrl,originalPrice,salePrice,discount,evaluateScore,30daysCommission,volume,packageType,lotNum,validTime,storeName,storeUrl,allImageUrls");
//            productApi.isPostRequest = false;
//            productApi.needAopSignature = false;
//            productApi.setParamMap(paramMap);
//
//            try {
//                String result = productApi.request();
//                ProductDetailResult productDetailResult = JsonMapper.json2pojo(result, ProductDetailResult.class);
//                if (productDetailResult != null && productDetailResult.result != null) {
//                    ProductDetail p = productDetailResult.result;
//                    Map<String, String> map = WebPageUtils.getSEOInfo(MessageFormat.format("https://www.aliexpress.com/item/{0}.html", productId));
//                    p.keywords = map.get(WebPageUtils.KEYWORDS);
//                    p.description = map.get(WebPageUtils.DESC);
//                    p.productCategory = productCategory;
//                    productRepository.save(p);
//                } else {
//                    resultStr = "product is not aff product";
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                resultStr = e.getMessage();
//            }
//        } else {
//            resultStr = "product have already existed.";
//        }

        return resultStr;
    }


}
