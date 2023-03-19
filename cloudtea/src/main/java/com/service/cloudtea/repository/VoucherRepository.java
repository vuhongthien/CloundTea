package com.service.cloudtea.repository;


import com.service.cloudtea.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Long> {
    @Query(value = "SELECT * FROM tbl_voucher WHERE voucher_code =:code", nativeQuery = true)
    Voucher findByCodeVoucher(@Param("code") String code);
}
