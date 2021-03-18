package hello.main;

import hello.utils.FileUtils;
import hello.utils.NetworkUtils;

import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class KeywordsGen {

    public static void main(String[] args) {
//        List<String> list = FileUtils.readFile("/Users/panchenxing/work/keywords.txt");
//        for (int i = 0; i < list.size(); i++) {
//            String addKeywords = null;
//            try {
//                addKeywords = "http://127.0.0.1:8080/admin/addKeywords?keywords=" + URLEncoder.encode(list.get(i), "utf-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            try {
//                String result = NetworkUtils.doGet(addKeywords);
//                System.out.println(result);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        String result = formatDate(Calendar.getInstance().getTime(), DATE_FORMAT);


        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date())+"+00:00";


        System.out.println(date);



    }


    public static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";


    public static String formatDate(Date date, String pattern) {
        if (pattern == null && pattern.equals("")) {
            pattern = DATE_FORMAT;
        }
        DateFormat df = new SimpleDateFormat(pattern, Locale.US);
        return df.format(date);
    }


}
