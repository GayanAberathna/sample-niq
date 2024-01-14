package lk.sample.domain.service.impl;

import lk.sample.application.exception.CommonException;
import lk.sample.domain.dto.ShopperData;
import lk.sample.domain.model.Shopper;
import lk.sample.domain.service.ShopperService;
import lk.sample.external.repository.ShopperPersonalizedProductRepository;
import lk.sample.external.repository.ShopperRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShopperServiceImpl implements ShopperService {

    //TODO: add proper error handling and add javaDocs

    private final ShopperRepository shopperRepository;
    private final ShopperPersonalizedProductRepository shopperPersonalizedProductRepository;

    @Override
    public ShopperData getRelevancyScore(String shopperId, String traceId) {
        final Shopper shopper = shopperRepository.findById(shopperId).orElseThrow(() ->
                new CommonException("No shopper found for given shopperId ".concat(shopperId), "005", "Validation", HttpStatus.NOT_FOUND));
        return ShopperData.builder()
                .withShopperId(shopperId)
                .withShelf(getShelfDetails(shopper)).build();
    }
    private List<ShopperData.ShelfItem> getShelfDetails(Shopper shopper) {
        return shopper.getProducts().stream().map(product -> ShopperData.ShelfItem.builder()
                .withProductId(product.getProductId())
                .withRelevancyScore(shopperPersonalizedProductRepository.findByProductAndShopper(product, shopper)
                        .orElseThrow(() ->new CommonException("No relevancy score found with shopperId "
                                .concat(shopper.getShopperId()).concat("and productId ")
                                .concat(product.getProductId()), "006", "Validation", HttpStatus.NOT_FOUND))
                        .getRelevancyScore()).build()).collect(Collectors.toList());
    }
    @Override
    public Page<Shopper> getAllShoppersWithPagination(Pageable pageable) {
        return shopperRepository.findAll(pageable);
    }

    @Override
    @Cacheable("getShopperById")
    public Shopper getShopperById(String shopperId) {
        return shopperRepository.findById(shopperId).orElse(null);
    }

    @Override
    @Transactional
    public Shopper saveShopper(Shopper shopper) {
        return shopperRepository.save(shopper);
    }

    @Override
    @Transactional
    public void deleteShopper(String shopperId) {
        shopperRepository.deleteById(shopperId);
    }
}

