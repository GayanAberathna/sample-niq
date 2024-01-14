package lk.sample.domain.service;

import lk.sample.domain.dto.ShopperData;
import lk.sample.domain.model.Shopper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ShopperService {

    Page<Shopper> getAllShoppersWithPagination(Pageable pageable);

    Shopper getShopperById(String shopperId);

    Shopper saveShopper(Shopper shopper);

    void deleteShopper(String shopperId);

    ShopperData getRelevancyScore(String shopperId, String traceId);
}
