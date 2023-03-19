package com.service.cloudtea.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_id")
    private Long voucherId;
    @Column(name = "voucher_code")
    private String code;
    @Column(name = "discount")
    private float discount;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "end_at")
    private LocalDateTime endAt;
    @ManyToOne(optional=false)
    @JoinColumn(name = "product_id", insertable=false, updatable=false)
    @ToString.Exclude
    private Product product;
    @Column(name = "product_id", nullable=false)
    private Long productId;
    @Column(name = "quantity")
    private Long quantity;
}
