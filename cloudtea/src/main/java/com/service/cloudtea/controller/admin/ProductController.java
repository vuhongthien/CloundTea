package com.service.cloudtea.controller.admin;

import com.service.cloudtea.dto.ProductDto;
import com.service.cloudtea.model.Product;
import com.service.cloudtea.service.impl.ProductServiceImpl;
import com.service.cloudtea.service.impl.ProductTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

import static com.service.cloudtea.utils.ProductUtils.calculateEndDate;

@RestController
@RequestMapping("/api/cloud-tea")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    ProductTypeServiceImpl productTypeService;

    /**
     * http://localhost:8080/api/list-product-all/1/2
     */
    @GetMapping("/product-all/{pageNumber}/{pageSize}")
    public Page<Product> listAll(@PathVariable(name = "pageNumber") Integer pageNumber,
                                 @PathVariable(name = "pageSize") Integer pageSize) {
        return productService.findAllProduct(PageRequest.of(pageNumber, pageSize));
    }

    /**
     * http://localhost:8080/api/list-product/0/3
     */
    @GetMapping("/product/{pageNumber}/{pageSize}")
    public Page<ProductDto> listAllDto(@PathVariable(name = "pageNumber") Integer pageNumber,
                                       @PathVariable(name = "pageSize") Integer pageSize) {
        return productService.findAllProductDto(PageRequest.of(pageNumber, pageSize));
    }

    /**
     * http://localhost:8080/api/find-product
     */
    @GetMapping("/product")
    public ResponseEntity<ProductDto> findProductDto(@RequestParam("product_id") Long productId) {
        ProductDto productDto = productService.findByIdDto(productId);
        if (productDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    /**
     * http://localhost:8080/api/add-product
     */
    @PostMapping("/product")
    public ProductDto addProduct(@RequestParam("type_product_id") Long typeProductId,
                                 @RequestParam("product_image") MultipartFile productImage,
                                 @RequestParam("product_discount") float productDiscount,
                                 @RequestParam("product_name") String productName,
                                 @RequestParam("product_price") float productPrice,
                                 @RequestParam("product_quantity") Long productQuantity,
                                 @RequestParam("number_end_day") Long numberEndDay

    ) {
        Product product = new Product();
        product.setImage(productImage.getOriginalFilename());
        product.setDiscount(productDiscount);
        product.setProductName(productName);
        product.setPrice(productPrice);
        product.setQuantity(productQuantity);
        product.setProductTypeId(typeProductId);
        product.setCreateAt(LocalDateTime.now());
        product.setActive(1);
        product.setEndAt(calculateEndDate(LocalDateTime.now(), numberEndDay));
        ProductDto productDto = productService.create(product);
        return productDto;
    }

    /**
     * http://localhost:8080/api/edit-product
     */
    @PatchMapping("/product")
    public ProductDto editProduct(@RequestParam("type_product_id") Long typeProductid,
                                  @RequestParam("product_id") Long productId,
                                  @RequestParam("product_image") MultipartFile productImage,
                                  @RequestParam("product_discount") float productDiscount,
                                  @RequestParam("product_name") String productName,
                                  @RequestParam("product_price") float productPrice,
                                  @RequestParam("product_quantity") Long productQuantity,
                                  @RequestParam("number_end_day") Long numberEndDay,
                                  @RequestParam("product_active") int productActive) {
        Product product = productService.findById(productId);
        product.setImage(productImage.getName());
        product.setDiscount(productDiscount);
        product.setProductName(productName);
        product.setPrice(productPrice);
        product.setQuantity(productQuantity);
        product.setActive(productActive);
        product.setProductTypeId(typeProductid);
        product.setEndAt(calculateEndDate(product.getCreateAt(), numberEndDay));
        ProductDto productDto = productService.create(product);
        return productDto;
    }

    /**
     * http://localhost:8080/api/drop-product
     */
    @DeleteMapping("/product")
    public ResponseEntity<String> dropProduct(@RequestParam("product_id") Long id) {
        productService.delete(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }
}
