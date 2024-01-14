package lk.sample.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "ShopperPersonalizedProducts")
//@IdClass(ShopperProductId.class)
public class ShopperPersonalizedProduct {
    //TODO: need to optimise @Id to combine shopperId and productId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopperProductId", nullable = false)
    private String shopperProductId;

    @ManyToOne
    @JoinColumn(name = "shopperId", nullable = false)
    private Shopper shopper;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @Column(name = "relevancyScore", nullable = false)
    private BigDecimal relevancyScore;
}
