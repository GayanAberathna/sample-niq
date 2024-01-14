package lk.sample.domain.service;

import lk.sample.domain.model.Product;
import lk.sample.domain.model.Shopper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> getAllProductsWithPagination(Pageable pageable);

    Product getProductById(String productId);

    Product saveProduct(Product product);

    void deleteProduct(String productId);

    List<Product> getProductsByShopper(String shopperId, String category, String brand, int limit, int offset);

    List<Shopper>  getShopperByProduct(String productId, int limit, int offset);
}
