package hello;

import hello.qrcode.QRCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * // 这个用来做界面的。
 */
@Controller    // This means that this class is a Controller
@RequestMapping(path = "/qrcode") // This means URL's start with /demo (after Application path)
public class QrcodeController {

    @RequestMapping("/gen.html")
    public void createQrCode(HttpServletRequest request, HttpServletResponse response, @RequestParam String content) {
        StringBuffer url = request.getRequestURL();
        try {
            OutputStream os = response.getOutputStream();
            //从配置文件读取需要生成二维码的连接
//            String requestUrl = GraphUtils.getProperties("requestUrl");
            //requestUrl:需要生成二维码的连接，logoPath：内嵌图片的路径，os：响应输出流，needCompress:是否压缩内嵌的图片
            QRCodeUtils.encode(content, "/static/images/qr_logo.png", os, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/create.html")
    public String index(Model model) {
        return "createQRCode";
    }




}
