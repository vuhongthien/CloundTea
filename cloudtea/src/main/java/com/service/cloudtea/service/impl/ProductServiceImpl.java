package com.service.cloudtea.service.impl;


import com.service.cloudtea.dto.ProductDto;
import com.service.cloudtea.model.Product;
import com.service.cloudtea.model.ProductType;
import com.service.cloudtea.repository.ProductRepository;
import com.service.cloudtea.service.ProductService;
import com.service.cloudtea.service.ProductTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.service.cloudtea.utils.ProductUtils.disCount;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductTypeService productTypeService;

    @Override
    public Page<Product> findAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public ProductDto create(Product product) {
        product.setRealPrice(disCount(product.getPrice(),product.getDiscount()));
        ProductDto productDto = mapper.map(productRepository.save(product), ProductDto.class);
        ProductType typeServiceById = productTypeService.findById(productDto.getProductTypeId());
        productDto.setTypeProductName(typeServiceById.getTypeProductName());
        return productDto;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public ProductDto findByIdDto(Long id) {
        Optional<Product> product= productRepository.findById(id);
        if(!product.isPresent()){
            return null;
        }
        ProductDto productDto = mapper.map(product.get(), ProductDto.class);
        productDto.setTypeProductName(product.get().getProductType().getTypeProductName());
        return productDto;
    }

    @Override
    public Page<ProductDto> findAllProductDto(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductDto> productDtos = products.getContent().stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toCollection(ArrayList::new));
        return new PageImpl<>(productDtos, pageable, products.getTotalElements());
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
