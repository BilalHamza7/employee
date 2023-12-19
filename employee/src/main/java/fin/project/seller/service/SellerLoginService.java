package fin.project.seller.service;

import fin.project.seller.data.SellerLogin;
import fin.project.seller.data.SellerLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerLoginService {
    @Autowired
    SellerLoginRepository sellerLoginRepository;

    public Optional<SellerLogin> verifySeller(String username, String password)
    {
        return sellerLoginRepository.findByUsernameAndPassword(username, password);
    }

    public int recordCredentials(SellerLogin createLogin) {
        var result = sellerLoginRepository.save(createLogin);
        return result.getId();
    }

    public Optional<SellerLogin> getSellerLogin(int id) {
        return sellerLoginRepository.findById(id);
    }
}
