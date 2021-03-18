package hello.sitemap;


import hello.utils.TimeUtils;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "sitemap")
public class SiteMapIndex {

    public SiteMapIndex() {

    }

    public SiteMapIndex(String loc) {
        this.loc = loc;
        this.lastmod = new Date();
    }

    public SiteMapIndex(String loc, Date lastmod) {
        this.loc = loc;
        this.lastmod = lastmod;
    }

    /**
     * url https://www.xxx.com
     */
    private String loc;
    /**
     * 最后更新时间 yyyy-MM-dd
     */
    private Date lastmod;

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


    @Override
    /** 重写 toString 适应xml格式 */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("<url>").append("\r\n");
        sb.append("<loc>" + loc + "</loc>").append("\r\n");
        sb.append("<lastmod>" + TimeUtils.getSiteMapTimeStr(getLastmod()) + "</lastmod>").append("\r\n");
        sb.append("</url>").append("\r\n");
        return sb.toString();
    }


}

