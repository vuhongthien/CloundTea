package com.service.cloudtea.service.impl;

import com.service.cloudtea.dto.CartDto;
import com.service.cloudtea.dto.ProductDto;
import com.service.cloudtea.model.Cart;
import com.service.cloudtea.model.Product;
import com.service.cloudtea.service.CartService;
import com.service.cloudtea.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.service.cloudtea.utils.TotalPriceUtils.TotalPriceCart;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;
    @Autowired
    ModelMapper mapper;
    Map<Long, Cart> cart = new HashMap<>();
    @Override
    public Map<Long, Cart> addCart(Cart cart) {
        Cart itemCart = new Cart();
        Product product = productService.findById(cart.getProductId());
        if(product != null && this.cart.containsKey(cart.getProductId())) {
            itemCart = this.cart.get(cart.getProductId());
            itemCart.setQuanty(itemCart.getQuanty() + 1);
            itemCart.setTotalPrice(TotalPriceCart(itemCart.getQuanty(),product.getRealPrice()));
        }
        else {
            itemCart.getProduct(product);
            itemCart.setQuanty(1);
            itemCart.setProductId(cart.getProductId());
            itemCart.setTotalPrice(product.getRealPrice());
        }
        this.cart.put(cart.getProductId(), itemCart);
        return this.cart;
    }

    @Override
    public Map<Long, Cart> editCart(Long id, int quanty) {
        if(cart == null) {
            return cart;
        }
        Cart itemCart = new Cart();
        Product product = productService.findById(id);
        if(product != null && cart.containsKey(id)) {
            itemCart = cart.get(id);
            itemCart.setQuanty(quanty);
            itemCart.setTotalPrice(TotalPriceCart(itemCart.getQuanty(),product.getRealPrice()));
        }
        cart.put(id, itemCart);
        return cart;
    }

    @Override
    public Map<Long, Cart> deleteCart(Long id) {
        if(cart == null) {
            return cart;
        }
        if(cart.containsKey(id)) {
            cart.remove(id);
        }
        return cart;
    }

    @Override
    public void deleteCartAll() {
        cart.clear();
    }

    @Override
    public Collection<CartDto> showAll() {
        Collection<CartDto> cartDtos = cart.values().stream().map(t->{
            ProductDto productDto = mapper.map(t.getProduct(), ProductDto.class);
            return new CartDto(t.getProductId(),t.getQuanty(),t.getTotalPrice(),productDto, totalPrice(), (long) totalQuanty());
        }).collect(Collectors.toList());
        return cartDtos;
    }

    @Override
    public int totalQuanty() {
        int totalQuanty = 0;
        for(Map.Entry<Long, Cart> itemCart : cart.entrySet()) {
            totalQuanty += itemCart.getValue().getQuanty();
        }
        return totalQuanty;
    }
    @Override
    public double totalPrice() {
        double totalPrice = 0;
        for(Map.Entry<Long, Cart> itemCart : cart.entrySet()) {
            totalPrice += itemCart.getValue().getTotalPrice();
        }
        return totalPrice;
    }

}
