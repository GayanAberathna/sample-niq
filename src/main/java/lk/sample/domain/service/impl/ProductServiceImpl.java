package lk.sample.domain.service.impl;

import lk.sample.application.exception.CommonException;
import lk.sample.domain.model.Product;
import lk.sample.domain.model.Shopper;
import lk.sample.domain.service.ProductService;
import lk.sample.external.repository.ProductRepository;
import lk.sample.external.repository.ShopperRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    //TODO: add proper error handling and add javaDocs

    private final ProductRepository productRepository;
    private final ShopperRepository shopperRepository;
    @Override
    public Page<Product> getAllProductsWithPagination(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    @Override
    @Cacheable("getProductById")
    public Product getProductById(String productId) {
        return productRepository.findById(productId).orElseThrow(() ->
                new CommonException("No product found with productId ".concat(productId), "006", "Validation", HttpStatus.NOT_FOUND));
    }
    @Override
    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    @Override
    @Transactional
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }
    @Override
    public List<Product> getProductsByShopper(String shopperId, String category, String brand, int limit, int offset) {
        final Shopper shopper = shopperRepository.findById(shopperId).orElseThrow(() ->
                new CommonException("No shop found with shopperId ".concat(shopperId), "006", "Validation", HttpStatus.NOT_FOUND));
        return productRepository.findByShopperAndCategoryAndBrand(shopper, category, brand, limit, offset);
    }

    @Override
    public List<Shopper> getShopperByProduct(String productId, int limit, int offset) {
        return shopperRepository.getShoppersByProductId(productId,limit,offset);
    }
}

