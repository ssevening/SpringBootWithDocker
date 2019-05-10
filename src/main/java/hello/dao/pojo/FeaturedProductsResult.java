package hello.dao.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class FeaturedProductsResult {

    public FeaturedProductInfo result;


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FeaturedProductInfo {
        public FeaturedProductList result;

        public long errorCode;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FeaturedProductList {
        public List<ProductDetail> products;
        public int totalResults;
    }


}
