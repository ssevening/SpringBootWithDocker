package hello;

import hello.dao.ProductRepository;
import hello.dao.UserRepository;
import hello.dao.pojo.Notice;
import hello.dao.pojo.ProductDetail;
import hello.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


// 这个就是专门用来做webservice使用的，直接输入JSON结果，用来做服务端的接口交互使用。

@Controller
public class GreetingController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


    @RequestMapping("/greetingall")
    public String greetingall(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "greetingall";
    }


    @RequestMapping("/queryById")
    public String queryById(@RequestParam Integer userId, Model model) {
        model.addAttribute("uuu", userRepository.findOne(new Long(userId)));
        return "queryById";
    }


    @RequestMapping("/queryByFirstName")
    public String queryByFirstName(@RequestParam String firstName, Model model) {
        model.addAttribute("users", userRepository.findByFirstNameEndsWith(firstName));
        return "queryByFirstName";
    }


    @RequestMapping("/findByEmail")
    public String findByEmail(@RequestParam String email, Model model) {
        model.addAttribute("user", userRepository.findByEmailAddress(email));
        return "findByEmail";
    }

    @RequestMapping("/product")
    public String findProductByProductId(@RequestParam String productId, Model model) {
        ProductDetail productDetail = productRepository.queryProductDetailByProductId(productId);
        if (productDetail != null) {
            model.addAttribute("product", productRepository.queryProductDetailByProductId(productId));
            return "product";
        } else {
            Notice notice = new Notice();
            notice.message = "Product not found.";
            model.addAttribute("message", notice);
            return "notice";
        }
    }


    @RequestMapping("/search")
    public String queryProductDetailBySearch(@RequestParam String keywords, @RequestParam int page, @RequestParam int size, Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        PageRequest pageRequest = new PageRequest(page, size, sort);
        model.addAttribute("productList", productService.findByProductTitleContains(keywords, pageRequest));
        return "search";
    }


}