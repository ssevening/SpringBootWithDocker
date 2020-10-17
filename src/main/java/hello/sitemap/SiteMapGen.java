package hello.sitemap;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import hello.utils.FileUtils;

public class SiteMapGen {

    public static String DATE_FORMAT = "yyyy-MM-dd";

    public static void main(String[] args) {
        SiteMapGen siteMapGen = new SiteMapGen();
        siteMapGen.createSiteMap("/Users/panchenxing/");
    }

    /**
     * 动态生成sitemap
     * 目录，web根目录，就是你的站点首页 index.html所在同级目录
     *
     * @author sun
     * @date 2018年3月31日 下午8:02:47
     */
    public void createSiteMap(String realPath) {
        try {
            System.out.println(realPath);
            long start = System.currentTimeMillis();
            String WEBSITE = "https://www.dealfuns.com";
            /** 存储位置,实际应用项目中，需要指到网站的根目录 request.getSession().getServletContext().getRealPath("/") **/

            List<Category> cList = findCategoryBy();
            if (cList == null || cList.size() < 1) {
                return;
            }
            List<String> siteMapList = new ArrayList<>();
            for (Category category : cList) {
                /** 查询每个分类下对应的文章 **/
                List<Article> aList = findArticleBy(category.getId());
                if (aList != null && aList.size() > 0) {
                    //start ======================================================================
                    String path = realPath + "/sitemap/" + category.getId();
                    fileExists(path);//判断文件夹是否存在，不存在则创建
                    WebSitemapGenerator sitemapGenerator = WebSitemapGenerator.builder(WEBSITE, new File(path)).gzip(false).build();
                    for (Article article : aList) {//遍历取出来的文章
                        String url = "https://www.xunl.net";//文章详情页的url地址
                        WebSitemapUrl sitemapUrl = new WebSitemapUrl.Options(url).lastMod(formatDate(article.getAddTime(), DATE_FORMAT)).priority(0.9).changeFreq(ChangeFreq.HOURLY).build();
                        sitemapGenerator.addUrl(sitemapUrl);
                    }
                    sitemapGenerator.write();
                    // end ======================================================================
                    siteMapList.add(WEBSITE + "/sitemap/" + category.getId() + "/sitemap.xml");//主索引文件需要这个路径
                }
            }

            //TODO
            //其实分类 - list 页 url也应该存一个sitemap，看自己吧，如果也想告诉搜索引擎，我的list列表页也很重要，那么就做

            /** 生成主索引文件 **/
            if (siteMapList != null && siteMapList.size() > 0) {
                Collection<Sitemap> s = new ArrayList();
                for (String url : siteMapList) {
                    s.add(new Sitemap(url));
                }
                Writer wt = new PrintWriter(new File(realPath + "/sitemap.xml"));
                SiteMapUtils.writeSitemapIndex(wt, s.iterator());
                wt.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("生成Sitemap完毕, 共耗时：" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟数据库查询 - 查询符合条件的文章分类
     *
     * @return
     * @author sun
     * @date 2018年3月31日 下午8:12:41
     */
    public List<Category> findCategoryBy() {
        List<Category> cList = new ArrayList<>();
        cList.add(new Category(1, new Date()));
        cList.add(new Category(2, new Date()));
        return cList;
    }

    /**
     * 模拟数据库查询 - 查询符合条件的文章
     *
     * @return
     * @author sun
     * @date 2018年3月31日 下午7:47:50
     */
    public List<Article> findArticleBy(Integer categoryId) {
        System.out.println("查询文章分类id为 " + categoryId + " 的所有符合条件的文章");
        List<Article> aList = new ArrayList<>();
        aList.add(new Article(10, new Date()));
        aList.add(new Article(11, new Date()));
        aList.add(new Article(12, new Date()));
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

    /**
     * 分类
     *
     * @author sun
     * @date 2018年3月31日 下午8:12:21
     */
    public class Category {
        private int id;
        private Date addTime;

        public Category(int i, Date addTime) {
            super();
            this.id = i;
            this.addTime = addTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getAddTime() {
            return addTime;
        }

        public void setAddTime(Date addTime) {
            this.addTime = addTime;
        }
    }

    /**
     * 文章
     *
     * @author sun
     * @date 2018年3月31日 下午7:31:42
     */
    public class Article {
        private int id;
        private Date addTime;

        public Article(int i, Date addTime) {
            super();
            this.id = i;
            this.addTime = addTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getAddTime() {
            return addTime;
        }

        public void setAddTime(Date addTime) {
            this.addTime = addTime;
        }
    }

}

