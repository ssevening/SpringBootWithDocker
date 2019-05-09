package hello.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebPageUtils {

    public static String DESC = "description";
    public static String KEYWORDS = "keywords";


    public static Map<String, String> getSEOInfo(String url) {
        HashMap<String, String> map = new HashMap<>();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).header("User-Agent","Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = doc.title();
        Elements metas = doc.head().select("meta");
        for (Element meta : metas) {
            String content = meta.attr("content");
            if (KEYWORDS.equalsIgnoreCase(meta.attr("name"))) {
                map.put(KEYWORDS, content);
            }
            if (DESC.equalsIgnoreCase(meta.attr("name"))) {
                map.put(DESC, content);
            }
        }
        return map;

    }
}

