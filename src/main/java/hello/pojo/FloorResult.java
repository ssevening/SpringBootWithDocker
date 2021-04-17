package hello.pojo;


import java.util.List;

public class FloorResult {

    public List<Floor> floors;

    public static class Floor {
        public String id;
        public String viewType;
        public String title;
        public List<FloorDetail> floorDetails;
    }

    public static class FloorDetail {
        public String id;
        public String title;
        // 当desc用
        public String subTitle;
        // native,url
        public String actionType;
        public String targetUrl;
        public String imageUrl;
        // coupon code 专用
        public String platform;
        public String valueToCopy;
        public String tips;
    }

}

