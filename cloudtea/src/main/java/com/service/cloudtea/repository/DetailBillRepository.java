package com.service.cloudtea.repository;

import com.service.cloudtea.model.DetailBill;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface DetailBillRepository extends JpaRepository<DetailBill, Long> {
    @Query(value = "SELECT * " +
            "FROM tbl_detail_bill a " +
            "WHERE a.bill_id =:billid", nativeQuery = true)
    Page<DetailBill> findByBillId(@Param("billid") Long billid, Pageable pageable);
}
