package fin.project.seller.service;

import fin.project.seller.data.Product;
import fin.project.seller.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    private String imageTargetDirectory = "D:/NIBM/Crafts-Of-Ceylonzip/public/loadimages"; //change path

    public String copyImage(Product product) throws IOException {
        // Extracting file name from the path
        String sourcePath = product.getFilePath(); // Assuming there is a getFilePath() method in your Product class
        String fileNameWithExtension = sourcePath.substring(sourcePath.lastIndexOf("\\") + 1);
        String fileName = fileNameWithExtension.substring(0, fileNameWithExtension.lastIndexOf("."));
        product.setFilePath(fileName);

        // Target file (new image path)
        File targetFile = new File(imageTargetDirectory, fileNameWithExtension);

        // Copy the image file to the target directory
        Files.copy(new File(sourcePath).toPath(), targetFile.toPath());

        productRepository.save(product);

        return "Good";
    }



    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getSellerProduct(int selId) {
        return productRepository.findBySelId(selId);
    }


//    public void createProduct(Product product) throws IOException {
//        // Assuming image path is provided in the request
//        String imagePath = product.getFilePath();
//
//        // Extracting file name from the path
//        String fileName = imagePath.substring(imagePath.lastIndexOf("\\") + 1);
//
//        // Target file (new image path)
//        File targetFile = new File(imageTargetDirectory, fileName);
//
//        // Save product details with the provided file path
//        Product newProduct = Product.builder()
//                .name(product.getName())
//                .category(product.getCategory())
//                .description(product.getDescription())
//                .price(product.getPrice())
//                .weight(product.getWeight())
//                .filePath(targetFile.getAbsolutePath())
//                .build();
//
//        // Save the product to the database
//        productRepository.save(newProduct);
//    }

}
