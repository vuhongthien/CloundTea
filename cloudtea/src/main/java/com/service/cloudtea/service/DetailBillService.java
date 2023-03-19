package com.service.cloudtea.service;

import com.service.cloudtea.model.DetailBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface DetailBillService {
    Page<DetailBill> findAllDetailBill(Pageable pageable);
    DetailBill findById(Long id);
    Page<DetailBill> findByBillId(Long id, Pageable pageable);
    void delete(Long id);
}
