package hello.dao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListFeaturedListResult {

    public ListFeaturedList result;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ListFeaturedList {
        public List<FeatureInfo> featuredList;
        public int currentPageNum;
        public int errorCode;
        public int totalPageNum;
    }


//    @JsonIgnoreProperties(ignoreUnknown = true)
//    public static class FeatureInfo {
//        public String featuredName;
//        public String featuredDesc;
//        public long productNum;
//    }
}
