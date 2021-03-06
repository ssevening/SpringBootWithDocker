package hello;

import hello.dao.BannerRepository;
import hello.dao.ProductRepository;
import hello.dao.UserRepository;
import hello.dao.pojo.BannerInfo;
import hello.dao.pojo.KeywordsInfo;
import hello.dao.pojo.User;
import hello.service.KeywordsInfoService;
import hello.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * // 这个用来做界面的。
 */
@Controller    // This means that this class is a Controller
@RequestMapping(path = "/admin") // This means URL's start with /demo (after Application path)
public class AdminController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private KeywordsInfoService keywordsInfoService;


    @GetMapping(path = "/addNewBanner") // Map ONLY GET Requests
    public @ResponseBody
    String addNewBanner(@RequestParam String title
            , @RequestParam String description, @RequestParam String bannerUrl, @RequestParam String promotionUrl, HttpServletRequest request) {

        HttpSession session = request.getSession();
        String loginStatus = (String) session.getAttribute("loginStatus");
        if (loginStatus.equals("true")) {
            BannerInfo bannerInfo = new BannerInfo();
            bannerInfo.setTitle(title);
            bannerInfo.setDescription(description);
            bannerInfo.setBannerUrl(bannerUrl);
            bannerInfo.setPromotionUrl(promotionUrl);
            bannerInfo.setGmtCreate(Calendar.getInstance().getTime());
            bannerInfo.setStatus("online");
            bannerRepository.saveAndFlush(bannerInfo);
        } else {
            return "pls login.";
        }


        return "200";
    }

    @GetMapping(path = "/addBanner.html")
    public String checkIfCanAddBanner(HttpServletRequest httpServletRequest) {
        // checkLogin
        return "addBanner";
    }


    @GetMapping(path = "/login.html")
    public String login(HttpServletRequest httpServletRequest, @RequestParam String userName
            , @RequestParam String pass) {
        // if (userName.equals("admin") & pass.equals("Hello")) {
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("loginStatus", "true");
        session.setAttribute("userId", "ssevening");

        // checkLogin
        return "login";
    }

    @GetMapping(path = "/listAllBanners") // Map ONLY GET Requests
    public @ResponseBody
    List<BannerInfo> listAddBanner() {
        return bannerRepository.findAll();
    }

    @GetMapping(path = "/deleteBanner") // Map ONLY GET Requests
    public @ResponseBody
    String listAddBanner(@RequestParam Long id) {
        bannerRepository.deleteById(id);
        return "200";
    }


    @GetMapping(path = "/upload.html")
    public String uploadView() {
        // checkLogin
        return "upload";
    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public List<BannerInfo> pubggupload(@RequestParam("file") MultipartFile file) throws Exception {
        List<BannerInfo> list = ExcelUtils.excelToShopIdList(file.getInputStream());

        return list;
    }


    @GetMapping("/exportExcel")
    @ResponseBody
    public String exportExcel(HttpServletResponse response) throws Exception {
        String xlsFile_name = "download.xlsx";     //输出xls文件名称
        //内存中只创建100个对象
        Workbook wb = new SXSSFWorkbook(100);           //关键语句
        Sheet sheet = null;     //工作表对象
        Row nRow = null;        //行对象
        Cell nCell = null;      //列对象

        int rowNo = 0;      //总行号
        int pageRowNo = 0;  //页行号
        wb.createSheet("bannerInfo");//创建新的sheet对象
        sheet = wb.getSheetAt(0);        //动态指定当前的工作表
        nRow = sheet.createRow(0);

        List<BannerInfo> bannerInfos = bannerRepository.findAll();
        List<String> titleKeyList = new ArrayList<>();
        titleKeyList.add("title");
        titleKeyList.add("bannerUrl");
        titleKeyList.add("promotionUrl");
        titleKeyList.add("列4");
        titleKeyList.add("列5");

        for (int i = 0; i < titleKeyList.size(); i++) {
            Cell cell_tem = nRow.createCell(i);
            cell_tem.setCellValue(titleKeyList.get(i));
        }

        for (int i = 0; i < bannerInfos.size(); i++) {
            BannerInfo bannerInfo = bannerInfos.get(i);
            Row bannerDataRow = sheet.createRow(i + 1);
            Cell cell_title = bannerDataRow.createCell(0);
            cell_title.setCellValue(bannerInfo.getTitle());
            cell_title.setCellType(CellType.STRING);

            Cell cell_bannerUrl = bannerDataRow.createCell(1);
            cell_bannerUrl.setCellValue(bannerInfo.getBannerUrl());
            cell_bannerUrl.setCellType(CellType.STRING);

            Cell cell_promotionUrl = bannerDataRow.createCell(2);
            cell_promotionUrl.setCellValue(bannerInfo.getPromotionUrl());
            cell_promotionUrl.setCellType(CellType.STRING);
        }
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + xlsFile_name);
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        wb.close();
        outputStream.close();
        return "";
    }


    @GetMapping(path = "/addKeywords") // Map ONLY GET Requests
    public @ResponseBody
    String addKeywordsInfo(@RequestParam String keywords) {
        KeywordsInfo keywordsInfo = new KeywordsInfo();
        keywordsInfo.setKeywords(keywords);
        keywordsInfo.setGmtCreate(Calendar.getInstance().getTime());
        keywordsInfoService.save(keywordsInfo);
        return "200";
    }


}
