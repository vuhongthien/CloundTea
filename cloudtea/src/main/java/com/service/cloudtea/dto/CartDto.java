package com.service.cloudtea.dto;

import com.service.cloudtea.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CartDto {
    private Long productId;
    private int quanty;
    private double totalPrice;
    private ProductDto productDto;
    private double AllPrice;
    private Long AllQuanty;


}
