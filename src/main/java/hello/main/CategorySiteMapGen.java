package hello.main;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import hello.api.AFFGetCategoryAPI;
import hello.api.AFFProductQueryAPI;
import hello.dao.pojo.KeywordsInfo;
import hello.pojo.AliexpressAffiliateCategoryGetResponse;
import hello.pojo.AliexpressAffiliateProductQueryResponse;
import hello.pojo.Category;
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

public class CategorySiteMapGen {

    public static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static String SITEMAP_ROOT_PATH = "/Users/panchenxing/map/";

    public static void main(String[] args) {
        CategorySiteMapGen siteMapGen = new CategorySiteMapGen();
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
            Map<String, Object> map = AFFGetCategoryAPI.getFromNet();
            AliexpressAffiliateCategoryGetResponse aliexpressAffiliateCategoryGetResponse = (AliexpressAffiliateCategoryGetResponse) map.get("result");
            List<Category> categoryInfos = aliexpressAffiliateCategoryGetResponse.getRespResult().getResult().getCategories();
            if (categoryInfos.size() > 0) {
                //start ======================================================================
                String path = realPath + "/sitemap/" + "1";
                fileExists(path);//判断文件夹是否存在，不存在则创建
                WebSitemapGenerator sitemapGenerator = WebSitemapGenerator.builder(WEBSITE, new File(path)).gzip(false).build();

                String indexUrl = "http://www.dealfuns.com/";
                WebSitemapUrl indexSiteMapUrl = new WebSitemapUrl.Options(indexUrl).lastMod(formatDate(Calendar.getInstance().getTime(), DATE_FORMAT)).priority(1.0).changeFreq(ChangeFreq.DAILY).build();
                sitemapGenerator.addUrl(indexSiteMapUrl);

                String smartUrl = "http://www.dealfuns.com/smartProducts.html";
                WebSitemapUrl smartSiteMapUrl = new WebSitemapUrl.Options(smartUrl).lastMod(formatDate(Calendar.getInstance().getTime(), DATE_FORMAT)).priority(0.9).changeFreq(ChangeFreq.DAILY).build();
                sitemapGenerator.addUrl(smartSiteMapUrl);

                for (Category category : categoryInfos) { //遍历取出来的文章
                    String url = "http://www.dealfuns.com/categoryItem.html?categoryId=" + category.getCategoryId() + "&categoryName=" + category.getCategoryName();//文章详情页的url地址
                    WebSitemapUrl sitemapUrl = new WebSitemapUrl.Options(url).lastMod(formatDate(Calendar.getInstance().getTime(), DATE_FORMAT)).priority(0.9).changeFreq(ChangeFreq.WEEKLY).build();
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
                Writer wt = new PrintWriter(new File(realPath + "/category_sitemap.xml"));
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

