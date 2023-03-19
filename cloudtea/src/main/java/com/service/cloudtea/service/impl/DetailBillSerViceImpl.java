package com.service.cloudtea.service.impl;

import com.service.cloudtea.model.DetailBill;
import com.service.cloudtea.repository.DetailBillRepository;
import com.service.cloudtea.service.DetailBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DetailBillSerViceImpl implements DetailBillService {
    @Autowired
    private DetailBillRepository detailBillRepository;
    @Override
    public Page<DetailBill> findAllDetailBill(Pageable pageable) {
        return detailBillRepository.findAll(pageable);
    }

    @Override
    public DetailBill findById(Long id) {
        return detailBillRepository.findById(id).get();
    }

    @Override
    public Page<DetailBill> findByBillId(Long id, Pageable pageable) {
        return detailBillRepository.findByBillId(id,pageable);
    }

    @Override
    public void delete(Long id) {
        detailBillRepository.deleteById(id);
    }
}
