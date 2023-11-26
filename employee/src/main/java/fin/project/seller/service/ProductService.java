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
import java.nio.file.Files;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    private String imageDirectory = "D:/NIBM//Crafts-Of-Ceylonzip/src/images/products";

    private String imageTargetDirectory = "D:\\NIBM\\Crafts-Of-Ceylonzip\\public\\loadimages"; //change path

    public void copyImage(String sourcePath) throws IOException {
        // Extracting file name from the path
        String fileName = sourcePath.substring(sourcePath.lastIndexOf("\\") + 1);

        // Target file (new image path)
        File targetFile = new File(imageTargetDirectory, fileName);

        // Copy the image file to the target directory
        Files.copy(new File(sourcePath).toPath(), targetFile.toPath());
    }
}
