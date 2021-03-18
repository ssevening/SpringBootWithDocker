package hello.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import hello.utils.StringUtil;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category extends TaobaoObject {

    public Category() {
    }

    private static final long serialVersionUID = 6782345851419552888L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    /**
     * 类目ID
     */
    @JsonProperty("category_id")
    private Long categoryId;
    /**
     * 类目名称
     */
    @JsonProperty("category_name")
    private String categoryName;
    /**
     * 父类目ID
     */
    @JsonProperty("parent_category_id")
    private Long parentCategoryId;

    private String firstImageUrl;

    private String secImageUrl;


    public String getDealFunWebItemsUrl() {
        return "/items/" + getCategoryId() + "/" + StringUtil.formatStringInUrl(getCategoryName()) + ".html";
    }

    public String getDealFunWebCategoryListUrl() {
        return "/category/" + getCategoryId() + "/" + StringUtil.formatStringInUrl(getCategoryName()) + ".html";
    }


    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getParentCategoryId() {
        return this.parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getSecImageUrl() {
        return secImageUrl;
    }

    public void setSecImageUrl(String secImageUrl) {
        this.secImageUrl = secImageUrl;
    }

    public String getFirstImageUrl() {
        return firstImageUrl;
    }

    public void setFirstImageUrl(String firstImageUrl) {
        this.firstImageUrl = firstImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
