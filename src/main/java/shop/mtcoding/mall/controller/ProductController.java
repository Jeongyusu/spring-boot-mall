package shop.mtcoding.mall.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.mall.model.Product;
import shop.mtcoding.mall.model.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//Contorller는 진입점
//뷰를 넘겨주려고 @Controller 사용
@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/product/update")
    public String update(int id, String name, int price, int qty){

        productRepository.updateBy(id, name, price, qty);
        return "redirect:/";
        //리다이렉트 - 요청 2개
    }

    @PostMapping("/product/delete")
    public String delete(int id){

        productRepository.deleteById(id);
        return "redirect:/";
    }


    @GetMapping("/product/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request){
        System.out.println("id : " + id);
        Product product = productRepository.findbyId(id);
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQty());
        request.setAttribute("p", product);

        return "detail";
    }

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        List<Product> productList = productRepository.findAll();
        request.setAttribute("productList", productList);
        return "home";
    }



    @GetMapping("/write")
    public String writePage(){
        return "write";
    }

    @PostMapping("/product")
    public void write(String name, int price, int qty, HttpServletResponse response) throws Exception{
        System.out.println("name : " + name);
        System.out.println("price : " + price);
        System.out.println("qty : " + qty);

        //컨트롤러의 메서드를 재호출하는 방법
        productRepository.save(name,price,qty);
        response.sendRedirect("/");
        //return "redirect:/"



    }



}