package fin.project.seller.controller;

import fin.project.seller.data.Order;
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

    @PostMapping("/seller/createCustomer")
    public Seller createCustomer(@RequestBody Seller seller){
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

    @PostMapping(path = "/product/createProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createProduct(@RequestPart("file") MultipartFile file,
                                                @RequestPart("name") String name,
                                                @RequestPart("description") String description,
                                                @RequestPart("category") String category,
                                                @RequestPart("price") float price,
                                                @RequestPart("weight") float weight) throws IOException {
        if(file.isEmpty())
        {
            return ResponseEntity.badRequest().body("please select an image");
        }
        else {
            return productService.createProduct(file, name, description, category, price, weight);
        }
    }

//    @GetMapping(path = "/sellerDetails")
//    public List<Seller> getSellerDetails()
//    {
//        return List<Seller>;
//    }
}
