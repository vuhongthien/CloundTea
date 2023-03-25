package com.service.cloudtea.service.impl;

import com.service.cloudtea.model.ProductType;
import com.service.cloudtea.repository.ProductTypeRepository;
import com.service.cloudtea.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public List<ProductType> findAllProductType() {
        return productTypeRepository.findAll().stream().filter(i-> i.getActive() ==1).collect(Collectors.toList());
    }

    @Override
    public ProductType create(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    @Override
    public ProductType findById(Long id) {
        return productTypeRepository.findById(id).get();
    }


    @Override
    public void delete(Long id) {
        productTypeRepository.deleteById(id);
    }
}
