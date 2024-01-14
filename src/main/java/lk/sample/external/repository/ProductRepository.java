package lk.sample.external.repository;

import lk.sample.domain.model.Product;
import lk.sample.domain.model.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByShopperAndCategoryAndBrand(Shopper shopper, String category, String brand, int limit, int offset);

}

