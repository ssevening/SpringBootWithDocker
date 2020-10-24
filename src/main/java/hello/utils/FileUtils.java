package hello.utils;

import hello.pojo.Product;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    /**
     * 写到资源文件
     *
     * @param students
     * @throws IOException
     */
    private static void writeToResource(List<Product> students) throws IOException {
        String basePath = getResourceBasePath();
        String studentResourcePath = new File(basePath, "student/student.txt").getAbsolutePath();
        // 保证目录一定存在
        ensureDirectory(studentResourcePath);
        System.out.println("studentResourcePath = " + studentResourcePath);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(studentResourcePath)));
        for (Product student : students) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(student.getProductTitle());
            buffer.append("\t");
            buffer.append(student.getProductId());
            buffer.append("\t");
            buffer.append(student.getFirstLevelCategoryId());
            buffer.append("\r\n");
            writer.write(buffer.toString());
        }

        writer.flush();
        writer.close();
    }

    /**
     * 保证拷贝的文件的目录一定要存在
     *
     * @param filePath 文件路径
     */
    public static void ensureDirectory(String filePath) {
        if (filePath != null) {
            return;
        }
        filePath = replaceSeparator(filePath);
        if (filePath.indexOf("/") != -1) {
            filePath = filePath.substring(0, filePath.lastIndexOf("/"));
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    /**
     * 将符号“\\”和“\”替换成“/”,有时候便于统一的处理路径的分隔符,避免同一个路径出现两个或三种不同的分隔符
     *
     * @param str
     * @return
     */
    public static String replaceSeparator(String str) {
        return str.replace("\\", "/").replace("\\\\", "/");
    }

    /**
     * 获取项目根路径
     *
     * @return
     */
    public static String getResourceBasePath() {
        // 获取跟目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            // nothing to do
        }
        if (path == null || !path.exists()) {
            path = new File("");
        }

        String pathStr = path.getAbsolutePath();
        // 如果是在eclipse中运行，则和target同级目录,如果是jar部署到服务器，则默认和jar包同级
        pathStr = pathStr.replace("\\target\\classes", "");

        return pathStr;
    }

    public static List<String> readFile(String path) {
        List<String> fileStrList = new ArrayList<>();
        File file = new File(path);
        FileReader fr;
        try {
            fr = new FileReader(file);
            java.io.BufferedReader br = new BufferedReader(fr);
            String line = null;
            StringBuffer sb = new StringBuffer();

            while ((line = br.readLine()) != null) {
                fileStrList.add(line.trim());
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileStrList;
    }

    public static List<String> readCSVFile(String path) {
        List<String> productIds = new ArrayList<>();
        File file = new File(path);
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
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productIds;
    }


    public static List<String> readXmlFiles(String dirPath) {
        List<String> productUrlList = new ArrayList<>();
        File mapDir = new File(dirPath);
        File[] files = mapDir.listFiles();
        for (File file : files) {
            FileReader fr;
            try {
                fr = new FileReader(file);
                java.io.BufferedReader br = new BufferedReader(fr);
                String line = null;
                StringBuffer sb = new StringBuffer();
                int i = 0;
                while ((line = br.readLine()) != null) {
                    if (line.contains("product.html")) {
                        productUrlList.add(line.replace("<loc>", "").replace("</loc>", ""));
                    }
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return productUrlList;
    }
}

