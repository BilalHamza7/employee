package fin.project.seller.service;

import fin.project.seller.data.Seller;
import fin.project.seller.data.SellerLogin;
import fin.project.seller.data.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public Seller createSeller(Seller seller)    {
        return sellerRepository.save(seller);
    }

    public Optional<Seller> getSeller(int id) {
        return sellerRepository.findById(id);
    }
}
