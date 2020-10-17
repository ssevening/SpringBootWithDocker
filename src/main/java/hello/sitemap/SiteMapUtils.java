package hello.sitemap;


import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

public class SiteMapUtils {

    protected final static String URLSET_START = "<?xml version='1.0' encoding='UTF-8'?>\n"
            + "<urlset xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
            + "         xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\"\n"
            + "         xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n";
    protected final static String URLSET_END = "\n</urlset>";

    protected final static String SITEMAPINDEX_START = "<?xml version='1.0' encoding='UTF-8'?>\n"
//          + "<?xml-stylesheet type=\"text/xsl\" href=\"http://lusongsong.com/zb_users/plugin/Nobird_Seo_Tools/Sitemap/sitemap.xsl\"?>"
            + "<sitemapindex xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
            + "         xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/siteindex.xsd\"\n"
            + "         xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n";
    protected final static String SITEMAPINDEX_END = "\n</sitemapindex>";

    public static void writeSitemapIndex(Writer writer, Iterator<? extends Sitemap> mappings) throws IOException {
        writeXml(writer, SITEMAPINDEX_START, mappings, SITEMAPINDEX_END);
    }

    public static long writeUrlset(Writer writer, Iterator<Url> urls) throws IOException {
        return writeXml(writer, URLSET_START, urls, URLSET_END);
    }

    private static long writeXml(Writer writer, String start, Iterator<?> it, String end) throws IOException {
        writer.write(start);
        long count = writeSubtree(writer, it);
        writer.write(end);
        return count;
    }

    public static long writeSubtree(Writer writer, Iterator<?> it) throws IOException {
        long size = 0;
        Marshaller m;
        try {
            JAXBContext jc = JAXBContext.newInstance(Sitemap.class, Url.class);
            m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FRAGMENT, true);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (PropertyException e) {
            throw new DataBindingException(e);
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }

        boolean first = true;
        while (it.hasNext()) {
            if (first)
                first = false;
            else
                writer.write("\n");
            try {
                m.marshal(it.next(), writer);
            } catch (JAXBException e) {
                throw new IOException(e);
            }
            size++;
        }
        return size;
    }

}

