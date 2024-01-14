package lk.sample.domain.service;

import lk.sample.domain.model.ShopperPersonalizedProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShopperPersonalizedProductService {

    Page<ShopperPersonalizedProduct> getAllPersonalizedProductsWithPagination(Pageable pageable);

    ShopperPersonalizedProduct getPersonalizedProductById(Integer id);

    ShopperPersonalizedProduct savePersonalizedProduct(ShopperPersonalizedProduct personalizedProduct);

    void deletePersonalizedProduct(Integer id);
}
