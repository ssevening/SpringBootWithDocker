package hello;

import hello.api.BaseAPI;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;


// 用来展示Feaature的产品列表使用

@Controller
public class FeatureController {


    @Autowired
    private FeatureService featureService;

    @GetMapping(path = "/importFeature") // Map ONLY GET Requests
    public @ResponseBody
    String importFeature(Model model) {
        /**
         * public String featuredName;
         *     public String featuredDesc;
         *
         *     public long productNum;
         *
         *
         *     public String seoTitle;
         *     public String seoDescription;
         *     public String seoKeywords;
         */

        BaseAPI listFeaturedList = new BaseAPI(CommonAPI.listFeaturedList);

        try {
            String listFeaturedListStr = listFeaturedList.request();
            ListFeaturedListResult listFeaturedListResult = JsonMapper.json2pojo(listFeaturedListStr, ListFeaturedListResult.class);

            if (listFeaturedListResult != null
                    && listFeaturedListResult.result != null
                    && listFeaturedListResult.result.featuredList != null) {
                List<FeatureInfo> featuredList = listFeaturedListResult.result.featuredList;
                featureService.addFeatureInfo(featuredList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "200";
    }

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