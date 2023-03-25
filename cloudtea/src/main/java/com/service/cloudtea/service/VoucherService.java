package com.service.cloudtea.service;

import com.service.cloudtea.model.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VoucherService {
    Page<Voucher> findAll(Pageable pageable);
    Voucher create(Voucher voucher);
    Voucher findById(Long id);
    Voucher findByCodeVoucher(String code);
    void delete(Long id);
}
