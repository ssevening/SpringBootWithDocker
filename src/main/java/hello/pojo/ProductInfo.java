package hello.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfo extends TaobaoObject {
    public List<Product> product;
}
