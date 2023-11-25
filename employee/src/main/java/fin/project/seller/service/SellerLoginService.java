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

    public boolean verifySeller(String username, String password)
    {
        Optional<SellerLogin> sellerLogin = sellerLoginRepository.findByUsernameAndPassword(username, password);
        return sellerLogin.isPresent();
    }

    public int recordCredentials(SellerLogin createLogin) {
        var result = sellerLoginRepository.save(createLogin);
        return result.getId();
    }
}
