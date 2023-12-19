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
import java.util.Optional;

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
    public Optional<SellerLogin> verifySeller(@RequestBody SellerLogin credentials) {
        return sellerLoginService.verifySeller(credentials.getUsername(), credentials.getPassword());
    }

    @GetMapping("/seller/getSeller")
    public Optional<Seller> getSeller(@RequestParam int id){
        return sellerService.getSeller(id);
    }

    @GetMapping("/seller/getSellerLogin")
    public Optional<SellerLogin> getCustomerLogin(@RequestParam int id){
        return sellerLoginService.getSellerLogin(id);
    }

    @PostMapping("/product/createProduct")
    public String createProduct(@RequestBody Product product) throws IOException {
        productService.copyImage(product);
        return "Good";
    }

    @GetMapping("/product/getProduct/{category}")
    public List<Product> getProductFromCategory(@PathVariable String category){
        return productService.getProductByCategory(category);
    }

    @GetMapping("/product/getMyProduct/{selId}")
    public List<Product> getSellerProduct(@PathVariable int selId){
        return productService.getSellerProduct(selId);
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
