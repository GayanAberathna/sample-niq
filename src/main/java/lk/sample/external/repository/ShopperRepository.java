package lk.sample.external.repository;

import lk.sample.domain.model.Product;
import lk.sample.domain.model.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopperRepository extends JpaRepository<Shopper, String> {
    @Query(value = "SELECT * FROM Shopper s WHERE s.productId = ?1 and LIMIT ?2 OFFSET ?3", nativeQuery = true)
    List<Shopper> getShoppersByProductId(String productId, int limit, int offset);
}
