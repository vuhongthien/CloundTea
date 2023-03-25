package com.service.cloudtea.controller.user;

import com.service.cloudtea.dto.CartDto;
import com.service.cloudtea.dto.ProductDto;
import com.service.cloudtea.model.Cart;
import com.service.cloudtea.service.impl.CartServiceImpl;
import com.service.cloudtea.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/api/cloud-tea")
public class CartController {
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    ProductServiceImpl productService;
    @PostMapping(value = "/add-cart")
    public Collection<CartDto> addcart(@RequestParam("productid") Long productId) {
        ProductDto productDto = productService.findByIdDto(productId);
           Cart cart = new Cart();
           cart.setProductId(productId);
           cart.setQuanty(1);
           cart.setTotalPrice(productDto.getPrice());
        cartService.addCart(cart);
        cartService.totalQuanty();
        cartService.totalPrice();
        return cartService.showAll();
    }
    @PutMapping(value = "/edit-cart")
    public Collection<CartDto> editcart(@RequestParam("productid") Long productId,
                                     @RequestParam("quanty") int quanty) {
        cartService.editCart(productId, quanty);
        cartService.totalQuanty();
        cartService.totalPrice();
        return cartService.showAll();
    }
    @DeleteMapping(value = "/delete-cart")
    public Collection<CartDto> deletecart(@RequestParam("productid") Long productId) {
        cartService.deleteCart(productId);
        cartService.totalQuanty();
        cartService.totalPrice();
        return cartService.showAll();
    }
    @GetMapping(value = "/get-cart")
    public Collection<CartDto> getallcart() {
        return cartService.showAll();
    }
}
