package hello;


import hello.dao.pojo.CouponCodeInfo;
import hello.pojo.FloorResult;
import hello.service.CouponCodeInfoService;
import hello.utils.AliYunOSSUtils;
import hello.utils.CouponUtils;

import hello.utils.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * // 这个用来做界面的。
 */
@Controller    // This means that this class is a Controller
@RequestMapping(path = "/coupon") // This means URL's start with /demo (after Application path)
public class CouponCodeController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private CouponCodeInfoService couponCodeInfoService;


    @GetMapping(path = "/add") // Map ONLY GET Requests
    @CrossOrigin
    public @ResponseBody
    String addNewBanner(@RequestParam String platform,
                        @RequestParam String title,
                        @RequestParam String subTitle,
                        @RequestParam(value = "imageUrl", required = false, defaultValue = "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/ic_coupon.png") String imageUrl,
                        @RequestParam String code,
                        @RequestParam(required = false) String targetUrl,
                        @RequestParam(value = "insights", required = false, defaultValue = "") String insights,
                        @RequestParam(value = "tips", required = false, defaultValue = "click to get the code.") String tips,
                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        CouponCodeInfo couponCodeInfo = new CouponCodeInfo();
        couponCodeInfo.setPlatform(platform);
        couponCodeInfo.setGmtCreate(Calendar.getInstance().getTime());
        couponCodeInfo.setTitle(title);
        if (subTitle.equals("undefined")) {
            subTitle = "";
        }
        couponCodeInfo.setSubTitle(subTitle);

        if (CouponUtils.getImageUrl(platform) != null) {
            couponCodeInfo.setImageUrl(CouponUtils.getImageUrl(platform));
        } else {
            if (imageUrl != null) {
                String imagePath = "Images" + File.separator + platform + imageUrl.substring(imageUrl.lastIndexOf("."));
                AliYunOSSUtils.uploadUrlContent(imagePath, imageUrl, false);
                couponCodeInfo.setImageUrl("https://csuperdeals.oss-us-west-1.aliyuncs.com/" + imagePath);
            }
        }

        if (code.length() >= 3 && !code.equals("undefined")) {
            couponCodeInfo.setCode(code);
            couponCodeInfo.setTargetUrl(CouponUtils.getTargetUrlByPlatform(platform));
            if (insights.equals("undefined")) {
                insights = "";
            }
            couponCodeInfo.setInsights(insights);
            couponCodeInfo.setStatus("VERIFIED");
            couponCodeInfoService.saveAndFlush(couponCodeInfo);
        }
        return "200";
    }

    @GetMapping(path = "/list")
    public @ResponseBody
    FloorResult list(HttpServletRequest httpServletRequest, @RequestParam String platform) {
        List<CouponCodeInfo> couponCodeInfoList = couponCodeInfoService.queryProductDetailByPlatform(platform);
        FloorResult floorResult = new FloorResult();
        floorResult.floors = new ArrayList<>();
        /**
         * {
         *             "id": "f1",
         *             "viewType": "9_0",
         *             "title": "AliExpress Coupon Codes"
         *         },
         */
        FloorResult.Floor topTitleFloor = new FloorResult.Floor();
        topTitleFloor.title = platform + " Coupon Codes";
        topTitleFloor.viewType = "9_0";
        topTitleFloor.id = "1";
        floorResult.floors.add(topTitleFloor);

        for (int i = 0; i < couponCodeInfoList.size(); i++) {
            CouponCodeInfo couponCodeInfo = couponCodeInfoList.get(i);
            /**
             * {
             *             "id": "1",
             *             "viewType": "c_c",
             *             "title": "banner",
             *             "floorDetails": [
             *                 {
             *                     "actionType": "url",
             *                     "id": "121",
             *                     "imageUrl": "https://csuperdeals.oss-us-west-1.aliyuncs.com/Images/ic_coupon.png",
             *                     "subTitle": "Get 70% off with coupon",
             *                     "targetUrl": "https://s.click.aliexpress.com/e/_pRmmQE2",
             *                     "title": "Save 70% Off",
             *                     "valueToCopy":"ALEXP933",
             *                     "platform":"AliExpress",
             *                     "tips":"Click to copy the code."
             *                 }
             *             ]
             *         },
             */
            FloorResult.Floor couponCodeFloor = new FloorResult.Floor();
            couponCodeFloor.id = "1";
            couponCodeFloor.viewType = "c_c";
            couponCodeFloor.title = "banner";
            couponCodeFloor.floorDetails = new ArrayList<>();
            FloorResult.FloorDetail floorDetail = new FloorResult.FloorDetail();
            floorDetail.actionType = "url";
            floorDetail.id = couponCodeInfo.getId() + "";
            floorDetail.imageUrl = couponCodeInfo.getImageUrl();
            floorDetail.subTitle = couponCodeInfo.getSubTitle();
            floorDetail.targetUrl = couponCodeInfo.getTargetUrl();
            floorDetail.title = couponCodeInfo.getTitle();
            floorDetail.valueToCopy = couponCodeInfo.getCode();
            floorDetail.platform = couponCodeInfo.getPlatform();
            floorDetail.tips = couponCodeInfo.getInsights();
            couponCodeFloor.floorDetails.add(floorDetail);
            floorResult.floors.add(couponCodeFloor);
        }

        FloorResult.Floor bottomTitleFloor = new FloorResult.Floor();
        topTitleFloor.title = "";
        topTitleFloor.viewType = "9_0";
        topTitleFloor.id = "1";
        floorResult.floors.add(bottomTitleFloor);

        try {
            String floorJson = JsonMapper.pojo2json(floorResult);
            AliYunOSSUtils.uploadString("coupons/" + platform + ".txt", floorJson, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return floorResult;
    }

    @GetMapping(path = "/deleteAll")
    public @ResponseBody
    String deleteAll(HttpServletRequest httpServletRequest) {
        couponCodeInfoService.findAll();
        return "200";
    }
}
