package hello.sitemap;


import hello.pojo.Product;
import hello.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class SiteMapIndexUtils {
    public final static String BEGIN_DOC = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n<sitemapindex xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\r\n";
    public final static String END_DOC = "</sitemapindex>";
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

    public String getProductSiteMapIndex(int indexNo) {
        StringBuffer sb = new StringBuffer();
        sb.append(BEGIN_DOC);


        for (int i = (indexNo - 1) * 200; i < indexNo * 200; i++) {
            sb.append(new SiteMapIndex("https://www.dealfuns.com/product/" + i + "/sitemap.xml", Calendar.getInstance().getTime()));
        }

        sb.append(END_DOC);
        return sb.toString();
    }
}

