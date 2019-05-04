package hello;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.plaf.TextUI;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * // 每天早八点到晚八点，间隔2分钟执行任务
 *
 * @Scheduled(cron="0 0/2 8-20 * * ?")
 * // 每天早八点到晚八点，间隔3分钟执行任务
 * @Scheduled(cron="0 0/3 8-20 * * ?")
 * // 每天早八点到晚八点，间隔1分钟执行任务
 * @Scheduled(cron="0 0/1 8-20 * * ?")
 */
@Component
public class ScheduledTest {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTest.class);

//    @Scheduled(cron = "0 0/2 8-20 * * ?")
//    public void executeFileDownLoadTask() {
//
//        // 间隔2分钟,执行工单上传任务
//        Thread current = Thread.currentThread();
//        System.out.println("定时任务1:" + current.getId());
//        logger.info("ScheduledTest.executeFileDownLoadTask 定时任务1:" + current.getId() + ",name:" + current.getName());
//
//    }
    // 每天早八点到晚八点，间隔2分钟执行任务
//    @Scheduled(cron = "0 0/2 8-20 * * ?")
//    public void executeUploadTask() {
//        Thread current = Thread.currentThread();
//        System.out.println("定时任务2:" + current.getId());
//        logger.info("ScheduledTest.executeUploadTask 定时任务2:" + current.getId() + ",name:" + current.getName());
//        // 获取百度的数据
//        String data = getDataFromInternet("http://www.baidu.com");
//        logger.info(data);
//        if (data != null || data.length() == 0) {
//            // 喜大普奔，百度的网站打不开啦！
//            // send mail to admin@baidu.com
//        }
//    }

    // 每天早八点到晚八点，间隔2分钟执行任务
    @Scheduled(cron = "0 0/2 8-20 * * ?")
    public void executeUploadBackTask() {

        // 间隔3分钟,执行工单上传任务
        Thread current = Thread.currentThread();
        System.out.println("定时任务3:" + current.getId());
        logger.info("ScheduledTest.executeUploadBackTask 定时任务3:" + current.getId() + ",name:" + current.getName());


    }


    public static String getDataFromInternet(String path) {
        try {
            URL url = new URL(path.trim());
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if (200 == urlConnection.getResponseCode()) {
                //得到输入流
                InputStream is = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while (-1 != (len = is.read(buffer))) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                return baos.toString("utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
