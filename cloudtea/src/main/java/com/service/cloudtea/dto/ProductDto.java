package com.service.cloudtea.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ProductDto {

    private Long productId;

    private Long productTypeId;

    private int star;

    private  int bought;

    private  String image;

    private  float discount;

    private String productName;

    private float price;

    private Long quantity;

    private LocalDateTime createAt;

    private LocalDateTime endAt;

    private String typeProductName;

    private double realPrice;

}
