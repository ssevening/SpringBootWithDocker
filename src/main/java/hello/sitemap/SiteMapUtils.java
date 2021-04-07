package hello.sitemap;


import hello.pojo.Product;
import hello.service.ProductService;
import hello.utils.AliYunOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Calendar;
import java.util.List;

@Component
public class SiteMapUtils {

    public final static String BEGIN_DOC = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\r\n";
    public final static String END_DOC = "</urlset>";
    public final static String CHANGEFREQ_ALWAYS = "always";
    public final static String CHANGEFREQ_HOURLY = "hourly";
    public final static String CHANGEFREQ_DAILY = "daily";
    public final static String CHANGEFREQ_WEEKLY = "weekly";
    public final static String CHANGEFREQ_MONTHLY = "monthly";
    public final static String CHANGEFREQ_YEARLY = "yearly";
    public final static String CHANGEFREQ_NEVER = "never";

    // https://support.google.com/webmasters/answer/75712?hl=en

    @Autowired
    private ProductService productService;

    public String getProductSiteMap(int pageNo) {
        StringBuffer sb = new StringBuffer();
        sb.append(BEGIN_DOC);
//            Page<Product> productPage = productService.getProductsByPageNo(pageNo, 500);
//            List<Product> productList = productPage.getContent();
        List<Product> productList = productService.queryProductByPageNo(pageNo, 500);
        int i = 0;
        for (Product product : productList) {
            sb.append(new SiteMap("https://www.dealfuns.com" + product.getDealFunWebUrl(), Calendar.getInstance().getTime(), CHANGEFREQ_WEEKLY, "0.8"));
        }
        sb.append(END_DOC);

        return sb.toString();
    }




}

