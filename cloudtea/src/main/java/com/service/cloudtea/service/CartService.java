package com.service.cloudtea.service;

import com.service.cloudtea.dto.CartDto;
import com.service.cloudtea.model.Cart;

import java.util.Collection;
import java.util.Map;

public interface CartService {
     Map<Long, Cart> addCart(Cart cart);

     Map<Long, Cart> editCart(Long id, int quanty);

     Map<Long, Cart> deleteCart(Long id);
     void deleteCartAll();
     Collection<CartDto> showAll();

     int totalQuanty();

     double totalPrice();
}
