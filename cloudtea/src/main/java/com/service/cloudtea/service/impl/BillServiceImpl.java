package com.service.cloudtea.service.impl;

import com.service.cloudtea.dto.CartDto;
import com.service.cloudtea.exception.DonFoundItemCart;
import com.service.cloudtea.exception.NotEnoughQuantity;
import com.service.cloudtea.model.*;
import com.service.cloudtea.repository.BillRepository;
import com.service.cloudtea.repository.DetailBillRepository;
import com.service.cloudtea.repository.VoucherRepository;
import com.service.cloudtea.service.BillService;
import com.service.cloudtea.service.CartService;
import com.service.cloudtea.service.ProductService;
import com.service.cloudtea.service.UserService;
import com.service.cloudtea.status.StatusCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static com.service.cloudtea.utils.TotalPriceUtils.TotalPrice;
import static com.service.cloudtea.utils.TotalPriceUtils.VoucherDiscount;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billRepository;
    @Autowired
    VoucherRepository voucherRepository;
    @Autowired
    DetailBillRepository detailBillRepository;
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;


    @Override
    public Page<Bill> findAllBill(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    @Override
    public List<Bill> listAll() {
        return billRepository.findAll();

    }

    @Override
    public Bill create(Long idUser, String code) {
        Bill bill = new Bill();
        bill.setDaySet(LocalDateTime.now());
        bill.setUserId(idUser);
        User user = userService.findById(idUser);
        bill.setUser(user);
        Bill b2 = billRepository.save(bill);
        Collection<CartDto> c = cartService.showAll();
        if (c == null) {
            try {
                throw new DonFoundItemCart(String.format(
                        StatusCart.ITEM_CART_NOT_FOUND + c));
            } catch (DonFoundItemCart e) {
                throw new RuntimeException(e);
            }
        }
        Double acc = 0.0;
        Voucher voucher = voucherRepository.findByCodeVoucher(code);
        if (voucher != null && voucher.getCode().isEmpty() == false && LocalDateTime.now().isBefore(voucher.getEndAt())) {

            for (CartDto x : c) {
                DetailBill newDetailBill = new DetailBill();
                newDetailBill.setBillId(b2.getBillId());
                newDetailBill.setPrice(x.getProductDto().getPrice());
                newDetailBill.setQuantity(x.getQuanty());
                newDetailBill.setImage(x.getProductDto().getImage());
                if (voucher.getProductId() == x.getProductId() && voucher.getQuantity() >= 1) {
                    newDetailBill.setTotalPrice(TotalPrice(x.getProductDto().getRealPrice() - VoucherDiscount(x.getProductDto().getRealPrice(), voucher.getDiscount()), x.getQuanty()));
                    voucher.setQuantity(voucher.getQuantity() - 1);
                    voucherRepository.save(voucher);
                } else {
                    newDetailBill.setTotalPrice(TotalPrice(x.getProductDto().getRealPrice(), x.getQuanty()));
                }
                newDetailBill.setProductId(x.getProductId());
                Product product = productService.findById(x.getProductId());
                if (x.getQuanty() < product.getQuantity()) {
                    product.setBought(product.getBought() + x.getQuanty());
                    product.setQuantity(product.getQuantity() - x.getQuanty());
                    productService.create(product);
                    Double totalPrice = newDetailBill.getTotalPrice();
                    acc = acc + totalPrice;
                    detailBillRepository.save(newDetailBill);
                } else {
                    try {
                        billRepository.deleteById(b2.getBillId());
                        throw new NotEnoughQuantity(String.format(StatusCart.NOT_ENOUGH_QUATITY_IN_CART));
                    } catch (NotEnoughQuantity e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        if (voucher == null || voucher.getCode().isEmpty() == true || LocalDateTime.now().isAfter(voucher.getEndAt())) {
            for (CartDto x : c) {
                DetailBill newDetailBill = new DetailBill();
                newDetailBill.setBillId(b2.getBillId());
                newDetailBill.setPrice(x.getProductDto().getPrice());
                newDetailBill.setQuantity(x.getQuanty());
                newDetailBill.setImage(x.getProductDto().getImage());
                newDetailBill.setTotalPrice(TotalPrice(x.getProductDto().getRealPrice(), x.getQuanty()));
                newDetailBill.setProductId(x.getProductId());
                Product product = productService.findById(x.getProductId());
                if (x.getQuanty() < product.getQuantity()) {
                    product.setBought(product.getBought() + x.getQuanty());
                    product.setQuantity(product.getQuantity() - x.getQuanty());
                    productService.create(product);
                    detailBillRepository.save(newDetailBill);
                    Double totalPrice = x.getTotalPrice();
                    acc = acc + totalPrice;
                } else {
                    try {
                        billRepository.deleteById(b2.getBillId());
                        throw new NotEnoughQuantity(String.format(StatusCart.NOT_ENOUGH_QUATITY_IN_CART));
                    } catch (NotEnoughQuantity e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        b2.setTotalPrice(acc);
        cartService.deleteCartAll();
        return billRepository.save(b2);
    }

    @Override
    public Bill findById(Long id) {
        return billRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        billRepository.deleteById(id);
    }
}
