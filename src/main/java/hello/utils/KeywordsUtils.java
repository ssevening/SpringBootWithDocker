package hello.utils;

import java.io.IOException;
import java.util.*;

public class KeywordsUtils {


    public static void main(String[] args) throws IOException {

        String testStr = "Adyce 2020 New Summer Green Bandage Dress Women Sexy Sleeveless Spaghetti Strap Red Club Celebrity Evening Party Dress Vestidos Adyce 2020 New Summer Women Black Lace Bandage Dress Sexy Backless Hollow Out Midi Celebrity Evening Runway Party Dress Vestidos";
        List<Map.Entry<String, Integer>> list = getKeywordsFromLongText(testStr);

        String seoKeywords = getSEOKeywords(list);
        System.out.println(seoKeywords);
    }

    public static List<Map.Entry<String, Integer>> getKeywordsFromLongText(String longText) {
        String[] array = longText.split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            String key = array[i];
            map.merge(key, 1, Integer::sum);
        }

        //这里将map.entrySet()转换成list
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            //升序排序
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        return list;
    }

    public static String getSEOKeywords(List<Map.Entry<String, Integer>> list) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            Map.Entry<String, Integer> map = list.get(i);
            if (map.getValue() >= 3) {
                sb.append(map.getKey()).append(",");
            }
        }
        return sb.toString();
    }


}
