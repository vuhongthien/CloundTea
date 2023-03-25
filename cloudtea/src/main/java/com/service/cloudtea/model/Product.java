package com.service.cloudtea.model;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "type_product_id", insertable = false, updatable = false)
    @ToString.Exclude
    private ProductType productType;
    @Column(name = "type_product_id", nullable = false)
    private Long productTypeId;
    @Column(name = "product_star")
    private int star;
    @Column(name = "product_bought")
    private int bought;
    @Column(name = "product_image")
    private String image;
    @Column(name = "product_discount")
    private float discount;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_price")
    private float price;
    @Column(name = "product_quantity")
    private Long quantity;
    @Column(name = "product_create_at")
    private LocalDateTime createAt;
    @Column(name = "product_end_at")
    private LocalDateTime endAt;
    @Column(name = "product_active")
    private int active;
    @Column(name = "product_real_price")
    private double realPrice;
}
