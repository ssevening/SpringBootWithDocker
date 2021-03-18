package hello.main;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProductSiteMapIndexGen {

    public static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static String SITEMAP_ROOT_PATH = "/Users/panchenxing/map/";

    public static void main(String[] args) {
        ProductSiteMapIndexGen siteMapGen = new ProductSiteMapIndexGen();
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
            for (int i = 0; i < 88; i++) {
                siteMapList.add(WEBSITE + "/sitemap/sitemap" + i + ".xml");//主索引文件需要这个路径
            }

//            /** 生成主索引文件 **/
//            if (siteMapList != null && siteMapList.size() > 0) {
//                Collection<SiteMap> s = new ArrayList();
//                for (String url : siteMapList) {
//                    s.add(new SiteMap(url));
//                }
//                Writer wt = new PrintWriter(new File(realPath + "/category_sitemap.xml"));
//                SiteMapUtils.writeSitemapIndex(wt, s.iterator());
//                wt.close();
//            }
            long end = System.currentTimeMillis();
            System.out.println("生成Sitemap完毕, 共耗时：" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

