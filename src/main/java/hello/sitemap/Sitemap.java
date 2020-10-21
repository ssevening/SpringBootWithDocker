package hello.sitemap;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sitemap")
public class Sitemap {

    private String ioc;

    public Sitemap() {
    }

    public Sitemap(String ioc) {
        super();
        this.ioc = ioc;
    }

    @XmlElement(name = "loc")
    public String getIoc() {
        return ioc;
    }

    public void setIoc(String ioc) {
        this.ioc = ioc;
    }

}

