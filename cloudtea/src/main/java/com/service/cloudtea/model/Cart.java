package com.service.cloudtea.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {
    private Long productId;
    private int quanty;
    private double totalPrice;
    private Product product;
    public void getProduct(Product product) {
        this.product = product;
    }
}
