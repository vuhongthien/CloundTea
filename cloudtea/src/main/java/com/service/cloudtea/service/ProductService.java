package com.service.cloudtea.service;

import com.service.cloudtea.dto.ProductDto;
import com.service.cloudtea.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProductService {
    Page<Product> findAllProduct(Pageable pageable);
    ProductDto create(Product product);
    Product findById(Long id);
    ProductDto findByIdDto(Long id);
    Page<ProductDto> findAllProductDto(Pageable pageable);
    void delete(Long id);

}
