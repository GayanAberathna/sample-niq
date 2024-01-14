package lk.sample.external.repository;

import lk.sample.domain.model.Product;
import lk.sample.domain.model.Shopper;
import lk.sample.domain.model.ShopperPersonalizedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopperPersonalizedProductRepository extends JpaRepository<ShopperPersonalizedProduct, Integer> {
    Optional<ShopperPersonalizedProduct> findByProductAndShopper(Product product, Shopper shopper);

}
