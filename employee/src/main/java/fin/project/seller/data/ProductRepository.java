package fin.project.seller.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("Select s From Product s Where s.category=?1") // only 1 parameter passing
    List<Product> findByCategory(String category);

}
