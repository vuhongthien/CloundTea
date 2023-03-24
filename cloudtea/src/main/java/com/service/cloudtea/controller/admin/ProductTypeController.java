package com.service.cloudtea.controller.admin;

import com.service.cloudtea.model.ProductType;
import com.service.cloudtea.service.impl.ProductTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/cloud-tea")
public class ProductTypeController {

    @Autowired
    ProductTypeServiceImpl productTypeService;

    @GetMapping("/product-type")
    public List<ProductType> listall(){
        return productTypeService.findAllProductType();
    }

    @PostMapping("/product-type")
    public ProductType addProductType(@RequestParam("TypeProductName") String TypeProductName){
        ProductType productType = new ProductType();
        productType.setTypeProductName(TypeProductName);
        productType.setActive(1);
        return productTypeService.create(productType);
    }

    @PutMapping("/product-type")
    public ProductType editProductType(@RequestParam("TypeProductName") String TypeProductName,
                                @RequestParam("Active")int Active,
                                @RequestParam("id") Long id){
        ProductType ProductType = productTypeService.findById(id);
        ProductType.setTypeProductName(TypeProductName);
        ProductType.setActive(Active);
        return productTypeService.create(ProductType);
    }

    @DeleteMapping("/product-type")
    public ResponseEntity dropProductType(@RequestParam("id") Long id){
         productTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
