package hello.sitemap;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "url")
public class Url {

    private String ioc;

    public Url() {
    }

    public Url(String ioc) {
        super();
        this.ioc = ioc;
    }

    @XmlElement(name = "ioc")
    public String getIoc() {
        return ioc;
    }

    public void setIoc(String ioc) {
        this.ioc = ioc;
    }

}

