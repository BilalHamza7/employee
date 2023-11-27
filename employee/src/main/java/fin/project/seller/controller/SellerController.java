package fin.project.seller.controller;

import fin.project.seller.data.Order;
import fin.project.seller.data.Product;
import fin.project.seller.data.Seller;
import fin.project.seller.data.SellerLogin;
import fin.project.seller.service.OrderService;
import fin.project.seller.service.ProductService;
import fin.project.seller.service.SellerLoginService;
import fin.project.seller.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class SellerController {

    @Autowired
    ProductService productService;
    @Autowired
    SellerService sellerService;
    @Autowired
    OrderService orderService;

    @Autowired
    SellerLoginService sellerLoginService;

    @PostMapping("/seller/createSeller")
    public Seller createSeller(@RequestBody Seller seller){
        return sellerService.createSeller(seller);
    }

    @PostMapping("/seller/recordCredentials")
    public int recordCredentials(@RequestBody SellerLogin createLogin){
        return sellerLoginService.recordCredentials(createLogin);

    }

    @PostMapping("/seller/verifySeller")
    public boolean verifySeller(@RequestParam String username,@RequestParam String password) {
        if (sellerLoginService.verifySeller(username, password)) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/product/createProduct")
    public String saveProduct(@RequestBody Product product) throws IOException {
        productService.copyImage(product);
        return "Good";
    }

    @GetMapping("/product/getProduct/{category}")
    public List<Product> getProductFromCategory(@PathVariable String category){
        return productService.getProductByCategory(category);
    }

    @GetMapping("/order/getOrder")
    public List<Order> getOrder(){
        return orderService.getOrder();
    }

    @PostMapping("/order/createOrder")
    public Order createOrder(@RequestBody Order order)
    {
        return orderService.createOrder(order);
    }
}
