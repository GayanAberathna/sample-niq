package lk.sample.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@Builder(setterPrefix = "with")
public class ShopperData {

    private String shopperId;
    private List<ShelfItem> shelf;

    // Inner class representing an item on the shelf
    @Getter
    @Setter
    @Builder(setterPrefix = "with")
    public static class ShelfItem {
        private String productId;
        private BigDecimal relevancyScore;
    }
}
