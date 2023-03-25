package com.service.cloudtea.controller.admin;

import com.service.cloudtea.model.Voucher;
import com.service.cloudtea.service.impl.VoucherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/cloud-tea")
@RequiredArgsConstructor
public class VoucherController {
    @Autowired
    VoucherServiceImpl voucherService;
    @GetMapping("/voucher/{pageNumber}/{pageSize}")
    public Page<Voucher> listall(@PathVariable(name = "pageNumber") Integer pageNumber,
                                 @PathVariable(name = "pageSize") Integer pageSize){
        return voucherService.findAll(PageRequest.of(pageNumber,pageSize));
    }
    @PostMapping("/voucher")
    public Voucher addVoucher(@RequestParam("voucher_code") String voucherCode,
                            @RequestParam("discount") float discount,
                              @RequestParam("quantity") Long quantity,
                              @RequestParam("product_id") Long productId,
                            @RequestParam("end_at") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endAt){
        Voucher voucher = new Voucher();
        voucher.setCode(voucherCode);
        voucher.setDiscount(discount);
        voucher.setCreateAt(LocalDateTime.now());
        voucher.setEndAt(endAt);
        voucher.setProductId(productId);
        voucher.setQuantity(quantity);
        return voucherService.create(voucher);
    }
    @PutMapping("/voucher")
    public Voucher editVoucher(@RequestParam("voucher_code") String voucherCode,
                             @RequestParam("discount") float discount,
                               @RequestParam("product_id") Long productId,
                             @RequestParam("end_at") LocalDateTime endAt,
                              @RequestParam("id") Long id){
        Voucher voucher = voucherService.findById(id);
        voucher.setCode(voucherCode);
        voucher.setDiscount(discount);
        voucher.setCreateAt(LocalDateTime.now());
        voucher.setEndAt(endAt);
        voucher.setProductId(productId);
        return voucherService.create(voucher);
    }
    @DeleteMapping("/voucher")
    public ResponseEntity dropVoucher(@RequestParam("id") Long id){
        voucherService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
