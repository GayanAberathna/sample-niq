package lk.sample.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Shoppers")
public class Shopper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopperId", nullable = false)
    private String shopperId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "registrationDate")
    private Date registrationDate;

    @OneToMany(mappedBy = "shopper", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Product> products;

}
