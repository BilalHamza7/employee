package fin.project.seller.service;

import fin.project.seller.data.Product;
import fin.project.seller.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    private String imageDirectory = "D:\\NIBM\\Crafts-Of-Ceylonzip\\src\\images\\products";

    public ResponseEntity<String> createProduct(MultipartFile file, String name, String description, String category, float price, float weight) throws IOException {

        try{
            String fileName = file.getOriginalFilename();
            String filePath = imageDirectory + File.separator + fileName;

            // Read the uploaded file into a BufferedImage
            InputStream input = file.getInputStream();
            BufferedImage image = ImageIO.read(input);

            // Specify the PNG file path
            String pngFilePath = imageDirectory + File.separator + fileName + ".png";

            // Write the BufferedImage as a PNG file
            ImageIO.write(image, "png", new File(pngFilePath));

            Product newProduct = new Product();//save all details
            newProduct.setProductName(name);
            newProduct.setDescription(description);
            newProduct.setCategory(category);
            newProduct.setPrice(price);
            newProduct.setWeight(weight);
            newProduct.setPath(pngFilePath); // Set the image path attribute

            // Save the new order in the repository
            productRepository.save(newProduct);
            return ResponseEntity.ok().body("Saved"+filePath);
        } catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the file: " + e.getMessage());
        }
    }
}
