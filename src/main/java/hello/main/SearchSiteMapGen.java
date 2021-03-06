package hello.main;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import hello.api.AFFProductQueryAPI;
import hello.dao.pojo.KeywordsInfo;
import hello.pojo.AliexpressAffiliateProductQueryResponse;
import hello.pojo.Product;
import hello.utils.FileUtils;
import hello.utils.StringUtil;

public class SearchSiteMapGen {

    public static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static String KEYWORDS_PATH = "/Users/panchenxing/Downloads/k.txt";

    public static String SITEMAP_ROOT_PATH = "/Users/panchenxing/map/";

    public static void main(String[] args) {
        SearchSiteMapGen siteMapGen = new SearchSiteMapGen();
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
            String WEBSITE = "https://www.dealfuns.com";
            /** 存储位置,实际应用项目中，需要指到网站的根目录 request.getSession().getServletContext().getRealPath("/") **/

            List<KeywordsInfo> cList = findKeywordsInfoByFile();
            if (cList == null || cList.size() < 1) {
                return;
            }
            String path = realPath + "/sitemap/keywords";
            fileExists(path);//判断文件夹是否存在，不存在则创建
            WebSitemapGenerator sitemapGenerator = WebSitemapGenerator.builder(WEBSITE, new File(path)).gzip(false).build();
            for (KeywordsInfo keywordsInfo : cList) {
                //start ======================================================================https://127.0.0.1/list/sex.html
                String url = "https://www.dealfuns.com/list/" + StringUtil.formatStringInUrl(keywordsInfo.getKeywords()) + ".html";//文章详情页的url地址
                WebSitemapUrl sitemapUrl = new WebSitemapUrl.Options(url).lastMod(formatDate(Calendar.getInstance().getTime(), DATE_FORMAT)).priority(0.9).changeFreq(ChangeFreq.WEEKLY).build();
                sitemapGenerator.addUrl(sitemapUrl);
            }
            sitemapGenerator.write();

            long end = System.currentTimeMillis();
            System.out.println("生成Sitemap完毕, 共耗时：" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询分类信息
     */
    public List<KeywordsInfo> findKeywordsInfoByFile() {
        List<KeywordsInfo> keywordsInfos = new ArrayList<>();
        List<String> list = FileUtils.readFile(KEYWORDS_PATH);
        for (int i = 0; i < list.size(); i++) {
            KeywordsInfo keywordsInfo = new KeywordsInfo();
            keywordsInfo.setGmtCreate(Calendar.getInstance().getTime());
            keywordsInfo.setKeywords(list.get(i));
            keywordsInfo.setId(new Long(i));
            keywordsInfos.add(keywordsInfo);

        }
        return keywordsInfos;
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

