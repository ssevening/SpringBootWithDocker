package hello.sitemap;


import hello.pojo.Product;
import hello.service.ProductService;
import hello.utils.AliYunOSSUtils;
import hello.utils.NetworkUtils;
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
        if (AliYunOSSUtils.isFileExists(getSitemapPath(pageNo), 7)) {
            return AliYunOSSUtils.readFileContent(getSitemapPath(pageNo));
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append(BEGIN_DOC);
            Page<Product> productPage = productService.getProductsByPageNo(pageNo, 500);
            List<Product> productList = productPage.getContent();
            int i = 0;
            for (Product product : productList) {
                sb.append(new SiteMap("https://www.dealfuns.com" + product.getDealFunWebUrl(), Calendar.getInstance().getTime(), CHANGEFREQ_WEEKLY, "0.8"));
            }
            sb.append(END_DOC);
            AliYunOSSUtils.uploadString(getSitemapPath(pageNo), sb.toString(), true);
            return sb.toString();
        }
    }

    private String getSitemapPath(int pageNo) {
        return "SiteMap" + File.separator + pageNo + ".txt";
    }


    public static void main(String[] args) {
        try {
            for (int i = 328; i < 500; i++) {
                System.out.println(i);
                String res = NetworkUtils.doGet("https://www.dealfuns.com/product/" + i + "/sitemap.xml");
                System.out.println(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

