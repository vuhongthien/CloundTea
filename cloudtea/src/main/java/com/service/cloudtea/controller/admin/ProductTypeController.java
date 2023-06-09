package com.service.cloudtea.controller.admin;

import com.service.cloudtea.model.ProductType;
import com.service.cloudtea.service.impl.ProductTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cloud-tea")
@RequiredArgsConstructor
public class ProductTypeController {

    @Autowired
    ProductTypeServiceImpl productTypeService;

    @GetMapping("/get-all-product-type")
    public List<ProductType> listall(){
        return productTypeService.findAllProductType();
    }

    @PostMapping("/add-product-type")
    public ProductType addProductType(@RequestParam("TypeProductName") String TypeProductName){
        ProductType productType = new ProductType();
        productType.setTypeProductName(TypeProductName);
        productType.setActive(1);
        return productTypeService.create(productType);
    }

    @PutMapping("/edit-product-type")
    public ProductType editProductType(@RequestParam("TypeProductName") String TypeProductName,
                                @RequestParam("Active")int Active,
                                @RequestParam("id") Long id){
        ProductType ProductType = productTypeService.findById(id);
        ProductType.setTypeProductName(TypeProductName);
        ProductType.setActive(Active);
        return productTypeService.create(ProductType);
    }

    @DeleteMapping("/drop-product-type")
    public ResponseEntity dropProductType(@RequestParam("id") Long id){
         productTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
