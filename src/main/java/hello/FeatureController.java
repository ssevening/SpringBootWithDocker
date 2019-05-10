package hello;

import hello.api.CommonAPI;
import hello.api.ListFeaturedProductsAPI;
import hello.dao.ProductRepository;
import hello.dao.UserRepository;
import hello.dao.pojo.*;
import hello.service.FeatureService;
import hello.service.ProductService;
import hello.utils.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;


// 用来展示Feaature的产品列表使用

@Controller
public class FeatureController {


    @Autowired
    private FeatureService featureService;


    @RequestMapping("/listFeaturedProducts")
    public String listFeaturedProducts(@RequestParam String promotionType, @RequestParam String pageNum, Model model) {

        FeatureInfo featureInfo = featureService.findFeatureInfoByFeaturedName(promotionType);

        if (featureInfo != null) {
            model.addAttribute("featureInfo", featureInfo);
            HashMap<String, String> paramMap = new HashMap<>();
            paramMap.put("language", "en");
            paramMap.put("localCurrency", "usd");
            paramMap.put("pageNum", pageNum);
            paramMap.put("promotionType", promotionType);
            ListFeaturedProductsAPI listFeaturedProductsAPI = new ListFeaturedProductsAPI(CommonAPI.listFeaturedProducts);
            listFeaturedProductsAPI.setParamMap(paramMap);
            try {
                String result = listFeaturedProductsAPI.request();
                FeaturedProductsResult featuredProductsResult = JsonMapper.json2pojo(result, FeaturedProductsResult.class);
                if (featuredProductsResult != null && featuredProductsResult.result != null && featuredProductsResult.result.result != null) {
                    /**
                     * public List<ProductDetail> products;
                     *         public int totalResults;
                     */
                    model.addAttribute("featuredProductsList", featuredProductsResult.result.result);
                } else {
                    Notice notice = new Notice();
                    notice.message = "No products found.";
                    model.addAttribute("message", notice);
                    return "notice";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Notice notice = new Notice();
            notice.message = "Page Not Found.";
            model.addAttribute("message", notice);
            return "notice";
        }

        return "featuredProducts";
    }


}