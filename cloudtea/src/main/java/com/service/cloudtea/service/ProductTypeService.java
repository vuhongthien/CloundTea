package com.service.cloudtea.service;

import com.service.cloudtea.model.ProductType;
import java.util.List;

public interface ProductTypeService {
    List<ProductType> findAllProductType();
    ProductType create(ProductType productType);
    ProductType findById(Long id);
    void delete(Long id);

}
