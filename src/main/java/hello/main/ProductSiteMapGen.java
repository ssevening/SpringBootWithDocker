package hello.main;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import hello.api.AFFProductQueryAPI;
import hello.dao.pojo.KeywordsInfo;
import hello.pojo.AliexpressAffiliateProductQueryResponse;
import hello.pojo.Product;
import hello.sitemap.SiteMapUtils;
import hello.sitemap.Sitemap;
import hello.utils.FileUtils;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProductSiteMapGen {

    public static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";


    public static String SITEMAP_ROOT_PATH = "/Users/panchenxing/map/";

    public static void main(String[] args) {
        ProductSiteMapGen siteMapGen = new ProductSiteMapGen();
        siteMapGen.createSiteMap(SITEMAP_ROOT_PATH);
    }

    /**
     * 动态生成sitemap
     * 目录，web根目录，就是你的站点首页 index.html所在同级目录
     *
     * @author sun
     * 2018年3月31日 下午8:02:47
     */
    public void createSiteMap(String realPath) {
        try {
            System.out.println(realPath);
            long start = System.currentTimeMillis();
            String WEBSITE = "http://www.dealfuns.com";
            /** 存储位置,实际应用项目中，需要指到网站的根目录 request.getSession().getServletContext().getRealPath("/") **/


            List<String> siteMapList = new ArrayList<>();

            /** 查询每个分类下对应的文章 **/

            List<String> aList = FileUtils.readXmlFiles("/Users/panchenxing/map/src_sitemap/");
            if (aList.size() > 0) {
                //start ======================================================================
                String path = realPath + "/sitemap/" + "product";
                fileExists(path);//判断文件夹是否存在，不存在则创建
                WebSitemapGenerator sitemapGenerator = WebSitemapGenerator.builder(WEBSITE, new File(path)).maxUrls(2000).gzip(true).build();
                for (String productUrl : aList) { //遍历取出来的文章
                    WebSitemapUrl sitemapUrl = new WebSitemapUrl.Options(productUrl).lastMod(formatDate(Calendar.getInstance().getTime(), DATE_FORMAT)).priority(0.8).changeFreq(ChangeFreq.MONTHLY).build();
                    sitemapGenerator.addUrl(sitemapUrl);
                }
                sitemapGenerator.write();
                // end ======================================================================
            }


            //TODO
            //其实分类 - list 页 url也应该存一个sitemap，看自己吧，如果也想告诉搜索引擎，我的list列表页也很重要，那么就做

            /** 生成主索引文件 **/
            if (siteMapList != null && siteMapList.size() > 0) {
                Collection<Sitemap> s = new ArrayList();
                for (String url : siteMapList) {
                    s.add(new Sitemap(url));
                }
                Writer wt = new PrintWriter(new File(realPath + "/product_sitemap_index.xml"));
                SiteMapUtils.writeSitemapIndex(wt, s.iterator());
                wt.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("生成Sitemap完毕, 共耗时：" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static Random random = new Random(System.currentTimeMillis());

    /**
     * 模拟数据库查询 - 查询符合条件的文章
     *
     * @return
     * @author sun
     */
    public List<Product> findProductsByKeywords(KeywordsInfo keywords) {
        System.out.println("查询文章分类id为: " + keywords.getId() + " keywords:" + keywords.getKeywords() + " 的所有符合条件的文章");
        List<Product> aList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
//            try {
//                // Thread.sleep(random.nextInt(10) * 100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }/Users/panchenxing/us_aliexpress.csv
            try {
                AliexpressAffiliateProductQueryResponse affiliateProductQueryResponse = AFFProductQueryAPI.getFromNet(keywords.getKeywords(), i + "", "");
                aList.addAll(AFFProductQueryAPI.getFromNet(keywords.getKeywords(), i + "", "").getRespResult().getResult().getProducts());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return aList;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     * @author sun
     * @date 2018年3月31日 下午8:05:30
     */
    public static String formatDate(Date date, String pattern) {
        if (pattern == null && pattern.equals("")) {
            pattern = DATE_FORMAT;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 判断文件目录是否存在 如果不存在，则创建新的文件夹
     *
     * @param path
     * @author sun
     * @date 2018年3月31日 下午7:58:24
     */
    public static void fileExists(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

