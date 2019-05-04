package hello.dao.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetailResult implements Serializable {

    public ProductDetail result;
    public long errorCode;
}
