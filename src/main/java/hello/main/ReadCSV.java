package hello.main;

import hello.utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
    public static void main(String[] args) {
        List<String> productIds = new ArrayList<>();
        File file = new File("/Users/panchenxing/us_aliexpress.csv");
        FileReader fr;
        try {
            fr = new FileReader(file);
            java.io.BufferedReader br = new BufferedReader(fr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (line.contains("aff_product")) {
                    String[] arr = line.split(",");
                    productIds.add(arr[0].replace("\"", ""));
                    i++;
                }
            }
            br.close();
            System.out.println(productIds.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
