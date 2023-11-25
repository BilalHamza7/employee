package fin.project.seller.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerLoginRepository extends JpaRepository<SellerLogin, Integer> {

    Optional<SellerLogin> findByUsernameAndPassword(String username, String password);

}
