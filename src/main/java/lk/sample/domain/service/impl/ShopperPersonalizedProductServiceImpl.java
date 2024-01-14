package lk.sample.domain.service.impl;

import lk.sample.domain.model.ShopperPersonalizedProduct;
import lk.sample.domain.service.ShopperPersonalizedProductService;
import lk.sample.external.repository.ShopperPersonalizedProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class ShopperPersonalizedProductServiceImpl implements ShopperPersonalizedProductService {

    //TODO: add proper error handling and add javaDocs
    private final ShopperPersonalizedProductRepository personalizedProductRepository;

    @Override
    public Page<ShopperPersonalizedProduct> getAllPersonalizedProductsWithPagination(Pageable pageable) {
        return personalizedProductRepository.findAll(pageable);
    }
    @Override
    public ShopperPersonalizedProduct getPersonalizedProductById(Integer id) {
        return personalizedProductRepository.findById(id).orElse(null);
    }
    @Override
    @Transactional
    public ShopperPersonalizedProduct savePersonalizedProduct(ShopperPersonalizedProduct personalizedProduct) {
        return personalizedProductRepository.save(personalizedProduct);
    }
    @Override
    @Transactional
    public void deletePersonalizedProduct(Integer id) {
        personalizedProductRepository.deleteById(id);
    }
}

