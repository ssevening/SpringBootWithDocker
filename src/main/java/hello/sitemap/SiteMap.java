package hello.sitemap;


import hello.utils.TimeUtils;

import javax.xml.bind.annotation.XmlRootElement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@XmlRootElement(name = "sitemap")
public class SiteMap {

    public SiteMap() {

    }

    public SiteMap(String loc) {
        this.loc = loc;
        this.lastmod = new Date();
        this.changefreq = "always";
        this.priority = "1.0";
    }

    public SiteMap(String loc, Date lastmod, String changefreq, String priority) {
        this.loc = loc;
        this.lastmod = lastmod;
        this.changefreq = changefreq;
        this.priority = priority;
    }

    /**
     * url https://www.xxx.com
     */
    private String loc;
    /**
     * 最后更新时间 yyyy-MM-dd
     */
    private Date lastmod;
    /**
     * 更新速度 always hourly daily weekly monthly yearly never
     */
    private String changefreq;
    /**
     * 权重 1.0 0.9 0.8
     */
    private String priority;

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Date getLastmod() {
        return lastmod;
    }

    public void setLastmod(Date lastmod) {
        this.lastmod = lastmod;
    }

    public String getChangefreq() {
        return changefreq;
    }

    public void setChangefreq(String changefreq) {
        this.changefreq = changefreq;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    @Override
    /** 重写 toString 适应xml格式 */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("<url>").append("\r\n");
        sb.append("<loc>" + loc + "</loc>").append("\r\n");
        sb.append("<lastmod>" + TimeUtils.getSiteMapTimeStr(getLastmod()) + "</lastmod>").append("\r\n");
        sb.append("<changefreq>" + changefreq + "</changefreq>").append("\r\n");
        sb.append("<priority>" + priority + "</priority>").append("\r\n");
        sb.append("</url>").append("\r\n");
        return sb.toString();
    }

}

