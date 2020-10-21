package hello.main;

import hello.utils.FileUtils;
import hello.utils.NetworkUtils;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class KeywordsGen {

    public static void main(String[] args) {
        List<String> list = FileUtils.readFile("/Users/panchenxing/work/keywords.txt");
        for (int i = 0; i < list.size(); i++) {
            String addKeywords = null;
            try {
                addKeywords = "http://127.0.0.1:8080/admin/addKeywords?keywords=" + URLEncoder.encode(list.get(i), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                String result = NetworkUtils.doGet(addKeywords);
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
